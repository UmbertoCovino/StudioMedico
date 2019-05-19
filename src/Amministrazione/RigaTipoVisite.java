package Amministrazione;

public class RigaTipoVisite {
	private String nome;
	private float prezzoFisso;
	private float costoManodopera;
	private float costoEsercizio;
	private int numeroVisite;
	private ReportTipoVisite reportTipoVisite;
	
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
	
	public int getNumeroVisite() {
		return numeroVisite;
	}
	
	public void setNumeroVisite(int numeroVisite) {
		this.numeroVisite = numeroVisite;
	}
	
	public ReportTipoVisite getReportTipoVisite() {
		return reportTipoVisite;
	}
	
	public void setReportTipoVisite(ReportTipoVisite reportTipoVisite) {
		this.reportTipoVisite = reportTipoVisite;
	}
}
