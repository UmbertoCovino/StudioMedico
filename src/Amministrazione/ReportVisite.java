package Amministrazione;

import java.util.ArrayList;

public class ReportVisite extends Report {
	private ArrayList<RigaVisite> risultato;

	public ReportVisite() {
		risultato = new ArrayList<RigaVisite>();
	}
	
	public ArrayList<RigaVisite> getRisultato() {
		return risultato;
	}

	public void setRisultato(ArrayList<RigaVisite> risultato) {
		this.risultato = risultato;
	}

	public void addRiga(RigaVisite riga) {
		risultato.add(riga);
	}
}
