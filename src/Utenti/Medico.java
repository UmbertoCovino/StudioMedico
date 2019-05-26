package Utenti;

public class Medico extends Utente {
	private int codice;
	private Specializzazione specializzazione;
	
	public Medico(String nome, String cognome, String email, String password) {
		super(nome, cognome, email, password);
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
}
