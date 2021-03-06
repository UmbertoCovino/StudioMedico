package Utenti;

import java.io.PrintStream;

public class Utente {
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private boolean admin;
	
	public Utente() { }
	
	public Utente(String nome, String cognome, String email, String password, boolean admin) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.admin = admin;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public void print(PrintStream out) {
		out.println(nome);
		out.println(cognome);
		out.println(email);
	}
}
