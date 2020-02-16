package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;

/** configuration애노테이션으로 스프링설정클래스를 정의 **/
@Configuration
public class AppCtx {
	
	/** Bean애노테이션을 통해 해당 메서드를 Bean객체로 등록 **/
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService();
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
//		pwdSvc.setMemberDao(memberDao()); Autowired사용으로 인해 setter메서드를 통해 의존주입하지않음 
		return pwdSvc;
	}
	
	// Bean객체는 객체타입이 겹칠경우 중복되어 익셉션발생, 그래서 Qulifier에노테이션을 사용하여 의존주입대상을 한정해야 함.
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
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter();
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
//		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
//		infoPrinter.setMemberDao(memberDao());
//		infoPrinter.setPrinter(memberPrinter());
		return new MemberInfoPrinter();
	}
	
	
	
	
	
}
