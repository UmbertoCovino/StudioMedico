package Utenti;

import java.io.PrintStream;

public class Medico extends Utente {
	private int codice;
	private Specializzazione specializzazione;
	
	public Medico() {
		super();
	}
	
	public Medico(String nome, String cognome, String email, String password) {
		super(nome, cognome, email, password, false);
	}

	public int getCodice() {
		return codice;
	}
	
	public void setCodice(int codice) {
		this.codice = codice;
	}
	
	public Specializzazione getSpecializzazione() {
		return specializzazione;
	}
	
	public void setSpecializzazione(Specializzazione specializzazione) {
		this.specializzazione = specializzazione;
	}

	public void print(PrintStream out) {
		out.println(codice);
		super.print(out);
		specializzazione.print(out);
	}

	@Override
	public String toString() {
		return getNome() + " " + getCognome() + ", " + specializzazione;
	}
}
