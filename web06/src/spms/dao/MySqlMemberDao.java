package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import spms.vo.Member;
//DAO란 데이터처리를 전문으로 하는 객체를 의미함  
//기존의 서블릿에서 하던 db연동을 통한 데이터처리를 하나의 객체를 만들어 거기서 처리함
public class MySqlMemberDao implements MemberDao {
	//ConnectionPool사용으로 이제 더 이상  MemberDao에 Connection을 직접적으로 보관할 필요가 없음
	//DBConnectionPool connPool;
	
	//
	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
		
	}
	
	//public void setDbConnectionPool(DBConnectionPool connPool) {
		//Connection을 직접적으로 보관하는 메서드 대신 DBConnectionPool객체를 저장하는 메서드를 사용
		//this.connPool = connPool;
	//}
	
	public List<Member> selectList() throws Exception{ //회원리스트
		//프로그램의 유연성을 위해 selectList()의 타입을 List로 지정
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//connection = connPool.getConnection(); //커넥션풀에서 커넥션을 가져옴
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT MNO,MNAME,EMAIL,CRE_DATE" + 
					" FROM MEMBERS" +
					" ORDER BY MNO ASC");
			ArrayList<Member> members = new ArrayList<Member>();
			while(rs.next()) {
				members.add(new Member().setNo(rs.getInt("MNO"))
									   .setName(rs.getString("MNAME"))
									   .setEmail(rs.getString("EMAIL"))
									   .setCreatedDate(rs.getDate("CRE_DATE"))
				);
			}
			return members;	
		}
		catch (Exception e) {
			throw e;
		} finally {
			try { if(rs!=null) rs.close();} catch (Exception e) {}
			try { if(stmt!=null) stmt.close();} catch (Exception e) {}
			try { //if(connection!=null) connPool.returnConnection(connection);
				//작업이 끝나면 사용한 커넥션을 다시 커넥션풀에 되돌려줌
					if(connection!=null) connection.close();
			} catch (Exception e) {}
		}
	}
	
	public int insert(Member member) throws Exception{ //회원추가
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)"
												+ "VALUES (?,?,?,NOW(),NOW())");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(stmt!=null) stmt.close();} catch (Exception e) {}
			try { if(connection!=null) connection.close();} catch (Exception e) {}
		}
	}
	
	public int delete(int no) throws Exception{ // 회원삭제
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			return stmt.executeUpdate("DELETE FROM MEMBERS WHERE MNO="+no);
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(stmt!=null) stmt.close();} catch (Exception e) {}
			try { if(connection!=null) connection.close();} catch (Exception e) {}
		}
	}
	
	public Member selectOne(int no) throws Exception{ // 회원상세정보조회
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT MNO,EMAIL,MNAME,CRE_DATE FROM MEMBERS WHERE MNO ="+no);
			 if(rs.next()){
				 return	new Member().setNo(rs.getInt("MNO"))
									.setEmail(rs.getString("EMAIL"))
									.setName(rs.getString("MNAME"))
									.setCreatedDate(rs.getDate("CRE_DATE"));
			}else {
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(rs!=null) rs.close();} catch (Exception e) {}
			try { if(stmt!=null) stmt.close();} catch (Exception e) {}
			try { if(connection!=null) connection.close();} catch (Exception e) {}
		}
	}
	
	public int update(Member member) throws Exception{ //회원정보 변경
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("UPDATE MEMBERS SET EMAIL=?,MNAME=?,MOD_DATE=now() WHERE MNO=?");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getNo());
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(stmt!=null) stmt.close();} catch (Exception e) {}
			try { if(connection!=null) connection.close();} catch (Exception e) {}
		}
	}
	
	public Member exist(String email, String password) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
			try {
				connection = ds.getConnection();
				stmt = connection.prepareStatement("SELECT MNAME,EMAIL FROM MEMBERS WHERE EMAIL=? AND PWD =?");
				stmt.setString(1, email);
				stmt.setString(2, password);
				rs = stmt.executeQuery();
				if(rs.next()) { //쿼리가 성공했다면 email,password가 맞다는 의미
					return new Member().setEmail(rs.getString("EMAIL"))
							.setName(rs.getString("MNAME"));
				}
			} catch (Exception e) {
				throw e;
			} finally {
				try { if(rs!=null) rs.close();} catch (Exception e) {}
				try { if(stmt!=null) stmt.close();} catch (Exception e) {}
				try { if(connection!=null) connection.close();} catch (Exception e) {}
			}
			return null;
	}
	
	
}
