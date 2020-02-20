package aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

// 간단하게 이 클래스는 메서드실행전/후(Around Advice)에서 사용할 공통기능클래스(Aspect)이다.
@Aspect //@Aspect를 붙이면 해당 클래스는 공통기능을 수행한다.
@Order(2) //@Order(숫자)는 Aspect의 적용순서를 지정한다 1이 제일먼저 시작함
public class ExeTimeAspect {

	//@Pointcut은 공통 기능을 적용할 대상을 적용한다 
	//chap07패키지와 그 하위 패키지에 위치한 public 메서드를 Pointcut으로 설정한다는 의미.
	//execution은 명시자를 의미한다 공통기능에 적용될 범위를 지정하는 역할
	// 수식어패턴(생략가능) 리턴타입패턴 클래스이름패턴 메서드이름패턴(파라미터패턴)의 형식으로 사용
	// *은 모든것 ..은 0개이상을 의미 
	@Pointcut("execution(public * chap7..*(..))") 
	private void publicTarget() {
		
	}
	//@Around는 Around Advice를 설정한다. 
	//@Around에 설정한 값이 publicTarget()인데 이것은 publicTarget()메서드에 정의한 Pointcut에 공통기능을 적용한다
	@Around("publicTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {//ProceedingJoinPoint는 프록시 대상 객체의 메서드를 호출할 때 사용
		long start = System.nanoTime();
		try {
			Object result = joinPoint.proceed(); // proceed메서드를 사용해 실제 대상 객체의 메서드를 호출함(실행)
			return result;
		} finally {
			long finish = System.nanoTime();
			Signature sig = joinPoint.getSignature();
			System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
					joinPoint.getTarget().getClass().getSimpleName(),
					sig.getName(),Arrays.toString(joinPoint.getArgs()),
					(finish-start)
					);
//			System.out.println("sig.getName : "+sig.getName()); 대상객체 메서드의 이름
//			System.out.println("sig.toLongString : "+sig.toLongString()); // 대상객체 메서드의 경로
//			System.out.println(joinPoint.getSignature()); // 대상객체 메서드의 경로
//			System.out.println(joinPoint.getArgs()); // 대상객체 파라미터 목록
			//Signature는 호출한 메서드의 정보(리턴타입,이름,매개변수)가 저장되어있는 객체
			//getTarget()호출한 메서드를 포함하는 객체 리턴
			//getArgs() 메서드를 호출할 때 넘겨준 인자목록을 Object배열로 리턴
		}
	}
}
