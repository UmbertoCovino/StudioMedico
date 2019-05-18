package Amministrazione;

import java.util.ArrayList;
import Utenti.Medico;

public class CalendarioDisponibilita {
	private ArrayList<Disponibilita> orari;
	private int anno;
	private boolean[] approvato;
	private Medico medico;
	
	public ArrayList<Disponibilita> getOrari() {
		return orari;
	}

	public void setOrari(ArrayList<Disponibilita> orari) {
		this.orari = orari;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public boolean[] getApprovato() {
		return approvato;
	}

	public void setApprovato(boolean[] approvato) {
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
