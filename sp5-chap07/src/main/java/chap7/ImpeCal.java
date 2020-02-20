package chap7;

public class ImpeCal implements Calculator {

	@Override
	public long factorial(long num) {
		long result = 1;
		for(long i=1;i<=num;i++) {
			result*= i;
		}
		return result;
	}

}
