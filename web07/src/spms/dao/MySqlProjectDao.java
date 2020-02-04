package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import spms.annotation.Component;
import spms.vo.Project;

@Component("projectDao")
public class MySqlProjectDao implements ProjectDao {
	SqlSessionFactory sqlSessionFactory;
	
//지금까지 데이터베이스커넥션을 얻기위해 DataSource 객체가 필요했지만 
//mybatis사용으로 이제 더이상 DataSource가 필요없음 그대신 SqlSessionFactory객체가 필요함 
//	DataSource ds;
//	public void setDataSource(DataSource ds) {
//		this.ds = ds;
//	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// sqlSessionFactory를 저장할 인스턴스 변수와 셋터 메서드 선언
		//sqlSessionFactory는 SQL을 실행할 떄 사용할 도구를 만듦
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public List<Project> selectList() throws Exception {
		//SqlSession은 SQL을 실행시킴 이 객체를 통해 SQL문 실행 가능해짐
		//그러나 직접 생성은 못하고 SqlSessionFactory를 통해 얻을 수 있음
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
 			return sqlSession.selectList("spms.dao.ProjectDao.selectList");
		} finally {
			sqlSession.close();
		}
//					"select PNO,PNAME,STA_DATE,END_DATE,STATE " +
//							"from projects " +
//					"order by CRE_DATE asc");
	}

	@Override
	public int insert(Project project) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		//sqlSession 객체를 생성할 떄, 매개변수에 true를 넣어주면 자동으로 commit을 실행함
		//하지만 이럴 경우, 트랜잭션을 다룰 수 없음
		try {
			int count = sqlSession.insert("spms.dao.ProjectDao.insert",project);
			sqlSession.commit();// commit메서드는 SQL의 commit 기능을 사용하는걸 의미함 결과를 실제DB에 반영하는 역할
			return count;
		} finally {
			sqlSession.close();
		}
//			stmt = connection.prepareStatement("insert into "
//					+ "projects(PNAME,CONTENT,STA_DATE,END_DATE,STATE,CRE_DATE,TAGS)"
//					+ "values (?,?,?,?,?,now(),?);");
	}

	@Override
	public Project selectOne(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.ProjectDao.selectOne", no);
		} finally {
			sqlSession.close();
		}
//			rs = stmt.executeQuery(
//					"select PNO,PNAME,CONTENT,STA_DATE,END_DATE,STATE,TAGS from projects where PNO = "+no+";");
	}

	@Override
	public int update(Project project) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.update("spms.dao.ProjectDao.update", project);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
//			stmt = connection.prepareStatement(
//					"update projects set PNO=?, PNAME=?, CONTENT=?, STA_DATE=?, END_DATE=?, STATE=?, TAGS=? where PNO=?");
	}

	@Override
	public int delete(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.delete("spms.dao.ProjectDao.delete",no);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
//			return stmt.executeUpdate("DELETE FROM projects WHERE PNO="+no);
	}
}
