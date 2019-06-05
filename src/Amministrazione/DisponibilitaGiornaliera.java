package Amministrazione;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
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
	
	@Override
	public String toString() {
		return GUI.FramePaziente.DATE_SDF.format(giorno);
	}

	public void print(PrintStream out) {
		out.println(this.giorno);
		out.println(new SimpleDateFormat("HH:mm:SS").format(super.getOraInizio()));
		out.println(new SimpleDateFormat("HH:mm:SS").format(super.getOraFine()));
		out.println(super.getMaxNumVisite());
		out.println(this.presenza);
	}
}
