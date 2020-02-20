package pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcut {
	//공통으로 사용하는 @Pointcut을 별도의 클래스로 정의하여 구성이 가능함
	//이렇게하면 @Pointcut의 관리가 수월함
	@Pointcut("execution(public * chap7..*(..))")
	public void commonTarget() {
		
	}
	
}
