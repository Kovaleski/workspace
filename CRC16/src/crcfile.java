import java.io.File;
import java.io.FileNotFoundException;
import java.math.*;
import java.util.Scanner;
public class crcfile {
	public final static int SIZE = 16; // CRC-? size.
	public final static String crc16 = "1 0000 1001 1000 1101";
	public static void main(String[] args) throws FileNotFoundException {
		 if(args[0].equals("c") == false && args[0].equals("v") == false)
			{
				System.out.println("Invalid Parameter, graceful exit sequence initiated.");
				System.out.println("...\n...\n*thump*\n*crash*\n*Screams of immense terror*");
				System.out.println("MAYDAY MAYDAY WE'RE GOING DOWN!");
				System.out.println("Before we die, I must tell you that I hid the money in the"
						+ "---\n...\n...\n...\nProgram Terminated");
			}; 
		Scanner inp = new Scanner(new File(args[1]));
		String hex1 = "A";
	    hex1 = inp.next();
	    inp.close();
		hex1 = appendCRC16(hex1);
		String hexCheck = "";
		if(args[0].charAt(0) == 'c' || args[0].charAt(0) == 'C'){
			if(inputValidCheck(hex1) == true){
				System.out.println("Input File(Hex) *Zeros appended: " + hex1);
				hex1 = hexToBin(hex1);
				System.out.println("Input File(Bin) *Zeros appended: " + hex1);
				System.out.println("Polynomial Used: " + crc16);
				System.out.println("Hence we added 16 zeroes in the binary, 4 in hex.");
				System.out.println("The binary string answer at each XOR step of CRC calculation is:");
				hexCheck = calculateCRC(crc16, hex1);
				
				System.out.println("--------------------");
				System.out.println("The computed CRC for this file is(bin): " + addSpaces(hexCheck));
				hexCheck = addSpaces(hexCheck);
				System.out.println("Which in hex is " + binToHex(hexCheck));
			}
			else
				System.out.println("Error in the input.");
		}
	}
	
	private static String addSpaces(String bin){
		int j;
		String ret = "";
		for(j = 0; j < bin.length(); j++){
			if(j % 4 == 0 && j != 0)
				ret = ret + " ";
			ret = ret + bin.charAt(j);
		}
		return ret;
	}

	private static String calculateCRC(String polynomial, String bin){
		String crc = ";";
		bin = bin.replaceAll("\\s",""); // take out spaces for XOR
		polynomial = polynomial.replaceAll("\\s+","");
		String temp = "";
		int i,j; int start = -1;
		for(i = 0; i < bin.length() - SIZE + 1; i++){
			start = findNextOne(bin, start);
	
			if(start >= bin.length() - SIZE) 
				break; // stop doing XORs
			crc =  XOR(bin.substring(start, start + SIZE +1), polynomial);
			temp = bin.substring(0, start) + crc + bin.substring(start + crc.length(), bin.length());
			
			bin = temp;
			start = findNextOne(bin,start);
			
			
			for(j = 0; j < bin.length(); j++){
				if(j % 4 == 0 && j != 0)
					System.out.print(" ");
				System.out.print(bin.charAt(j));
			}
			System.out.println();
		}
		//System.out.println(bin);
		//System.out.println(temp);
		
		return bin.substring(bin.length() - SIZE, bin.length());
	}
	
	private static String charPerLine(String in){
		String out = ""; int i;
		for(i = 0; i < in.length(); i++){
			if(i % 80 == 0)
				out = out + " ";
			
			out = out + in.charAt(i);
		}
		return out;
	}
	
	private static int findNextOne(String bin, int start){
		int i;
		int loc = start;
		//System.out.println(start);
		for(i = 0; i < bin.length(); i++){
				if(bin.charAt(i) == '1'){ // if binary is a 1, we start division here
					loc = i;			
				//System.out.println(loc);
				break;
			}
		}
		if(loc == -1) loc = 0;
		//System.out.println("Loc " + loc);
		return loc;
	}
	
	private static String appendCRC16(String input){
		return input + "0000"; // append 16 binary 0's (4 in hex)
	}
	
