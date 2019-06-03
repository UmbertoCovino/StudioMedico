package Visite;

import java.io.PrintStream;
import java.util.ArrayList;

import Utenti.Specializzazione;

public class TipologiaVisita {
	private int id;
	private String nome;
	private float prezzoFisso;
	private float costoManodopera;
	private float costoEsercizio;
	private ArrayList<Specializzazione> specializzazioniIdonee;
	
	public TipologiaVisita(int id, String nome, float prezzoFisso, float costoManodopera, float costoEsercizio,
			ArrayList<Specializzazione> specializzazioniIdonee) {
		this.id = id;
		this.nome = nome;
		this.prezzoFisso = prezzoFisso;
		this.costoManodopera = costoManodopera;
		this.costoEsercizio = costoEsercizio;
		this.specializzazioniIdonee = specializzazioniIdonee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public float getPrezzoFisso() {
		return prezzoFisso;
	}
	
	public void setPrezzoFisso(float prezzoFisso) {
		this.prezzoFisso = prezzoFisso;
	}
	
	public float getCostoManodopera() {
		return costoManodopera;
	}
	
	public void setCostoManodopera(float costoManodopera) {
		this.costoManodopera = costoManodopera;
	}
	
	public float getCostoEsercizio() {
		return costoEsercizio;
	}
	
	public void setCostoEsercizio(float costoEsercizio) {
		this.costoEsercizio = costoEsercizio;
	}
	
	public ArrayList<Specializzazione> getSpecializzazioniIdonee() {
		return specializzazioniIdonee;
	}
	
	public void setSpecializzazioniIdonee(ArrayList<Specializzazione> specializzazioniIdonee) {
		this.specializzazioniIdonee = specializzazioniIdonee;
	}

	public void print(PrintStream out) {
		out.println(nome);
		out.println(prezzoFisso);
		out.println(costoManodopera);
		out.println(costoEsercizio);
		out.println("Specializzazioni idonee: " + specializzazioniIdonee.toString());
	}

	@Override
	public String toString() {
		return nome;
	}
}
