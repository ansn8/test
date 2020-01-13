package spms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

//DB커넥션풀이란 여러개의 DB커넥션을 관리하는 객체를 말함
//하나의 커넥션을 여러DAO에서 사용할 경우, 하나의 DAO에서 예외가 발생했을 때 롤백을 해야하는데
//문제는 그 커넥션객체의 롤백을 호출할시, 모든 DAO에서의 커넥션작업이 롤백이 되어버림
//그래서 웹브라우저의 각요청에 대해 개별DB커넥션을 사용해야함
//**DB커넥션풀을 사용하면 사용된 객체를 버리지않고 다시 재사용이 가능해서 매우 효율적임**

public class DBConnectionPool {
	String url;
	String username;
	String password;
	
	//Connection객체를 보관할 ArrayList
	ArrayList<Connection> connList = new ArrayList<Connection>();
	
	public DBConnectionPool(String driver, String url, String username, String password) throws Exception {
		//생성자에서는 DB커넥션 생성에 필요한 값을 매개변수로 받음
		this.url = url;
		this.username = username;
		this.password = password;
	
		Class.forName(driver);
	}
	
	//커넥션을 가져오는 메서드
	public Connection getConnection() throws Exception {
		if(connList.size() > 0) { // connList에 이미 생성된 DB커넥션이 존재할 경우 그 값을 꺼내서 리턴함
			Connection conn = connList.get(0);
			if(conn.isValid(10)) { // .isValid(대기시간)메서드는 Connection이 아직 유효한지 검사하는 유효성검사 메서드
				return conn;
			}
		}
		return DriverManager.getConnection(url,username,password); // 생성된 DB커넥션이 없을경우 새로 생성함
	}
	
	//커넥션객체를 쓰고난 다음, 다시 커넥션풀에 커넥션객체를 반환하는 메서드
	public void returnConnection(Connection conn) throws Exception{
		connList.add(conn);
	}
	
	//웹과 종료되기전, 이메서드를 호출하여 DB와 연결된 모든 것을 끊는 메서드
	public void closeAll() {
		for(Connection conn : connList) {
			try {conn.close(); } catch (Exception e) {	}
		}
	}
	
}
