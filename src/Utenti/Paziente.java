package Utenti;

import java.io.PrintStream;

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

	public void print(PrintStream out) {
		out.println(super.getNome());
		out.println(super.getCognome());
		out.println(super.getEmail());
		out.println(codiceFiscale);
	}
}
