// Christopher Kovaleski
// COP 3502
// MCSS.java
// Spring 2014
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class mcss {
	public Scanner inp = new Scanner(new File("mcss.txt"));
	public final int numRuns = 100;
	public int numSequences;
	public int numInts;
	public int[] sequence;
	public static void main(String[] s) throws FileNotFoundException{
		new mcss();
	}
	
	mcss() throws FileNotFoundException {
		int i = 0;
		// Determine number of sequences
		numSequences = inp.nextInt();
		//System.out.println(numSequences);
		
		while(i < numSequences){
			readFile();
			System.out.print(mcss_linear(sequence) + " ");
			//printSeq(sequence);
			runTimes(sequence);
			i++;
		}
		
		// Close input file stream
		inp.close();
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
		
		// Timer for the Squared method
		start = System.nanoTime();
		for(i = 0; i < numRuns ;i++)
			mcss_squared(seq);
		end = System.nanoTime();
		System.out.print((end-start) / numRuns + " ");	
		start = 0; end = 0;
		
		// Timer for the Linear method
		start = System.nanoTime();
		for(i = 0; i < numRuns ;i++)
			mcss_linear(seq);
		end = System.nanoTime();
		System.out.println((end-start) / numRuns);	
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
