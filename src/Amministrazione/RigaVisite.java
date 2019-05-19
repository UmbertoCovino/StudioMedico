package Amministrazione;

import java.util.Date;

public class RigaVisite {
	private Date giorno;
	private Date ora;
	private String nomeTipologiaVisita;
	private String nomeMedico;
	private String nomePaziente;
	private ReportVisite reportVisite;
	
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
	
	public String getNomeMedico() {
		return nomeMedico;
	}
	
	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}
	
	public String getNomePaziente() {
		return nomePaziente;
	}
	
	public void setNomePaziente(String nomePaziente) {
		this.nomePaziente = nomePaziente;
	}
	
	public ReportVisite getReportVisite() {
		return reportVisite;
	}
	
	public void setReportVisite(ReportVisite reportVisite) {
		this.reportVisite = reportVisite;
	}
}
