package Utenti;

public class MedicoHandler extends UtenteHandler {

	protected Utente newElement(String nome, String cognome, String email, String password) {
		return new Medico(nome, cognome, email, password);
	}

}
