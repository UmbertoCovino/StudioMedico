package Visite;

import Persistenza.GestoreDatabase;
import java.util.Date;
import Utenti.Medico;
import Utenti.Paziente;

import java.util.ArrayList;
import java.util.Calendar;

import Amministrazione.CalendarioDisponibilita;
import Amministrazione.Disponibilita;
import Amministrazione.DisponibilitaGiornaliera;

public class GestorePrenotazioni {
	private static GestorePrenotazioni instance;
	private GestoreDatabase gestoreDB;
	private Prenotazione prenotazione;

	private GestorePrenotazioni() {
		gestoreDB = GestoreDatabase.getInstance();
	}

	public static GestorePrenotazioni getInstance() {
		if(instance == null)
			instance = new GestorePrenotazioni();
		return instance;
	}

	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}

	public void createPrenotazione(Date giorno, Date ora, TipologiaVisita tipologiaVisita, Medico medico, Paziente paziente) {
		Prenotazione prenotazione = new Prenotazione(giorno, ora, tipologiaVisita, medico, paziente);
		gestoreDB.insertPrenotazione(prenotazione);
	}

	public void modifyPrenotazione(int id, Date giorno, Date ora, TipologiaVisita tipologiaVisita, Medico medico) {
		gestoreDB.updatePrenotazione(id, giorno, ora, tipologiaVisita, medico);
	}

	public void deletePrenotazione(int id) {
		gestoreDB.deletePrenotazione(id);
	}

	public ArrayList<Prenotazione> getPrenotazioni(String codiceFiscalePaziente) {
		return gestoreDB.getPrenotazioni(codiceFiscalePaziente);
	}
	
	public ArrayList<Prenotazione> getPrenotazioniByMedico(String codiceFiscalePaziente, int codiceMedico) {
		return gestoreDB.getPrenotazioniByMedico(codiceFiscalePaziente, codiceMedico);
	}

	public void createVisita(Prenotazione prenotazione, String diagnosi, String terapia) {
		Visita visita = new Visita(prenotazione, diagnosi, terapia);
		gestoreDB.insertVisita(visita);
	}

	public CalendarioDisponibilita getCalendarioDisponibilita(int codiceMedico) {
		return gestoreDB.getCalendarioDisponibilita(codiceMedico);
	}
	
	public CalendarioDisponibilita getCalendarioDisponibilitaFiltered(int codiceMedico, String nomeTipologiaVisita) {
		CalendarioDisponibilita calendarioDisponibilita = gestoreDB.getCalendarioDisponibilita(codiceMedico);
		
		if (calendarioDisponibilita != null) {
			ArrayList<Prenotazione> prenotazioniPerMedicoETipologiaVisita = getPrenotazioni(codiceMedico, nomeTipologiaVisita);
		
			// filtro i giorni saturi
			// disponibilità filtrate sulla base delle prenotazioni: se un giorno è saturo di prenotazioni, esso non viene aggiunto alla ComboBox
			// quindi per ogni disponibilità nel calendario controllo se in quel giorno il numero di pren. è == al maxNumVisite
			ArrayList<DisponibilitaGiornaliera> disponibilitaFiltrate = new ArrayList<>(calendarioDisponibilita.getOrari());
			
			for (DisponibilitaGiornaliera disponibilita: disponibilitaFiltrate)
				if (getNumberOfPrenotationsInThisDay(prenotazioniPerMedicoETipologiaVisita, disponibilita.getGiorno()) == disponibilita.getMaxNumVisite())
					disponibilitaFiltrate.remove(disponibilita);
			
			calendarioDisponibilita.setOrari(disponibilitaFiltrate);
		}
		
		return calendarioDisponibilita;
	}
	
	// usato solo dal metodo getCalendarioDisponibilitaFiltered
	private int getNumberOfPrenotationsInThisDay(ArrayList<Prenotazione> prenotazioni, Date giorno) {
		int numberOfPrenotationsInThisDay = 0;
		
		for (Prenotazione prenotazione: prenotazioni)
			if (prenotazione.getGiorno().equals(giorno));
				numberOfPrenotationsInThisDay++;
		
		return numberOfPrenotationsInThisDay;
	}

	public ArrayList<Date> getOrariFiltered(DisponibilitaGiornaliera disponibilita, int codiceMedico, String nomeTipologiaVisita) {
		ArrayList<Prenotazione> prenotazioniPerMedicoETipologiaVisita = getPrenotazioni(codiceMedico, nomeTipologiaVisita);
		
		ArrayList<Prenotazione> prenotazioniFiltrate = filterPrenotationsByDay(prenotazioniPerMedicoETipologiaVisita, disponibilita.getGiorno());
		
		return filterOrari(disponibilita, prenotazioniFiltrate);
	}

	// usato solo dal metodo getOrariFiltered
	private ArrayList<Prenotazione> filterPrenotationsByDay(ArrayList<Prenotazione> prenotazioni, Date giorno) {
		// filtra le prenotazioni (filtrate già perMedicoETipologiaVisita) in base al giorno specificato
		ArrayList<Prenotazione> prenotazioniGiornaliere = new ArrayList<>();
		
		for (Prenotazione prenotazione: prenotazioni)
			if (prenotazione.getGiorno().equals(giorno))
				prenotazioniGiornaliere.add(prenotazione);
		
		return prenotazioniGiornaliere;
	}

	// usato solo dal metodo getOrariFiltered
	@SuppressWarnings("deprecation")
	protected ArrayList<Date> filterOrari(Disponibilita disponibilita, ArrayList<Prenotazione> prenotazioni) {
		// riempio l'array orari di halfhours da oraInizio a oraFine
		ArrayList<Date> orari = new ArrayList<>();

		long minutesFromInizioToFine = ((disponibilita.getOraFine().getTime() - disponibilita.getOraInizio().getTime()) / 1000) / 60;
		long numberOfHalfHours = minutesFromInizioToFine / 30;

		Calendar halfHours = Calendar.getInstance();
		halfHours.setTime(disponibilita.getOraInizio());

		for (int i = 0; i < numberOfHalfHours; i++) {
			orari.add(halfHours.getTime());
//			orari.add(GUI.FramePaziente.TIME_SDF.format(halfHours.getTime()));

			halfHours.set(Calendar.MINUTE, halfHours.get(Calendar.MINUTE) + 30);
		}
		
		// filtro l'array di halfHours sulla base delle prenotazioni esistenti; per ogni mezza ora in "orari" controllo se c'è già una prenotazione
		// in quello stesso orario: in tal caso rimuovo lo slot "mezzaorale" da "orari"
		ArrayList<Date> orariFiltrati = new ArrayList<>(orari);
		for (Date orario: orari)
			for (Prenotazione prenotazione: prenotazioni)
				if (prenotazione.getOra().getHours() == orario.getHours() && prenotazione.getOra().getMinutes() == orario.getMinutes())
					orariFiltrati.remove(orario);

		return orariFiltrati;
	}
	
	public ArrayList<Prenotazione> getPrenotazioni(int codiceMedico, String nomeTipologiaVisita){
		return gestoreDB.getPrenotazioni(codiceMedico, nomeTipologiaVisita);
	}

	public ArrayList<Medico> getMedici(String nomeTipologiaVisita) {
		return gestoreDB.getMedici(nomeTipologiaVisita);
	}

	public ArrayList<TipologiaVisita> getTipologieVisite() {
		return gestoreDB.getTipologieVisite();
	}
}
