package Amministrazione;

public class RigaTipoVisite {
	private String nome;
	private float prezzoFisso;
	private float costoManodopera;
	private float costoEsercizio;
	private int numeroVisite;
	
	public RigaTipoVisite(String nome, float prezzoFisso, float costoManodopera, float costoEsercizio, int numeroVisite) {
		this.nome = nome;
		this.prezzoFisso = prezzoFisso;
		this.costoManodopera = costoManodopera;
		this.costoEsercizio = costoEsercizio;
		this.numeroVisite = numeroVisite;
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
	
	public int getNumeroVisite() {
		return numeroVisite;
	}
	
	public void setNumeroVisite(int numeroVisite) {
		this.numeroVisite = numeroVisite;
	}
}
