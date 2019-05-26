package Utenti;

public abstract class UtenteHandler {

	protected abstract Utente newElement(String nome, String cognome, String email, String password);

	public Utente createElement(String nome, String cognome, String email, String password) {	
		return newElement(nome, cognome, email, password);
	}

}
