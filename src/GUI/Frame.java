package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Frame extends JFrame { 
	
	public Frame(String title, boolean isRootWindow) {
		super(title);
		
		setLocation(100, 100);
	    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    
	    if (isRootWindow) {
	    	Frame frame = this;
		    
		    addWindowListener(new java.awt.event.WindowAdapter() {
		        @Override
		        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		            /*if (JOptionPane.showConfirmDialog(frame,
		            		"Sei sicuro di voler terminare l'applicazione?",
		            		"Attenzione",
		            		JOptionPane.YES_NO_OPTION,
		            		JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {*/
		                System.exit(0);
		            //}
		        }
		    });
	    }
	}
}
