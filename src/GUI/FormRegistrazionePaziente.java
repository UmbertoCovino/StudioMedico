package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FormRegistrazionePaziente extends Frame { 
	private static final int MAX_FRAME_WIDTH = 99999,
			 				 EXTRA_FRAME_WIDTH = 100,
							 BUTTONS_GAP = 15;

	public FormRegistrazionePaziente(Frame parentFrame) {
		super("Registrazione paziente", parentFrame, "Sei sicuro di voler annullare la registrazione?");
		
		// dichiarazione elementi
		
		JLabel nomeLabel = new JLabel("Nome");
		JLabel cognomeLabel = new JLabel("Cognome");
		JLabel emailLabel = new JLabel("Email");
		JLabel passwordLabel = new JLabel("Password");
		JLabel codiceFiscaleLabel = new JLabel("Codice fiscale");
		
		JTextField nomeTextField = new JTextField();
		JTextField cognomeTextField = new JTextField();
		JTextField emailTextField = new JTextField();
		JTextField passwordTextField = new JTextField();
		JTextField codiceFiscaleTextField = new JTextField();
		
		JButton confirmButton = new JButton("Conferma");
		JButton cancelButton = new JButton("Annulla");
		
		// event handlers
		
		Frame thisFrame = this;
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 
				//JOptionPane.showMessageDialog(frame, "Messaggio.", "Attenzione", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
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
			   			.addComponent(nomeLabel)
		   				.addComponent(cognomeLabel)
		   				.addComponent(emailLabel)
		   				.addComponent(passwordLabel)
		   				.addComponent(codiceFiscaleLabel))
		   			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		   				.addComponent(nomeTextField)
		   				.addComponent(cognomeTextField)
		   				.addComponent(emailTextField)
		   				.addComponent(passwordTextField)
		   				.addComponent(codiceFiscaleTextField)))
		   		.addGroup(layout.createSequentialGroup()
		   			.addComponent(cancelButton)
		   			.addComponent(confirmButton))
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(nomeLabel)
					.addComponent(nomeTextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(cognomeLabel)
					.addComponent(cognomeTextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(emailLabel)
					.addComponent(emailTextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(passwordLabel)
					.addComponent(passwordTextField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(codiceFiscaleLabel)
					.addComponent(codiceFiscaleTextField))
				.addGap(BUTTONS_GAP)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(cancelButton)
					.addComponent(confirmButton))
		);
		
		// operazioni finali
		
		show(EXTRA_FRAME_WIDTH);
	}
}
