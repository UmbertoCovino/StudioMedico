package Amministrazione;

import java.util.ArrayList;

import GUI.FormCreazioneReport;
import GUI.FormVisualizzazioneReport;
import GUI.Frame;
import Utenti.Medico;

public class GUIControllerAmministrazione {
	private static GUIControllerAmministrazione instance;
	private GestoreAmministrazione gestoreAmministrazione;

	private GUIControllerAmministrazione() {
		gestoreAmministrazione = GestoreAmministrazione.getInstance();
	}

	public static GUIControllerAmministrazione getInstance() {
		if(instance == null)
			instance = new GUIControllerAmministrazione();
		return instance;
	}

	public void createFormCreazioneReport(Frame parentFrame) {
		new FormCreazioneReport(parentFrame);
	}

	public void createFormVisualizzazioneReport(Frame parentFrame, Report report) {
		new FormVisualizzazioneReport(parentFrame, report);
	}

	public void createReport(String tipologia) {
		gestoreAmministrazione.createReport(tipologia);
	}

	public ArrayList<Medico> getMedici() {
		return gestoreAmministrazione.getMedici();
	}

	public void createReport(String tipologia, Medico medico) {
		gestoreAmministrazione.createReport(tipologia, medico);
	}
}
