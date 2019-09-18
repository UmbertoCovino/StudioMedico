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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Utenti.Medico;
import Utenti.MedicoHandler;
import Visite.Prenotazione;
import java.util.Date;

import Visite.TipologiaVisita;
import Visite.Visita;
import Visite.Fattura;
import Amministrazione.CalendarioDisponibilita;
import Amministrazione.DisponibilitaDirector;
import Amministrazione.DisponibilitaGiornaliera;
import Amministrazione.DisponibilitaGiornalieraBuilder;
import Visite.Pagamento;
import Amministrazione.Report;
import Amministrazione.ReportDirector;
import Amministrazione.ReportMedici;
import Amministrazione.ReportMediciBuilder;
import Amministrazione.ReportTipoVisite;
import Amministrazione.ReportTipoVisiteBuilder;
import Amministrazione.ReportVisite;
import Amministrazione.ReportVisiteBuilder;
import Amministrazione.ReportVisitePerMedico;
import Amministrazione.ReportVisitePerMedicoBuilder;

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
		
			String url = "jdbc:mysql://localhost:3306/" + DB_NAME + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			
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

/*
 **************************************** package AMMINISTARAZIONE ************************************************************************
 */

	/*
	 * 		SD Crea report TEST OK
	 */
	public ArrayList<Medico> getMedici() {
		ArrayList<Medico> medici = new ArrayList<Medico>();
		
		String query = "select * "
					 + "from medici";
		
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
 **************************************** package UTENTI **********************************************************************************
 */
	
	/*
	 *		SD Autenticazione		TEST OK
	 */
	public Utente getUtente(String email) {
		Utente utente = null;
		String nome;
		String cognome;
		String password;
				
		//ricerca nella tabella medici, poi nella tabella dei pazienti, infine in quella dei proprietari
		
		String query = "select * "
					 + "from medici M "
					 + "where email = '" + email + "'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			if(rs.next()) {
				utente = this.getMedico(rs);
				utente.setPassword(rs.getString("password"));
			} else {
				query = "select * "
					  + "from pazienti P "
					  + "where email = '" + email + "'";
				
				rs = statement.executeQuery(query);
				if(rs.next()) {
					utente = this.getPaziente(rs);
					utente.setPassword(rs.getString("password"));
				} else {
					query = "select * "
						  + "from proprietari "
						  + "where email = '" + email + "'";
					
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
		
		return utente;
	}
	
	
	/*
	 *		SD Ricerca paziente		TEST OK
	 */
	public Paziente getPaziente(String codiceFiscale) {
		Paziente paziente = null;

		String query = "select * "
					 + "from pazienti "
					 + "where codice_fiscale = '" + codiceFiscale + "'";
		
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
	 * 		SD Registrazione	TEST OK
	 */
	public boolean isUtenteGiaPresente(String email) {
		if(this.getUtente(email) != null)
			return true;
		return false;
	}

	
	/*
	 * 		SD Registrazione	TEST OK
	 */
	public boolean isPazienteGiaPresente(String codiceFiscale) {
		if(this.getPaziente(codiceFiscale) != null)
			return true;
		return false;
	}

	
/*
 **************************************** package VISITE (PRENOTAZIONI) *******************************************************************
 */
	
	/*
	 * 		SD Modifica e Elimina prenotazione visita  	TEST OK - DA OTTIMIZZARE
	 */
	public ArrayList<Prenotazione> getPrenotazioni(String codiceFiscalePaziente) {
		ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		
		String query = "select * "
					 	+ "from prenotazioni PR "
					 	+ "join tipologie_visite TV on PR.id_tipologia_visita = TV.id "
					 	+ "join tipologie_visite_specializzazioni TVS on TV.id = TVS.id_tipologia_visita "
					 	+ "join medici M on PR.codice_medico = M.codice "
					 	+ "join pazienti P on PR.codice_fiscale_paziente = P.codice_fiscale "
					 	+ "left join visite V on PR.id = V.id_prenotazione "
					 	+ "where PR.codice_fiscale_paziente = '" + codiceFiscalePaziente + "' "
			 				+ "and V.id_prenotazione is NULL "
					 		+ "and PR.giorno > curdate() "					 	
			 		 	+ "order by TV.nome";
//		select * from prenotazioni PR join tipologie_visite TV on PR.id_tipologia_visita = TV.id join tipologie_visite_specializzazioni TVS on TV.id = TVS.id_tipologia_visita join medici M on PR.codice_medico = M.codice join pazienti P on PR.codice_fiscale_paziente = P.codice_fiscale left join visite V on PR.id = V.id_prenotazione where PR.codice_fiscale_paziente = '' and V.id_prenotazione is NULL and PR.giorno >= curdate() order by TV.nome;
		try {
			ResultSet rs = statement.executeQuery(query);
		
			while(rs.next()) {
				int id = rs.getInt("PR.id");
				Date giorno = rs.getDate("PR.giorno");
				Date ora = rs.getTime("PR.ora", Calendar.getInstance());
				Medico medico = this.getMedico(rs);
				Paziente paziente = this.getPaziente(rs);
				
				TipologiaVisita tipologiaVisita = this.getTipologiaVisita(rs);
				String currentTV = rs.getString("TV.nome");
				String previousTV = null;
				
				if(!rs.isFirst()) {
					rs.previous();
					previousTV = rs.getString("TV.nome");
					rs.next();
				}
				if(!rs.isFirst() && previousTV.equals(currentTV)) {
					prenotazioni.get(prenotazioni.size()-1).getTipologiaVisita().getSpecializzazioniIdonee().add(new Specializzazione(rs.getString("TVS.nome_specializzazione")));
				} else {
					Prenotazione prenotazione = new Prenotazione(id, giorno, ora, tipologiaVisita, medico, paziente);
					prenotazioni.add(prenotazione);
				}
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prenotazioni;
	}

	
	/*
	 * 		SD Prenota visita 		TEST OK - DA OTTIMIZZARE
	 */
	public ArrayList<TipologiaVisita> getTipologieVisite() {
		ArrayList<TipologiaVisita> tipologieVisite = new ArrayList<TipologiaVisita>();
		
		String query = "select * "
					 + "from tipologie_visite T "
					 + "join tipologie_visite_specializzazioni TS on T.id = TS.id_tipologia_visita "
					 + "order by T.nome";
	
		try {
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {	
				int id = rs.getInt("id");
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
					TipologiaVisita tipologiaVisita = new TipologiaVisita(id, nome, prezzo_fisso, costo_manodopera, costo_esercizio, specializzazioniIdonee);
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
	 * 		SD Prenota vista	TEST OK
	 */
	public ArrayList<Medico> getMedici(String nomeTipologiaVisita) {
		ArrayList<Medico> medici = new ArrayList<Medico>();
		
		String query = "select * "
					 + "from medici M "
					 + "join tipologie_visite_specializzazioni TS on M.nome_specializzazione = TS.nome_specializzazione "
					 + "join tipologie_visite T on T.id = TS.id_tipologia_visita "
					 + "where T.nome = '" + nomeTipologiaVisita + "'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
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
	 * 		SD Esegue visita		TEST OK 
	 */
	public ArrayList<Prenotazione> getPrenotazioniByMedico(String codiceFiscalePaziente, int codiceMedico) {
		ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		
		String query = "select * "
					 	+ "from prenotazioni PR "
					 	+ "join tipologie_visite TV on PR.id_tipologia_visita = TV.id "
					 	+ "join tipologie_visite_specializzazioni TVS on TV.id = TVS.id_tipologia_visita "
					 	+ "join medici M on PR.codice_medico = M.codice "
					 	+ "join pazienti P on PR.codice_fiscale_paziente = P.codice_fiscale "
					 	+ "left join visite V on PR.id = V.id_prenotazione "
					 	+ "where PR.codice_fiscale_paziente = '" + codiceFiscalePaziente + "' "
					 			+ "and V.id_prenotazione is NULL "
					 			+ "and M.codice = '" + codiceMedico + "' "
					 			+ "and PR.giorno <= curdate() "
			 		 	+ "order by TV.nome";
		
		try {
			ResultSet rs = statement.executeQuery(query);
		
			while(rs.next()) {
				int id = rs.getInt("PR.id");
				Date giorno = rs.getDate("PR.giorno");
				Date ora = rs.getTime("PR.ora", Calendar.getInstance());
				Medico medico = this.getMedico(rs);
				Paziente paziente = this.getPaziente(rs);
				
				TipologiaVisita tipologiaVisita = this.getTipologiaVisita(rs);
				String currentTV = rs.getString("TV.nome");
				String previousTV = null;
				
				if(!rs.isFirst()) {
					rs.previous();
					previousTV = rs.getString("TV.nome");
					rs.next();
				}
				
				if(!rs.isFirst() && previousTV.equals(currentTV)) {
					prenotazioni.get(prenotazioni.size()-1).getTipologiaVisita().getSpecializzazioniIdonee().add(new Specializzazione(rs.getString("TVS.nome_specializzazione")));
				} else {
					Prenotazione prenotazione = new Prenotazione(id, giorno, ora, tipologiaVisita, medico, paziente);
					prenotazioni.add(prenotazione);
				}
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prenotazioni;
	}

	
/*
 **************************************** package VISITE (VISITE) *************************************************************************
 */	
	
	/*
	 * 		SD Visualizza storico visite		 TEST OK
	 */
	public ArrayList<Visita> getVisite(String codiceFiscalePaziente) {
		ArrayList<Visita> visite = new ArrayList<Visita>();
		
		String query = "select * "
					 + "from visite V "
					 + "join prenotazioni PR on V.id_prenotazione = PR.id "
					 + "join tipologie_visite TV on PR.id_tipologia_visita = TV.id "
					 + "join tipologie_visite_specializzazioni TVS on TV.id = TVS.id_tipologia_visita "
					 + "join medici M on PR.codice_medico = M.codice "
					 + "join pazienti P on PR.codice_fiscale_paziente = P.codice_fiscale "
					 + "left join fatture F on V.id = F.id_visita "
					 + "where PR.codice_fiscale_paziente = '" + codiceFiscalePaziente + "' "
					 + "order by TV.nome";
//		select * from visite V join prenotazioni PR on V.id_prenotazione = PR.id join tipologie_visite TV on PR.id_tipologia_visita = TV.id join tipologie_visite_specializzazioni TVS on TV.id = TVS.id_tipologia_visita join medici M on PR.codice_medico = M.codice join pazienti P on PR.codice_fiscale_paziente = P.codice_fiscale left join fatture F on V.id = F.id_visita where PR.codice_fiscale_paziente = '' and F.id_visita is NULL and M.codice = '' order by TV.nome;
		try {
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				int id = rs.getInt("V.id");
				String diagnosi = rs.getString("diagnosi");
				String terapia = rs.getString("terapia");
				
				Prenotazione prenotazione = this.getPrenotazione(rs);
				String currentTV = rs.getString("TV.nome");
				String previousTV = null;
				
				if(!rs.isFirst()) {
					rs.previous();
					previousTV = rs.getString("TV.nome");
					rs.next();
				}
				
				if(!rs.isFirst() && previousTV.equals(currentTV)) {
					visite.get(visite.size()-1).getTipologiaVisita().getSpecializzazioniIdonee().add(new Specializzazione(rs.getString("TVS.nome_specializzazione")));
				} else {
					Visita visita = new Visita(id, prenotazione, diagnosi, terapia);
					visite.add(visita);
				}
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return visite;
	}	
	
	
	/*
	 * 		SD Genera fattura		 TEST OK
	 */
	public ArrayList<Visita> getVisiteByMedico(String codiceFiscalePaziente, int codiceMedico) {
		ArrayList<Visita> visite = new ArrayList<Visita>();
		
		String query = "select * "
					 + "from visite V "
					 + "join prenotazioni PR on V.id_prenotazione = PR.id "
					 + "join tipologie_visite TV on PR.id_tipologia_visita = TV.id "
					 + "join tipologie_visite_specializzazioni TVS on TV.id = TVS.id_tipologia_visita "
					 + "join medici M on PR.codice_medico = M.codice "
					 + "join pazienti P on PR.codice_fiscale_paziente = P.codice_fiscale "
					 + "left join fatture F on V.id = F.id_visita "
					 + "where PR.codice_fiscale_paziente = '" + codiceFiscalePaziente + "' "
					 		+ "and F.id_visita is NULL "
							+ "and M.codice = '" + codiceMedico + "' "
					 + "order by TV.nome";
//		select * from visite V join prenotazioni PR on V.id_prenotazione = PR.id join tipologie_visite TV on PR.id_tipologia_visita = TV.id join tipologie_visite_specializzazioni TVS on TV.id = TVS.id_tipologia_visita join medici M on PR.codice_medico = M.codice join pazienti P on PR.codice_fiscale_paziente = P.codice_fiscale left join fatture F on V.id = F.id_visita where PR.codice_fiscale_paziente = '' and F.id_visita is NULL and M.codice = '' order by TV.nome;
		try {
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				int id = rs.getInt("V.id");
				String diagnosi = rs.getString("diagnosi");
				String terapia = rs.getString("terapia");
				
				Prenotazione prenotazione = this.getPrenotazione(rs);
				String currentTV = rs.getString("TV.nome");
				String previousTV = null;
				
				if(!rs.isFirst()) {
					rs.previous();
					previousTV = rs.getString("TV.nome");
					rs.next();
				}
				
				if(!rs.isFirst() && previousTV.equals(currentTV)) {
					visite.get(visite.size()-1).getTipologiaVisita().getSpecializzazioniIdonee().add(new Specializzazione(rs.getString("TVS.nome_specializzazione")));
				} else {
					Visita visita = new Visita(id, prenotazione, diagnosi, terapia);
					visite.add(visita);
				}
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return visite;
	}
	

	/*
	 * 		SD Paga visita		TEST OK
	 */
	public ArrayList<Fattura> getFatture(String codiceFiscalePaziente) {
		ArrayList<Fattura> fatture = new ArrayList<Fattura>();
		
		String query = "select * "
					 	+ "from fatture F "
					 	+ "join visite V on F.id_visita = V.id "
					 	+ "join prenotazioni PR on V.id_prenotazione = PR.id "
					 	+ "join tipologie_visite TV on PR.id_tipologia_visita = TV.id "
					 	+ "join tipologie_visite_specializzazioni TVS on TV.id = TVS.id_tipologia_visita "
					 	+ "join medici M on PR.codice_medico = M.codice "
					 	+ "join pazienti P on PR.codice_fiscale_paziente = P.codice_fiscale "
					 	+ "left join pagamenti PA on F.id = PA.id_fattura "
					 	+ "where F.codice_fiscale_paziente = '" + codiceFiscalePaziente + "' "
					 			+ "and PA.id_fattura is NULL "
			 		 	+ "order by TV.nome";
//		select * from fatture F join visite V on F.id_visita = V.id join prenotazioni PR on V.id_prenotazione = PR.id join tipologie_visite TV on PR.id_tipologia_visita = TV.id join tipologie_visite_specializzazioni TVS on TV.id = TVS.id_tipologia_visita join medici M on PR.codice_medico = M.codice join pazienti P on PR.codice_fiscale_paziente = P.codice_fiscale left join pagamenti PA on F.id = PA.id_fattura where F.codice_fiscale_paziente = 'CF1' order by TV.nome;
			try {
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				int id = rs.getInt("F.id");
				Visita visita = this.getVisita(rs);
				
				String currentTV = rs.getString("TV.nome");
				String previousTV = null;
				
				if(!rs.isFirst()) {
					rs.previous();
					previousTV = rs.getString("TV.nome");
					rs.next();
				}
				if(!rs.isFirst() && previousTV.equals(currentTV)) {
					fatture.get(fatture.size()-1).getVisita().getTipologiaVisita().getSpecializzazioniIdonee().add(new Specializzazione(rs.getString("TVS.nome_specializzazione")));
				} else {
					Fattura fattura = new Fattura(id, visita);
					fatture.add(fattura);
				}
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return fatture;
	}

	
	/*
	 * 		SD Paga visita 		TEST OK		NOT USED
	 */
	public ArrayList<Pagamento> getPagamentiFatture(ArrayList<Integer> idFatture) {
		ArrayList<Pagamento> pagamenti = new ArrayList<Pagamento>();
		
		String query = "select * "
					 	+ "from pagamenti PA "
					 	+ "join fatture F on PA.id_fattura = F.id "
					 	+ "join visite V on F.id_visita = V.id "
					 	+ "join prenotazioni PR on V.id_prenotazione = PR.id "
					 	+ "join tipologie_visite TV on PR.id_tipologia_visita = TV.id "
					 	+ "join tipologie_visite_specializzazioni TVS on TV.id = TVS.id_tipologia_visita "
					 	+ "join medici M on PR.codice_medico = M.codice "
					 	+ "join pazienti P on PR.codice_fiscale_paziente = P.codice_fiscale "
			 		 	+ "order by TV.nome";

		try {
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				if (idFatture.contains(rs.getInt("PA.id_fattura"))) {
					Date data = rs.getDate("PA.data");
					String metodo_pagamaneto = rs.getString("PA.metodo_pagamento");
					
					Fattura fattura = this.getFattura(rs);
					String currentTV = rs.getString("TV.nome");
					String previousTV = null;
					
					if (!rs.isFirst()) {
						rs.previous();
						previousTV = rs.getString("TV.nome");
						rs.next();
					}
					if (!rs.isFirst() && previousTV.equals(currentTV)) {
						pagamenti.get(pagamenti.size() - 1).getFattura().getVisita().getTipologiaVisita()
								.getSpecializzazioniIdonee()
								.add(new Specializzazione(rs.getString("TVS.nome_specializzazione")));
					} else {
						Pagamento pagamento = new Pagamento(fattura, metodo_pagamaneto, data);
						pagamenti.add(pagamento);
					} 
				}
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pagamenti;
	}

	
/*
 **************************************** getREPORT ***************************************************************************************
 */
	

	/*
	 * 		SD Report[1] 		TEST ?
	 * 		Elenco di visite effettuare ordinate per medico
	 */
	public Report getReportVisite() {
		ReportVisite report = null;
		
		String query = "select PR.giorno as giorno, PR.ora as ora, TV.nome as nome_tipologia_visita, M.nome as nome_medico, P.nome as nome_paziente "
						+ "from visite V "
						+ "join prenotazioni PR on V.id_prenotazione = PR.id "
						+ "join tipologie_visite TV on PR.id_tipologia_visita = TV.id "
						+ "join medici M on PR.codice_medico = M.codice "
						+ "join pazienti P on PR.codice_fiscale_paziente = P.codice_fiscale "
						+ "order by nome_medico";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			report = (ReportVisite) ReportDirector.buildPart(new ReportVisiteBuilder(), rs);
			
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return report;
	}


	/*
	 * 		SD Report[2] 		TEST ?
	 * 		Elenco di visite effettuare ordinate per giorno con indicazione del medico
	 */
	public Report getReportVisitePerMedico(int codiceMedico) {
		ReportVisitePerMedico report = null;
		
		String query = "select PR.giorno as giorno, PR.ora as ora, TV.nome as nome_tipologia_visita, P.nome as nome_paziente, M.codice, M.nome, M.cognome, M.email, M.nome_specializzazione "
						+ "from visite V "
						+ "join prenotazioni PR on V.id_prenotazione = PR.id "
						+ "join tipologie_visite TV on PR.id_tipologia_visita = TV.id "
						+ "join medici M on PR.codice_medico = M.codice "
						+ "join pazienti P on PR.codice_fiscale_paziente = P.codice_fiscale "
						+ "where M.codice = '" + codiceMedico + "' "
						+ "order by giorno";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				report = (ReportVisitePerMedico) ReportDirector.buildPart(new ReportVisitePerMedicoBuilder(), rs);
				rs.previous();
				report.setMedico(GestoreDatabase.getInstance().getMedico(rs));
			}
								
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return report;
	}


	/*
	 * 		SD Report[3] 		TEST ?
	 * 		Elenco dei medici ordinati per numero di visite
	 */
	public Report getReportMedici() {
		ReportMedici report = null;
		
		String query = "select M.nome as nome, M.cognome as cognome, M.email as email, M.codice as codice, M.nome_specializzazione as nome_specializzazione, count(*) as num_visite "
						+ "from visite V "
						+ "join prenotazioni PR on V.id_prenotazione = PR.id "
						+ "join medici M on PR.codice_medico = M.codice";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			report = (ReportMedici) ReportDirector.buildPart(new ReportMediciBuilder(), rs);
						
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return report;
	}
	

	/*
	 * 		SD Report[4] 		TEST ?
	 * 		Elenco delle tipologie di visite ordinate per numerosita
	 */
	public Report getReportTipologieVisite() {
		ReportTipoVisite report = null;
		
		String query = "select TV.nome as nome, TV.prezzo_fisso as prezzo_fisso, TV.costo_manodopera as costo_manodopera, TV.costo_esercizio as costo_esercizio, count(*) as num_visite "
				+ "from visite V "
				+ "join prenotazioni PR on V.id_prenotazione = PR.id "
				+ "join tipologie_visite TV on PR.id_tipologia_visita = TV.id";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			report = (ReportTipoVisite) ReportDirector.buildPart(new ReportTipoVisiteBuilder(), rs);
					
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return report;
	}


/*
 **************************************** METODI EXTRA PER FAR FUNZIONARE LE QUERY ********************************************************
 */
	
	private Fattura getFattura(ResultSet rs) throws SQLException {
		Visita visita = this.getVisita(rs);
		
		return new Fattura(visita);
	}
	
	private Visita getVisita(ResultSet rs) throws SQLException {
		int id = rs.getInt("V.id");
		String diagnosi = rs.getString("diagnosi");
		String terapia = rs.getString("terapia");
		Prenotazione prenotazione = this.getPrenotazione(rs);
		
		return new Visita(id, prenotazione, diagnosi, terapia);
	}

	private Prenotazione getPrenotazione(ResultSet rs) throws SQLException {
		int id = rs.getInt("PR.id");
		Date giorno = rs.getDate("PR.giorno");
		Date ora = rs.getTime("PR.ora", Calendar.getInstance());
		Medico medico = this.getMedico(rs);
		Paziente paziente = this.getPaziente(rs);
		TipologiaVisita tipologiaVisita = this.getTipologiaVisita(rs);
		
		return new Prenotazione(id, giorno, ora, tipologiaVisita, medico, paziente);
	}
	
	private TipologiaVisita getTipologiaVisita(ResultSet rs) throws SQLException {
		int id = rs.getInt("TV.id");
		String nome = rs.getString("TV.nome");					
		float prezzo_fisso = rs.getFloat("TV.prezzo_fisso");
		float costo_manodopera = rs.getFloat("TV.costo_manodopera");
		float costo_esercizio = rs.getFloat("TV.costo_esercizio");
		Specializzazione specializzazione = new Specializzazione(rs.getString("TVS.nome_specializzazione")); 
		ArrayList<Specializzazione> specializzazioniIdonee = new ArrayList<Specializzazione>(1);
		specializzazioniIdonee.add(specializzazione);
	
		return new TipologiaVisita(id, nome, prezzo_fisso, costo_manodopera, costo_esercizio, specializzazioniIdonee);
	}
	
	private Medico getMedico(ResultSet rs) throws SQLException {
		String nome = rs.getString("M.nome");
		String cognome = rs.getString("M.cognome");
		String email = rs.getString("M.email");
			
		MedicoHandler handler = new MedicoHandler();
		Medico medico = (Medico) handler.createElement(nome, cognome, email, "");
		medico.setCodice(rs.getInt("M.codice"));
		medico.setSpecializzazione(new Specializzazione(rs.getString("M.nome_specializzazione")));
		
		return medico;
	}
	
	private Paziente getPaziente(ResultSet rs) throws SQLException {
		String nome = rs.getString("P.nome");
		String cognome = rs.getString("P.cognome");
		String email = rs.getString("P.email");
			
		PazienteHandler handler = new PazienteHandler();
		Paziente paziente = (Paziente) handler.createElement(nome, cognome, email, "");
		paziente.setCodiceFiscale(rs.getString("P.codice_fiscale"));
		
		return paziente;
	}

	
/*
 **************************************** METODI IN PIU' **********************************************************************************
 */	
			
	/*
	 * 		GET GENERICO 	DA TESTARE
	 */
	public ArrayList<Paziente> getPazienti() {
		ArrayList<Paziente> pazienti = new ArrayList<Paziente>();
		
		String query = "select * "
					 + "from pazienti";
		
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
		
		String query = "select * "
					 + "from medici "
					 + "where codice = '" + codice + "'";
		
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
	 *		SD ???		DA TESTARE
	 */
	public Prenotazione getPrenotazione(int id) {
		Prenotazione prenotazione = null;
		
		String query = "select * "
					 + "from prenotazioni "
					 + "where id = '" + id + "'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()) {
				Date giorno = rs.getDate("giorno");
				Date ora = rs.getTime("ora", Calendar.getInstance());
				TipologiaVisita tipologiaVisita = this.getTipologiaVisita(rs.getInt("id_tipologia_visita"));
				Medico medico = this.getMedico(rs.getInt("codice_medico"));
				Paziente paziente = this.getPaziente(rs.getString("codice_fiscale_paziente"));
					
				prenotazione = new Prenotazione(id, giorno, ora, tipologiaVisita, medico, paziente);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prenotazione;
	}

	
	/*
	 *		SD ??? USATO IN getPrenotazione(int)
	 */
	public TipologiaVisita getTipologiaVisita(int id) {
		TipologiaVisita tipologiaVisita = null;
		
		String query = "select * "
				 + "from tipologie_visite TV "
				 + "join tipologie_visite_specializzazioni TVS on TV.id = TVS.id_tipologia_visita "
					 + "where id = '" + id + "'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()) {
				String nome = rs.getString("nome");					
				float prezzo_fisso = rs.getFloat("prezzo_fisso");
				float costo_manodopera = rs.getFloat("costo_manodopera");
				float costo_esercizio = rs.getFloat("costo_esercizio");
				ArrayList<Specializzazione> specializzazioniIdonee = new ArrayList<Specializzazione>();
				
				do {
					Specializzazione specializzazione = new Specializzazione(rs.getString("nome_specializzazione"));
					specializzazioniIdonee.add(specializzazione);
				} while (rs.next());
				
				tipologiaVisita = new TipologiaVisita(id, nome, prezzo_fisso, costo_manodopera, costo_esercizio, specializzazioniIdonee);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tipologiaVisita;
	}

	
	/*
	 * 		SD ???		DA TESTARE
	 */
	public Visita getVisita(int id) {
		Visita visita = null;
		
		String query = "select * "
					 + "from visite "
					 + "where id = '" + id + "'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()) {
				String diagnosi = rs.getString("diagnosi");
				String terapia = rs.getString("terapia");
				Prenotazione prenotazione = this.getPrenotazione(rs.getInt("id_prenotazione"));
				
				visita = new Visita(id, prenotazione, diagnosi, terapia);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return visita;
	}
		
	
	/*
	 * 		SD ???
	 */
	public TipologiaVisita getTipologiaVisita(String nome) {
		TipologiaVisita tipologiaVisita = null;
		
		String query = "select * "
					 + "from tipologie_visite TV "
					 + "join tipologie_visite_specializzazioni TVS on TV.id = TVS.id_tipologia_visita "
					 + "where nome = '" + nome + "'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()) {					
				int id = rs.getInt("id");
				float prezzo_fisso = rs.getFloat("prezzo_fisso");
				float costo_manodopera = rs.getFloat("costo_manodopera");
				float costo_esercizio = rs.getFloat("costo_esercizio");
				ArrayList<Specializzazione> specializzazioniIdonee = new ArrayList<Specializzazione>();
				
				do {
					Specializzazione specializzazione = new Specializzazione(rs.getString("nome_specializzazione"));
					specializzazioniIdonee.add(specializzazione);
				} while (rs.next());
				
				tipologiaVisita = new TipologiaVisita(id, nome, prezzo_fisso, costo_manodopera, costo_esercizio, specializzazioniIdonee);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tipologiaVisita;
	}

	
	/*
	 * 		SD ???		DA TESTARE
	 */
	public CalendarioDisponibilita getCalendarioDisponibilita(int codiceMedico, int anno) {
		CalendarioDisponibilita calendarioDisponibilita = null;
		
		String query = "select * "
					 + "from calendario_disponibilita "
					 + "where codice_medico = '" + codiceMedico + "' and anno = '" + anno + "'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()) {					
//				Medico medico = this.getMedico(rs.getInt("codice_medico"));
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return calendarioDisponibilita;
	}


	/*
	 * 		SD ???	 DA TESTARE
	 */
	public ArrayList<Visita> getVisite() {
		ArrayList<Visita> visite = new ArrayList<Visita>();
		
		String query = "select * "
					 + "from visite V "
					 + "join prenotazioni PR on V.id_prenotazione = PR.id "
					 + "join tipologie_visite TV on PR.id_tipologia_visita = TV.id "
					 + "join tipologie_visite_specializzazioni TVS on TV.id = TVS.id_tipologia_visita "
					 + "join medici M on PR.codice_medico = M.codice "
					 + "join pazienti P on PR.codice_fiscale_paziente = P.codice_fiscale "
			 		 + "order by TV.nome";

		try {
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				int id = rs.getInt("V.id");
				String diagnosi = rs.getString("diagnosi");
				String terapia = rs.getString("terapia");
				
				Prenotazione prenotazione = this.getPrenotazione(rs);
				String currentTV = rs.getString("TV.nome");
				String previousTV = null;
				
				if(!rs.isFirst()) {
					rs.previous();
					previousTV = rs.getString("TV.nome");
					rs.next();
				}
				if(!rs.isFirst() && previousTV.equals(currentTV)) {
					visite.get(visite.size()-1).getTipologiaVisita().getSpecializzazioniIdonee().add(new Specializzazione(rs.getString("TVS.nome_specializzazione")));
				} else {
					Visita visita = new Visita(id, prenotazione, diagnosi, terapia);
					visite.add(visita);
				}
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return visite;
}

		
/*
 **************************************** WORKING IN PROGRESS **********************************************************************************
 */
	
	/*
	 * 		SD Prenota Visita 	WORKING IN PROGRESS 	DA TESTARE
	 */
	@SuppressWarnings("deprecation")
	public CalendarioDisponibilita getCalendarioDisponibilita(int codiceMedico) {
		CalendarioDisponibilita calendarioDisponibilita = null;
		
		String query = "select * "
					 + "from calendario_disponibilita CD "
					 + "join disponibilita D on CD.id = D.id_calendario_disponibilita "
					 + "join medici M on CD.codice_medico = M.codice "
					 + "where codice_medico = '" + codiceMedico + "' "
					 + "and D.giorno > curdate()";
//		select * from calendario_disponibilita CD join disponibilita D on CD.id = D.id_calendario_disponibilita join medici M on CD.codice_medico = M.codice where codice_medico = '';
		try {
			ResultSet rs = statement.executeQuery(query);
			if(rs.next()) {
				int anno = 0;
				Medico medico = this.getMedico(rs);
				
				ArrayList<DisponibilitaGiornaliera> disponibilita = new ArrayList<DisponibilitaGiornaliera>();
				do {
					DisponibilitaGiornaliera disp = (DisponibilitaGiornaliera) DisponibilitaDirector.buildPart(new DisponibilitaGiornalieraBuilder(), rs);
					disponibilita.add(disp);
				} while(rs.next());
				
				if(!disponibilita.isEmpty())
					anno = disponibilita.get(1).getGiorno().getYear();
				
				calendarioDisponibilita = new CalendarioDisponibilita(anno, medico, disponibilita);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return calendarioDisponibilita;
	}


	/*
	 * 		SD Prenota visia. Chiamato da getCalendarioDisponibilita(int codiceMedico, String nomeTipologiaVisita)		DA TESTARE
	 */
	public ArrayList<Prenotazione> getPrenotazioni(int codiceMedico, String nomeTipologiaVisita){
		ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		
		String query = "select * "
					 	+ "from prenotazioni PR "
					 	+ "join tipologie_visite TV on PR.id_tipologia_visita = TV.id "
					 	+ "join tipologie_visite_specializzazioni TVS on TV.id = TVS.id_tipologia_visita "
					 	+ "join medici M on PR.codice_medico = M.codice "
					 	+ "join pazienti P on PR.codice_fiscale_paziente = P.codice_fiscale "
					 	+ "where PR.codice_medico = '" + codiceMedico + "' and TV.nome = '" + nomeTipologiaVisita + "' "
					 	+ "and PR.giorno > curdate() "
			 		 	+ "order by TV.nome";
//		select * from prenotazioni PR join tipologie_visite TV on PR.id_tipologia_visita = TV.id join tipologie_visite_specializzazioni TVS on TV.id = TVS.id_tipologia_visita join medici M on PR.codice_medico = M.codice join pazienti P on PR.codice_fiscale_paziente = P.codice_fiscale where PR.codice_medico = '' and TV.nome = '' order by TV.nome;
		try {
			ResultSet rs = statement.executeQuery(query);
		
			while(rs.next()) {
				int id = rs.getInt("PR.id");
				Date giorno = rs.getDate("PR.giorno");
				Date ora = rs.getTime("PR.ora", Calendar.getInstance());
				Medico medico = this.getMedico(rs);
				Paziente paziente = this.getPaziente(rs);
				
				TipologiaVisita tipologiaVisita = this.getTipologiaVisita(rs);
//				String currentTV = rs.getString("TV.nome");
//				String previousTV = null;
				
//				if(!rs.isFirst()) {
//					rs.previous();
//					previousTV = rs.getString("TV.nome");
//					rs.next();
//				}
//				
//				if(!rs.isFirst() && previousTV.equals(currentTV)) {
//					prenotazioni.get(prenotazioni.size()-1).getTipologiaVisita().getSpecializzazioniIdonee().add(new Specializzazione(rs.getString("TVS.nome_specializzazione")));
//				} else {
					Prenotazione prenotazione = new Prenotazione(id, giorno, ora, tipologiaVisita, medico, paziente);
					prenotazioni.add(prenotazione);
//				}
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prenotazioni;
	}


	/*
	 * 		SD Registrazione	TEST OK
	 */
	public void insertPaziente(Paziente p) {
		String insert = "insert into pazienti "
						+ "values ('" + p.getCodiceFiscale() + "', '" + p.getNome() + "', '" + p.getCognome() + "', '" + p.getEmail() + "', '" + p.getPassword() + "')";
		try {
			statement.executeUpdate(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 		SD Prenota visita		DA TESTARE
	 */
	public void insertPrenotazione(Prenotazione p) {
		String insert = "insert into prenotazioni (giorno, ora, id_tipologia_visita, codice_medico, codice_fiscale_paziente) "
						+ "values ('" + new SimpleDateFormat("YYYY-MM-dd").format(p.getGiorno()) + "', "
						+ "'" + new SimpleDateFormat("HH:mm:SS").format(p.getOra()) + "', "
						+ "'" + p.getTipologiaVisita().getId() + "', '" + p.getMedico().getCodice() + "', '" + p.getPaziente().getCodiceFiscale() + "')";
		try {
			statement.executeUpdate(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 		SD Modifica prenotazione visita		TEST OK
	 */
	public void updatePrenotazione(int id, Date giorno, Date ora, TipologiaVisita tipologiaVisita, Medico medico) {
		String update = "update prenotazioni set "
						+ "giorno = '" + new SimpleDateFormat("YYYY-MM-dd").format(giorno) + "', "
						+ "ora = '" + new SimpleDateFormat("HH:mm:SS").format(ora) + "', "
						+ "id_tipologia_visita = '" + tipologiaVisita.getId() + "', codice_medico = '" + medico.getCodice() + "' where id = '" + id + "'";
		try {
			statement.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/*
	 * 		SD Elimina prenotazione visita		TEST OK
	 */
	public void deletePrenotazione(int id) {
		String delete = "delete from prenotazioni "
						+ "where id = '" + id + "'";
		try {
			statement.executeUpdate(delete);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 		SD Esegue visita		DA TESTARE
	 */
	public void insertVisita(Visita v) {
		String insert = "insert into visite (diagnosi, terapia, id_prenotazione) "
						+ "values ('" + v.getDiagnosi() + "', '" + v.getTerapia() + "', '" + v.getPrenotazione().getId() + "')";
		try {
			statement.executeUpdate(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 		SD Genera fattura		DA TESTARE
	 */
	public void insertFattura(Fattura f) {
		String insert = "insert into fatture (importo, id_visita, codice_fiscale_paziente) "
						+ "values ('" + f.getImporto() + "', '" + f.getVisita().getId() + "', '" + f.getPaziente().getCodiceFiscale() + "')";
		try {
			statement.executeUpdate(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 		SD Paga visita		DA TESTARE
	 */
	public void insertPagamento(Pagamento p) {
		String insert = "insert into pagamenti (data, metodo_pagamento, id_fattura) "
						+ "values ('" + new SimpleDateFormat("YYYY-MM-dd").format(p.getDataPagamento()) + "', "
						+ "'" + p.getMetodoPagamento() + "', '" + p.getFattura().getId() + "')";
		try {
			statement.executeUpdate(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/*
	 * 		WORKING IN PROGRESS
	 */
	public void insertTipologiaVisita(TipologiaVisita tipologiaVisita) {

	}

	
	/*
	 * 		WORKING IN PROGRESS
	 */
	public void insertCalendarioDisponibilita(CalendarioDisponibilita calendarioDisponibilita) {

	}

	public int getIdFattura(Fattura fattura) {
		int id = 0;
		
		String query = "select * "
					 	+ "from fatture "
					 	+ "where id_visita = '" + fattura.getVisita().getId() + "'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			
			if (rs.next()) {
				id = rs.getInt("id");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}
}