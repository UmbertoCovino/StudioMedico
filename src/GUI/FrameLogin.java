package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FrameLogin extends Frame {
	private static final int MAX_FRAME_WIDTH = 99999,
			 				 EXTRA_FRAME_WIDTH = 100,
							 BUTTONS_GAP = 15;

	public FrameLogin() {
		super("Autenticazione", true);
		
		// dichiarazione elementi
						
		JLabel emailLabel = new JLabel("Email");
		JLabel passwordLabel = new JLabel("Password");
		
		JTextField emailTextField = new JTextField();
		JTextField passwordTextField = new JTextField();
		
		JButton loginButton = new JButton("Login");
		JButton signUpButton = new JButton("Registrati");
		
		// event handlers
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 
				//JOptionPane.showMessageDialog(frame, "Messaggio.", "Attenzione", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 
			}
		});
		
		// posizionamento elementi
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.CENTER)
		   		.addGroup(layout.createSequentialGroup()
		   			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		   				.addComponent(emailLabel)
		   				.addComponent(passwordLabel))
		   			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		   				.addComponent(emailTextField)
		   				.addComponent(passwordTextField)))
		   		.addGroup(layout.createSequentialGroup()
		   			.addComponent(signUpButton)
		   			.addComponent(loginButton))
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(emailLabel)
					.addComponent(emailTextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(passwordLabel)
					.addComponent(passwordTextField))
				.addGap(BUTTONS_GAP)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(signUpButton)
					.addComponent(loginButton))
		);
		
		// operazioni finali
		
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setSize(new Dimension(getWidth() + EXTRA_FRAME_WIDTH, getHeight()));
		setVisible(true);
	}
}
