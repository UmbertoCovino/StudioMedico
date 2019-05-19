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

	private GestorePrenotazioni() {	}

	public static GestorePrenotazioni getInstance() {
		if(instance == null)
			instance = new GestorePrenotazioni();
		return instance;
	}

	public GestoreDatabase getGestoreDB() {
		return gestoreDB;
	}

	public void setGestoreDB(GestoreDatabase gestoreDB) {
		this.gestoreDB = gestoreDB;
	}

	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}

	public void createPrenotazione(Date giorno, Date ora, TipologiaVisita tipologiaVisita, Medico medico, Paziente paziente) {

	}

	public void modifyPrenotazione(int id, Date giorno, Date ora, TipologiaVisita tipologiaVisita, Medico medico) {

	}

	public void deletePrenotazione(int id) {

	}

	public ArrayList<Prenotazione> getPrenotazioni(String codiceFiscalePaziente) {
		return null;
	}

	public void createVisita(Prenotazione prenotazione, String diagnosi, String terapia) {

	}

	public CalendarioDisponibilita getCalendarioDisponibilita(int codiceMedico, String nomeTipologiaVisita) {
		return null;
	}

	public ArrayList<Medico> getMedici(String nomeTipologiaVisita) {
		return null;
	}

	public ArrayList<TipologiaVisita> getTipologieVisite() {
		return null;
	}

	public ArrayList<Prenotazione> getPrenotazioniFromDate(String codiceFiscalePaziente, Date date) {
		return null;
	}

}
