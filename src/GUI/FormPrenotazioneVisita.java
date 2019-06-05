package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
import Visite.Prenotazione;
import Visite.TipologiaVisita;

class Time extends java.util.Date {

	public Time(java.util.Date d) {
		super(d.getYear(), d.getMonth(), d.getDay(), d.getHours(), d.getMinutes(), d.getSeconds());
	}

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
		calendarioLabel = new JLabel("Calendario disponibilità");
		orarioLabel = new JLabel("Orari disponibili per il giorno selezionato");
		
		tipologiaVisitaComboBox = new JComboBox<>();
		medicoComboBox = new JComboBox<>();
		calendarioComboBox = new JComboBox<>();
		orarioComboBox = new JComboBox<>();
		
		confirmButton = new JButton("Conferma");
		cancelButton = new JButton("Annulla");
		
		tipologiaVisitaComboBox.setEnabled(false);
		medicoComboBox.setEnabled(false);
		calendarioComboBox.setEnabled(false);
		orarioComboBox.setEnabled(false);

		confirmButton.setEnabled(false);
		
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
		tipologiaVisitaComboBox.setSelectedIndex(-1);
		tipologiaVisitaComboBox.setEnabled(true);
	}

	@Override
	protected void addingEventHandlers() {
		Frame thisFrame = this;
		
		tipologiaVisitaComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Medico> medici = GUIControllerPrenotazioni.getInstance().getMedici(((TipologiaVisita) tipologiaVisitaComboBox.getSelectedItem()).getNome());
				updateMedici(medici);
				medicoComboBox.setSelectedIndex(-1);
				medicoComboBox.setEnabled(true);
				
				medicoComboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CalendarioDisponibilita calendarioDisponibilita = GUIControllerPrenotazioni.getInstance().getCalendarioDisponibilita(
								((Medico) medicoComboBox.getSelectedItem()).getCodice());
						ArrayList<Prenotazione> prenotazioniPerMedico = GUIControllerPrenotazioni.getInstance().getPrenotazioni(
								((Medico) medicoComboBox.getSelectedItem()).getCodice(),
								((TipologiaVisita) tipologiaVisitaComboBox.getSelectedItem()).getNome());
						
						updateCalendarioDisponibilita(calendarioDisponibilita, prenotazioniPerMedico);
						calendarioComboBox.setSelectedIndex(-1);
						calendarioComboBox.setEnabled(true);
				
						calendarioComboBox.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								updateOrari(((DisponibilitaGiornaliera) calendarioComboBox.getSelectedItem()),
										filterPrenotationsByDay(prenotazioniPerMedico, ((DisponibilitaGiornaliera) calendarioComboBox.getSelectedItem()).getGiorno()));
								orarioComboBox.setSelectedIndex(-1);
								orarioComboBox.setEnabled(true);
								confirmButton.setEnabled(true);
							}
						});
					}
				});
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

					JOptionPane.showMessageDialog(thisFrame, "La prenotazione è stata aggiunta con successo!", "Prenotazione aggiunta", JOptionPane.INFORMATION_MESSAGE);
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

	private ArrayList<Prenotazione> filterPrenotationsByDay(ArrayList<Prenotazione> prenotazioniPerMedico, Date giorno) {
		ArrayList<Prenotazione> prenotazioniGiornaliere = new ArrayList<>();
		
		for (Prenotazione prenotazione: prenotazioniGiornaliere)
			if (prenotazione.getGiorno().getDay() == giorno.getDay())
				prenotazioniGiornaliere.add(prenotazione);
		
		return prenotazioniGiornaliere;
	}

	protected boolean dataIsValid() {
		if (tipologiaVisitaComboBox.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Bisogna selezionare una tipologia di visita.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (medicoComboBox.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Bisogna selezionare un medico.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (calendarioComboBox.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Bisogna selezionare un giorno.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (orarioComboBox.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Bisogna selezionare un orario.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else
			return true;
		
		return false;
	}

	protected void updateMedici(ArrayList<Medico> medici) {
		medicoComboBox.setModel(new DefaultComboBoxModel<Medico>(medici.toArray(new Medico[medici.size()])));
	}

	protected void updateCalendarioDisponibilita(CalendarioDisponibilita calendarioDisponibilita, ArrayList<Prenotazione> prenotazioni) {
		// disponibilità filtrate sulla base delle prenotazioni: se un giorno è saturo di prenotazioni, esso non viene aggiunto alla ComboBox
		// quindi per ogni disponibilità nel calendario controllo se in quel giorno il numero di pren. è == al maxNumVisite 
		ArrayList<DisponibilitaGiornaliera> disponibilitaFiltrate = new ArrayList<>(calendarioDisponibilita.getOrari());
		
		for (DisponibilitaGiornaliera disponibilita: disponibilitaFiltrate)
			if (getNumberOfPrenotationsInThisDay(prenotazioni, disponibilita.getGiorno()) == disponibilita.getMaxNumVisite())
				disponibilitaFiltrate.remove(disponibilita);
		
		calendarioComboBox.setModel(new DefaultComboBoxModel<DisponibilitaGiornaliera>(disponibilitaFiltrate.toArray(new DisponibilitaGiornaliera[calendarioDisponibilita.getOrari().size()])));
	}
	
	private int getNumberOfPrenotationsInThisDay(ArrayList<Prenotazione> prenotazioni, Date giorno) {
		int numberOfPrenotationsInThisDay = 0;
		
		for (Prenotazione prenotazione: prenotazioni)
			if (prenotazione.getGiorno().getDay() == giorno.getDay());
				numberOfPrenotationsInThisDay++;
		
		return numberOfPrenotationsInThisDay;
	}

	protected void updateOrari(Disponibilita disponibilita, ArrayList<Prenotazione> prenotazioni) {
		ArrayList<Time> orari = new ArrayList<>();

		long minutesFromInizioToFine = ((disponibilita.getOraFine().getTime() - disponibilita.getOraInizio().getTime()) / 1000) / 60;
		long numberOfHalfHours = minutesFromInizioToFine / 30;

		Calendar halfHours = Calendar.getInstance();
		halfHours.setTime(disponibilita.getOraInizio());

		for (int i = 0; i < numberOfHalfHours; i++) {
			orari.add(new Time(halfHours.getTime()));
//			orari.add(FramePaziente.TIME_SDF.format(halfHours.getTime());

			halfHours.set(Calendar.MINUTE, halfHours.get(Calendar.MINUTE) + 30);
		}
		
		// filtro l'array di halfHours sulla base delle prenotazioni esistenti; per ogni mezza ora in "orari" controllo se c'è già una prenotazione
		// in quello stesso orario: in tal caso rimuovo lo slot "mezzaorale" da "orari"
		for (Time orario: orari)
			for (Prenotazione prenotazione: prenotazioni)
				if (prenotazione.getGiorno().getHours() == orario.getHours() && prenotazione.getGiorno().getMinutes() == orario.getMinutes())
					orari.remove(orario);

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
