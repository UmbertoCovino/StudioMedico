package Amministrazione;

import Persistenza.GestoreDatabase;

import java.util.ArrayList;

import GUI.FormCreazioneReport;
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
		Report report = null;
		
		if (tipologia.equals(FormCreazioneReport.TIPOLOGIE_REPORT[0])) {
			report = gestoreDB.getReportVisite();
			
			if (report == null) {
				report = new ReportVisite();
				report.setTipologia(tipologia);
			}
		} else if (tipologia.equals(FormCreazioneReport.TIPOLOGIE_REPORT[2])) {
			report = gestoreDB.getReportMedici();
			
			if (report == null) {
				report = new ReportMedici();
				report.setTipologia(tipologia);
			}
		} else if (tipologia.equals(FormCreazioneReport.TIPOLOGIE_REPORT[3])) {
			report = gestoreDB.getReportTipologieVisite();
			
			if (report == null) {
				report = new ReportTipoVisite();
				report.setTipologia(tipologia);
			}
		}
		
		return report;
	}

	public ArrayList<Medico> getMedici() {
		return gestoreDB.getMedici();
	}

	public Report createReport(String tipologia, Medico medico) {
		Report report = null;

		if (tipologia.equals(FormCreazioneReport.TIPOLOGIE_REPORT[1])) {
			report = gestoreDB.getReportVisitePerMedico(medico.getCodice());
		
			if (report == null) {
				report = new ReportVisitePerMedico();
				report.setTipologia(tipologia);
			}
		}

		return report;
	}
}
