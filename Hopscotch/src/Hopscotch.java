import java.io.*;
import java.util.Scanner;

/**
 * COP3503C Computer Science 2
 * Hopscotch.java
 * Purpose: Implement Dynamic Programming.
 *
 * @author Christopher Kovaleski
 * @version 1.0 4/19/2014
 */


public class Hopscotch {
	
	public static void main(String []arg) throws FileNotFoundException, UnsupportedEncodingException{
		new Hopscotch();
	}
	
	Hopscotch() throws FileNotFoundException, UnsupportedEncodingException{
		Scanner inp = new Scanner(new File("hopscotch.in"));
		//PrintWriter out = new PrintWriter("hopscotch.out", "UTF-8");

		int cases = inp.nextInt();
		for(int i = 0; i < cases; i++){
			int number = inp.nextInt();
			System.out.println("Game #" + (i+1) + ": "
					+ iterative(number));
			//out.println("Game #" + (i+1) + ": "
			//		+ iterative(number));
		}
		inp.close();
		//out.close();
	}
	
	/*
	 * Bottom up method using DP, checks all the possible
	 * solutions and stored the previously solved values into
	 * an int array.
	 */
	static int iterative(int num){
		int f[] = new int[num + 1];
		int i = 1;
		f[0] = 0;
		//f[1] = 1;
		
		for(i = 1; i <= num; i++){
			if(rule2(i) == true)
				f[i] = Math.min(
						f[i - useRule2(i)] + 3,
						f[i - 1] + 1);
			else if(rule3(i) == true && rule4(i) == true){
				int rule3 = Math.min(
						f[i - useRule3(i)] + 4,
						f[i - 1] + 1);
				int rule4 = Math.min(
						f[i - 4] + 2,
						f[i - 1] + 1);
				f[i] = Math.min(rule3, rule4);
				
			}
			else if(rule3(i) == true)
				f[i] = Math.min(
						f[i - useRule3(i)] + 4,
						f[i - 1] + 1);
			else if(rule4(i) == true)
				f[i] = Math.min(
						f[i - 4] + 2,
						f[i - 1] + 1);
			else{
				f[i] = f[i-1] + 1;
			}
		}
		
		return f[num];
	}
	
	static int min(int a, int b, int c, int d){
		if( a <= b && a <= c && a <= d)
			return a;
		if( b <= a && b <= c && b <= d)
			return b;
		if( c <= a && c <= b && a <= d)
			return c;
		if( d <= b && d <= c && d <= a)
			return d;
		
		else
			return a;
	}
	
	
/*
 * Attempt at a recursive solution worked for all
 * test cases under 50, did not add memoization
 * due to me getting the bottom up method working first.
 * This function is therefore useless and just for my records.	
 */
	/*
	static int recursive(int num){
		if(rule2(num) == true)
			System.out.print("@ ");
		if(rule3(num) == true)
			System.out.print("# ");
		if(rule4(num) == true)
			System.out.print("$ ");
		System.out.print(num + ": ");
		
		if(num == 0)
			return 0;
		
		// if the digits in rule 2 are less than 3
		// your score would be smaller using 3 rule 1's
		if(rule2(num) == true && useRule2(num) >= 3){
			System.out.println("rule 2 score = " + (score+3));
			score += 3;
			return recursive(num - useRule2(num));
		}
		else if(rule3(num) == true && rule4(num) == true){
			System.out.print("*");
			
			return recursive(num - max);
		}

		else if(rule4(num) == true){
			System.out.println("rule 4 score = " + (score+2));
			score += 2;
			return recursive(num - useRule4(num));
		}
		
		else if(rule3(num) == true && useRule3(num) >= 4){
			System.out.println("rule 3 score = " + (score+4)
					);
			score += 4;
			return recursive(num - useRule3(num));
		}
		else{
			System.out.println("rule 1 score = " + (score+1));
			score += 1;
			return recursive(num - useRule1(num));
		}
	}*/
	
	/*
	 * Rule 1 can always be used
	 */
	static boolean rule1(int num){
		return true;
	}
	
	static int useRule1(int num){
		return 1;
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
	
	/*
	 * Return the number minus its "digits" value.
	 */
	static int useRule2(int num){
		if(rule2(num) == false)
			// To help the min function, return a high
			// value if we shouldn't use rule 2
			return 99999;
		return (num % 10);
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
		if(rule3(num) == false)
			// To help the min function, return a high
			// value if we shouldn't use rule 3
			return 99999;
		
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
		if(rule4(num) == false)
			// To help the min function, return a high
			// value if we shouldn't use rule 4
			return 99999;
		return 4;
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