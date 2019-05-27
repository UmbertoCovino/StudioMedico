package Persistenza;

import Utenti.Utente;
import Utenti.Paziente;
import Utenti.PazienteHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Utenti.Medico;
import Utenti.MedicoHandler;
import Visite.Prenotazione;
import java.util.Date;
import Visite.TipologiaVisita;
import Visite.Visita;
import Visite.Fattura;
import Amministrazione.CalendarioDisponibilita;
import Visite.Pagamento;
import Amministrazione.Report;

public class GestoreDatabase {
	private final String DB_NAME = "StudioMedico";
	
	private static GestoreDatabase instance;
	private Connection connection;
	private Statement statement;

	private GestoreDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
			String url = "jdbc:mysql://localhost:3306/"+ DB_NAME +"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
			
			this.connection = DriverManager.getConnection(url, "root", "qwerty");
			this.statement= this.connection.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static GestoreDatabase getInstance() {
		if(instance == null)
			instance = new GestoreDatabase();
		return instance;
	}

	public Utente getUtente(String email) {
		return null;
	}
	
	public Utente getUtente(String email, String password) {
		//query al DB per trovare l'utente e capire se è: paziente, medico, admin
		
		Utente utente = null;
		if(email.equals("mc@uni.com") && password.equals("123")) {
			PazienteHandler handler = new PazienteHandler();
			utente = (Paziente) handler.createElement("M", "C", email, password);
		}
		else if(email.equals("uc@uni.com") && password.equals("123")) {
			MedicoHandler handler = new MedicoHandler();
			utente = (Medico) handler.createElement("U", "C", email, password);
		}
		
		return utente;
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
	
	public ArrayList<Prenotazione> getPrenotazioni(int codiceMedico, String nomeTipologiaVisita){
		return null;
	}
}
