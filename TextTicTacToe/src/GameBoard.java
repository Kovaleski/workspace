// Christopher Kovaleski
// COP 3330
// Tic Tac Toe With AI

import java.util.Scanner;
import java.util.Random;

public class GameBoard {
	enum Options{X,O,E}; // E for empty
	public int i, j, k; // declare counters
	public static Random rand = new Random();
	public static Options[][] Board = new Options[3][3]; 
	public static Scanner in = new Scanner(System.in);
	public GameBoard(){ // Creates an array of enums when the object is generated(The gameboard)
		createGameBoard();
	}
	public static void main(String []arg){
		boolean gameOverCheck = false; // check if game has ended
		boolean drawCheck = false; // check for draw
		int ai;
		createGameBoard();
		System.out.println("Welcome to Tic Tac Toe!");
		System.out.println("Play with AI? 1 for Yes, 0 for No");
		ai = in.nextInt();
		while(gameOverCheck == false)
		{
			playGame(1); // let player 1 play
			gameOverCheck = gameOver();
			drawCheck = checkDraw();
			if (gameOverCheck == true)
			{
				if(drawCheck == false)
					System.out.println("Player 1 Wins");
				else
					System.out.println("Game ended in a Draw.");
				break;
			}
			if(gameOverCheck == false)
			{
				if(ai != 1)
					playGame(2);
				else
					playGameAI(2);
				gameOverCheck = gameOver();
				drawCheck = checkDraw();
				if (gameOverCheck == true)
				{
					if(drawCheck == false)
						System.out.println("Player 2 Wins");
					else
						System.out.println("Game ended in a Draw.");
					break;
				}
			}
		}
		in.close();
	}
	private static void createGameBoard() {
		//Options[][] Board = new Options[3][3]; // create gameboard from enum type
		int i, j;
		for(i = 0; i < 3; i++){
			for(j = 0; j < 3; j++){
				Board[i][j] = Options.E;
			}
		}
		//printBoard();
	}
	
	private static boolean checkEmpty(int x, int y) // check if coordinate location is empty
	{
		if(Board[x][y] == Options.E)
			return true;
		else
			return false;
	}
	
	private static boolean gameOver()
	{
		if(checkEmpty(0, 0) == false && checkEmpty(0, 1) == false &&
			checkEmpty(0, 2) == false && checkEmpty(1, 0) == false &&
			checkEmpty(1, 1) == false && checkEmpty(1, 2) == false && 
			checkEmpty(2, 0) == false && checkEmpty(2, 1) == false &&
			checkEmpty(2, 2) == false) // Check for draw
			return true;
		else if (checkDiagonal() == true)
			return true;
		else if (checkLine() == true)
			return true;
		else
			return false;
	}
	private static boolean checkDraw()
	{
		if(checkEmpty(0, 0) == false && checkEmpty(0, 1) == false &&
			checkEmpty(0, 2) == false && checkEmpty(1, 0) == false &&
			checkEmpty(1, 1) == false && checkEmpty(1, 2) == false && 
			checkEmpty(2, 0) == false && checkEmpty(2, 1) == false &&
			checkEmpty(2, 2) == false) // Check for draw
			
			return true;
		else
			return false;
	}
	
	private static boolean checkDiagonal()
	{
		if(Board[0][0] == Board[1][1] && Board[0][0] == Board[2][2]
				&& Board[1][1] != Options.E){
			//System.out.println("DIAGONAL");
			return true;}
		else if(Board[0][2] == Board[1][1] && Board[2][0] == Board[1][1]
				&& Board[1][1] != Options.E){
			//System.out.println("DIAGONAL2");
			return true;}
		else
			return false;
	}
	
	private static boolean checkLine()
	{
		if(Board[0][0] == Board[0][1] && Board[0][1] == Board[0][2]
				&& Board[0][0] != Options.E){
			return true;}
		else if(Board[1][0] == Board[1][1] && Board[1][1] == Board[1][2]
				&& Board[1][1] != Options.E){
			return true;}
		else if(Board[2][0] == Board[2][1] && Board[2][1] == Board[2][2]
				&& Board[2][0] != Options.E){
			return true;}
		else if(Board[0][0] == Board[1][0] && Board[1][0] == Board[2][0]
				&& Board[0][0] != Options.E){
			return true;}
		else if(Board[0][1] == Board[1][1] && Board[1][1] == Board[2][1]
				&& Board[0][1] != Options.E){
			return true;}
		else if(Board[0][2] == Board[1][2] && Board[1][2] == Board[2][2]
				&& Board[0][2] != Options.E){
			return true;}
		else
			return false;
	}
	
	public static void playGame(int playerNumber)
	{
		int x, y; // used for coordinates from the scanner.
		
		System.out.println("Enter coordinates Player " + playerNumber);
		System.out.println("Row coordinate: ");
		x = in.nextInt();
		x = checkInt(x);
		System.out.println("Column coordinate: ");
		y = in.nextInt();
		y = checkInt(y);

		while(checkEmpty(x,y) == false)
		{
			System.out.println("This square is taken, please try again.");
			System.out.println("Enter coordinates Player " + playerNumber);
			System.out.println("Row coordinate: ");
			x = in.nextInt();
			x = checkInt(x);
			System.out.println("Column coordinate: ");
			y = in.nextInt();
			y = checkInt(y);
		}
		if(checkEmpty(x, y) == true)
		{
			if (playerNumber == 1){
				Board[x][y] = Options.X;
			}
			else
				Board[x][y] = Options.O;
		}
		printBoard();
	}
	
	public static void playGameAI(int playerNumber)
	{
		int x, y; // used for coordinates from the scanner.
		System.out.println("Enter coordinates Player " + playerNumber);
		x = rand.nextInt(3);
		y = rand.nextInt(3);
		System.out.println("X: " + x + " Y: " + y);
		while(Board[x][y] != Options.E)
		{
			x = rand.nextInt(3);
			y = rand.nextInt(3);

		}

		Board[x][y] = Options.O;

		printBoard();
	}
	
	private static int checkInt(int num)
	{
		int value;
		// Check if int is valid
		if(num > 2 || num < 0)
		{
			System.out.println("Invalid number (0,1,2 are valid entries)");
			//Scanner in = new Scanner(System.in);
			value = in.nextInt();
			value = checkInt(value);
			//in.close();
			return value;
		}
		else
			return num;		
	}
	private static void printBoard()
	{
		System.out.println("-------");
		System.out.println("-" + Board[0][0] + "-" + Board[0][1] + "-" + Board[0][2] + "-");
		System.out.println("-" + Board[1][0] + "-" + Board[1][1] + "-" + Board[1][2] + "-");
		System.out.println("-" + Board[2][0] + "-" + Board[2][1] + "-" + Board[2][2] + "-");
		System.out.println("-------");
	}	
}
