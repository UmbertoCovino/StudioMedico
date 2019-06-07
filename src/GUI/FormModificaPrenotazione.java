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

@SuppressWarnings({"serial", "deprecation"})
public class FormModificaPrenotazione extends Frame {
	private int prenotazioneId;
	private TipologiaVisita prenotazioneTipologiaVisita;
	private Medico prenotazioneMedico;
	private Date prenotazioneGiorno;
	private Date prenotazioneOra;
	
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
	
	protected ArrayList<Prenotazione> prenotazioniPerMedico;

	public FormModificaPrenotazione(Frame parentFrame, int id, TipologiaVisita tipologiaVisita, Medico medico, Date giorno, Date ora) {
		super("Modifica prenotazione", parentFrame, "Sei sicuro di voler annullare la modifica alla prenotazione?");
		
		prenotazioneId = id;
		prenotazioneTipologiaVisita = tipologiaVisita;
		prenotazioneMedico = medico;
		prenotazioneGiorno = giorno;
		prenotazioneOra = ora;
		
		setExtraFrameWidth(100);
		
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
		
		// carico i dati della prenotazione da modificare
		settingPrenotazioneData();
		
		// posizionamento elementi
		elementsPositioning();
		
		// visualizzazione frame
		showFrame();
	}

	private void updateTipologieVisite(ArrayList<TipologiaVisita> tipologieVisite) {
		tipologiaVisitaComboBox.setModel(new DefaultComboBoxModel<TipologiaVisita>(tipologieVisite.toArray(new TipologiaVisita[tipologieVisite.size()])));
		tipologiaVisitaComboBox.setSelectedItem(null);
		tipologiaVisitaComboBox.setEnabled(true);
	}

	private void settingPrenotazioneData() {
		for (int i = 0; i < tipologiaVisitaComboBox.getModel().getSize(); i++)
            if (tipologiaVisitaComboBox.getModel().getElementAt(i).getId() == prenotazioneTipologiaVisita.getId())
            	tipologiaVisitaComboBox.setSelectedItem(tipologiaVisitaComboBox.getItemAt(i));
        
		for (int i = 0; i < medicoComboBox.getModel().getSize(); i++)
            if (medicoComboBox.getModel().getElementAt(i).getCodice() == prenotazioneMedico.getCodice())
            	medicoComboBox.setSelectedItem(medicoComboBox.getItemAt(i));
		
		for (int i = 0; i < calendarioComboBox.getModel().getSize(); i++)
            if (calendarioComboBox.getModel().getElementAt(i).getGiorno().equals(prenotazioneGiorno))
            	calendarioComboBox.setSelectedItem(calendarioComboBox.getItemAt(i));
		
		for (int i = 0; i < orarioComboBox.getModel().getSize(); i++)
            if (orarioComboBox.getModel().getElementAt(i).getHours() == prenotazioneOra.getHours() && orarioComboBox.getModel().getElementAt(i).getMinutes() == prenotazioneOra.getMinutes())
            	orarioComboBox.setSelectedItem(orarioComboBox.getItemAt(i));
	}

	@Override
	protected void addingEventHandlers() {
		Frame thisFrame = this;
		
		tipologiaVisitaComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Medico> medici = GUIControllerPrenotazioni.getInstance().getMedici(((TipologiaVisita) tipologiaVisitaComboBox.getSelectedItem()).getNome());
				updateMedici(medici);
				
				confirmButton.setEnabled(false);
				medicoComboBox.setSelectedItem(null);
				medicoComboBox.setEnabled(true);
				((DefaultComboBoxModel<DisponibilitaGiornaliera>) calendarioComboBox.getModel()).removeAllElements();
				calendarioComboBox.setEnabled(false);
				((DefaultComboBoxModel<Time>) orarioComboBox.getModel()).removeAllElements();
				orarioComboBox.setEnabled(false);
			}
		});
				
		medicoComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (medicoComboBox.getSelectedItem() != null) {
					CalendarioDisponibilita calendarioDisponibilita = GUIControllerPrenotazioni.getInstance().getCalendarioDisponibilita(
							((Medico) medicoComboBox.getSelectedItem()).getCodice());
					prenotazioniPerMedico = GUIControllerPrenotazioni.getInstance().getPrenotazioni(
							((Medico) medicoComboBox.getSelectedItem()).getCodice(),
							((TipologiaVisita) tipologiaVisitaComboBox.getSelectedItem()).getNome());
					
					updateCalendarioDisponibilita(calendarioDisponibilita, prenotazioniPerMedico);

					confirmButton.setEnabled(false);
					calendarioComboBox.setSelectedItem(null);
					calendarioComboBox.setEnabled(true);
					((DefaultComboBoxModel<Time>) orarioComboBox.getModel()).removeAllElements();
					orarioComboBox.setEnabled(false);
				}
			}
		});
		
		calendarioComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (calendarioComboBox.getSelectedItem() != null) {
					updateOrari(((DisponibilitaGiornaliera) calendarioComboBox.getSelectedItem()),
							filterPrenotationsByDay(prenotazioniPerMedico, ((DisponibilitaGiornaliera) calendarioComboBox.getSelectedItem()).getGiorno()));

					confirmButton.setEnabled(false);
					orarioComboBox.setSelectedItem(null);
					orarioComboBox.setEnabled(true);
				}
			}
		});
		
		orarioComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (orarioComboBox.getSelectedItem() != null) {
					confirmButton.setEnabled(true);
				}
			}
		});
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dataIsValid()) {
					GUIControllerPrenotazioni.getInstance().notifyData(
							prenotazioneId,
							((DisponibilitaGiornaliera) calendarioComboBox.getSelectedItem()).getGiorno(),
							(Date) orarioComboBox.getSelectedItem(),
							(TipologiaVisita) tipologiaVisitaComboBox.getSelectedItem(),
							(Medico) medicoComboBox.getSelectedItem());

					JOptionPane.showMessageDialog(thisFrame, "La prenotazione è stata modificata con successo!", "Prenotazione modificata", JOptionPane.INFORMATION_MESSAGE);
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
		
		for (Prenotazione prenotazione: prenotazioniPerMedico)
			if (prenotazione.getGiorno().getDay() == giorno.getDay())
				prenotazioniGiornaliere.add(prenotazione);
		
		return prenotazioniGiornaliere;
	}

	// inutile però per sicurezza è meglio tenerlo
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
		ArrayList<Time> orariFiltrati = new ArrayList<>(orari);
		for (Time orario: orari)
			for (Prenotazione prenotazione: prenotazioni)
				if ((prenotazione.getOra().getHours() == orario.getHours() && prenotazione.getOra().getMinutes() == orario.getMinutes())
						&& !(prenotazione.getOra().getHours() == prenotazioneOra.getHours() && prenotazione.getOra().getMinutes() == prenotazioneOra.getMinutes()))
					orariFiltrati.remove(orario);

		orarioComboBox.setModel(new DefaultComboBoxModel<Time>(orariFiltrati.toArray(new Time[orariFiltrati.size()])));
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
