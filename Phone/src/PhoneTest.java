// Debugging extra credit problem: PhoneTest.java
// Program creates a GUI that resembles a phone with functionality.
import javax.swing.JFrame;

public class PhoneTest
{
   // execute application
   public static void main( String args[] )
   {
      Phone application = new Phone();
      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      application.setSize( 200, 300 );
      application.setVisible( true );
   } // end main
} // end class PhoneTest
