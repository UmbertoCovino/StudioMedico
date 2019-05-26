package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Utenti.GUIControllerUtenti;
import Utenti.Medico;
import Utenti.Paziente;
import Utenti.Utente;

public class FrameLogin extends Frame {
	private JLabel emailLabel;
	private JLabel passwordLabel;
	private JTextField emailTextField;
	private JPasswordField passwordTextField;
	private JButton loginButton;
	private JButton signUpButton;

	public FrameLogin() {
		super("Autenticazione", true);
		
		setExtraFrameWidth(100);
		
		// dichiarazione elementi
		emailLabel = new JLabel("Email");
		passwordLabel = new JLabel("Password");
		
		emailTextField = new JTextField();
		passwordTextField = new JPasswordField();
		
		loginButton = new JButton("Login");
		signUpButton = new JButton("Registrati");
		
		// aggiunta event handlers
		addingEventHandlers();
		
		// posizionamento elementi
		elementsPositioning();
		
		// visualizzazione frame
		showFrame();
	}

	protected void addingEventHandlers() {
		Frame thisFrame = this;
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dataIsValid()) {
					Utente utente = GUIControllerUtenti.getInstance().notifyCredentials(emailTextField.getText().trim(), String.valueOf(passwordTextField.getPassword()));
					
					if (utente != null) {
						if (utente instanceof Medico) {
							GUIControllerUtenti.getInstance().createFrameMedico((Medico) utente);
						} else if (utente instanceof Paziente) {
							GUIControllerUtenti.getInstance().createFramePaziente((Paziente) utente);
						} else if (utente.isAdmin()) {
							GUIControllerUtenti.getInstance().createFrameProprietario(utente);
						}
					} else {
						JOptionPane.showMessageDialog(thisFrame, "Le credenziali immesse non sono corrette.", "Attenzione", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIControllerUtenti.getInstance().createFormRegistrazionePaziente(thisFrame);
			}
		});
	}

	protected boolean dataIsValid() {
		if (emailTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Il campo email non può essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (!FormRegistrazionePaziente.isEmailValid(emailTextField.getText())) {
			JOptionPane.showMessageDialog(this, "Il campo email deve contenere un indirizzo email valido.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (String.valueOf(passwordTextField.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(this, "Il campo password non può essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
//		} else if (String.valueOf(passwordTextField.getPassword()).length() < 6) {
//			JOptionPane.showMessageDialog(this, "La password non può essere più corta di 6 caratteri.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else
			return true;
		
		return false;
	}

	protected void elementsPositioning() {
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
				.addGap(getButtonsGap())
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(signUpButton)
					.addComponent(loginButton))
		);
		
		getRootPane().setDefaultButton(loginButton);
	}
}
