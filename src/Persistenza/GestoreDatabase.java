package Persistenza;

import Utenti.Utente;
import Utenti.Paziente;
import Utenti.PazienteHandler;
import Utenti.Specializzazione;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Utenti.Medico;
import Utenti.MedicoHandler;
import Visite.Prenotazione;
import java.util.Date;

import javax.sql.StatementEvent;

import Visite.TipologiaVisita;
import Visite.Visita;
import Visite.Fattura;
import Amministrazione.CalendarioDisponibilita;
import Visite.Pagamento;
import Amministrazione.Report;

public class GestoreDatabase {
	private final String DB_NAME = "studiomedico"; // studio_medico
	private final String DB_USER = "root";
	private final String DB_PASSWORD = "qwerty";
	private final String REFERENCED_LIBRARY = "com.mysql.jdbc.Driver";
	
	private static GestoreDatabase instance;
	private Connection connection;
	private Statement statement;

	private GestoreDatabase() {
		try {
			Class.forName(REFERENCED_LIBRARY);
		
			String url = "jdbc:mysql://localhost:3306/"+ DB_NAME +"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
			
			this.connection = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
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

	public void provaDB() {
		String query = "select * from Attori where CodiceFiscale='Z1'";
		try {
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				System.out.println("eseguito");
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getString(4));
				System.out.println(rs.getString(5));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

/*
 * 	DA CANCELLARE
 *
	public Utente getUtente(String emailUtente, String passwordUtente) {
		String query = "select email from pazienti where email='"+emailUtente+"'";
		try {
			ResultSet rs = statement.executeQuery(query);
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
*/	

	/*
	 *		SD Autenticazione
	 */
	public Utente getUtente(String email) {
		Utente utente = null;
			
		String nome;
		String cognome;
		String password;
		String codiceFiscale;
		int codice;
		Specializzazione specializzazione;
		
		String query = "select * "
					 + "from medici "
					 + "where email = '" + email + "'";
		
		try {
			System.out.println(query);
			System.out.println("Cerco nei medici");
			ResultSet rs = statement.executeQuery(query);
			
			//ricerca nella tabella medici, poi nella tabella dei pazienti, infine in quella dei proprietari 
			if(rs.next()) {
				codice = rs.getInt("codice");
				nome = rs.getString("nome");
				cognome = rs.getString("cognome");
				password = rs.getString("password");
				specializzazione = new Specializzazione(rs.getString("nome_specializzazione"));
				
				MedicoHandler handler = new MedicoHandler();
				Medico medico = (Medico) handler.createElement(nome, cognome, email, password);
				medico.setCodice(codice);
				medico.setSpecializzazione(specializzazione);
				utente = medico;
			} else {
				query = "select * "
					  + "from pazienti "
					  + "where email = '" + email + "'";
				
				System.out.println(query);
				System.out.println("Cerco nei pazienti");
				
				rs = statement.executeQuery(query);
				
				if(rs.next()) {
					codiceFiscale = rs.getString("codice_fiscale");
					nome = rs.getString("nome");
					cognome = rs.getString("cognome");
					password = rs.getString("password");
					
					PazienteHandler handler = new PazienteHandler();
					Paziente paziente = (Paziente) handler.createElement(nome, cognome, email, password);
					paziente.setCodiceFiscale(codiceFiscale);
					
					utente = paziente;
				} else {
					query = "select * "
						  + "from proprietari "
						  + "where email = '" + email + "'";
				
					System.out.println(query);
					System.out.println("Cerco nei proprietari");
					
					rs = statement.executeQuery(query);
					
					if(rs.next()) {
						nome = rs.getString("nome");
						cognome = rs.getString("cognome");
						password = rs.getString("password");
					
						utente = new Utente(nome, cognome, email, password, true);
					}
				}
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(utente);
		return utente;
	}

	public void insertPaziente(Paziente paziente) {

	}

	/*
	 *		SD Ricerca paziente
	 */
	public Paziente getPaziente(String codiceFiscale) {
		Paziente paziente = null;
		
		String nome;
		String cognome;
		String email;
		
		String query = "select * "
					 + "from pazienti "
					 + "where codice_fiscale = '" + codiceFiscale + "'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()) {
				nome = rs.getString("nome");
				cognome = rs.getString("cognome");
				email = rs.getString("email");
					
				PazienteHandler handler = new PazienteHandler();
				paziente = (Paziente) handler.createElement(nome, cognome, email, "");
				paziente.setCodiceFiscale(codiceFiscale);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return paziente;
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
