package Amministrazione;

import java.util.ArrayList;
import Utenti.Medico;

public class CalendarioDisponibilita {
	private ArrayList<DisponibilitaGiornaliera> orari;
	private int anno;
	private boolean approvato;
	private Medico medico;
	
	public CalendarioDisponibilita(int anno, Medico medico, ArrayList<DisponibilitaGiornaliera> disponibilita) {
		this.anno = anno;
		this.medico = medico;
		this.orari = disponibilita;
	}

	public ArrayList<DisponibilitaGiornaliera> getOrari() {
		return orari;
	}

	public void setOrari(ArrayList<DisponibilitaGiornaliera> orari) {
		this.orari = orari;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public boolean getApprovato() {
		return approvato;
	}

	public void setApprovato(boolean approvato) {
		this.approvato = approvato;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public void addDisponibilita(Disponibilita disponibilita) {

	}

	public boolean removeDisponibilita(Disponibilita disponibilita) {
		return false;
	}
}
