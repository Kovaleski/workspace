// Christopher Kovaleski
// COP 3502
// Graph.java
// Spring 2014

import java.io.*; 
import java.util.*;

public class Graph{
	public int numGraphs;
	public int numVertices;
	public int start;
	public Scanner inp = new Scanner(new File("graphs.txt"));
	public boolean[][] graph ;
	public int currGraph = 0;
	
	public static void main(String[] s) throws FileNotFoundException{
		new Graph();
	}
	
	Graph() throws FileNotFoundException{
		
		// kept out of the readFile method since it's only appearing 1 time.
		numGraphs = inp.nextInt();		
		while (currGraph < numGraphs){
			readFile();
			BFS();
			System.out.println();
			DFS();
			System.out.println();
			currGraph = currGraph + 1;
		}
		// close the file stream
		inp.close();
	}
	// Read everything from the file "graph.txt"
	void readFile() throws FileNotFoundException{

		start = inp.nextInt();
		//System.out.print(start + " ");
		numVertices= inp.nextInt();
		//System.out.println(numVertices);
		int i, j;
		int currInt = 0; // used to convert to boolean on the fly
		// the first 1,1 location in the graph will always be 0
		graph = new boolean[numVertices][numVertices];
		// convert to boolean matrix
		for (i = 0; i < numVertices; i++){
			for(j = 0; j < numVertices; j++){
				currInt = inp.nextInt();
				if(currInt == 0){
					graph[i][j] = false; // 0 to false
				}
				else if(currInt == 1){
					graph[i][j] = true; // 1 to true
				}
				else
					System.out.println("Invalid number in matrix");
			}
		}
	}
	
	public void BFS(){
		boolean[] visited=new boolean[numVertices+1];
		System.out.print("BFS(" + currGraph + ", " + start + "): ");
		for (int i=0; i< numVertices; ++i)
			if (visited[i] == false){				
				callBFS((start + i) % numVertices,visited);
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
				if ((visited[i] == false) &&
						(graph[loc][i])){
					priorityQ.add(i);
					visited[i]=true;
				}
			System.out.print(loc + " ");
		}
	}
	
	public void DFS(){
		boolean[] adjmat=new boolean[numVertices]; 
		 // print start node
		System.out.print("DFS(" + currGraph + ", " + start +"): ");
		System.out.print(start + " ");

		for (int i=0; i<numVertices; ++i) // check all nodes that aren't visited
				callDFS((start+i)%numVertices,adjmat);
				// use the starting node to go through (hence mod)
		
	}

	public void callDFS(int loc, boolean[] visited){		
		visited[loc]=true;
		int[] visitedArray = new int[numVertices+1];
		visitedArray[0] = loc;
		
		// Call DFS method for all t
		for (int i=0; i<numVertices; ++i)
			if ((visited[i] == false) && 
					graph[loc][i]){
				System.out.print(i + " ");
				callDFS(i,visited);
				visitedArray[i+1] = i;
			}
	}
	
	void printBackwards(int a[]){
		int l = a.length;
		int i = 0;
		for(i = 0; i < l; i++){
			System.out.print(a[l-i]);
		}
	}
	

}
