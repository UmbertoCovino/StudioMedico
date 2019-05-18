package GUI;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FrameLogin extends Frame {
	static final int MINIMUM_FRAME_WIDTH = 350,
			 		 MINIMUM_FRAME_HEIGHT = 150,
			 		 FIRST_ROW_Y = 10, SECOND_ROW_Y = 40, THIRD_ROW_Y = 60,
					 FIRST_COLUMN_X = 10, SECOND_COLUMN_X = 140;
	
	public FrameLogin() {
		super("Autenticazione", MINIMUM_FRAME_WIDTH, MINIMUM_FRAME_HEIGHT);
		
		int frameCenterX = getWidth() / 2;

		JPanel formPanel = new JPanel();
		formPanel.setLayout(null);
		
		JLabel emailLabel = new JLabel("Email", SwingConstants.RIGHT);
		JLabel passwordLabel = new JLabel("Password", SwingConstants.RIGHT);
		
		JTextField emailTextField = new JTextField();
		JTextField passwordTextField = new JTextField();
		
		JButton loginButton = new JButton("Login");
		JButton signUpButton = new JButton("Registrati");
		
		// event handlers
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 
			}
		});
		
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 
			}
		});
		
		// posizionamento elementi
		
		formPanel.setBounds(frameCenterX - 100, FIRST_ROW_Y, 300, 75);
		
		emailLabel.setBounds(FIRST_COLUMN_X, FIRST_ROW_Y, emailLabel.getPreferredSize().width, emailLabel.getPreferredSize().height);
		emailTextField.setBounds(SECOND_COLUMN_X, FIRST_ROW_Y, 80, emailTextField.getPreferredSize().height);
		
		passwordLabel.setBounds(FIRST_COLUMN_X, SECOND_ROW_Y, passwordLabel.getPreferredSize().width, passwordLabel.getPreferredSize().height);
		passwordTextField.setBounds(SECOND_COLUMN_X, SECOND_ROW_Y, 80, passwordTextField.getPreferredSize().height);
		
		loginButton.setBounds((getWidth() / 2) - (loginButton.getPreferredSize().width / 2), 100, loginButton.getPreferredSize().width, loginButton.getPreferredSize().height);
		signUpButton.setBounds(FIRST_COLUMN_X, 100, signUpButton.getPreferredSize().width, signUpButton.getPreferredSize().height);
		
		// aggiunta al frame
		
		formPanel.add(emailLabel);
		formPanel.add(emailTextField);
		formPanel.add(passwordLabel);
		formPanel.add(passwordTextField);
		
		getContentPane().add(formPanel);
		getContentPane().add(loginButton);
		getContentPane().add(signUpButton);
		
		setVisible(true);
	}
}
