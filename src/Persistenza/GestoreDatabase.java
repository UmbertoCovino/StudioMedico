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
	private final String DB_NAME = "studio_medico";
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
	 * 		WORKING IN PROGRESS
	 */
	public void insertPaziente(Paziente paziente) {

	}

	
	/*
	 *		SD Ricerca paziente		DA TESTARE
	 */
	public Paziente getPaziente(String codiceFiscale) {
		Paziente paziente = null;
		
		String query = "select *"
						+ " from pazienti"
						+ " where codice_fiscale='"+ codiceFiscale +"'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()) {
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
					
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
	 * 		GET GENERICO 	DA TESTARE
	 */
	public ArrayList<Paziente> getPazienti() {
		ArrayList<Paziente> pazienti = new ArrayList<Paziente>();
		
		String query = "select *"
						+ " from pazienti";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
					
				PazienteHandler handler = new PazienteHandler();
				Paziente paziente = (Paziente) handler.createElement(nome, cognome, email, "");
				paziente.setCodiceFiscale(rs.getString("codice_fiscale"));
				
				pazienti.add(paziente);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pazienti;
	}
	
	
	/*
	 *		SD ???		DA TESTARE
	 */
	public Medico getMedico(int codice) {
		Medico medico = null;
		
		String query = "select *"
						+ " from medici"
						+ " where codice='"+ codice +"'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()) {
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
					
				MedicoHandler handler = new MedicoHandler();
				medico = (Medico) handler.createElement(nome, cognome, email, "");
				medico.setCodice(codice);
				medico.setSpecializzazione(new Specializzazione(rs.getString("nome_specializzazione")));
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return medico;
	}

	
	/*
	 * 		GET GENERICO	DA TESTARE
	 */
	public ArrayList<Medico> getMedici() {
		ArrayList<Medico> medici = new ArrayList<Medico>();
		
		String query = "select *"
						+ " from medici";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
					
				MedicoHandler handler = new MedicoHandler();
				Medico medico = (Medico) handler.createElement(nome, cognome, email, "");
				medico.setCodice(rs.getInt("codice"));
				medico.setSpecializzazione(new Specializzazione(rs.getString("nome_specializzazione")));
				
				medici.add(medico);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return medici;

	}

	
	/*
	 * 		WORKING IN PROGRESS
	 */
	public void insertPrenotazione(Prenotazione prenotazione) {

	}
	
	
	/*
	 *		SD ???		DA TESTARE
	 */
	public Prenotazione getPrenotazione(int id) {
		Prenotazione prenotazione = null;
		
		String query = "select *"
						+ " from prenotazioni"
						+ " where id='"+ id +"'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()) {
				Date giorno = rs.getDate("giorno");
				Date ora = rs.getTime("ora");
				TipologiaVisita tipologiaVisita = this.getTipologiaVisita(rs.getInt("id_tipologia_visita"));
				Medico medico = this.getMedico(rs.getInt("codice_medico"));
				Paziente paziente = this.getPaziente(rs.getString("codice_fiscale_paziente"));
					
				prenotazione = new Prenotazione(giorno, ora, tipologiaVisita, medico, paziente);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prenotazione;
	}

	
	/*
	 *		USATO IN getPrenotazione(int)
	 */
	public TipologiaVisita getTipologiaVisita(int id) {
		TipologiaVisita tipologiaVisita = null;
		
		String query = "select *"
						+ " from tipologie_visite T"
						+ " join tipologie_visite_specializzazioni TS on T.id=TS.id_tipologia_visita"
						+ " where id='"+ id +"'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()) {
				String nome = rs.getString("nome");					
				float prezzo_fisso = rs.getFloat("prezzo_fisso");
				float costo_manodopera = rs.getFloat("costo_manodopera");;
				float costo_esercizio = rs.getFloat("costo_esercizio");;
				ArrayList<Specializzazione> specializzazioniIdonee = new ArrayList<Specializzazione>();
				
				Specializzazione specializzazione = new Specializzazione(rs.getString("nome_specializzazione"));
				specializzazioniIdonee.add(specializzazione);
				while(rs.next()) {
					specializzazione = new Specializzazione(rs.getString("nome_specializzazione"));
					specializzazioniIdonee.add(specializzazione);
				}
				
				tipologiaVisita = new TipologiaVisita(nome, prezzo_fisso, costo_manodopera, costo_esercizio, specializzazioniIdonee);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tipologiaVisita;
	}

	
	
	/*
	 * 		WORKING IN PROGRESS
	 */
	public void updatePrenotazione(int id, Date giorno, Date ora, TipologiaVisita tipologiaVisita, Medico medico) {

	}

	
	/*
	 * 		WORKING IN PROGRESS
	 */
	public void deletePrenotazione(int id) {

	}

	
	/*
	 * 		SD Modifica e Elimina prenotazione visita  	DA TESTARE
	 */
	public ArrayList<Prenotazione> getPrenotazioni(String codiceFiscalePaziente) {
		ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		
		String query = "select *"
						+ " from prenotazioni"
						+ " where codice_fiscale_paziente='"+ codiceFiscalePaziente +"'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Date giorno = rs.getDate("giorno");
				Date ora = rs.getTime("ora");
				TipologiaVisita tipologiaVisita = this.getTipologiaVisita(rs.getInt("id_tipologia_visita"));
				Medico medico = this.getMedico(rs.getInt("codice_medico"));
				Paziente paziente = this.getPaziente(codiceFiscalePaziente);
					
				Prenotazione prenotazione = new Prenotazione(giorno, ora, tipologiaVisita, medico, paziente);
				
				prenotazioni.add(prenotazione);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prenotazioni;
	}

	
	/*
	 * 		WORKING IN PROGRESS
	 */
	public void insertVisita(Visita visita) {

	}

	
	/*
	 * 		SD ???		DA TESTARE
	 */
	public Visita getVisita(int id) {
		Visita visita = null;
		
		String query = "select *"
						+ " from visite"
						+ " where id='"+ id +"'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()) {
				String diagnosi = rs.getString("diagnosi");
				String terapia = rs.getString("terapia");
				Prenotazione prenotazione = this.getPrenotazione(rs.getInt("id_prenotazione"));
				
				visita = new Visita(prenotazione, diagnosi, terapia);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return visita;
	}

	
	/*
	 * 		SD Visualizza storico visite DA TESTARE
	 */
	public ArrayList<Visita> getVisite(String codiceFiscalePaziente) {
		ArrayList<Visita> visite = new ArrayList<Visita>();
		
		String query = "select *"
						+ " from visite V"
						+ " join prenotazioni P on V.id_prenotazione=P.id"
						+ " where codice_fiscale_paziente='"+ codiceFiscalePaziente +"'";

		try {
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				String diagnosi = rs.getString("diagnosi");
				String terapia = rs.getString("terapia");
				Prenotazione prenotazione = this.getPrenotazione(rs.getInt("id_prenotazione"));	
				
				Visita visita = new Visita(prenotazione, diagnosi, terapia);
				
				visite.add(visita);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return visite;
	}

	
	/*
	 * 		WORKING IN PROGRESS
	 */
	public void insertFattura(Fattura fattura) {

	}

	
	/*
	 * 		WORKING IN PROGRESS
	 */
	public void insertTipologiaVisita(TipologiaVisita tipologiaVisita) {

	}

	
	/*
	 * 		SD ???
	 */
	public TipologiaVisita getTipologiaVisita(String nome) {
		TipologiaVisita tipologiaVisita = null;
		
		String query = "select *"
						+ " from tipologie_visite T"
						+ " join tipologie_visite_specializzazioni TS on T.id=TS.id_tipologia_visita"
						+ " where nome='"+ nome +"'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()) {					
				float prezzo_fisso = rs.getFloat("prezzo_fisso");
				float costo_manodopera = rs.getFloat("costo_manodopera");
				float costo_esercizio = rs.getFloat("costo_esercizio");
				ArrayList<Specializzazione> specializzazioniIdonee = new ArrayList<Specializzazione>();
				
				Specializzazione specializzazione = new Specializzazione(rs.getString("nome_specializzazione"));
				specializzazioniIdonee.add(specializzazione);
				while(rs.next()) {
					specializzazione = new Specializzazione(rs.getString("nome_specializzazione"));
					specializzazioniIdonee.add(specializzazione);
				}
				
				tipologiaVisita = new TipologiaVisita(nome, prezzo_fisso, costo_manodopera, costo_esercizio, specializzazioniIdonee);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tipologiaVisita;
	}

	
	/*
	 * 		GET GENERICO (da ottimizzare)
	 */
	public ArrayList<TipologiaVisita> getTipologieVisite() {
		ArrayList<TipologiaVisita> tipologieVisite = new ArrayList<TipologiaVisita>();
		
		String query = "select *"
						+ " from tipologie_visite T"
						+ " join tipologie_visite_specializzazioni TS on T.id=TS.id_tipologia_visita"
						+ " order by T.nome";
	
		try {
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {					
				String nome = rs.getString("nome");
				float prezzo_fisso = rs.getFloat("prezzo_fisso");
				float costo_manodopera = rs.getFloat("costo_manodopera");
				float costo_esercizio = rs.getFloat("costo_esercizio");
				Specializzazione specializzazione = new Specializzazione(rs.getString("nome_specializzazione")); 
				
				ArrayList<Specializzazione> specializzazioniIdonee = new ArrayList<Specializzazione>(1);
				specializzazioniIdonee.add(specializzazione);
				
				if(!tipologieVisite.isEmpty() && nome.equals(tipologieVisite.get(tipologieVisite.size()-1).getNome())) {
					tipologieVisite.get(tipologieVisite.size()-1).getSpecializzazioniIdonee().add(specializzazione);
				} else {
					TipologiaVisita tipologiaVisita = new TipologiaVisita(nome, prezzo_fisso, costo_manodopera, costo_esercizio, specializzazioniIdonee);
					tipologieVisite.add(tipologiaVisita);
				}
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tipologieVisite;

	}

	
	/*
	 * 		WORKING IN PROGRESS
	 */
	public void insertCalendarioDisponibilita(CalendarioDisponibilita calendarioDisponibilita) {

	}

	
	/*
	 * 		SD ???		DA TESTARE
	 */
	public CalendarioDisponibilita getCalendarioDisponibilita(int codiceMedico, int anno) {
		CalendarioDisponibilita calendarioDisponibilita = null;
		
		String query = "select *"
						+ " from calendario_disponibilita"
						+ " where codice_medico='"+ codiceMedico +"' and anno='"+ anno +"'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()) {					
				Medico medico = this.getMedico(rs.getInt("codice_medico"));
				
				calendarioDisponibilita = new CalendarioDisponibilita(anno, medico);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return calendarioDisponibilita;
	}

	
	/*
	 * 		SD Paga visita		DA TESTARE
	 */
	public ArrayList<Fattura> getFatture(String codiceFiscalePaziente) {
		ArrayList<Fattura> fatture = new ArrayList<Fattura>();
		
		String query = "select *"
						+ " from fatture"
						+ " where codice_fiscale_paziente='"+ codiceFiscalePaziente +"'";

		try {
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Visita visita = this.getVisita(rs.getInt("id_visita"));	
				
				Fattura fattura = new Fattura(visita);
				
				fatture.add(fattura);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return fatture;
	}

	
	/*
	 * 		WORKING IN PROGRESS
	 */
	public void insertPagamento(Pagamento pagamento) {

	}

	
	/*
	 * 		WORKING IN PROGRESS
	 */
	public ArrayList<Pagamento> getPagamentiFatture(ArrayList<Integer> idFatture) {
		return null;
	}

	
	/*
	 * 		SD Prenota Visita 	WORKING IN PROGRESS 	DA TESTARE
	 */
	public CalendarioDisponibilita getCalendarioDisponibilita(int codiceMedico, String nomeTipologiaVisita) {
		CalendarioDisponibilita calendarioDisponibilita = null;
		
		String query = "select *"
						+ " from calendario_disponibilita"
						+ " where codice_medico='"+ codiceMedico +"'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()) {					
				Medico medico = this.getMedico(rs.getInt("codice_medico"));
				
			//	calendarioDisponibilita = new CalendarioDisponibilita(anno, medico);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return calendarioDisponibilita;
	}

	
	/*
	 * 		SD Prenota vista
	 */
	public ArrayList<Medico> getMedici(String nomeTipologiaVisita) {
		ArrayList<Medico> medici = new ArrayList<Medico>();
		
		String query = "select *"
						+ " from medici M "
						+ " join tipologie_visite_specializzazioni TS on M.nome_specializzazione = TS.nome_specializzazione"
						+ " join tipologie_visite T on T.id = TS.id_tipologia_visita"
						+ " where T.nome='"+ nomeTipologiaVisita +"'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()) {
				String nome = rs.getString("M.nome");
				String cognome = rs.getString("M.cognome");
				String email = rs.getString("M.email");
					
				MedicoHandler handler = new MedicoHandler();
				Medico medico = (Medico) handler.createElement(nome, cognome, email, "");
				medico.setCodice(rs.getInt("codice"));
				medico.setSpecializzazione(new Specializzazione(rs.getString("M.nome_specializzazione")));
				
				medici.add(medico);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return medici;
	}

	
	/*
	 * 		SD Registrazione	DA TESTARE
	 */
	public boolean isUtenteGiaPresente(String email) {
		if(this.getUtente(email) != null)
			return true;
		return false;
	}

	
	/*
	 * 		SD Registrazione	DA TESTARE
	 */
	public boolean isPazienteGiaPresente(String codiceFiscale) {
		if(this.getPaziente(codiceFiscale) != null)
			return true;
		return false;
	}

	
	/*
	 * 		WORKING IN PROGRESS
	 */
	public ArrayList<Visita> getVisite() {
		return null;
	}

	
	/*
	 * 		WORKING IN PROGRESS
	 */
	public Report getReportVisite() {
		return null;
	}

	
	/*
	 * 		WORKING IN PROGRESS
	 */
	public Report getReportVisitePerMedico(int codiceMedico) {
		return null;
	}

	
	/*
	 * 		WORKING IN PROGRESS
	 */
	public Report getReportMedici() {
		return null;
	}

	
	/*
	 * 		WORKING IN PROGRESS
	 */
	public Report getReportTipologieVisite() {
		return null;
	}

	
	/*
	 * 		WORKING IN PROGRESS
	 */
	public ArrayList<Prenotazione> getPrenotazioniFromDate(String codiceFiscalePaziente, Date date) {
		return null;
	}
	
	
	/*
	 * 		WORKING IN PROGRESS
	 */
	public ArrayList<Prenotazione> getPrenotazioni(int codiceMedico, String nomeTipologiaVisita){
		return null;
	}
}