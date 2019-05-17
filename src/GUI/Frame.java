package GUI;

import javax.swing.JFrame;

public class Frame extends JFrame {
	
	public Frame(String title) {
		super(title);
		
		setSize(400, 500);
		setLayout(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
