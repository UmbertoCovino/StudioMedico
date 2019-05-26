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
import Amministrazione.Disponibilita;
import Utenti.Medico;
import Visite.GUIControllerPrenotazioni;
import Visite.TipologiaVisita;

public class FormModificaPrenotazione extends Frame {
	private int prenotazioneId;
	
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

	public FormModificaPrenotazione(Frame parentFrame, int id, TipologiaVisita tipologiaVisita, Medico medico, Date giorno, Date ora) {
		super("Modifica prenotazione", parentFrame, "Sei sicuro di voler annullare la modifica alla prenotazione?");
		
		prenotazioneId = id;
		
		setExtraFrameWidth(100);
		
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
		updateTipologieVisite(GUIControllerPrenotazioni.getInstance().getTipologieVisite());
		
		// aggiunta event handlers
		addingEventHandlers();
		
		// posizionamento elementi
		elementsPositioning();
		
		// visualizzazione frame
		showFrame();
	}

	private void updateTipologieVisite(ArrayList<TipologiaVisita> tipologieVisite) {
		tipologiaVisitaComboBox.setModel(new DefaultComboBoxModel<TipologiaVisita>(tipologieVisite.toArray(new TipologiaVisita[tipologieVisite.size()])));
	}

	@Override
	protected void addingEventHandlers() {
		Frame thisFrame = this;
		
		tipologiaVisitaComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Medico> medici = GUIControllerPrenotazioni.getInstance().getMedici(((TipologiaVisita) tipologiaVisitaComboBox.getSelectedItem()).getNome());
				updateMedici(medici);
			}
		});
		
		medicoComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalendarioDisponibilita calendarioDisponibilita = GUIControllerPrenotazioni.getInstance().getCalendarioDisponibilita(
						((Medico) medicoComboBox.getSelectedItem()).getCodice(),
						((TipologiaVisita) tipologiaVisitaComboBox.getSelectedItem()).getNome());
				updateCalendarioDisponibilita(calendarioDisponibilita);
			}
		});
		
		calendarioComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateOrari(((Disponibilita) calendarioComboBox.getSelectedItem()));
			}
		});
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dataIsValid()) {
					GUIControllerPrenotazioni.getInstance().notifyData(
							prenotazioneId,
							(Date) calendarioComboBox.getSelectedItem(),
							(Date) orarioComboBox.getSelectedItem(),
							(TipologiaVisita) tipologiaVisitaComboBox.getSelectedItem(),
							(Medico) medicoComboBox.getSelectedItem());
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
		if (tipologiaVisitaComboBox.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Bisogna selezionare una tipologia di visita.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (medicoComboBox.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Bisogna selezionare un medico.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (calendarioComboBox.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Bisogna selezionare un giorno.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (orarioComboBox.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Bisogna selezionare un orario.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else
			return true;
		
		return false;
	}

	protected void updateMedici(ArrayList<Medico> medici) {
		medicoComboBox.setModel(new DefaultComboBoxModel<Medico>(medici.toArray(new Medico[medici.size()])));
	}

	protected void updateCalendarioDisponibilita(CalendarioDisponibilita calendarioDisponibilita) {
		calendarioComboBox.setModel(new DefaultComboBoxModel<Date>((calendarioDisponibilita.getOrari().toArray(new Date[calendarioDisponibilita.getOrari().size()]))));
	}

	protected void updateOrari(Disponibilita disponibilita) {
//		ArrayList<String> orari = new ArrayList<>();
//		
//		Duration diff = (disponibilita.getOraFine() - disponibilita.getOraInizio());
//		
//		for (int i = 0; i < diff.toMinutes() / 30; i = i + 30) {
//			date + 30;
//			orari.add(FramePaziente.TIME_SDF.format(date);
//		}
//		
//		orariComboBox.setModel(new DefaultComboBoxModel<String>(orari.toArray(new String[orari.size()])));
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
				.addGap(getButtonsGap())
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(cancelButton)
					.addComponent(confirmButton))
		);
		
		getRootPane().setDefaultButton(confirmButton);
	}
}
