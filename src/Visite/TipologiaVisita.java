package Visite;

import Utenti.Specializzazione;

public class TipologiaVisita {
	private String nome;
	private float prezzoFisso;
	private float costoManodopera;
	private float costoEsercizio;
	private Specializzazione[] specializzazioniIdonee;
	
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
	
	public Specializzazione[] getSpecializzazioniIdonee() {
		return specializzazioniIdonee;
	}
	
	public void setSpecializzazioniIdonee(Specializzazione[] specializzazioniIdonee) {
		this.specializzazioniIdonee = specializzazioniIdonee;
	}
}
