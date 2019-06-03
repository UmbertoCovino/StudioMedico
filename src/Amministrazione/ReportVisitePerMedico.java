package Amministrazione;

import Utenti.Medico;
import java.util.ArrayList;

public class ReportVisitePerMedico extends Report {
	private Medico medico;
	private ArrayList<RigaVisitePerMedico> risultato;
	
	public ReportVisitePerMedico() {
		medico = new Medico();
		risultato = new ArrayList<RigaVisitePerMedico>();
	}
	
	public Medico getMedico() {
		return medico;
	}
	
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	public ArrayList<RigaVisitePerMedico> getRisultato() {
		return risultato;
	}
	
	public void setRisultato(ArrayList<RigaVisitePerMedico> risultato) {
		this.risultato = risultato;
	}

	public void addRiga(RigaVisitePerMedico riga) {
		risultato.add(riga);
	}

	@Override
	public String toString() {
		return "ReportVisitePerMedico [medico=" + medico + ", risultato=" + risultato + "]";
	}
}
