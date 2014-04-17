package comets;

public class Ship extends SpaceObject{
	public Ship(double x, double y, double velocityX, double velocityY){
		super(x, y, velocityX, velocityY, 10);
	}
	
	
	
	
	//TheFox8bit.wav
	private final double shipAcceleration = 0.1; // given in specs
	private final double shipTurningRate = 0.1;  // given in specs
	private double currAngle = 0; // starting point will be 0 degrees
	
	
	public void accelerate(){
		xVelocity = xVelocity + 
				(shipAcceleration * Math.sin(currAngle));
		yVelocity = yVelocity + 
				(shipAcceleration * Math.cos(currAngle));	
	}
	
	public Shot fire(){
		// you can use any shot speed you want, I just found 10 to add the most fun
		double newVelocityX = xVelocity + (10 * Math.sin(currAngle));
		double newVelocityY = yVelocity + (10 * Math.cos(currAngle));
		// create new shot based on the specs
		Shot bullet = new Shot(x, y, newVelocityX, newVelocityY);
		return bullet;
	}
	
	public double getAngle(){
		return currAngle;
	}
	
	// helper method for testing
	public void setAngle(double angleSet){
		currAngle = angleSet;
	}

	public void rotateLeft(){
		currAngle = currAngle + shipTurningRate; // left
	}
	
	public void rotateRight(){
		currAngle = currAngle - shipTurningRate; // right
	}
}
