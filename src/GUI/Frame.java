package GUI;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Frame extends JFrame { 
	
	public Frame(String title, boolean isRootWindow) {
		super(title);
	    
	    if (isRootWindow) {
	    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    	Frame thisFrame = this;
		    
		    addWindowListener(new java.awt.event.WindowAdapter() {
		        @Override
		        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//		            if (JOptionPane.showConfirmDialog(thisFrame,
//		            		"Sei sicuro di voler terminare l'applicazione?",
//		            		"Attenzione",
//		            		JOptionPane.YES_NO_OPTION,
//		            		JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
		                System.exit(0);
//		            }
		        }
		    });
	    } else
	    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	// has parent frame dependency
	public Frame(String title, Frame parentFrame) {
		super(title);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		parentFrame.setVisible(false);
    	
		Frame thisFrame = this;
		
		addWindowListener(new java.awt.event.WindowAdapter() {
	        @Override
	        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				parentFrame.setVisible(true);
				thisFrame.dispose();
	        }
	    });
	}
	
	// has parent frame dependency with message
	public Frame(String title, Frame parentFrame, String dialogMessage) {
		super(title);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		parentFrame.setVisible(false);
    	
		Frame thisFrame = this;
		
		addWindowListener(new java.awt.event.WindowAdapter() {
	        @Override
	        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	        	if (JOptionPane.showConfirmDialog(thisFrame,
	        			dialogMessage,
		        		"Attenzione",
		        		JOptionPane.YES_NO_OPTION,
		        		JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					parentFrame.setVisible(true);
					thisFrame.dispose();
		        }
	        }
	    });
	}

	protected void show(final int EXTRA_FRAME_WIDTH) {
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setSize(new Dimension(getWidth() + EXTRA_FRAME_WIDTH, getHeight()));
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
