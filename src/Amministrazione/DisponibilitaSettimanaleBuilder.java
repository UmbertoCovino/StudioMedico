package Amministrazione;

import java.util.Date;

public class DisponibilitaSettimanaleBuilder extends DisponibilitaBuilder {

	public Disponibilita buildPart() {
		return null;
	}

	public void setDisponibilita(Date oraInizio, Date oraFine, int maxNumVisite) { }
	
	public void setGiorno(DayOfTheWeek dayOfTheWeek) { }
}
