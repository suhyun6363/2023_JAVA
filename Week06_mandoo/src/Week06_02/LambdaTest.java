package Week06_02;
// stream 이용하지 않고 람다식을 작성하여 테스트

interface ArrayProcessing {
	double apply(double[] array);
}

public class LambdaTest {
	//(1)
	public static final ArrayProcessing maxer = array -> {
		double maxNum = 0.0;
		
		for(double arrayItem : array) {
			if(arrayItem >=  maxNum)
				maxNum = arrayItem;
		}
		
		return maxNum;
	};
	
	//(2)
	public static final ArrayProcessing miner = array -> {
		double minNum = 50000.0;
		
		for(double arrayItem : array) {
			if(arrayItem <=  minNum)
				minNum = arrayItem;
		}
		
		return minNum;
	};
	
	//(3)
	public static final ArrayProcessing sumer = array -> {
		double sum = 0.0;
		
		for(double arrayItem : array)
			sum += arrayItem;
		return sum;	
	};
	
	public static void main(String[] args) {
		// 예시 배열 생성
        double[] numbers = { 10.5, 7.2, 15.8, 4.3, 12.1 };

        // 최대값 계산
        double maxResult = maxer.apply(numbers);
        System.out.println("최대값: " + maxResult);

        // 최소값 계산
        double minResult = miner.apply(numbers);
        System.out.println("최소값: " + minResult);

        // 합 계산
        double sumResult = sumer.apply(numbers);
        System.out.println("합: " + sumResult);
		
	}
}
