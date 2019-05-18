package GUI;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

public class Frame extends JFrame {
	
	public Frame(String title, int minimumWidth, int minimumHeight) {
		super(title);
		
		setLocation(50, 50);
		setMinimumSize(new Dimension(minimumWidth, minimumHeight));
		
		setLayout(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		addComponentListener(new ComponentListener() {
//			public void componentShown(ComponentEvent e) {
//			}
//			
//			public void componentResized(ComponentEvent e) {
//				int widthOffset = (getWidth() - initialWidth),
//					heightOffset = (getHeight() - initialHeight);
//				
//				setSize(423 + widthOffset, 215 + heightOffset);
//				
//				infoLabel.setLocation(15, 260 + heightOffset);
//				inflowLabel.setLocation(15, 310 + heightOffset);
//				inflowTextField.setLocation(180, 310 + heightOffset);
//				outflowLabel.setLocation(15, 335 + heightOffset);
//				outflowTextField.setLocation(180, 335 + heightOffset);
//				
//				stopRestartListAddingButton.setLocation(322 + widthOffset, 307 + heightOffset);
//				refreshTankDataButton.setLocation(332 + widthOffset, 375 + heightOffset);
//			}
//			
//			public void componentMoved(ComponentEvent e) {
//			}
//			
//			public void componentHidden(ComponentEvent e) {
//			}
//		});
	}
}
