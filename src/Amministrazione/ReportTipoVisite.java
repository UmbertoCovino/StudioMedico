package Amministrazione;

import java.util.ArrayList;

public class ReportTipoVisite extends Report {
	private ArrayList<RigaTipoVisite> risultato;

	public ArrayList<RigaTipoVisite> getRisultato() {
		return risultato;
	}

	public void setRisultato(ArrayList<RigaTipoVisite> risultato) {
		this.risultato = risultato;
	}

	public void addRiga(RigaTipoVisite riga) {
		risultato.add(riga);	
	}
}