	private static String XOR(String str1, String str2){
		String XOR = "";
		//System.out.println(str1);
		//System.out.println(str2);
		int i;
		for(i = 0; i < str1.length(); i++)
		{
			if(str1.charAt(i) == ' ')
				XOR = XOR + " ";
			else if(str1.charAt(i) == str2.charAt(i))
				XOR = XOR + "0";
			else if(str1.charAt(i) != str2.charAt(i))
				XOR = XOR + "1";
		}

		return XOR;
	}
	
	private static boolean inputValidCheck(String in)
	{
		int i;
		int flag = 0;
		for(i = 0; i < in.length(); i++)
		{
			if(in.charAt(i) != '0' && in.charAt(i) != '1' && in.charAt(i) != '2' &&
					in.charAt(i) != '3' && in.charAt(i) != '4' && in.charAt(i) != '5' &&
					in.charAt(i) != '6' && in.charAt(i) != '7' && in.charAt(i) != '8' &&
					in.charAt(i) != '9' && in.charAt(i) != 'A' && in.charAt(i) != 'a' &&
					in.charAt(i) != 'B' && in.charAt(i) != 'b' && in.charAt(i) != 'C' &&
					in.charAt(i) != 'c' && in.charAt(i) != 'D' && in.charAt(i) != 'd' &&
					in.charAt(i) != 'E' && in.charAt(i) != 'e' && in.charAt(i) != 'F' &&
					in.charAt(i) != 'f')
				flag = 1; // raise the flag if the input is invalid
		}
		if(flag == 1)
			return false; // input invalid
		else
			return true;  // input valid
	}

	private static String hexToBin(String hex){
		String binaryForm = ""; 
		int i;
		for(i = 0; i < hex.length(); i++){
			binaryForm = binaryForm + hexCharToBin(hex.charAt(i));
			binaryForm = binaryForm + " ";
		}
		return binaryForm;
	}
	
	private static String binToHex(String bin){
		String hexForm = "";
		String tempBin = "";
		int i;
		for(i = 0; i< bin.length(); i += 5){
			tempBin = bin.substring(i, i+4); // grab substring of length 4, the binary;
			hexForm = hexForm + binStringToHex(tempBin);
		}
		return hexForm;
	}
	
	private static String hexCharToBin(char hexChar){
		String binary;
		if(hexChar == '0')
			binary = "0000";
		else if(hexChar == '1')
			binary = "0001";
		else if(hexChar == '2')
			binary = "0010";
		else if(hexChar == '3')
			binary = "0011";
		else if(hexChar == '4')
			binary = "0100";
		else if(hexChar == '5')
			binary = "0101";
		else if(hexChar == '6')
			binary = "0110";
		else if(hexChar == '7')
			binary = "0111";
		else if(hexChar == '8')
			binary = "1000";
		else if(hexChar == '9')
			binary = "1001";
		else if(hexChar == 'A' || hexChar == 'a')
			binary = "1010";
		else if(hexChar == 'B' || hexChar == 'b')
			binary = "1011";
		else if(hexChar == 'C' || hexChar == 'c')
			binary = "1100";
		else if(hexChar == 'D' || hexChar == 'd')
			binary = "1101";
		else if(hexChar == 'E' || hexChar == 'e')
			binary = "1110";
		else if(hexChar == 'F' || hexChar == 'f')
			binary = "1111";
		else
			binary = "INVALID";
		return binary;
	}

	private static String binStringToHex(String bin){
		String hex;
		//System.out.println(bin);
		if(bin.equals("0000"))
			hex = "0";
		else if(bin.equals("0001"))
			hex = "1";
		else if(bin.equals("0010"))
			hex = "2";
		else if(bin.equals("0011"))
			hex = "3";
		else if(bin.equals("0100"))
			hex = "4";
		else if(bin.equals("0101"))
			hex = "5";
		else if(bin.equals("0110"))
			hex = "6";
		else if(bin.equals("0111"))
			hex = "7";
		else if(bin.equals("1000"))
			hex = "8";
		else if(bin.equals("1001"))
			hex = "9";
		else if(bin.equals("1010"))
			hex = "A";
		else if(bin.equals("1011"))
			hex = "B";
		else if(bin.equals("1100"))
			hex = "C";
		else if(bin.equals("1101"))
			hex = "D";
		else if(bin.equals("1110"))
			hex = "E";
		else if(bin.equals("1111"))
			hex = "F";
		else
			hex = "INVALID";
		return hex;
	}
}
