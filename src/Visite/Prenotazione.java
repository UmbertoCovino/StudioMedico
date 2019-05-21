package Visite;

import java.util.Date;
import Utenti.Medico;
import Utenti.Paziente;

public class Prenotazione {
	private int id;
	private Date giorno;
	private Date ora;
	private TipologiaVisita tipologiaVisita;
	private Medico medico;
	private Paziente paziente;
	
	public Prenotazione(int id, Date giorno, Date ora, TipologiaVisita tipologiaVisita, Medico medico, Paziente paziente) {
		this.id = id;
		this.giorno = giorno;
		this.ora = ora;
		this.tipologiaVisita = tipologiaVisita;
		this.medico = medico;
		this.paziente = paziente;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getGiorno() {
		return giorno;
	}
	
	public void setGiorno(Date giorno) {
		this.giorno = giorno;
	}
	
	public Date getOra() {
		return ora;
	}
	
	public void setOra(Date ora) {
		this.ora = ora;
	}
	
	public TipologiaVisita getTipologiaVisita() {
		return tipologiaVisita;
	}
	
	public void setTipologiaVisita(TipologiaVisita tipologiaVisita) {
		this.tipologiaVisita = tipologiaVisita;
	}
	
	public Medico getMedico() {
		return medico;
	}
	
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	public Paziente getPaziente() {
		return paziente;
	}
	
	public void setPaziente(Paziente paziente) {
		this.paziente = paziente;
	}
}
