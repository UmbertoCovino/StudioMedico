package Amministrazione;

import Persistenza.GestoreDatabase;

import java.util.ArrayList;
import Utenti.Medico;

public class GestoreAmministrazione {
	private static GestoreAmministrazione instance;
	private GestoreDatabase gestoreDB;

	private GestoreAmministrazione() {
		gestoreDB = GestoreDatabase.getInstance();
	}

	public static GestoreAmministrazione getInstance() {
		if(instance == null)
			instance = new GestoreAmministrazione();
		return instance;
	}

	public Report createReport(String tipologia) {
		Report report;
		switch(tipologia) {
			case "Elenco di visite effettuare ordinate per medico":
				report = gestoreDB.getReportVisite();
				break;
			case "Elenco dei medici ordinati per numero di visite":
				report = gestoreDB.getReportMedici();
				break;
			case "Elenco delle tipologie di visite ordinate per numerosita":
				report = gestoreDB.getReportTipologieVisite();
				break;
			default:
				report = null;
		}
		return report;
	}

	public ArrayList<Medico> getMedici() {
		return gestoreDB.getMedici();
	}

	public Report createReport(String tipologia, Medico medico) {
		Report report = null;
		
		if(tipologia.equals("Elenco di visite effettuare ordinate per giorno con indicazione del medico"))
			report = gestoreDB.getReportVisitePerMedico(medico.getCodice());
		
		return report;
	}
}
