package Amministrazione;

public class RigaMedici {
	private String nome;
	private String cognome;
	private String email;
	private int codice;
	private String nomeSpecializzazione;
	private int numeroVisite;
	private ReportMedici reportMedici;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getCodice() {
		return codice;
	}
	
	public void setCodice(int codice) {
		this.codice = codice;
	}
	
	public String getNomeSpecializzazione() {
		return nomeSpecializzazione;
	}
	
	public void setNomeSpecializzazione(String nomeSpecializzazione) {
		this.nomeSpecializzazione = nomeSpecializzazione;
	}
	
	public int getNumeroVisite() {
		return numeroVisite;
	}
	
	public void setNumeroVisite(int numeroVisite) {
		this.numeroVisite = numeroVisite;
	}
	
	public ReportMedici getReportMedici() {
		return reportMedici;
	}
	
	public void setReportMedici(ReportMedici reportMedici) {
		this.reportMedici = reportMedici;
	}
}
