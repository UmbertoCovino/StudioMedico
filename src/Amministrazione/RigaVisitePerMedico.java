package Amministrazione;

import java.util.Date;

public class RigaVisitePerMedico {
	private Date giorno;
	private Date ora;
	private String nomeTipologiaVisita;
	private String nomePaziente;
	
	public RigaVisitePerMedico(Date giorno, Date ora, String nomeTipologiaVisita, String nomePaziente) {
		this.giorno = giorno;
		this.ora = ora;
		this.nomeTipologiaVisita = nomeTipologiaVisita;
		this.nomePaziente = nomePaziente;
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
	
	public String getNomeTipologiaVisita() {
		return nomeTipologiaVisita;
	}
	
	public void setNomeTipologiaVisita(String nomeTipologiaVisita) {
		this.nomeTipologiaVisita = nomeTipologiaVisita;
	}
	
	public String getNomePaziente() {
		return nomePaziente;
	}
	
	public void setNomePaziente(String nomePaziente) {
		this.nomePaziente = nomePaziente;
	}
}
