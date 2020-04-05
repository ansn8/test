package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import spring.AuthService;
import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

@Configuration
//@ComponentScan(basePackages = {"spring"})
@EnableTransactionManagement
public class MemberConfig {
	@Bean(destroyMethod ="close") //DB연동을 위해 DataSource를 빈으로 등록하고 사용함 
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/spring5fs?serverTimezone=Asia/Seoul");
		ds.setUsername("spring5");
		ds.setPassword("spring5");
		ds.setInitialSize(2); //초기 커넥션 갯수 지정
		ds.setMaxActive(10); // 커넥션풀에서 가져올수 있는 최대 커넥션 갯수를 지정
		ds.setTestWhileIdle(true); // 유휴 커넥션 검사on
		ds.setMinEvictableIdleTimeMillis(1000 * 60 * 3); // 최소유휴시간 3분
		ds.setTimeBetweenEvictionRunsMillis(1000 * 10); //10초 주기로 유휴커넥션 검사실시
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
	
	@Bean
	public MemberRegisterService memberRegisterService() {
		return new MemberRegisterService();
	}
	@Bean
	public ChangePasswordService changePasswordService() {
		return new ChangePasswordService();
	}
	@Bean
	public AuthService authService() {
		return new AuthService();
	}
}
