package Visite;

import java.io.PrintStream;

import Utenti.Paziente;

public class Fattura {
	private int id;
	private float importo;
	private Visita visita;
	private Paziente paziente;
	
	public Fattura(Visita visita) {
		this.importo = visita.getTipologiaVisita().getCostoEsercizio() +
						visita.getTipologiaVisita().getCostoManodopera() +
						visita.getTipologiaVisita().getPrezzoFisso();
		this.visita = visita;
		this.paziente = visita.getPaziente();
	}

	public Fattura(int id, Visita visita) {
		this.id = id;
		this.importo = visita.getTipologiaVisita().getCostoEsercizio() +
				visita.getTipologiaVisita().getCostoManodopera() +
				visita.getTipologiaVisita().getPrezzoFisso();
		this.visita = visita;
		this.paziente = visita.getPaziente();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public float getImporto() {
		return importo;
	}
	
	public void setImporto(float importo) {
		this.importo = importo;
	}
	
	public Visita getVisita() {
		return visita;
	}
	
	public void setVisita(Visita visita) {
		this.visita = visita;
	}
	
	public Paziente getPaziente() {
		return paziente;
	}

	public void setPaziente(Paziente paziente) {
		this.paziente = paziente;
	}

	public void print(PrintStream out) {
		out.println(id);
		out.println(importo);
		visita.print(out);
	}
}
