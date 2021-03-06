package Utenti;

import Persistenza.GestoreDatabase;

public class GestoreUtenti {
	private static GestoreUtenti instance;
	private GestoreDatabase gestoreDB;
	private UtenteHandler creator;
	private UtenteHandler utenteHandler;

	private GestoreUtenti() {
		gestoreDB = GestoreDatabase.getInstance();
	}

	public static GestoreUtenti getInstance() {
		if(instance == null)
			instance = new GestoreUtenti();
		return instance;
	}

	public UtenteHandler getCreator() {
		return creator;
	}

	public void setCreator(UtenteHandler creator) {
		this.creator = creator;
	}

	public UtenteHandler getUtenteHandler() {
		return utenteHandler;
	}

	public void setUtenteHandler(UtenteHandler utenteHandler) {
		this.utenteHandler = utenteHandler;
	}

	public void registerPaziente(String nome, String cognome, String email, String password, String codiceFiscale) throws EmailGiaRegistrataException, CodiceFiscaleGiaRegistratoException {
		if(gestoreDB.isUtenteGiaPresente(email)) {
			throw new EmailGiaRegistrataException(email);			
		} else if(gestoreDB.isPazienteGiaPresente(codiceFiscale)) {
			throw new CodiceFiscaleGiaRegistratoException(codiceFiscale);
		} else {			
			PazienteHandler handler = new PazienteHandler();
			Paziente paziente = (Paziente) handler.createElement(nome, cognome, email, password);
			paziente.setCodiceFiscale(codiceFiscale);
			
			gestoreDB.insertPaziente(paziente);
		}
	}

	public Utente authentication(String email, String password) {
		Utente utente = gestoreDB.getUtente(email);
		if(utente != null && utente.getPassword().equals(password)) {
			utente.setPassword("");
			return utente;
		}
		
		return null;
	}

	public Paziente getPaziente(String codiceFiscale) {
		return gestoreDB.getPaziente(codiceFiscale);
	}
}
