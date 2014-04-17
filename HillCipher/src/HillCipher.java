import java.lang.Object;
import java.io.*;
import java.util.Scanner;

public class HillCipher {
	public static void main(String []arg) throws IOException
	{
	    int k[][] = new int[500][500];
	    int p[]=new int[10001];
	    int c[]=new int[10001];
	    int i, j, x, m;
	    Scanner scan = new Scanner(System.in);
	    String keyLoc, plainLoc, storeLoc;

	    String plainString = "";
	    int size;
	    int shift = 0;
	    char cipherText[] = new char[10001];
	    int pad;
	    
	    // grab file location
	    System.out.println("Please enter the name of the file storing the key:");
	    keyLoc = scan.next();
	    
	    // grab size of matrix
	    Scanner keyScanner = new Scanner(new File(keyLoc));
	    size = keyScanner.nextInt();  
	    
	    // read in matrix
	    for(i=0;i<size;i++){
	    	//System.out.println();
	    	for(j=0;j<size;j++){
	    		k[i][j] = keyScanner.nextInt();
	    		//System.out.print(k[i][j] + " ");
	    	}
	    }
	    
	    System.out.println("Please enter the name of the file to encrypt:");
	    plainLoc = scan.next();
	    // Store the whole text into a string
	    Scanner plainScanner = new Scanner(new File(plainLoc));
	    while (plainScanner.hasNextLine()) {
	        plainString = plainString + "\n" + plainScanner.nextLine();
	    }
	      
	    
	    // Convert the string to lowercase with no white space.
	    plainString = plainString.toLowerCase();
	    plainString = plainString.replaceAll("[^a-zA-Z0-9]", "");
	    
	    // Pad the string if needed with "x"
	    pad = plainString.length() % size; 
	    while(pad != 0){
	    	plainString = plainString + "x";
	    	//System.out.println("here");
	    	pad = plainString.length() % size;
	    }
	    //System.out.println(plainString);
	    char plainText[] = plainString.toCharArray();
	    
	    // store the plaintext into an integer array.
	    for(i= 0; i < plainString.length();i++){
	    	p[i] = (plainText[i] - 97) % 26;
	    }

	    i = 0;
	    shift = 0;
	    
	    // perform the multiplication of the matrices
	    for(x = 0; x < plainString.length() / size; x++){
	    	for(j = 0; j < size; j++){
	    		for(m = 0; m < size; m++){
	    			// Multiply matrix, mod by 26 to get it in number range.
	    			c[i] = ((c[i] + (k[j][m] * p[m + shift])) % 26);
	    		}
	    		i = i + 1;
	    	} 
	    	// shift to next set of letters
	    	// if the matrix size is (5*5), you do 5 characters at a time. 
	    	shift = shift + size;
	    }
	    
	    // store the ciphered integer array into a character array.
	    // add 97 to get to ascii lowercase range
	    for(i=0; i < plainString.length(); i++){
	    	cipherText[i] = (char) (c[i] + 97);
	    }
	    //System.out.println(cipherText[0]);
	    //System.out.println(c[0]);
	    String cipherString = new String(cipherText);
	    
	    

	    
	    System.out.println("Please enter the name of the file to store the ciphertext:");
	   	storeLoc = scan.next();
	    
	    // Write output to file
	    PrintWriter out = new PrintWriter(new FileWriter(storeLoc)); 
	   	// Print in 80 character lines
	    for(i=0; i < cipherString.length(); i++){
		    out.print(cipherString.charAt(i));
		    if((i+1) % 80 == 0){
		    	out.println();
		    }
	    }
	    out.close();
	    
	    
	   
	    //System.out.println(cipherString);
	    /*System.out.println("strlen = " + plainString.length());	    
	    System.out.println("size = " + size);
	    System.out.println("pad = " + pad);
	    System.out.println(plainString.length() % size);
	    System.out.println(plainString);
	    */
	    scan.close();
	    keyScanner.close();
	    plainScanner.close();
	}
	

}
