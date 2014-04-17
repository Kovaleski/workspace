// Christopher Kovaleski
// COP 3502
// Toposort.java
// Implementation of a DFS and Decrease by One Topological Sort
// Spring 2014

import java.io.*; 
import java.util.*;

public class TopoSort{
	public int T[];
	public int numGraphs;
	public int numVertices;
	public int start = 0;
	public Scanner inp = new Scanner(new File("graphs.txt"));
	public boolean[][] graph ;
	public int[][] intGraph;
	public boolean[] onStack ;
	public int currGraph = 0;
	public int[] orderA;
	public Stack<Integer> s;
	public Stack<Integer> fin;
	public boolean cycle = false;
	public Queue<Integer> Q;
	
	public static void main(String[] s) throws FileNotFoundException{
		new TopoSort();
	}
	
	TopoSort() throws FileNotFoundException{
		
		// kept out of the readFile method since it's only appearing 1 time.
		numGraphs = inp.nextInt();		
		while (currGraph < numGraphs){
			start = 0;
			s = new Stack<Integer>();
			fin = new Stack<Integer>();
			onStack = new boolean[numVertices+(99*99)]; // keeps track of which items have been on the stack
			Arrays.fill(onStack, Boolean.FALSE);
			s.push(start); // push starting location onto stack
			onStack[start] = true; // first item has been on stack
			readFile();
			
			// Run both DFS and Decrease and Conquer Topological searches
			// DAC contains the check for cycles, so I run this before printing anything
			DFS();
			DAC();
			
			// push items with no incoming edges onto the stack
			for(int i=0; i<numVertices; i++){
				if(onStack[i]==false){
					fin.push(i);
				}
			}
			
			// Print output of the DFS Topological sort if there is no cycle
			System.out.print("TS(" + currGraph + ", " + "DFS" +"): ");
			if (cycle == true)
				System.out.print("NO TOPOLOGICAL SORT");
			else
				printStack(fin);
			System.out.println();
			
			// Print out the DBO topological sort is there is no cycle
			System.out.print("TS(" + currGraph + ", " + "DBO" +"): ");
			if(cycle == true)
				System.out.print("NO TOPOLOGICAL SORT");
			else
				for(int i = 0; i < numVertices; i++){
					System.out.print(T[i] + " ");
				}
			System.out.println();
			
			// Increment to next graph and reset the cycle variable
			currGraph = currGraph + 1;
			cycle = false;
		}
		
		// close the file stream
		inp.close();
	}
	
	public void DAC(){
		boolean[] vis=new boolean[numVertices+1];
		start = 0;
		Q=new LinkedList<Integer>(); // A queue to process nodes
		T = new int[numVertices+100000];
		//Q.add(start);
		vis[start]=true;
		boolean incoming[] = new boolean[numVertices];
		boolean found[] = new boolean[numVertices];
		int count = 0;
		// Check which have no incoming edges
		incoming = incoming(incoming);
		for(int k = 0; k<numVertices; k++){
			if(incoming[k] == false){
				Q.add(k);
				found[k] = true;
			}
		}
		for(int i = 0; i < 5; i++){

			//System.out.print(incoming[i] + " ");
		}
		//System.out.print("||");
		while (Q.isEmpty() == false)
		{
			// Dequeue Vertex and add V to ordering T;
			int curr = Q.peek();
			T[count] = Q.poll();
			//System.out.print(T[count] + "|");
			count++;
			//System.out.print(count + "*");
			//found[T[count]] = true;
			// Delete outgoing edges from V
			for(int j = 0; j < numVertices; j++){
				if(intGraph[curr][j] == 1){
					//System.out.print("*" + T[count] + " " + j + "*");
					intGraph[curr][j] = 0;
				}
				resetIncoming(incoming);
				incoming(incoming);
			}
			
			// Enqueue adjacent edges of V without incoming edges
			for(int i=0; i < numVertices; i++){
				if(incoming[i] == false && found[i] == false){
					//System.out.println("*" + i + "*");
					Q.add(i);				
					//System.out.print("*" + i + "*");
					found[i] = true;
					resetIncoming(incoming);
					incoming(incoming);
				}
			}
			//System.out.println();
		}
		
		// check for cycle
		// if there is more than one of a number in Array T
		// then the topological sort did not hit all nodes
		// and there is a cycle
		for(int i=0; i < numVertices; i++){
			for(int j=0; j< numVertices; j++){
				if(T[i] == T[j] && i != j){
					cycle = true;
				}
			}
		}
	}

