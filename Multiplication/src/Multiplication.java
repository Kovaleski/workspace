import java.io.*; 
import java.util.*;
import java.lang.reflect.Array;
import java.math.*;

/**
 * COP3503C Computer Science 2
 * Multiplication.java
 * Purpose: Implement binary multiplication algorithms.
 *
 * @author Christopher Kovaleski
 * @version 1.0 3/4/2014
 */
public class Multiplication {
	Scanner inp = new Scanner(new File("mult.txt"));
	String multiplier, multiplicand;
	int bitsInMultiplier, bitsInMultiplicand;
	int[] multiplierArray, multiplicandArray;
	
	/**
	 * The main method of the Multiplication class
	 * Creates a new multiplication object.
	 * @param s
	 * @throws FileNotFoundException
	 */
	public static void main(String[] s) throws FileNotFoundException{
		new Multiplication();
	}
	
	/**
	 * Runs the Multiplication class methods as directed in the given specs.
	 * @throws FileNotFoundException
	 */
	Multiplication() throws FileNotFoundException{
		int iterations = inp.nextInt();
		
		for(int i = 0; i < iterations; i++){
			
			// Read in file
			readFile();
			
			// Run each algorithm
			binMultBasic(multiplicandArray, multiplierArray);
			binMultKaratsuba(multiplicandArray, multiplierArray);
		}
	}
	
	/**
	 * Takes input from the file, stores input into global variables.
	 * @throws FileNotFoundException
	 */
	void readFile() throws FileNotFoundException{
		bitsInMultiplicand = inp.nextInt();
		multiplicand = inp.next();
		multiplicandArray = stringToBinary(multiplicand);
		bitsInMultiplier = inp.nextInt();
		multiplier = inp.next();
		multiplierArray = stringToBinary(multiplier);
	}
	
	/**
	 * Takes two binary arrays, multiplies them, returns the answer in an array
	 * @param Multiplicand
	 * @param Multiplier
	 * @return
	 */
	int[] binMultBasic(int[] mcand, int[] mplier){
		int size = mcand.length + mplier.length;
		int mult[] = new int[size];
		int curr[] = new int[size];	
		
		if((mcand.length == 1 && mcand[0] == 0) || (mplier.length == 1 && mplier[0] == 0)){
			int[] zero = new int[1];
			zero[0] = 0;
			printAnswerArray(zero);
			return zero;
		}
		
		for(int i = mplier.length -1 ; i >= 0; i--){
			curr = multStep(mcand, mplier[i], i, size);

			// Add the current step to the multiple
			mult = sum(mult, curr);
		}
		
		int answer[] = new int[mplier.length + mcand.length];
		for(int i = 0; i < answer.length; i++){
			answer[i] = mult[i];
		}
		
		printAnswerArray(answer);
		return answer;
	}
	
	/**
	 * Performs Karatsuba's algorithm on a binary integer arrays
	 * @param multiplicand
	 * @param multiplier
	 */
	int[] binMultKaratsuba(int[] multiplicand, int[] multiplier){
		int sizeMultiplicand = (int)powerOfTwo(bitsInMultiplicand);
		int sizeMultiplier = (int)powerOfTwo(bitsInMultiplicand);
		int n = 1; // 2^n
		int power = 1; // the n in 2^n
		int[] answer = new int[sizeMultiplicand + sizeMultiplier];
		
		// base case
		if(multiplicand.length == 1 || multiplier.length == 1 || n == 1){
			answer[0] = multiplicand[0] * multiplier[0];
			return base(multiplicand, multiplier);
		}
		
		// check which power of 2 is larger		
		if(sizeMultiplicand > sizeMultiplier){
			n = sizeMultiplicand;
			power = (int)powerOfTwoN(bitsInMultiplicand);
		}
		else{
			n = sizeMultiplier;
			power = (int)powerOfTwoN(bitsInMultiplier);
		}
		// pad zeroes
		int[] paddedMultiplicand = padLeft(multiplicand, n - multiplicand.length);
		int[] paddedMultiplier = padLeft(multiplier, n - multiplier.length);
		printIntArray(paddedMultiplier);
		// Create low and high arrays used in Karatsuba multiplication
		int[] a1 = Arrays.copyOfRange(paddedMultiplicand, 0, n/2);
		int[] a0 = Arrays.copyOfRange(paddedMultiplicand, n/2, n);
		int[] b1 = Arrays.copyOfRange(paddedMultiplier, 0, n/2);
		int[] b0 = Arrays.copyOfRange(paddedMultiplier, n/2, n);
		
		
		// Perform 3 steps of the Karatsuba method.
		int[] c2 = binMultKaratsuba(a1 , b1);
		int[] c0 = binMultKaratsuba(a0 , b0);
		int[] c1 = sum(binMultKaratsuba(sum(a1 , a0) , sum(b1 , b0)), 
				twosComplement(sum(c2 , c0)));
		int[] c = sum(sum(padRight(c2, power), padRight(c1, power/2)),c0);
		
		return c;
	}
	
