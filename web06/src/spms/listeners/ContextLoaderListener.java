package spms.listeners;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import spms.controls.LogInController;
import spms.controls.LogOutController;
import spms.controls.MemberAddController;
import spms.controls.MemberDeleteController;
import spms.controls.MemberListController;
import spms.controls.MemberUpdateController;
import spms.dao.MySqlMemberDao;
//리스너를 배치하기위해 어노테이션으로 정의
@WebListener
public class ContextLoaderListener implements ServletContextListener {
//	Connection conn;
//	DBConnectionPool connPool;
	//지금까지 서블릿요청을 위해서 항상 DAO인스턴스를 계속 생성했음(객체생성) 비효율적
	//그래서 DAO처럼 여러서블릿이 사용하는 객체는 서로 공유하는 편이 메모리관리, 속도측면에서 효율적임
	
	//DriverManager를 대체하는 DateSource
	//DateSource는 서버에서 관리하기 떄문에 DB나 JDBC드라이버가 변경되어도 어플리케이션 수정안함
	//Connection, Statement 객체를 풀링할 수 있고, 분산 트랜잭션이 가능해짐
	//자체적으로 커넥션풀 기능을 구현함 개발자가 별도로 커넥션풀을 준비할 필요가 없음 
	//BasicDataSource ds;
	
	//DataSource를 직접쓰지않고 서버에 보관하여 사용하므로 BasicDataSource가 필요없음
	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 이메서드는 웹어플리케이션이 시작될 때 호출됨 공용객체를 사용할때 여기에 작성하면됨
		try {
			ServletContext sc = event.getServletContext();
			
			//BasicDataSource를 직접사용하지 않고 톰캣서버에서 자원을 찾기위해 InitialContext를 사용
			//lookup메서드를 사용하면 서버에 등록해놓은 DataSource자원을 가져올 수 있음
			InitialContext initialContext = new InitialContext();
			DataSource ds =  (DataSource) initialContext.lookup("java:comp/env/jdbc/studydb");
			
//			ds = new BasicDataSource();
//			ds.setDriverClassName(sc.getInitParameter("driver"));
//			ds.setUrl(sc.getInitParameter("url"));
//			ds.setUsername(sc.getInitParameter("username"));
//			ds.setPassword(sc.getInitParameter("password"));
			
//			MemberDao memberDao = new MemberDao();
			MySqlMemberDao memberDao = new MySqlMemberDao();
			memberDao.setDataSource(ds);
			
			//이제 DB를 연결을 각 컨트롤러에 직접적으로 주입함 별도로 꺼내서 사용할 일이 없음.
//			sc.setAttribute("memberDao", memberDao);
			sc.setAttribute("/auth/logIn.do", new LogInController().setMemberDao(memberDao));
			sc.setAttribute("/auth/logout.do", new LogOutController());
			sc.setAttribute("/member/list.do", new MemberListController().setMemberDao(memberDao));
			sc.setAttribute("/member/add.do", new MemberAddController().setMemberDao(memberDao));
			sc.setAttribute("/member/update.do", new MemberUpdateController().setMemberDao(memberDao));
			sc.setAttribute("/member/delete.do", new MemberDeleteController().setMemberDao(memberDao));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		try {
//			ServletContext sc = event.getServletContext();
////			Class.forName(sc.getInitParameter("driver"));
////			conn = DriverManager.getConnection(
////					sc.getInitParameter("url"),
////					sc.getInitParameter("username"),
////					sc.getInitParameter("password"));
//			//dao는 ServletContext에 저장되어 각 서블릿에서 공유될 것
//			
//			//커넥션풀 객체를 위한 코드 추가 
//			connPool = new DBConnectionPool(
//					sc.getInitParameter("driver"),
//					sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password"));
//			
//			//MemberDao객체를 생성하고 커넥션풀객체를 setDbConnectionPool메서드에 넣음
//			MemberDao memberdao = new MemberDao();
//			memberdao.setDbConnectionPool(connPool);
//			sc.setAttribute("memberDao", memberdao);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// 이메서드는 웹어플리케이션이 종료되기전에 호출됨 자원을 해제할때는 여기에
//		try {
//			conn.close();
//		} catch (Exception e) {	}
		//웹이 종료될때 모든 커넥션객체를 닫음
//		connPool.closeAll();
		
		//웹종료시 서버에 이미 설정해두었기 때문에 close메서드가 필요없음
//		try {
//			if(ds!=null) {
//				ds.close();
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
	}


}
