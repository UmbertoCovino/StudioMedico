package GUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FrameLogin extends Frame {
	
	public FrameLogin(String title) {
		super(title);
		
		JLabel emailLabel = new JLabel("Email");
		JLabel passwordLabel = new JLabel("Password");
		
		JTextField emailTextField = new JTextField();
		JTextField passwordTextField = new JTextField();
		
		JButton loginButton = new JButton();
		JButton signUpButton = new JButton();
		
		add(emailLabel);
		add(passwordLabel);
		add(emailTextField);
		add(passwordTextField);
		add(loginButton);
		add(signUpButton);

		setVisible(true);
	}
	
//	public void show() {
//		setVisible(true);
//	}
}
