package Utenti;

public class Paziente extends Utente {
	private String codiceFiscale;

	public Paziente (String nome, String cognome, String email, String password) {
		super(nome, cognome, email, password, false);
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
}
