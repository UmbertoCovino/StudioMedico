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
		if(gestoreDB.isUtenteGiaPresente(email)) {
			//popup "email gia registrata"
		} else if(gestoreDB.isPazienteGiaPresente(codiceFiscale)) {
			//popup "email gia registrata"
		} else {			
			PazienteHandler handler = new PazienteHandler();
			Paziente paziente = (Paziente) handler.createElement();
			
			//valutare se fare tutti questi set o modificare il metodo createElement()
			paziente.setNome(nome);
			paziente.setCognome(cognome);
			paziente.setEmail(email);
			paziente.setPassword(password);
			paziente.setCodiceFiscale(codiceFiscale);
			
			gestoreDB.insertPaziente(paziente);
			//popup "registrazione avvenuta con successo"
		}
	}

	public Utente authentication(String email, String password) {
		return gestoreDB.getUtente(email, password);
	}

	public Paziente getPaziente(String codiceFiscale) {
		return gestoreDB.getPaziente(codiceFiscale);
	}
}
