package Amministrazione;

public class DisponibilitaOraria {
	private int oraInizio;
	private int oraFine;
	private boolean occupato;
	
	public int getOraInizio() {
		return oraInizio;
	}
	
	public void setOraInizio(int oraInizio) {
		this.oraInizio = oraInizio;
	}
	
	public int getOraFine() {
		return oraFine;
	}
	
	public void setOraFine(int oraFine) {
		this.oraFine = oraFine;
	}
	
	public boolean isOccupato() {
		return occupato;
	}
	
	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}
}
