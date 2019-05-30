package Utenti;

import java.io.PrintStream;

public class Specializzazione {
	private String nome;

	public Specializzazione(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}

	public void print(PrintStream out) {
		out.println(nome);
	}
}
