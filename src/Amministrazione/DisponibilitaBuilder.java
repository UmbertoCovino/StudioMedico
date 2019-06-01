package Amministrazione;

import java.util.Date;

public abstract class DisponibilitaBuilder {
	public abstract Disponibilita buildPart();
	
	public void setDisponibilita(Date oraInizio, Date oraFine, int maxNumVisite) { }
		
	public void setGiorno(Date giorno, boolean presenza) { }
	
	public void setGiorno(DayOfTheWeek dayOfTheWeek) { }
}
