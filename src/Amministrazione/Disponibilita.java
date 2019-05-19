package Amministrazione;

import java.util.Date;

public abstract class Disponibilita {
	private Date oraInizio;
	private Date oraFine;
	private int maxNumVisite;
	
	public Date getOraInizio() {
		return oraInizio;
	}
	
	public void setOraInizio(Date oraInizio) {
		this.oraInizio = oraInizio;
	}
	
	public Date getOraFine() {
		return oraFine;
	}
	
	public void setOraFine(Date oraFine) {
		this.oraFine = oraFine;
	}
	
	public int getMaxNumVisite() {
		return maxNumVisite;
	}
	
	public void setMaxNumVisite(int maxNumVisite) {
		this.maxNumVisite = maxNumVisite;
	}
}
