package Amministrazione;

import java.util.Date;

public class DisponibilitaGiornalieraBuilder extends DisponibilitaBuilder {
	DisponibilitaGiornaliera disponibilita;

	public Disponibilita buildPart() {
		return disponibilita;
	}
	
	public void setDisponibilita(Date oraInizio, Date oraFine, int maxNumVisite) {
		disponibilita.setOraInizio(oraInizio);
		disponibilita.setOraFine(oraFine);
		disponibilita.setMaxNumVisite(maxNumVisite);
	}
	
	public void setGiorno(Date giorno, boolean presenza) {
		disponibilita.setGiorno(giorno);
		disponibilita.setPresenza(presenza);
	}
}
