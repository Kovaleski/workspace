package comets;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

public class SmallComet extends Comet {
	public SmallComet(double x, double y, double velocityX, double velocityY){
		super(x, y, velocityX, velocityY, smallSize); // explicit call to superclass constructor
	}

	public Vector<Comet> explode(){
		// do not add new comets, the smalls are the "final" form
		Vector<Comet>smallComet = new Vector<Comet>(); 
		String boom = "file:explosion.wav";
		//int i = 0;
		try {
		AudioClip clip = Applet.newAudioClip(new URL(boom));
		clip.play();
		
	
		} catch (MalformedURLException murle) {
		System.out.println(murle);
		}
		return smallComet;
	}
}
