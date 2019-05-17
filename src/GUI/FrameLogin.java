package GUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameLogin extends Frame {
	
	public FrameLogin(String title) {
		super(title);
		
		JLabel emailLabel = new JLabel("Email");
		JLabel passwordLabel = new JLabel("Password");
		
		JTextField emailTextField = new JTextField();
		JTextField passwordTextField = new JTextField();
		
		JButton loginButton = new JButton("Login");
		JButton signUpButton = new JButton("Registrati");
		
		JPanel panel = new JPanel();
		
		emailLabel.setBounds(40, 40, 50, 50);
		
		panel.add(emailLabel);
		panel.add(passwordLabel);
		panel.add(emailTextField);
		panel.add(passwordTextField);
		panel.add(loginButton);
		panel.add(signUpButton);
		
		add(panel);
	}
}
