
public class Hopscotch {
	public static void main(String []arg){

		for(int i = 0; i < 1000; i++){
			if(rule2(i) == true){
				System.out.println(i);
			}
		}
	}
		
	static boolean rule2(int num){
		if(num < 10)
			return false;
		
		if(isPrime(num) == true)
			return true;
		
		return false;
	}
	
	static boolean rule4(int num){
		if(num % 7 == 0)
			return true;
		return false;
	}
	
	static boolean isPrime(int num){
		// Check if it is divisible by 2
		if (num % 2 == 0)
			return false;
		
		// Check odd numbers > 1 that are < num^2
		// as sqrt(num) is the median factor
		for(int i = 3; i*i <= num; i += 2){
				if(num % i == 0)
					return false;
		}
		
		return true;
	}
}
