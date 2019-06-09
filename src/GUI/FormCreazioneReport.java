package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Amministrazione.GUIControllerAmministrazione;
import Utenti.Medico;

@SuppressWarnings("serial")
public class FormCreazioneReport extends Frame {
	public static final String[] TIPOLOGIE_REPORT = new String[]{"Elenco di visite effettuate ordinate per medico",
																 "Elenco di visite effettuate ordinate per giorno con indicazione del medico", 
																 "Elenco dei medici ordinati per numero di visite",
																 "Elenco delle tipologie di visite ordinate per numerosità"};
	
	private JLabel tipologiaReportLabel;
	private JLabel mediciLabel;
	private JComboBox<Object> tipologiaReportComboBox;
	private JComboBox<Object> medicoComboBox;
	private JButton confirmButton;
	private JButton cancelButton;

	public FormCreazioneReport(Frame parentFrame) {
		super("Creazione report", parentFrame);
		
		// dichiarazione elementi
		tipologiaReportLabel = new JLabel("Tipologia report");
		mediciLabel = new JLabel("Medico");
		
		tipologiaReportComboBox = new JComboBox<>();
		medicoComboBox = new JComboBox<>();
		
		confirmButton = new JButton("Conferma");
		cancelButton = new JButton("Annulla");

		fillComboBox(tipologiaReportComboBox, TIPOLOGIE_REPORT);
		
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
			private Dimension savedSize;

			public void actionPerformed(ActionEvent e) {
				if (tipologiaReportComboBox.getSelectedIndex() == 2) {
					ArrayList<Medico> medici = GUIControllerAmministrazione.getInstance().getMedici();
					updateMedici(medici);
					
					savedSize = thisFrame.getMinimumSize();
					mediciLabel.setVisible(true);
					medicoComboBox.setVisible(true);
					refreshFrameDims();
				} else {
					if (mediciLabel.isVisible() && medicoComboBox.isVisible()) {
						mediciLabel.setVisible(false);
						medicoComboBox.setVisible(false);
						thisFrame.setMinimumSize(savedSize);
						refreshFrameDims();
					}
				}
			}
		});
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dataIsValid()) {
					if (tipologiaReportComboBox.getSelectedIndex() == 2) {
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
		if (tipologiaReportComboBox.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Il campo tipologia report non può essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (tipologiaReportComboBox.getSelectedIndex() == 2 && medicoComboBox.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Il campo medico non può essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else
			return true;
		
		return false;
	}
	
	protected void updateMedici(ArrayList<Medico> medici) {
		fillComboBox(medicoComboBox, castArrayList(medici));
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
