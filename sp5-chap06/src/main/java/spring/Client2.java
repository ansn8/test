package spring;

import org.springframework.beans.factory.annotation.Autowired;


public class Client2{
	private String host2;
	public void setHost(String host2) {
		this.host2 = host2;
	}
	
	public void connect() {
		System.out.println("Client2.connect() 실행");
	}
	public void send() {
		System.out.println("Client2.send() 실행");
	}
	public void close() {
		System.out.println("Client2.close() 실행");
	}
	
}
