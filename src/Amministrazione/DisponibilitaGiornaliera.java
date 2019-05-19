package Amministrazione;

import java.util.Date;

public class DisponibilitaGiornaliera extends Disponibilita {
	private Date giorno;
	private boolean presenza;
	
	public Date getGiorno() {
		return giorno;
	}
	
	public void setGiorno(Date giorno) {
		this.giorno = giorno;
	}
	
	public boolean isPresenza() {
		return presenza;
	}
	
	public void setPresenza(boolean presenza) {
		this.presenza = presenza;
	}
}
