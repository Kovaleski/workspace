// Christopher Kovaleski
// COP 3330
// Due December 6, 2013
// Debugging problem Chapter 14: Phone.java
// Program creates a GUI that resembles a phone with functionality.
import java.awt.BorderLayout;
//import java.awt.Container; I was testing things with this
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Phone extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; // just wanted eclipse to stop warning me.
	//lcdJPanel = new JPanel ();
   private JButton keyJButton[]; // Jbutton needed to be JButton
   private JPanel keyJPanel;
   private JPanel lcdJPanel;
   private JTextArea lcdJTextArea;
   private String lcdOutput = "";
   //private int count; // not really sure why this is even here
   
   // constructor sets up GUI
   public Phone()
   {
      super( "Phone" );
      lcdJPanel = new JPanel(); // lcdJPanel never initiated.
      lcdJTextArea = new JTextArea( 4, 15 );
      lcdJTextArea.setEditable( false );
      lcdJPanel.add( lcdJTextArea );
      
      keyJButton = new JButton[ 15 ];
      
      // initialize all digit key Buttons
      for ( int i = 3; i <= 11; i++ )
         keyJButton[ i ] = new JButton( String.valueOf( i - 2 ) );
      
      // initialize all non-digit key Buttons
      keyJButton[ 0 ] = new JButton( "Send" );
      keyJButton[ 1 ] = new JButton( "clr" );
      keyJButton[ 2 ] = new JButton( "End" );
      keyJButton[ 12 ] = new JButton( "*" );
      keyJButton[ 13 ] = new JButton( "0" );
      keyJButton[ 14 ] = new JButton( "#" );
      
      keyJButton[ 0 ].addActionListener(
         new ActionListener(){ // actionlistener never added.
            public void actionPerformed( ActionEvent e ) 
            {
               lcdOutput = "Calling...\n\n" + lcdOutput;
               lcdJTextArea.setText( lcdOutput );
            } // end method actionPerformed
         } // end new ActionListener
   ); // end addActionListener call
      
      keyJButton[ 1 ].addActionListener(
         
         new ActionListener()
         {
            public void actionPerformed( ActionEvent e ) 
            {
               if ( lcdOutput.length() == 0 || 
                  lcdOutput.substring( 0, 1 ).equals( "C" ) )
                  return;
               else
               {
                  lcdOutput = lcdOutput.substring( 0, ( lcdOutput.length() - 1 ) );
                  lcdJTextArea.setText( lcdOutput );
               } // end else
            } // end method actionPerformed
         } // end object ActionLstener
      ); // end addActionListener call
      
      keyJButton[ 2 ].addActionListener(
         
         new ActionListener()
         {
            public void actionPerformed( ActionEvent e ) 
            {            
               lcdJTextArea.setText( " " );
               lcdOutput = "";
            } // end method actionPerformed
         } // end new ActionListener
      ); // end ActionListener call
          
      for ( int i = 3; i <= 14; i++ )
      {
         keyJButton[ i ].addActionListener(
           
            new ActionListener()
            {
               public void actionPerformed( ActionEvent e ) 
               {
                  lcdOutput += e.getActionCommand();

                  if ( lcdOutput.substring( 0, 1 ).equals( "C" ) )
                      return;

                  lcdJTextArea.append( e.getActionCommand() );
               } // end method actionPerformed
            } // end new ActionListener
         ); // end addActionListener call
      } // end for loop
                   
      // set keyJPanel layout to grid layout
      keyJPanel = new JPanel();
      keyJPanel.setLayout( new GridLayout( 5, 3 ) );

     
            
      // add buttons to keyJPanel
      for ( int i = 0; i <= 14; i++ ){
         keyJPanel.add( keyJButton[ i ] );
      }
      
      // add components to container
      add( lcdJPanel, BorderLayout.NORTH ); // needed to be lcdJPanel, we were passin in 2 strings
      add( keyJPanel ,  BorderLayout.SOUTH); // keys JPanel never added
   } // end Phone constructor
} // end class Phone