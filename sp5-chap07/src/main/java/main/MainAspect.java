package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import chap7.Calculator;
import chap7.RecCal;
import config.AppCtx;

public class MainAspect {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		Calculator cal = ctx.getBean("calculator",Calculator.class);

//		RecCal cal = ctx.getBean("calculator",RecCal.class);
		//이렇게 RecCal에 빈객체를 넣으면 calculator빈객체는 RecCal타입으로 만들었지만, 만들어진 프록시는 RecCal이 Calculator인터페이스를 상속받은 타입이므로
		//익셉션을 발생시킴
		//만약, 빈객체가 인터페이스를 상속할 때 인터페이스가 아닌 클래스를 이용해서 프록시를 생성하고 싶은 경우
		//빈객체를 만드는 클래스에 @EnableAspectJAutoProxy(proxyTargetClass = true)를 추가해야함
		long fiveFact = cal.factorial(5);
		System.out.println("cal.factorial(5) : "+fiveFact);
		System.out.println(cal.getClass().getName());
		ctx.close();
	}

}
