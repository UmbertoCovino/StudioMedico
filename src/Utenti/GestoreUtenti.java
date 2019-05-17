package Utenti;

import Persistenza.GestoreDatabase;

public class GestoreUtenti {

	private static GestoreUtenti instance;

	private GestoreDatabase gestoreDB;

	private UtenteHandler creator;

	private UtenteHandler utenteHandler;

	private GestoreUtenti() {

	}

	public static GestoreUtenti getInstance() {
		return null;
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
