// Christopher Kovaleski
// Assignment 5
// 11/22/13
// COP 3330 Fall 2013
// Windows 8/ Ubuntu used
// Created in Eclipse
// Purpose: Use the given code to create a working game called comets

package comets;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

public class LargeComet extends Comet{
	public LargeComet(double x, double y, double velocityX, double velocityY){
		super(x, y, velocityX, velocityY, largeSize);
	}
	
	public Vector<Comet> explode(){
		// Explode large comet into 2 medium sized ones
		medium.add(new MediumComet(x, y, (1 + Math.random() * 3), (1 + Math.random() * 3)));
		medium.add(new MediumComet(x, y, (1 + Math.random() * 3), (1 + Math.random() * 3)));
		
		String boom = "file:explosion.wav";
		//int i = 0;
		try {
		AudioClip clip = Applet.newAudioClip(new URL(boom));
		clip.play();
		
	
		} catch (MalformedURLException murle) {
		System.out.println(murle);
		}
		return medium;
	}
}
