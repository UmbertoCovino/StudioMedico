package GUI;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public abstract class Frame extends JFrame {
	private int maxFrameWidth;
	private int extraFrameWidth;
	private int buttonsGap;
	
	private Frame(String title) {
		super(title);
		
		maxFrameWidth = 9999;
		extraFrameWidth = 0;
		buttonsGap = 15;
	}
	
	public Frame(String title, boolean isRootWindow) {
		this(title);
	    
	    if (isRootWindow) {
	    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    	Frame thisFrame = this;
		    
		    addWindowListener(new java.awt.event.WindowAdapter() {
		        @Override
		        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		            if (JOptionPane.showConfirmDialog(thisFrame,
		            		"Sei sicuro di voler terminare l'applicazione?",
		            		"Attenzione",
		            		JOptionPane.YES_NO_OPTION,
		            		JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
		                System.exit(0);
		            }
		        }
		    });
	    } else
	    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	// has parent frame dependency without message on close; is not a root window
	public Frame(String title, Frame parentFrame) {
		this(title);
		
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
	
	// has parent frame dependency with message on close; is not a root window
	public Frame(String title, Frame parentFrame, String dialogMessage) {
		this(title);
		
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

	public int getMaxFrameWidth() {
		return maxFrameWidth;
	}

	public void setMaxFrameWidth(int maxFrameWidth) {
		this.maxFrameWidth = maxFrameWidth;
	}

	public int getExtraFrameWidth() {
		return extraFrameWidth;
	}

	public void setExtraFrameWidth(int extraFrameWidth) {
		this.extraFrameWidth = extraFrameWidth;
	}

	public int getButtonsGap() {
		return buttonsGap;
	}

	public void setButtonsGap(int buttonsGap) {
		this.buttonsGap = buttonsGap;
	}

	protected void showFrame() {
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setSize(new Dimension(getWidth() + extraFrameWidth, getHeight()));
		setLocationRelativeTo(null);
		setVisible(true);
	}

	protected abstract void addingEventHandlers();

	protected abstract void elementsPositioning();
}
