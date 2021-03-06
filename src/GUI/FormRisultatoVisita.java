package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import Visite.GUIControllerPrenotazioni;
import Visite.Prenotazione;

@SuppressWarnings("serial")
public class FormRisultatoVisita extends Frame {
	private Prenotazione prenotazione;
	
	private JLabel diagnosiLabel;
	private JLabel terapiaLabel;
	private JTextArea diagnosiTextArea;
	private JTextArea terapiaTextArea;
	private JScrollPane diagnosiScrollPane;
	private JScrollPane terapiaScrollPane;
	private JButton confirmButton;
	private JButton cancelButton;

	public FormRisultatoVisita(Frame parentFrame, Prenotazione prenotazione) {
		super("Registrazione risultato visita", parentFrame);
		
		this.prenotazione = prenotazione;
		
		setExtraFrameWidth(100);
		
		// dichiarazione elementi
		diagnosiLabel = new JLabel("Diagnosi");
		terapiaLabel = new JLabel("Terapia");
		
		diagnosiTextArea = new JTextArea();
		terapiaTextArea = new JTextArea();
		
		diagnosiScrollPane = new JScrollPane(diagnosiTextArea);
		terapiaScrollPane = new JScrollPane(terapiaTextArea);
		
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
		            		"Confermi la registrazione della visita?",
		            		"Attenzione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						GUIControllerPrenotazioni.getInstance().notifyData(prenotazione, diagnosiTextArea.getText().trim(), terapiaTextArea.getText().trim());

						JOptionPane.showMessageDialog(thisFrame, "Il risultato della visita � stato registrato con successo!", "Risultato visita registrato", JOptionPane.INFORMATION_MESSAGE);
						closeFrame();
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
		if (diagnosiTextArea.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Il campo diagnosi non pu� essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (diagnosiTextArea.getText().length() > 200) {
			JOptionPane.showMessageDialog(this, "La diagnosi non pu� essere pi� lunga di 200 caratteri.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (terapiaTextArea.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Il campo terapia non pu� essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (terapiaTextArea.getText().length() > 200) {
			JOptionPane.showMessageDialog(this, "La terapia non pu� essere pi� lunga di 200 caratteri.", "Attenzione", JOptionPane.WARNING_MESSAGE);
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
		   				.addComponent(diagnosiLabel)
		   				.addComponent(terapiaLabel))
		   			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		   				.addComponent(diagnosiScrollPane)
		   				.addComponent(terapiaScrollPane)))
		   		.addGroup(layout.createSequentialGroup()
		   			.addComponent(cancelButton)
		   			.addComponent(confirmButton))
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(diagnosiLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(diagnosiScrollPane))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(terapiaLabel)
					.addComponent(terapiaScrollPane))
				.addGap(getButtonsGap())
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(cancelButton)
					.addComponent(confirmButton))
		);
		
		getRootPane().setDefaultButton(confirmButton);
	}
}
