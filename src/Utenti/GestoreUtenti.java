package Utenti;

import Persistenza.GestoreDatabase;

public class GestoreUtenti {
	private static GestoreUtenti instance;
	private GestoreDatabase gestoreDB;
	private UtenteHandler creator;
	private UtenteHandler utenteHandler;

	private GestoreUtenti() { }

	public static GestoreUtenti getInstance() {
		if(instance == null)
			instance = new GestoreUtenti();
		return instance;
	}

	public GestoreDatabase getGestoreDB() {
		return gestoreDB;
	}

	public void setGestoreDB(GestoreDatabase gestoreDB) {
		this.gestoreDB = gestoreDB;
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

	public void registerPaziente(String nome, String cognome, String email, String password, String codiceFiscale) {

	}

	public Utente authentication(String email, String password) {
		return null;
	}

	public Paziente getPaziente(String codiceFiscale) {
		return null;
	}
}
