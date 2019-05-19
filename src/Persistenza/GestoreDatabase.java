package Persistenza;

import Utenti.Utente;
import Utenti.Paziente;
import java.util.ArrayList;
import Utenti.Medico;
import Visite.Prenotazione;
import java.util.Date;
import Visite.TipologiaVisita;
import Visite.Visita;
import Visite.Fattura;
import Amministrazione.CalendarioDisponibilita;
import Visite.Pagamento;
import Amministrazione.Report;

public class GestoreDatabase {
	private static GestoreDatabase instance;

	private GestoreDatabase() {	}

	public static GestoreDatabase getInstance() {
		if(instance == null)
			instance = new GestoreDatabase();
		return instance;
	}

	public Utente getUtente(String email) {
		return null;
	}

	public void insertPaziente(Paziente paziente) {

	}

	public Paziente getPaziente(String codiceFiscale) {
		return null;
	}

	public ArrayList<Paziente> getPazienti() {
		return null;
	}

	public Medico getMedico(int codice) {
		return null;
	}

	public ArrayList<Medico> getMedici() {
		return null;
	}

	public void insertPrenotazione(Prenotazione prenotazione) {

	}

	public Prenotazione getPrenotazione(int id) {
		return null;
	}

	public void updatePrenotazione(int id, Date giorno, Date ora, TipologiaVisita tipologiaVisita, Medico medico) {

	}

	public void deletePrenotazione(int id) {

	}

	public ArrayList<Prenotazione> getPrenotazioni(String codiceFiscalePaziente) {
		return null;
	}

	public void insertVisita(Visita visita) {

	}

	public Visita getVisita(int id) {
		return null;
	}

	public ArrayList<Visita> getVisite(String codiceFiscalePaziente) {
		return null;
	}

	public void insertFattura(Fattura fattura) {

	}

	public void insertTipologiaVisita(TipologiaVisita tipologiaVisita) {

	}

	public TipologiaVisita getTipologiaVisita(String nome) {
		return null;
	}

	public ArrayList<TipologiaVisita> getTipologieVisite() {
		return null;
	}

	public void insertCalendarioDisponibilita(CalendarioDisponibilita calendarioDisponibilita) {

	}

	public CalendarioDisponibilita getCalendarioDisponibilita(int codiceMedico, int anno) {
		return null;
	}

	public ArrayList<Fattura> getFatture(String codiceFiscalePaziente) {
		return null;
	}

	public void insertPagamento(Pagamento pagamento) {

	}

	public ArrayList<Pagamento> getPagamentiFatture(ArrayList<Integer> idFatture) {
		return null;
	}

	public CalendarioDisponibilita getCalendarioDisponibilita(int codiceMedico, String nomeTipologiaVisita) {
		return null;
	}

	public ArrayList<Medico> getMedici(String nomeTipologiaVisita) {
		return null;
	}

	public boolean isUtenteGiaPresente(String email) {
		return false;
	}

	public boolean isPazienteGiaPresente(String codiceFiscale) {
		return false;
	}

	public ArrayList<Visita> getVisite() {
		return null;
	}

	public Report getReportVisite() {
		return null;
	}

	public Report getReportVisitePerMedico(int codiceMedico) {
		return null;
	}

	public Report getReportMedici() {
		return null;
	}

	public Report getReportTipologieVisite() {
		return null;
	}

	public ArrayList<Prenotazione> getPrenotazioniFromDate(String codiceFiscalePaziente, Date date) {
		return null;
	}
}
