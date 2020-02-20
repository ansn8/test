package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aspect.ExeTimeAspect;
import chap7.Calculator;
import chap7.RecCal;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true) //@Aspect를 붙인 클래스를 공통기능으로 사용하기 위해 이 에노테이션을 설정클래스에 사용해야함
//이걸 추가하면 스프링이 @Aspect가 붙은 빈 객체를 찾아서 빈 객체의 @Pointcut 설정과 @Around설정을 사용한다.
public class AppCtx {
	
	@Bean //@Aspect가 붙은 빈 객체 - 이 객체의 @Pointcut과 @Around를 사용함
	public ExeTimeAspect exeTimeAspect() {
		return new ExeTimeAspect();
	}
	
	@Bean
	public Calculator calculator() {
		return new RecCal();
	}
}