	int[] padLeft(int[] bin, int pad){
		int[] answer = new int[bin.length + pad];
		
		// pad
		for(int i = 0; i < pad; i++){
			answer[i] = 0;
		}
		// fill in the rest
		for(int i = pad; i < answer.length; i++){
			answer[i] = bin[i - pad];
		}
		
		return answer;
	}

	int[] padRight(int[] bin, int pad){
		int[] answer = new int[bin.length + pad];
		
		// pad
		for(int i = 0; i < bin.length; i++){
			answer[i] = bin[i];
		}
		// fill in the rest
		for(int i = bin.length; i < answer.length; i++){
			answer[i] = 0;
		}
		
		return answer;
	}
	
	/**
	 * Calculate the 2s complement of a number
	 * @param binary number
	 * @return Two's complement of the binary number
	 */
	int[] twosComplement(int[] bin){
		int[] answer = new int[bin.length+1];
		
		// Binary array equivalent to 1.
		int[] addOne = new int[bin.length + 1];
		addOne[bin.length] = 1;

		// Reverse the array
		for(int i = 0; i < bin.length; i++){
			if(bin[i] == 0){
				answer[i+1] = 1;
			}
			else
				answer[i+1] = 0;
		}
		
		// Add one to the array
		answer = sum(answer, addOne);
		return answer;
	}
	
	/**
	 * Not working yet
	 * @param array
	 * @return
	 */
	int[] removeLeadingZero(int[] array){
		int leadLocation = array.length * 2;
		for(int i = 0; i < array.length; i++){
			if(array[i] == 1){
				leadLocation = i;
			}
		}
		
		if(leadLocation == array.length * 2){
			
		}
		
		for(int i = leadLocation; i < array.length; i++){
			
		}
		return array;
	}

	/**
	 * Takes in integer, gives the first n in 2^n that is above
	 * @param size
	 * @return
	 */
	double powerOfTwoN(int size){
		double answer = 1;
		int power = 1;
		while(answer < size){
			answer = Math.pow(2, power);
			power++;
		}
		return power;
	}
	
	/**
	 * Takes in integer, gives the first power of 2 that is above
	 * @param size
	 * @return
	 */
	double powerOfTwo(int size){
		double answer = 1;
		int power = 1;
		while(answer < size){
			answer = Math.pow(2, power);
			power++;
		}
		return answer;
	}
	

	
	/**
	 * Reverses and integer array
	 * @param integer array
	 * @return reversed array
	 */
	int[] reverse(int[] a){
		int[] temp = new int[a.length];
		for(int i = 0; i < a.length; i++){
			temp[i] = a[a.length - 1 - i];
		}
		
		return temp;
	}
	
	int[] base(int[] a, int[] b){
		return binMultBasic(a,b);
	}
	
	/**
	 * Extends the array being added at each step of the mult algorithm;
	 * @param a
	 * @param bin
	 * @param count
	 * @return extended array for the multiplication step
	 */
	int[] multStep(int[] a, int bin, int count, int size){
		int b[] = new int[size];
		int temp[] = new int[size];
		
		// add the zeros to extend the binary for addition
		if(bin == 1){
			for(int i = 0; i < (a.length + count); i++){
				if(i < count)
					b[i] = 0;				
				if(i < a.length){
					b[i+count] = a[i];
				}
				
			}
			
			
			// put the zeroes before the array 
			for(int i = size -1; i >= 0; i--){
				if(i < (size - b.length)){
					temp[i] = 0;
				}
				else;
					temp[i] = b[i];
			}
			
			// reverse the temp array to prepare for the addition
			for (int i = 0; i < size; i++){
				b[i] = temp[size - 1 - i];
			}
			
			// find location of the first 1 in reverse
			int[] temp2 = new int[size];
			int found = 0;
			//int loc = 0;
			int j = 0 + count + 1;
			//System.out.println();
			for (int i= size - 1; i > 0; i--){
				if(found == 0 && b[i] == 1){
					//loc = i;
					found = 1;
					//j = loc;
				}
				
				if(found == 1){
					temp2[j] = b[i];
					j++;
				}
			}
			
			return temp2;
		}
		else
			return b;
		
	}
	
	
	long arrayToInt(int[] a){
		String b = Arrays.toString(a);
		b = b.replaceAll("[^0-9]","");
		long answer = Long.parseLong(b, 2);
		return answer;
	}
	
