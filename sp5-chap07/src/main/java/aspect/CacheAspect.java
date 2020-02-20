package aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1) //@Order(숫자)는 Aspect의 적용순서를 지정한다 1이 제일먼저 시작함
public class CacheAspect {
	private Map<Long,Object> cache = new HashMap<Long,Object>();
	
//	@Pointcut("execution(public * chap7..*(long))")
//	public void cacheTarget() {
//		
//	}
	
	//@Pointcut을 따로 지정하지않고 @Around에 직접적으로 execution을 직접지정도 가능 
	//@Around("execution(public * chap7..*(long))") 이런식으로
	//@Pointcut을 별도의 클래스로 구성하여 사용도 가능함
	@Around("pointcut.CommonPointcut.commonTarget()")
	public Object execute(ProceedingJoinPoint joinpoint) throws Throwable {
		Long num = (Long) joinpoint.getArgs()[0];
		if(cache.containsKey(num)) { // 키에 맞는 값이 캐시에 존재하면 캐시에서 값을 꺼내 리턴
			System.out.printf("CacheAspect : Cache에서 구함 [%d] \n",num);
			return cache.get(num);
		}
		
		Object result = joinpoint.proceed(); // 키에 맞는 값이 캐시에 존재 하지않을시 프록시대상객체를 실행하여 결과를 캐시에 넣음
		cache.put(num, result);
		System.out.printf("CacheAspect : Cache에 추가 %d \n",num);
		return result;
	}
}
