// Christopher Kovaleski
// MCSS Solver for variable inputs
// COP 3502
// MCSS.java
// Spring 2014
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class mcssSolver {
	public Scanner inp = new Scanner(new File("mcssinp.txt"));
	public final int numRuns = 100;
	public int numLines;
	public int numInts;
	int algType;
	int numNs;
	public int[] sequence;
	public static void main(String[] s) throws FileNotFoundException{
		new mcssSolver();
	}
	
	mcssSolver() throws FileNotFoundException {
		int i = 0;
		
		// Determine number of sequences
		numLines = inp.nextInt();
		//System.out.println(numLines);
		while (i < numLines){
			int j = 0;
			algType = inp.nextInt();
			//System.out.println("algType: " + algType);
			numNs = inp.nextInt();
			//System.out.println("numNs: " + numNs);
			while(j < numNs){
				solver();
				j++;
			}
			i++;
		}
		
		// Close input file stream
		inp.close();
	}
	
	public void solver() {

		int N = inp.nextInt();
		int numRuns = 10;
		//int j = 0; 
		// create and populate array with random ints on range [-1000, 1000]
		int [] array = new int[N];

		for (int i = 0; i < N; i++)
			array[i] = (int)(Math.random() * 2001) - 1000;

		// start timer
		long start = 0;
		long end = 0;
		
		if (algType == 1){
			start = System.nanoTime();
			for (int i = 0; i < numRuns; i++)
				mcss_linear(array);
			end = System.nanoTime();
		}
		else if (algType == 2){
			start = System.nanoTime();
			for (int i = 0; i < numRuns; i++)
				mcss_squared(array);
			end = System.nanoTime();
		}
		else if (algType == 3){
			start = System.nanoTime();
			for (int i = 0; i < numRuns; i++)
				mcss_cubed(array);
			end = System.nanoTime();
		}
		else
			System.out.println("input Invalid.");
		// end timer
		

		// convert nanoseconds to ms and divide by numRuns to get average runtime
		// for a single function call
		System.out.println("Alg = " + algType + " || N = " + N + " || Time: " + (((end - start)/ numRuns)));
	}
	
	void printSeq(int[] a){
		System.out.println();
		for(int i = 0; i < a.length; i++)
			System.out.print("[" + i +"]=" + a[i] + ";" );
		System.out.println();
	}
	// Bring in a sequence array, run algorithms with times printed
	void runTimes(int[] seq){
		long start, end;
		int i;
		start = 0; end = 0;
		// Timer for the Cubed method
		start = System.nanoTime();
		for(i = 0; i < numRuns ;i++)
			mcss_cubed(seq);
		end = System.nanoTime();
		
		// Convert to milliseconds and divide by
		// numRuns tp get avg runtime for a call
		System.out.print((end-start) / numRuns + " ");
		start = 0; end = 0;		
	}
	
	void readFile() {
		numInts = inp.nextInt();
		// System.out.println(numInts);
		int i = 0; 

		sequence = new int[numInts];	
		for (i = 0; i < numInts; i++){
			// Fill in array
			try{
				sequence[i] = inp.nextInt();
				}catch(InputMismatchException e){
				System.out.print(e.getMessage());
			}
			//System.out.println(sequence[i]);
		}
		
		
	}

	//Sean Szumlanski's Code
	//Easier for me to throw it in my own code to avoid
	//having to know how you guys will place the file

		public int mcss_cubed(int [] array) {
			int max = 0;

			for (int i = 0; i < array.length; i++) {
				for (int j = i; j < array.length; j++) {
					int sum = 0;

					for (int k = i; k <= j; k++)
						sum += array[k];

					if (sum > max)
						max = sum;
				}
			}
			return max;
		}

		public int mcss_squared(int [] array) {
			int max = 0;

			for (int i = 0; i < array.length; i++) {
				int sum = 0;
				for (int j = i; j < array.length; j++) {
					sum += array[j];

					if (sum > max)
						max = sum;
				}
			}
			return max;
		}

		public int mcss_linear(int [] array) {
			int max = 0, sum = 0;

			for (int i = 0; i < array.length; i++) {
				sum += array[i];

				if (sum > max)
					max = sum;
				else if (sum < 0)
					sum = 0;
			}
			return max;
		}

}

