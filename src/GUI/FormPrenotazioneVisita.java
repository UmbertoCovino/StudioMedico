package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Amministrazione.CalendarioDisponibilita;
import Amministrazione.Disponibilita;
import Amministrazione.DisponibilitaGiornaliera;
import Utenti.Medico;
import Visite.GUIControllerPrenotazioni;
import Visite.TipologiaVisita;

class Time extends java.util.Date {

	@Override
	public String toString() {
		return GUI.FramePaziente.TIME_SDF.format(this);
	}
}

public class FormPrenotazioneVisita extends Frame {
	private JLabel tipologiaVisitaLabel;
	private JLabel medicoLabel;
	private JLabel calendarioLabel;
	private JLabel orarioLabel;
	private JComboBox<TipologiaVisita> tipologiaVisitaComboBox;
	private JComboBox<Medico> medicoComboBox;
	private JComboBox<DisponibilitaGiornaliera> calendarioComboBox;
	private JComboBox<Time> orarioComboBox;
	private JButton confirmButton;
	private JButton cancelButton;

	public FormPrenotazioneVisita(Frame parentFrame) {
		super("Prenotazione visita", parentFrame, "Sei sicuro di voler annullare la prenotazione alla visita?");
		
		// dichiarazione elementi
		tipologiaVisitaLabel = new JLabel("Tipologia visita");
		medicoLabel = new JLabel("Medico");
		calendarioLabel = new JLabel("Calendario disponibilit�");
		orarioLabel = new JLabel("Orari disponibili per il giorno selezionato");
		
		tipologiaVisitaComboBox = new JComboBox<>();
		medicoComboBox = new JComboBox<>();
		calendarioComboBox = new JComboBox<>();
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
				updateOrari(((DisponibilitaGiornaliera) calendarioComboBox.getSelectedItem()));
			}
		});
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dataIsValid()) {
					GUIControllerPrenotazioni.getInstance().notifyData(
							((DisponibilitaGiornaliera) calendarioComboBox.getSelectedItem()).getGiorno(),
							(java.util.Date) orarioComboBox.getSelectedItem(),
							(TipologiaVisita) tipologiaVisitaComboBox.getSelectedItem(),
							(Medico) medicoComboBox.getSelectedItem());

					JOptionPane.showMessageDialog(thisFrame, "La prenotazione � stato aggiunta con successo!", "Prenotazione aggiunta", JOptionPane.INFORMATION_MESSAGE);
					closeFrame();
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
		calendarioComboBox.setModel(new DefaultComboBoxModel<DisponibilitaGiornaliera>((calendarioDisponibilita.getOrari().toArray(new DisponibilitaGiornaliera[calendarioDisponibilita.getOrari().size()]))));
	}

	protected void updateOrari(Disponibilita disponibilita) {
		ArrayList<Time> orari = new ArrayList<>();

		long minutesFromInizioToFine = ((disponibilita.getOraFine().getTime() - disponibilita.getOraInizio().getTime()) / 1000) / 60;
		long numberOfHalfHours = minutesFromInizioToFine / 30;

		Calendar halfHours = Calendar.getInstance();
		halfHours.setTime(disponibilita.getOraInizio());

		for (int i = 0; i < numberOfHalfHours; i++) {
			orari.add((Time) halfHours.getTime());
//			orari.add(FramePaziente.TIME_SDF.format(halfHours.getTime());

			halfHours.set(Calendar.MINUTE, halfHours.get(Calendar.MINUTE) + 30);
		}

		orarioComboBox.setModel(new DefaultComboBoxModel<Time>(orari.toArray(new Time[orari.size()])));
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
