
public class Hopscotch {
	public static void main(String []arg){

		for(int i = 1; i < 122; i++){
			if(rule3(i) == true){
				System.out.print(i + " = ");
				System.out.println(useRule3(i));
			}
		}
	}
	
	
	/*
	 * Rule 1 can always be used
	 */
	static boolean rule1(int num){
		return true;
	}
	
	static int useRule1(int num){
		return num - 1;
	}
	
	/*
	 * If the number is prime and greater than 10, return true
	 */
	static boolean rule2(int num){
		if(num < 10)
			return false;
		
		if(isPrime(num) == true)
			return true;
		
		return false;
	}
	
	static int useRule2(int num){
		return num - 4;
	}
	
	/*
	 * If the number is a multiple of 11, return true
	 */
	static boolean rule3(int num){
		if(num % 11 == 0)
			return true;
		
		return false;
	}
	
	/*
	 * Return the sum of the digits
	 */
	static int useRule3(int num){
		String numStr = Integer.toString(num);
		int sum = 0;
		// Parse string version of number and add the digits
		for(int i = 0 ; i < numStr.length(); i++){
			sum += Integer.parseInt(numStr.substring(i, i+1));
		}
		return sum;
	}
	
	/*
	 * If the number is a factor of 10, return true
	 */
	static boolean rule4(int num){
		if(num % 7 == 0)
			return true;
		return false;
	}
	
	static int useRule4(int num){
		return num - 4;
	}
	
	/*
	 * If the number is prime, return true
	 */
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
