import java.util.Scanner;

public class GameBoard {
	enum Options{EX,OH,EMPTY}; // I don't like using O as a name, so I chose to use phonetics instead.
	int i, j, k; // declare counters
	public static Options[][] Board = new Options[3][3]; 
	GameBoard() {
		//Options[][] Board = new Options[3][3]; // create gameboard from enum type
		for(i = 0; i < 3; i++){
			for(j = 0; j < 3; j++){
				Board[i][j] = Options.EMPTY;
			}
		}
	}
	
	private static boolean checkEmpty(int x, int y) // check if coordinate location is empty
	{
		if(Board[x][y] == Options.EMPTY)
			return true;
		else
			return false;
	}
	
	public void playGame()
	{
		int x, y; // used for coordinates from the scanner.
		Scanner in = new Scanner(System.in);
		x = in.nextInt();
		y = in.nextInt();
		if(checkEmpty(x, y) == false)
			Board[x][y] = Options.EX;
		in.close();
		printBoard();
	}
	
	private static void printBoard()
	{
		System.out.println("---------------");
		System.out.println("-" + Board[0][0] + "-" + Board[0][1] + "-" + Board[0][2] + "-");
		System.out.println("---------------");
	}
	
}
