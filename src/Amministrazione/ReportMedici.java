package Amministrazione;

import java.util.ArrayList;

public class ReportMedici extends Report {
	private ArrayList<RigaMedici> risultato;

	public ArrayList<RigaMedici> getRisultato() {
		return risultato;
	}

	public void setRisultato(ArrayList<RigaMedici> risultato) {
		this.risultato = risultato;
	}
	
	public void addRiga(RigaMedici riga) {
		risultato.add(riga);
	}
}
