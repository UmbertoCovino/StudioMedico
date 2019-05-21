package Amministrazione;

import java.util.ArrayList;

import GUI.FormCreazioneReport;
import GUI.FormVisualizzazioneReport;
import Utenti.Medico;

public class GUIControllerAmministrazione {
	private static GUIControllerAmministrazione instance;
	private GestoreAmministrazione gestoreAmministrazione;

	private GUIControllerAmministrazione() { }

	public static GUIControllerAmministrazione getInstance() {
		if(instance == null)
			instance = new GUIControllerAmministrazione();
		return instance;
	}

	public GestoreAmministrazione getGestoreAmministrazione() {
		return gestoreAmministrazione;
	}

	public void setGestoreAmministrazione(GestoreAmministrazione gestoreAmministrazione) {
		this.gestoreAmministrazione = gestoreAmministrazione;
	}

	public void createFormCreazioneReport() {
		new FormCreazioneReport();
	}

	public void createFormVisualizzazioneReport() {
		new FormVisualizzazioneReport();
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
