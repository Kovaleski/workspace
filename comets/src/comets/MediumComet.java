package comets;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

public class MediumComet extends Comet{
	public MediumComet(double x, double y, double velocityX, double velocityY){
		super(x, y, velocityX, velocityY, mediumSize);
	}
	
	public Vector<Comet> explode(){
		// add 3 new small comets
			small.add(new SmallComet
					(x, y, (Math.random() * 3) + 1, (Math.random() * 3) + 1));
			small.add(new SmallComet
					(x, y, (Math.random() * 3) + 1, (Math.random() * 3) + 1));
			small.add(new SmallComet
					(x, y, (Math.random() * 3) + 1, (Math.random() * 3) + 1));
			String boom = "file:explosion.wav";
			//int i = 0;
			try {
			AudioClip clip = Applet.newAudioClip(new URL(boom));
			clip.play();
			
		
			} catch (MalformedURLException murle) {
			System.out.println(murle);
			}
		return small;
	}
}