	int[] sum(int[] a, int[] b){
		int c = 0;
		int tempSum[] = new int[a.length];
		if(a.length == b.length){
			for(int i = a.length; i > 0 ; i--){
					tempSum[i-1] = ((a[i-1] ^ b[i-1]) ^ c);
				  	c = ((a[i-1] & b[i-1]) | (a[i-1] & c)) | (b[i-1] & c);
				}
		}
		int sum1[] = new int[tempSum.length + (c/2 + 1)];
		if(c != 0){
			sum1[0] = 1;
			for(int i = 0; i < tempSum.length; i++){
				sum1[i+1] = tempSum[i];
			}
		}
		else{
			sum1 = tempSum;
		}
		//int sum2[] = checkAdd(a,b);
		
		//if(Arrays.equals(sum1, sum2) == true)
			return sum1;
		//else
		//	return sum2;
	}
	
	/**
	 * Takes in two numbers, return 1 or 0 based on the XOR operation
	 * @param num1
	 * @param num2
	 * @return
	 */
	int XOR(int num1, int num2){
		if(num1 == num2)
			return 0;
		else
			return 1;
	}
	
	/**
	 * Checks if a number is odd, returns true if it is.
	 * @param num
	 * @return
	 */
	boolean isOdd(int num){
		if (num % 2 == 0)
			return false; 
		else
			return true;
	}
	
	/**
	 * 	Takes in a multiplicand and multiplier and returns the result.
	 * @param multiplicant
	 * @param multiplier
	 * @return answer to the multiplication expression
	 */
	long checkMult(long multiplicant, long multiplier){
		long answer = multiplicant * multiplier;
		return answer;
	}
	
	/**
	 * Takes in 2 binary arrays, returns the addition of the two in an array
	 * @param a
	 * @param b
	 * @return
	 */
	int[] checkAdd(int[] a,int[] b){
		String first = Arrays.toString(a);
		first = first.replaceAll("[^0-9]","");
		String second = Arrays.toString(b);
		second = second.replaceAll("[^0-9]","");
		long f = Long.parseLong(first, 2);
		long s = Long.parseLong(second, 2);
		String answer = Long.toBinaryString(f+s);
		int add[] = new int[1000];
		add = stringToBinary(answer);
		return add;
	}
	
	/**
	 * Converts a string of binary characters into an integer array.
	 * @param String of binary characters
	 * @return Integer array storing binary
	 */
	int[] stringToBinary(String string){
		int[] bin = new int[string.length()];
		for(int i=0; i < bin.length; i++){
			if(string.charAt(i) == '1'){
				bin[i] = 1;
			}
			else if(string.charAt(i) == '0'){
				bin[i] = 0;
			}
			else
				return null;
		}
		
		int[] binReverse = new int[bin.length];
		for(int i=bin.length; i > 0; i--){
			int j = 0;
			binReverse[j] = bin[i-1];
			j++;
		}
		return bin;
	}
	
	/**
	 * Adds the column values of a 2x2 array and stores the answer in an array.
	 * @param array
	 * @return
	 */
	int[] arraySum(int[][] array){
		int numRows = array[0].length;
		int numCols = array.length;
		int sum[] = new int[numRows];
		
		for(int i = 0; i < numRows; i++){
			sum[i] = 0;
		}
		
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){
				sum[i] += array[j][i];
			}
		}
		
		return sum;
	}
	
	/**
	 * Sends the array in order to System.out
	 * Does not print zeroes before the first 1 is found
	 * @param array
	 */
	void printIntArray(int[] array){
		
		if (array.length != 1){
			for(int i = 0; i < array.length; i++){
				//if(foundOne == 1)
				//	System.out.print(array[i]);
				//if (array[i] == 1 && foundOne == 0){
				//	foundOne = 1;
					System.out.print(array[i]);
				//}
			}
		}
		else{
			System.out.print("0000");
		}
		System.out.println();
	}
	
	/**
	 * Prints everything after the leading 1
	 * Used in the output. Aka 001101 would become 1101 printed
	 * @param array
	 */
	void printAnswerArray(int[] array){
		if (array.length != 1){
			boolean foundOne = false;
			for(int i = 0; i < array.length; i++){
				if(foundOne == true)
					System.out.print(array[i]);
				if (array[i] == 1 && foundOne == false){
					foundOne = true;
					System.out.print(array[i]);
				}
			}
		}
		else{
			System.out.print("0000");
		}
		System.out.println();
	}

}