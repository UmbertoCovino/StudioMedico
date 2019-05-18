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
	static final int 		 			FIRST_COLUMN_WIDTH = 60, SECOND_COLUMN_WIDTH = 150,
									 	FIRST_COLUMN_X = 10, 	SECOND_COLUMN_X = FIRST_COLUMN_WIDTH + 15,
			 		 FIRST_ROW_Y  = 10,
			 		 SECOND_ROW_Y = 40,
			 		 THIRD_ROW_Y  = 80,
			 		 MINIMUM_FRAME_WIDTH = FIRST_COLUMN_WIDTH + SECOND_COLUMN_WIDTH + 20,
					 MINIMUM_FRAME_HEIGHT = THIRD_ROW_Y + 60;
	
	public FrameLogin() {
		super("Autenticazione", MINIMUM_FRAME_WIDTH, MINIMUM_FRAME_HEIGHT);
		
		int frameCenterX = getWidth() / 2;
		
		JPanel formPanel = new JPanel();
		
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
		
		emailLabel.setBounds(FIRST_COLUMN_X, FIRST_ROW_Y + 5, FIRST_COLUMN_WIDTH, emailLabel.getPreferredSize().height);
		emailTextField.setBounds(SECOND_COLUMN_X, FIRST_ROW_Y, SECOND_COLUMN_WIDTH, emailTextField.getPreferredSize().height);
		
		passwordLabel.setBounds(FIRST_COLUMN_X, SECOND_ROW_Y + 5, FIRST_COLUMN_WIDTH, passwordLabel.getPreferredSize().height);
		passwordTextField.setBounds(SECOND_COLUMN_X, SECOND_ROW_Y, SECOND_COLUMN_WIDTH, passwordTextField.getPreferredSize().height);

		signUpButton.setBounds(frameCenterX - (loginButton.getPreferredSize().width / 2) - (signUpButton.getPreferredSize().width / 2), THIRD_ROW_Y, signUpButton.getPreferredSize().width, signUpButton.getPreferredSize().height);
		loginButton.setBounds(frameCenterX - (loginButton.getPreferredSize().width / 2) + (signUpButton.getPreferredSize().width / 2), THIRD_ROW_Y, loginButton.getPreferredSize().width, loginButton.getPreferredSize().height);
		
		// aggiunta al frame
		
		formPanel.add(emailLabel);
		formPanel.add(emailTextField);
		formPanel.add(passwordLabel);
		formPanel.add(passwordTextField);
		
		getContentPane().add(formPanel);
		getContentPane().add(loginButton);
		getContentPane().add(signUpButton);
		
		pack();
		setVisible(true);
	}
}
