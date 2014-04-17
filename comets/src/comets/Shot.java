package comets;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;


public class Shot extends SpaceObject {
	private int shotCounter = 0;
	private int shotCount = 0;
	
	
	
	public Shot(double x, double y, double velocityX, double velocityY){

		super(x, y, velocityX, velocityY, 3); // 3 for the shot size
		// explicit call to the superclass constructor
		String pew = "file:pew.wav";
		//int i = 0;
		try {
		AudioClip clip = Applet.newAudioClip(new URL(pew));
		clip.play();
		
	
		} catch (MalformedURLException murle) {
		System.out.println(murle);
		}

	}
	
	public void move(){
		shotCounter++;
		shotCount = shotCounter;
		super.move();// call to the superclass move method

	}
	
	public int getAge(){
		shotCount = shotCounter;
		return shotCount;
		
	}
}
