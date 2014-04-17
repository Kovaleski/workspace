import java.io.*; 
import java.util.*;

/**
 * COP3503C Computer Science 2
 * Heap.java
 * Purpose: Implement heap functions.
 *
 * @author Christopher Kovaleski
 * @version 1.0 4/1/2014
 */

public class Heap {
	Scanner inp = new Scanner(new File("heapops.txt"));
	List<Integer> heap = new ArrayList<Integer>();
	
	public static void main(String[] s) throws FileNotFoundException{
		new Heap();
	}
	
	Heap() throws FileNotFoundException{
		while(inp.hasNext()){
			command();
		}
		
	}
	
	void command(){
		String command = inp.next();						
		if(command.contains("load")){
			// Load Array
			load();

		}
		if(command.contains("print")){
			// Print heap
			print(heap);
		}
		if(command.contains("build-heap")){
			// Build heap
			buildHeap(heap);
		}
		if(command.contains("delete-max")){
			// Delete top element
			deleteMax(heap);
		
		}
		if(command.contains("insert")){
			// Grab number to insert
			int insertion = inp.nextInt();
			// Insert number into heap
			insert(heap, insertion);
			
		}
		if(command.contains("heapsort")){
			heapsort(heap);
			//print(heap);
		}
	}
	
	void heapify2(List<Integer> heap, int i){
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int max;
		
		if(right >= heap.size() || left >= heap.size()){
			return;
		}
		max = heap.get(left) > heap.get(i) ? left:i;
		
		if(heap.get(right) > heap.get(max))
			max = right;
		if(max != i){
			int temp = heap.get(max);
			heap.set(max, heap.get(i));
			heap.set(i, temp);
			
			heapify(heap, max);
		}
		
	}
	
	
	void heapsort(List<Integer> heap){
		if(heap.size() == 0){
			System.out.print("(empty)");
			System.out.println();
		}
		
			else{
			// Perform the delete-max operation until heap is empty
			// Outputs in decreasing order
			while(heap.size() != 0){
				System.out.print(heap.get(0) + " ");
				deleteMax(heap);
			}
			System.out.println();
		}
	}
	
	void deleteMax(List<Integer> heap){
		// Store last heap element
		int temp = heap.get(heap.size() - 1);
		
		// Switch top with the last int in heap
		heap.set(heap.size() - 1, heap.get(0));
		heap.set(0, temp);
		
		// Remove largest element
		heap.remove(heap.size() - 1);
		
		// Build heap
		buildHeap(heap);	
		
	}
	
	void insert(List<Integer> heap,int num){
		heap.add(num);
	}
	
	void buildHeap(List<Integer> heap){
		// Used to account for empty final nodes
		// The data structure doesn't return null, so I use -99 as a placehold
		insert(heap, -99);
		insert(heap, -99);
		
		
		for(int i = heap.size()/2 + 1; i >= 0; i--){
			// Uncommenting this code shows how the heap is built step-by-step
			//print(heap);
			heapify2(heap, i);
		}
		heap.remove(heap.size() - 1);
		heap.remove(heap.size() - 1);
	}
	
	int left(List<Integer> heap, int i){
		if(heap.size() - 1 >= (2*i)){
			return 2 * i;
		}
		else
			return -1;
	}
	
	int right(List<Integer> heap, int i){
		if(heap.size() - 1 >= (2*i + 2)){
			return 2 * i + 1;
		}
		else
			return -1;
	}
	
	void print(List<Integer> heap){
		if(heap.size() == 0){
			System.out.print("(empty)");
			System.out.println();
		}
		else{
			for (int r = 0; r < heap.size(); r++)
			    System.out.print(heap.get(r) + " ");
			System.out.println();
		}
	}
	void heapify(List<Integer> heap, int i){
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int max;
		
		if(right >= heap.size() || left >= heap.size()){
			return;
		}
		max = heap.get(left) > heap.get(i) ? left:i;
		
		if(heap.get(right) > heap.get(max))
			max = right;
		if(max != i){
			int temp = heap.get(max);
			heap.set(max, heap.get(i));
			heap.set(i, temp);
			
			heapify(heap, max);
		}
		
	}
		
	void load(){
		String line;
		heap.clear();
		line = inp.nextLine().trim();
		
		// Read all integers into the heap
		while(line.indexOf(' ') != -1){
			line = line.trim();
			//System.out.println(line);
			
			// Grab the next number up unto the next space.
			if(line.charAt(0) != ' '){
				int index = line.indexOf(' ');
		
				// Check is a space is the first letter
				if(index != -1){
					String firstNumber = line.substring(0, index);
					line = line.substring(index).trim();
					// if more than a Space if left. 
					//  store it in the ArrayList
					if(line.charAt(0) != ' '){
						heap.add(Integer.parseInt(firstNumber));
					}
				}
				else{
				}
			}	
		}
		
		// Account for the last number in the heap
		line = line.trim();
		if(line.charAt(0) != ' '){
			heap.add(Integer.parseInt(line.trim()));
		}
		
		// Transform array into heap
	}
}
