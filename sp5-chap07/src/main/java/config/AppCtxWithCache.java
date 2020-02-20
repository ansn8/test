package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aspect.CacheAspect;
import aspect.ExeTimeAspect;
import chap7.Calculator;
import chap7.RecCal;

@Configuration
@EnableAspectJAutoProxy
public class AppCtxWithCache {

	@Bean
	public CacheAspect cacheAspect() {
		return new CacheAspect();
	}
	
	@Bean
	public ExeTimeAspect exeTimeAspect() {
		return new ExeTimeAspect();
	}
	
	@Bean
	public Calculator calculator() {
		return new RecCal();
	}
}
