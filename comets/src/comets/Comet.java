package comets;
import java.util.Vector;

public abstract class Comet extends SpaceObject {
	public abstract Vector<Comet> explode();
	public Comet(double x, double y, double velocityX, double velocityY, double r){
		super(x, y, velocityX, velocityY, r);
	}
	
	protected static final int smallSize  = 20;
	protected static final int mediumSize = 30;
	protected static final int largeSize= 40;
	
	// Smalls are generated 3 per medium
	protected Vector<Comet> small = new Vector<Comet>(3);
	// Mediums are generated 2 per large
	protected Vector<Comet> medium = new Vector<Comet>(2);
	

}
