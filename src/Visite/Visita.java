package Visite;

import java.io.PrintStream;
import java.util.Date;
import Utenti.Medico;
import Utenti.Paziente;

public class Visita {
	private int id;
	private Date giorno;
	private Date ora;
	private String diagnosi;
	private String terapia;
	private TipologiaVisita tipologiaVisita;
	private Medico medico;
	private Paziente paziente;
	private Prenotazione prenotazione;
	
	public Visita(int id, Prenotazione prenotazione, String diagnosi, String terapia) {
		this.id = id;
		this.giorno = prenotazione.getGiorno();
		this.ora = prenotazione.getOra();
		this.diagnosi = diagnosi;
		this.terapia = terapia;
		this.tipologiaVisita = prenotazione.getTipologiaVisita();
		this.medico = prenotazione.getMedico();
		this.paziente = prenotazione.getPaziente();
		this.prenotazione = prenotazione;
	}

	public Visita(Prenotazione prenotazione, String diagnosi, String terapia) {
		this.giorno = prenotazione.getGiorno();
		this.ora = prenotazione.getOra();
		this.diagnosi = diagnosi;
		this.terapia = terapia;
		this.tipologiaVisita = prenotazione.getTipologiaVisita();
		this.medico = prenotazione.getMedico();
		this.paziente = prenotazione.getPaziente();
		this.prenotazione = prenotazione;
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
	
	public String getDiagnosi() {
		return diagnosi;
	}
	
	public void setDiagnosi(String diagnosi) {
		this.diagnosi = diagnosi;
	}
	
	public String getTerapia() {
		return terapia;
	}
	
	public void setTerapia(String terapia) {
		this.terapia = terapia;
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
	
	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}

	public void print(PrintStream out) {
		out.println(id);
		out.println(diagnosi);
		out.println(terapia);
		prenotazione.print(out);
	}
}
