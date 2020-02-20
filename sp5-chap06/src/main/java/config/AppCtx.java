package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import spring.Client;
import spring.Client2;
import spring.MemberPrinter;

/** configuration애노테이션으로 스프링설정클래스를 정의 **/
@Configuration
@ComponentScan(basePackages = {"spring"})
//@Component를 사용한 클래스를 빈에 등록하기 위해서 @ComponentScan을 적용해야함 
//basePackages 속성은 스캔할 패키지를 지정함 지정된 패키지와 그 하위에 속한 클래스 모두를 스캔함
public class AppCtx {
	/** Bean애노테이션을 통해 해당 메서드를 Bean객체로 등록 **/
	
	//라이프사이클 관련 코드
	@Bean(initMethod="connect", destroyMethod="close")
	public Client2 client2() {
		Client2 client2 = new Client2();
		client2.setHost("host2");
		return client2;
	}
	
	@Bean
	@Scope("prototype")
	public Client client() {
		//프로토타입으로 생성했으므로 아래의 Client는 자기자기만 사용하는 독립적인 객체임
		Client client = new Client();
		client.setHost("prototype-host");
		return client;
	}
	
	@Bean
	@Qualifier("listPrinter")
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("infoPrinter")
	public MemberPrinter memberPrinter2() {
		return new MemberPrinter();
	}
	
}
