package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Amministrazione.GUIControllerAmministrazione;
import Utenti.GUIControllerUtenti;
import Utenti.Medico;
import Visite.GUIControllerPrenotazioni;
import Visite.TipologiaVisita;

public class FormCreazioneReport extends Frame {
	private static final String[] TIPOLOGIE_REPORT = new String[]{"Elenco di visite effettuate ordinate per medico",
																  "Elenco di visite effettuate ordinate per giorno con indicazione del medico", 
																  "Elenco dei medici ordinati per numero di visite",
																  "Elenco delle tipologie di visite ordinate per numerosità"};
	
	private JLabel tipologiaReportLabel;
	private JLabel mediciLabel;
	private JComboBox<String> tipologiaReportComboBox;
	private JComboBox<Medico> medicoComboBox;
	private JButton confirmButton;
	private JButton cancelButton;

	public FormCreazioneReport(Frame parentFrame) {
		super("Creazione report", parentFrame);
		
		// dichiarazione elementi
		tipologiaReportLabel = new JLabel("Tipologia report");
		mediciLabel = new JLabel("Medico");
		
		tipologiaReportComboBox = new JComboBox<>(TIPOLOGIE_REPORT);
		medicoComboBox = new JComboBox<>();
		
		confirmButton = new JButton("Conferma");
		cancelButton = new JButton("Annulla");
		
		mediciLabel.setVisible(false);
		medicoComboBox.setVisible(false);
		
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
		
		tipologiaReportComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tipologiaReportComboBox.getSelectedIndex() == 1) {
					ArrayList<Medico> medici = GUIControllerAmministrazione.getInstance().getMedici();
					updateMedici(medici);
					
					mediciLabel.setVisible(true);
					medicoComboBox.setVisible(true);
					pack();
				} else {
					if (mediciLabel.isVisible() && medicoComboBox.isVisible()) {
						mediciLabel.setVisible(false);
						medicoComboBox.setVisible(false);
						pack();
					}
				}
			}
		});
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dataIsValid()) {
					if (tipologiaReportComboBox.getSelectedIndex() == 1) {
						GUIControllerAmministrazione.getInstance().createReport((String) tipologiaReportComboBox.getSelectedItem(), (Medico) medicoComboBox.getSelectedItem());
					} else {
						GUIControllerAmministrazione.getInstance().createReport((String) tipologiaReportComboBox.getSelectedItem());
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
		if (tipologiaReportComboBox.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Il campo tipologia report non può essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (tipologiaReportComboBox.getSelectedIndex() == 1 && medicoComboBox.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Il campo medico non può essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else
			return true;
		
		return false;
	}
	
	protected void updateMedici(ArrayList<Medico> medici) {
		medicoComboBox.setModel(new DefaultComboBoxModel<Medico>(medici.toArray(new Medico[medici.size()])));
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
		   				.addComponent(tipologiaReportLabel)
		   				.addComponent(mediciLabel))
		   			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		   				.addComponent(tipologiaReportComboBox)
		   				.addComponent(medicoComboBox)))
		   		.addGroup(layout.createSequentialGroup()
		   			.addComponent(cancelButton)
		   			.addComponent(confirmButton))
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(tipologiaReportLabel)
					.addComponent(tipologiaReportComboBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(mediciLabel)
						.addComponent(medicoComboBox))
				.addGap(getButtonsGap())
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(cancelButton)
					.addComponent(confirmButton))
		);
		
		getRootPane().setDefaultButton(confirmButton);
	}
}
