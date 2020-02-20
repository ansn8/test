package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import aspect.CacheAspect;
import chap7.Calculator;
import config.AppCtxWithCache;

public class MainAspectWithCache {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtxWithCache.class);
		
		Calculator cal = ctx.getBean("calculator",Calculator.class);
		cal.factorial(7);
		System.out.println("----");
		cal.factorial(7);
		System.out.println("----");
		cal.factorial(5);
		System.out.println("----");
		cal.factorial(5);
		System.out.println("----");
		ctx.close();
		
	}

}
