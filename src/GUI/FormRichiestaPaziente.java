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

import Utenti.GUIControllerUtenti;

public class FormRichiestaPaziente extends Frame {
	private JLabel codiceFiscaleLabel;
	private JTextField codiceFiscaleTextField;
	private JButton confirmButton;
	private JButton cancelButton;

	public FormRichiestaPaziente(Frame parentFrame) {
		super("Ricerca paziente", parentFrame);
		
		setExtraFrameWidth(100);
		
		// dichiarazione elementi
		codiceFiscaleLabel = new JLabel("Codice fiscale");
		
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
					GUIControllerUtenti.getInstance().getPaziente(codiceFiscaleTextField.getText().trim());
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
		if (codiceFiscaleTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Il campo codice fiscale non può essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (codiceFiscaleTextField.getText().length() != 16) {
			JOptionPane.showMessageDialog(this, "Il codice fiscale deve essere esattamente di 16 caratteri.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else
			return true;
		
		return false;
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
		   				.addComponent(codiceFiscaleLabel))
		   			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		   				.addComponent(codiceFiscaleTextField)))
		   		.addGroup(layout.createSequentialGroup()
		   			.addComponent(cancelButton)
		   			.addComponent(confirmButton))
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
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
