package comets;

public abstract class SpaceObject {
	public SpaceObject (double currX, double currY, double currVelocityX, double currVelocityY, double r){
		x = currX; y = currY; xVelocity = currVelocityX; yVelocity = currVelocityY; radius = r; 
		// set all the object's properties (sent in by CometsMain)
	}
	protected double x;
	protected double y;   
	protected double radius;
	// Get/Set methods for the variables
	public double getXPosition(){
		return x;
	}
	
	public void setX(double setX){
		x = setX;
	}
	
	public double getYPosition(){
		return y;
	}
	
	public void setY(double setY){
		y = setY;
	}	
	
	public double getRadius(){
		return radius;
	}
	
	public void setR(double setR){
		radius = setR;
	}
	
	// account for call from CometsMain
	public boolean overlapping(SpaceObject object){	

		if ((Math.sqrt(Math.pow(x - object.getXPosition(), 2.0) + Math.pow(y - object.getYPosition(), 2.0)))
				< (radius + object.getRadius())){ 
			// Return true if distance (from formula) is less than the collective radii
			return true;
		}
		else{
			// false (they aren't actually overlapping)
			return false;
		}
	}
	public static double playfieldHeight; // found in configure method of CometsMain
	public static double playfieldWidth; // set in configure method of CometsMain
	protected double xVelocity; 
	protected double yVelocity; 
			
	public void move(){
		// move an object by using the coordinate and it's current velocity
		// to determine where to move it
		
		// doesn't work too well when called from the Shot ie: no fun, too slow.
		// To account for this I call the superclass twice.
		x = x + xVelocity;
		y = y + yVelocity;	
		if (x >  playfieldWidth){
			x = x - playfieldWidth;
		}
		if (x <= 0){
			x = playfieldWidth;
		}
		if (y > playfieldHeight){
			y = y - playfieldHeight;
		}
		if (y < 0){
			y = playfieldHeight;
		}
	}
}

