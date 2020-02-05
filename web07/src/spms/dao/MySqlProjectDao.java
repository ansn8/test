package spms.dao;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

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
	public List<Project> selectList(HashMap<String,Object> paramMap) throws Exception {
		//SqlSession은 SQL을 실행시킴 이 객체를 통해 SQL문 실행 가능해짐
		//그러나 직접 생성은 못하고 SqlSessionFactory를 통해 얻을 수 있음
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
 			return sqlSession.selectList("spms.dao.ProjectDao.selectList",paramMap);
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
			Project original = sqlSession.selectOne("spms.dao.ProjectDao.selectOne", project.getNo());
			Hashtable<String,Object> paramMap = new Hashtable<String,Object>();
			
			//기존의 값과 새로들어온 값이 같다면 update돌릴필요가 없음 다르다면 새로운 내용이니까 update돌려야함
			if(!project.getTitle().equals(original.getTitle())){
				paramMap.put("title", project.getTitle());
			}
			if(!project.getContent().equals(original.getContent())){
				paramMap.put("content", project.getContent());
			}
			if(project.getStartDate().compareTo(original.getStartDate()) != 0){
				paramMap.put("startDate",project.getStartDate());
			}
			if(project.getEndDate().compareTo(original.getEndDate()) != 0) {
				paramMap.put("endDate", project.getEndDate());
			}
			if(project.getState() != original.getState()) {
				paramMap.put("state", project.getState());
			}
			if(!project.getTags().equals(original.getTags())) {
				paramMap.put("tags", project.getTags());
			}
			if(paramMap.size() > 0) {
				paramMap.put("no", project.getNo());
				int count = sqlSession.update("spms.dao.ProjectDao.update", paramMap);
				sqlSession.commit();
				return count;
			} else {
				return 0;
			}
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
