package Visite;

import Persistenza.GestoreDatabase;
import java.util.Date;
import Utenti.Medico;
import Utenti.Paziente;

import java.util.ArrayList;
import Amministrazione.CalendarioDisponibilita;

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
	
	public ArrayList<Prenotazione> getPrenotazioniFromDate(String codiceFiscalePaziente, int codiceMedico, Date date) {
		return gestoreDB.getPrenotazioniFromDate(codiceFiscalePaziente, codiceMedico, date);
	}

	public void createVisita(Prenotazione prenotazione, String diagnosi, String terapia) {
		Visita visita = new Visita(prenotazione, diagnosi, terapia);
		gestoreDB.insertVisita(visita);
	}

	public CalendarioDisponibilita getCalendarioDisponibilita(int codiceMedico) {
		return gestoreDB.getCalendarioDisponibilita(codiceMedico);
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