	boolean[] resetIncoming(boolean incoming[]){
		for(int i = 0; i < incoming.length; i++){
			incoming[i] = false;
		}
		return incoming;
	}
	boolean[] incoming(boolean incoming[]){
		for(int i=0; i <numVertices; i++){
			int j = 0;
			while(j < numVertices){
				if(intGraph[i][j] == 1){
					//System.out.print( "'" + i + j + "'");
					incoming[j] = true;
				}
				j++;
			}
		}

		return incoming;
	}
	public void printQ(Queue<Integer> q){
		while(!q.isEmpty()){
			System.out.print(q.peek ()+ " ");
			q.remove();
		}
	}
	
	public void printStack(Stack<Integer> s){
		while(!s.isEmpty()){
			System.out.print(s.peek ()+ " ");
			s.pop();
		}
	}
	// Read everything from the file "graph.txt"
	void readFile() throws FileNotFoundException{
		numVertices= inp.nextInt();

		int i, j;
		int currInt = 0; // used to convert to boolean on the fly
		// the first 1,1 location in the graph will always be 0
		graph = new boolean[numVertices][numVertices];
		intGraph = new int[numVertices][numVertices];
		// convert to boolean matrix
		for (i = 0; i < numVertices; i++){
			for(j = 0; j < numVertices; j++){
				currInt = inp.nextInt();
				if(currInt == 0){
					graph[i][j] = false; // 0 to false
					intGraph[i][j] = 0;
				}
				else if(currInt == 1){
					graph[i][j] = true; // 1 to true
					intGraph[i][j] = 1;
				}
				else
					System.out.println("Invalid number in matrix");
			}
		}
	}
	public void BFS(){
		boolean[] visited=new boolean[numVertices+1];
		//System.out.print("BFS(" + currGraph + ", " + start + "): ");

		for (int i=0; i< numVertices; ++i){
			if(visited[i] == true)
				//System.out.print("|" + i + "|");
			if (visited[i] == false){
				callBFS((start + i) % numVertices,visited);
			

			}
		}
	}
	
	public void callBFS(int start, boolean[] visited){
		// create a queue
		Queue<Integer> priorityQ=new LinkedList<Integer>(); // A queue to process nodes
		priorityQ.add(start);
		visited[start]=true;
		
		// BFS as long as as the queue is not empty
		while (priorityQ.isEmpty() == false)
		{
			int i;
			int loc=priorityQ.remove();
			for (i=0; i<numVertices; ++i)
				if ((visited[i] == false) && (graph[loc][i])){
					priorityQ.add(i);
					visited[i]=true;
				}

			//System.out.print(loc + " ");
		}
	}
	
	
	
	public void DFS(){
		boolean[] adjmat=new boolean[numVertices]; 
		 // print start node
		//System.out.print("TS(" + currGraph + ", " + "DFS" +"): ");
		for (int i=0; i<numVertices; ++i){
			
			callDFS((start+i)%numVertices,adjmat);
		}
	}

	public void callDFS(int loc, boolean[] visited){		
		
		visited[loc]=true;
		int[] visitedArray = new int[numVertices+1];
		visitedArray[loc] = 1;


		// Call DFS method for all t
		for (int i=0; i<numVertices; ++i){
			//System.out.println(i);
			
			if((visited[i] == true) && graph[loc][i] == true){
				// store the stack Backwards for TOPOSORT
				if (visited[loc] == true)
					//cycle = true;
				//System.out.print("-");

				while(!s.isEmpty()){			
					//System.out.print("|" + s.peek() + "|");
					fin.push(s.pop());
					
				}
			}
			if ((visited[i] == false) && 
					graph[loc][i] == true){

				//System.out.print("|");
				s.push(i); // push onto stack
				onStack[i] = true;
				visitedArray[i+1] = i;
				visited[i] = true;
				callDFS(i,visited);
				
			}
		}
		// Finish emptying the stack into the reversed final stack
		while(!s.isEmpty()){
			fin.push(s.pop());
		}
	}
}