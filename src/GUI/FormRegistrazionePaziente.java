package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Utenti.CodiceFiscaleGiaRegistratoException;
import Utenti.EmailGiaRegistrataException;
import Utenti.GUIControllerUtenti;

@SuppressWarnings("serial")
public class FormRegistrazionePaziente extends Frame {
	private JLabel nomeLabel;
	private JLabel cognomeLabel;
	private JLabel emailLabel;
	private JLabel passwordLabel;
	private JLabel codiceFiscaleLabel;
	private JTextField nomeTextField;
	private JTextField cognomeTextField;
	private JTextField emailTextField;
	private JPasswordField passwordTextField;
	private JTextField codiceFiscaleTextField;
	private JButton confirmButton;
	private JButton cancelButton;

	public FormRegistrazionePaziente(Frame parentFrame) {
		super("Registrazione paziente", parentFrame, "Sei sicuro di voler annullare la registrazione?");
		
		setExtraFrameWidth(100);
		
		// dichiarazione elementi
		nomeLabel = new JLabel("Nome");
		cognomeLabel = new JLabel("Cognome");
		emailLabel = new JLabel("Email");
		passwordLabel = new JLabel("Password");
		codiceFiscaleLabel = new JLabel("Codice fiscale");
		
		nomeTextField = new JTextField();
		cognomeTextField = new JTextField();
		emailTextField = new JTextField();
		passwordTextField = new JPasswordField();
		codiceFiscaleTextField = new JTextField();
		
		confirmButton = new JButton("Conferma");
		cancelButton = new JButton("Annulla");
		
		// aggiunta event handlers
		addingEventHandlers();
		
		// posizionamento elementi
		elementsPositioning();
		
		// visualizzazione frame
		showFrame();
	}

	@Override
	protected void addingEventHandlers() {
		Frame thisFrame = this;
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dataIsValid()) {
					if (JOptionPane.showConfirmDialog(thisFrame,
		            		"Confermi i dati immessi per la registrazione?",
		            		"Attenzione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						try {
							GUIControllerUtenti.getInstance().notifyData(nomeTextField.getText().trim(),
									cognomeTextField.getText().trim(),
									emailTextField.getText().trim(),
									String.valueOf(passwordTextField.getPassword()),
									codiceFiscaleTextField.getText().trim().toUpperCase());
							
							JOptionPane.showMessageDialog(thisFrame, "La registrazione è andata a buon fine! Adesso puoi utilizzare le tue nuove credenziali per accedere.", "Registrazione effettuata con successo!", JOptionPane.INFORMATION_MESSAGE);
							closeFrame();
						} catch (EmailGiaRegistrataException e1) {
							JOptionPane.showMessageDialog(thisFrame, "Spiacenti, l'indirizzo email inserito è già registrato nel sistema.", "Errore", JOptionPane.ERROR_MESSAGE);
						} catch (CodiceFiscaleGiaRegistratoException e2) {
							JOptionPane.showMessageDialog(thisFrame, "Spiacenti, il codice fiscale inserito è già registrato nel sistema.", "Errore", JOptionPane.ERROR_MESSAGE);
						}
		            }
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
			}
		});
	}

	protected boolean dataIsValid() {
		if (nomeTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Il campo nome non può essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (nomeTextField.getText().length() > 80) {
			JOptionPane.showMessageDialog(this, "Il nome non può essere più lungo di 80 caratteri.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (cognomeTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Il campo cognome non può essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (cognomeTextField.getText().length() > 80) {
			JOptionPane.showMessageDialog(this, "Il cognome non può essere più lungo di 80 caratteri.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (emailTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Il campo email non può essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (emailTextField.getText().length() > 80) {
			JOptionPane.showMessageDialog(this, "L'email non può essere più lunga di 80 caratteri.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (!isEmailValid(emailTextField.getText().trim())) {
			JOptionPane.showMessageDialog(this, "Il campo email deve contenere un indirizzo email valido.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (String.valueOf(passwordTextField.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(this, "Il campo password non può essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (String.valueOf(passwordTextField.getPassword()).length() < 6) {
			JOptionPane.showMessageDialog(this, "La password non può essere più corta di 6 caratteri.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (String.valueOf(passwordTextField.getPassword()).length() > 20) {
			JOptionPane.showMessageDialog(this, "La password non può essere più lunga di 20 caratteri.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (codiceFiscaleTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Il campo codice fiscale non può essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (codiceFiscaleTextField.getText().length() != 16) {
			JOptionPane.showMessageDialog(this, "Il codice fiscale deve essere esattamente di 16 caratteri.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else
			return true;
		
		return false;
	}

	public static boolean isEmailValid(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

		return email.matches(regex);
	}

	@Override
	protected void elementsPositioning() {
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
				.addGap(getButtonsGap())
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(cancelButton)
					.addComponent(confirmButton))
		);
		
		getRootPane().setDefaultButton(confirmButton);
	}
}
