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
		String query = "select * from pazienti";
		try {
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				System.out.println("eseguito");
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getString(4));
				System.out.println(rs.getString(5));
//				System.out.println(rs.getString(6));
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
				
		String query = "select *"
				+ " from medici"
				+ " where email='"+ email +"'";
		
		try {
			System.out.println(query);
			System.out.println("Cerco nei medici");
			ResultSet rs = statement.executeQuery(query);
			
			//ricerca nella tabella medici, poi nella tabella dei pazienti, infine in quella dei proprietari 
			if(rs.next()) {
				int codice = rs.getInt("codice");
				nome = rs.getString("nome");
				cognome = rs.getString("cognome");
				password = rs.getString("password");
				Specializzazione specializzazione = new Specializzazione(rs.getString("nome_specializzazione"));
				
				MedicoHandler handler = new MedicoHandler();
				Medico medico = (Medico) handler.createElement(nome, cognome, email, password);
				medico.setCodice(codice);
				medico.setSpecializzazione(specializzazione);
				utente = medico;
			} else {
				query = "select *"
						+ " from pazienti"
						+ " where email='"+ email +"'";
				
				System.out.println(query);
				System.out.println("Cerco nei pazienti");
				
				rs = statement.executeQuery(query);
				
				if(rs.next()) {
					String codiceFiscale = rs.getString("codice_fiscale");
					nome = rs.getString("nome");
					cognome = rs.getString("cognome");
					password = rs.getString("password");
					
					PazienteHandler handler = new PazienteHandler();
					Paziente paziente = (Paziente) handler.createElement(nome, cognome, email, password);
					paziente.setCodiceFiscale(codiceFiscale);
					
					utente = paziente;
				} else {
					query = "select *"
							+ " from proprietari"
							+ " where email='"+ email +"'";
				
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

	/*
	 * 		WORKING
	 */
	public void insertPaziente(Paziente paziente) {

	}

	/*
	 *		SD Ricerca paziente
	 *		DA TESTARE
	 */
	public Paziente getPaziente(String codiceFiscale) {
		Paziente paziente = null;
		
		String query = "select *"
						+ "from pazienti"
						+ " where codice_fiscale='"+ codiceFiscale +"'";
		String nome;
		String cognome;
		String email;
		
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

	/*
	 * 		WORKING
	 */
	public ArrayList<Paziente> getPazienti() {
		return null;
	}
	
	/*
	 *		SD ???
	 *		DA TESTARE
	 */
	public Medico getMedico(int codice) {
		Medico medico = null;
		
		String query = "select *"
						+ "from medici"
						+ " where codice='"+ codice +"'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()) {
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
				Specializzazione specializzazione = new Specializzazione(rs.getString("nome_specializzazione"));
					
				MedicoHandler handler = new MedicoHandler();
				medico = (Medico) handler.createElement(nome, cognome, email, "");
				medico.setSpecializzazione(specializzazione);
				medico.setCodice(codice);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return medico;
	}

	/*
	 * 		WORKING
	 */
	public ArrayList<Medico> getMedici() {
		return null;
	}

	/*
	 * 		WORKING
	 */
	public void insertPrenotazione(Prenotazione prenotazione) {

	}
	
	/*
	 *		SD ???
	 *		DA TESTARE
	 */
	public Prenotazione getPrenotazione(int id) {
		Prenotazione prenotazione = null;
		
		String query = "select *"
						+ "from prenotazioni"
						+ " where id='"+ id +"'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()) {
				Date giorno = rs.getDate("giorno");
				Date ora = rs.getTime("ora");
				int id_tipologia_visita = rs.getInt("id_tipologia_visita");
				int codice_medico = rs.getInt("codice_medico");
				String codice_fiscale_paziente = rs.getString("codice_fiscale_paziente");
					
//				prenotazione = new Prenotazione(giorno, ora, id_tipologia_visita, codice_medico, codice_fiscale_paziente);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prenotazione;
	}

	/*
	 * 		WORKING
	 */
	public void updatePrenotazione(int id, Date giorno, Date ora, TipologiaVisita tipologiaVisita, Medico medico) {

	}

	/*
	 * 		WORKING
	 */
	public void deletePrenotazione(int id) {

	}

	/*
	 * 		WORKING
	 */
	public ArrayList<Prenotazione> getPrenotazioni(String codiceFiscalePaziente) {
		return null;
	}

	/*
	 * 		WORKING
	 */
	public void insertVisita(Visita visita) {

	}

	/*
	 * 		WORKING
	 */
	public Visita getVisita(int id) {
		return null;
	}

	/*
	 * 		WORKING
	 */
	public ArrayList<Visita> getVisite(String codiceFiscalePaziente) {
		return null;
	}

	/*
	 * 		WORKING
	 */
	public void insertFattura(Fattura fattura) {

	}

	/*
	 * 		WORKING
	 */
	public void insertTipologiaVisita(TipologiaVisita tipologiaVisita) {

	}

	/*
	 * 		WORKING
	 */
	public TipologiaVisita getTipologiaVisita(String nome) {
		return null;
	}

	/*
	 * 		WORKING
	 */
	public ArrayList<TipologiaVisita> getTipologieVisite() {
		return null;
	}

	/*
	 * 		WORKING
	 */
	public void insertCalendarioDisponibilita(CalendarioDisponibilita calendarioDisponibilita) {

	}

	/*
	 * 		WORKING
	 */
	public CalendarioDisponibilita getCalendarioDisponibilita(int codiceMedico, int anno) {
		return null;
	}

	/*
	 * 		WORKING
	 */
	public ArrayList<Fattura> getFatture(String codiceFiscalePaziente) {
		return null;
	}

	/*
	 * 		WORKING
	 */
	public void insertPagamento(Pagamento pagamento) {

	}

	/*
	 * 		WORKING
	 */
	public ArrayList<Pagamento> getPagamentiFatture(ArrayList<Integer> idFatture) {
		return null;
	}

	/*
	 * 		WORKING
	 */
	public CalendarioDisponibilita getCalendarioDisponibilita(int codiceMedico, String nomeTipologiaVisita) {
		return null;
	}

	/*
	 * 		WORKING
	 */
	public ArrayList<Medico> getMedici(String nomeTipologiaVisita) {
		return null;
	}

	/*
	 * 		SD Registrazione
	 * 		da testare
	 */
	public boolean isUtenteGiaPresente(String email) {
		if(this.getUtente(email) != null)
			return true;
		return false;
	}

	/*
	 * 		SD Registrazione
	 * 		da testare
	 */
	public boolean isPazienteGiaPresente(String codiceFiscale) {
		if(this.getPaziente(codiceFiscale) != null)
			return true;
		return false;
	}

	/*
	 * 		WORKING
	 */
	public ArrayList<Visita> getVisite() {
		return null;
	}

	/*
	 * 		WORKING
	 */
	public Report getReportVisite() {
		return null;
	}

	/*
	 * 		WORKING
	 */
	public Report getReportVisitePerMedico(int codiceMedico) {
		return null;
	}

	/*
	 * 		WORKING
	 */
	public Report getReportMedici() {
		return null;
	}

	/*
	 * 		WORKING
	 */
	public Report getReportTipologieVisite() {
		return null;
	}

	/*
	 * 		WORKING
	 */
	public ArrayList<Prenotazione> getPrenotazioniFromDate(String codiceFiscalePaziente, Date date) {
		return null;
	}
	
	/*
	 * 		WORKING
	 */
	public ArrayList<Prenotazione> getPrenotazioni(int codiceMedico, String nomeTipologiaVisita){
		return null;
	}
}