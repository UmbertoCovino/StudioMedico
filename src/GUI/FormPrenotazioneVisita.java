package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Amministrazione.CalendarioDisponibilita;
import Utenti.Medico;
import Visite.GUIControllerPrenotazioni;
import Visite.TipologiaVisita;

public class FormPrenotazioneVisita extends Frame { 
	private static final int MAX_FRAME_WIDTH = 99999,
			 				 EXTRA_FRAME_WIDTH = 100,
							 BUTTONS_GAP = 15;
	private JLabel tipologiaVisitaLabel;
	private JLabel medicoLabel;
	private JLabel calendarioLabel;
	private JLabel orarioLabel;
	private JComboBox<TipologiaVisita> tipologiaVisitaComboBox;
	private JComboBox<Medico> medicoComboBox;
	private JComboBox<Date> calendarioComboBox;
	private JComboBox<Date> orarioComboBox;
	private JButton confirmButton;
	private JButton cancelButton;

	public FormPrenotazioneVisita(Frame parentFrame) {
		super("Prenotazione visita", parentFrame, "Sei sicuro di voler annullare la prenotazione alla visita?");
		
		// dichiarazione elementi
		tipologiaVisitaLabel = new JLabel("Tipologia visita");
		medicoLabel = new JLabel("Medico");
		calendarioLabel = new JLabel("Calendario disponibilità");
		orarioLabel = new JLabel("Orari disponibili per il giorno selezionato");
		
		tipologiaVisitaComboBox = new JComboBox<>();
		medicoComboBox = new JComboBox<>();
		calendarioComboBox = new JComboBox<Date>();
		orarioComboBox = new JComboBox<>();
		
		confirmButton = new JButton("Conferma");
		cancelButton = new JButton("Annulla");
		
		// caricamento dati in tipologiaVisitaComboBox
//		updateTipologieVisite(GUIControllerPrenotazioni.getInstance().getTipologieVisite());
		
		// aggiunta event handlers
		addingEventHandlers();
		
		// posizionamento elementi
		elementsPositioning();
		
		// visualizzazione frame
		showFrame(EXTRA_FRAME_WIDTH);
	}

	private void updateTipologieVisite(ArrayList<TipologiaVisita> tipologieVisite) {
//		tipologiaVisitaComboBox.setModel(new DefaultComboBoxModel<TipologiaVisita>(tipologieVisite.toArray(new TipologiaVisita[tipologieVisite.size()])));
	}

	@Override
	protected void addingEventHandlers() {
		Frame thisFrame = this;
		
		tipologiaVisitaComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				ArrayList<Medico> medici = GUIControllerPrenotazioni.getInstance().getMedici(((TipologiaVisita) tipologiaVisitaComboBox.getSelectedItem()).getNome());
//				updateMedici(medici);
			}
		});
		
		medicoComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				CalendarioDisponibilita calendarioDisponibilita = GUIControllerPrenotazioni.getInstance().getCalendarioDisponibilita(
//						((Medico) medicoComboBox.getSelectedItem()).getCodice(),
//						((TipologiaVisita) tipologiaVisitaComboBox.getSelectedItem()).getNome());
//				updateCalendarioDisponibilita(calendarioDisponibilita);
			}
		});
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dataIsValid()) {
//					GUIControllerPrenotazioni.getInstance().notifyData(
//							giorno,
//							ora,
//							tipologiaVisita,
//							medico);
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
//		if (tipologiaVisitaComboBox.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(this, "Il campo nome non può essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
//		} else if (medicoComboBox.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(this, "Il campo cognome non può essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
//		} else if (calendarioCalendar.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(this, "Il campo email non può essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
//		} else if (orarioComboBox.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(this, "Il campo codice fiscale non può essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
//		} else
//			return true;
//		
		return false;
	}

	protected void updateMedici(ArrayList<Medico> medici) {
//		medicoComboBox.setModel(new DefaultComboBoxModel<Medico>(medici.toArray(new Medico[medici.size()])));
	}

	protected void updateCalendarioDisponibilita(CalendarioDisponibilita calendarioDisponibilita) {
//		calendarioComboBox.setModel(new DefaultComboBoxModel<Date>((calendarioDisponibilita.getOrari().toArray(new Date[calendarioDisponibilita.getOrari().size()])));
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
			   			.addComponent(tipologiaVisitaLabel)
		   				.addComponent(medicoLabel)
		   				.addComponent(calendarioLabel)
		   				.addComponent(orarioLabel))
		   			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		   				.addComponent(tipologiaVisitaComboBox)
		   				.addComponent(medicoComboBox)
		   				.addComponent(calendarioComboBox)
		   				.addComponent(orarioComboBox)))
		   		.addGroup(layout.createSequentialGroup()
		   			.addComponent(cancelButton)
		   			.addComponent(confirmButton))
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(tipologiaVisitaLabel)
					.addComponent(tipologiaVisitaComboBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(medicoLabel)
					.addComponent(medicoComboBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(calendarioLabel)
					.addComponent(calendarioComboBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(orarioLabel)
					.addComponent(orarioComboBox))
				.addGap(BUTTONS_GAP)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(cancelButton)
					.addComponent(confirmButton))
		);
	}
}
