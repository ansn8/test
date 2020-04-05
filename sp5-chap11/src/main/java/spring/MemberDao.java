package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import vo.Member;

// Component 에노테이션에 값을 주지 않을경우 클래스이름의 첫글자를 소문자로 바꾼 값을 빈이름으로 사용한다
// 그러므로 이 클래스의 빈이름은 memberDao가 된다 
// @Component("이름") 이런식으로 에노테이션에 값을 줄경우  "이름"을 빈이름으로 사용한다
@Component
public class MemberDao {
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	private RowMapper<Member> memberRowMapper = new RowMapper<Member>() {
		@Override
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			Member member = new Member(
					rs.getString("EMAIL"),
					rs.getString("PASSWORD"),
					rs.getString("NAME"),
					rs.getTimestamp("REGDATE").toLocalDateTime()
					);
					member.setId(rs.getLong("ID"));
					return member;
			}
		};
	
	public Member selectByEmail(String email) { //기존의 conn방식이 아닌 JdbcTemplate와 rowMapper를 이용한 방식 
		List<Member> results = jdbcTemplate.query("select * from spring5fs.member where EMAIL=?",
				memberRowMapper,email);
			return results.isEmpty() ? null : results.get(0);
	}
	
	public List<Member> selectAll(){
		List<Member> results = jdbcTemplate.query("select * from spring5fs.member",
				memberRowMapper);
			return results;
	}
	
	public int count() { // 쿼리의 결과가 1행일때 사용할 수 있는 queryForObject 메서드
		Integer count = jdbcTemplate.queryForObject("select count(*) from spring5fs.member", Integer.class);
		return count;
	
	}
	public void insert(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(
					"insert into spring5fs.member(EMAIL,PASSWORD,NAME,REGDATE) values(?,?,?,?)", new String[] {"ID"});
					pstmt.setString(1, member.getEmail());
					pstmt.setString(2, member.getPassword());
					pstmt.setString(3, member.getName());
					pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
					return pstmt;
			}
		},keyHolder);
//		람다식을 쓰면 코드가 간결해짐 근데 이해하기 어려움
//		jdbcTemplate.update((Connection conn) -> {
//			PreparedStatement pstmt = conn.prepareStatement(
//				"insert into spring5fs.member(EMAIL,PASSWORD,NAME,REGDATE) values(?,?,?,?)", new String[] {"ID"} );
//			pstmt.setString(1, member.getEmail());
//			pstmt.setString(2, member.getPassword());
//			pstmt.setString(3, member.getName());
//			pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
//			return pstmt;
//		},keyHolder);
		
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}
	
	public void update(Member member) {
		jdbcTemplate.update(
			"update spring5fs.member set NAME = ?, PASSWORD = ? where EMAIL = ?",
			member.getName(),
			member.getPassword(),
			member.getEmail()
			);
	}
	
	public List<Member> selectByRegdate(LocalDateTime from, LocalDateTime to){
		//날짜를 이용한 회원검색 기능구현 from과 to 시간사이에 있는 Member목록을 구함
		List<Member> results = jdbcTemplate.query(
				"select * from spring5fs.member where REGDATE between ? and ? order by REGDATE desc",
				memberRowMapper,from, to);
		return results;
	}

	public Member selectById(Long memId) {
		List<Member> results = jdbcTemplate.query(
				"select * from spring5fs.member where ID = ?", memberRowMapper, memId);
		return results.isEmpty() ? null : results.get(0);
	}
}
