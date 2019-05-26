package Utenti;

public class PazienteHandler extends UtenteHandler {

	protected Utente newElement(String nome, String cognome, String email, String password) {
		return new Paziente(nome, cognome, email, password);
	}

}
