
import java.text.DecimalFormat;
/* Name: Christopher Kovaleski
COP 3330 – Fall 2013
Assignment title: Program 1: Creating A Java Application From A UML Diagram 
Date: September 8, 2013 */
import java.util.Scanner;

public class HeatIndexCalculator {
	public static int temperature;
	public static double humidity;
	public static double heatIndex;
	
	public static void main(String args[]) {
		// Calls the methods expressed in the UML diagram and takes
		// input from the user in order to satisfy the method parameters.
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the current temperature in degree Fahrenheit:");
		temperature = sc.nextInt();
		System.out.println("Please enter the current humidity as a percentage:");
		humidity = sc.nextDouble();
		
		heatIndex = calculateHeatIndex(temperature, humidity);
		printHeatIndex(temperature, humidity, heatIndex);
		sc.close();
	}
	
	public static double calculateHeatIndex(
		// Method uses constants c1 through c9 in addition to the input
		// humidity and temperature to calculate the heat index.
			
		int currentTemp, double currentHumidity){
		double calculatedHumidity = 0;
		double c1, c2, c3, c4, c5, c6, c7, c8, c9;
		int T = currentTemp;
		double R = currentHumidity;
		c1 = -42.379;
		c2 = 2.04901523;
		c3 = 10.14333127;
		c4 = -0.22475541;
		c5 = -0.00683783;
		c6 = -0.0548172;
		c7 = 0.00122874;
		c8 = 0.00085282;
		c9 = -.00000199;
		
		calculatedHumidity = c1 + (c2*T) + (c3*R)+(c4*T*R)+(c5*T*T)
				+(c6*R*R)+(c7*T*T*R)+(c8*T*R*R)+(c9*T*T*R*R);
		
		return calculatedHumidity;
	}
	
	public static void printHeatIndex(
		// Method prints out the current heat index, temperature, and humidity.
			
		int currentTemp, double currentHumidity, double calculatedHeatIndex){
		System.out.println("At a temperature of " + currentTemp +
				" and a humidity of " + currentHumidity + " percent...");
		System.out.print("It actually feels like: ");
		printDoubleTwoPlaces(calculatedHeatIndex);
		System.out.print("F");
	}
	
    public static void printDoubleTwoPlaces(double doubleToFormat) {
    	 
        DecimalFormat formattedDouble = new DecimalFormat("#.##");
        System.out.print(formattedDouble.format(doubleToFormat));
    }
}
