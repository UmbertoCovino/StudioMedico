package Amministrazione;

import java.util.ArrayList;
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

	}

	public void createFormVisualizzazioneReport() {

	}

	public void createReport(String tipologia) {

	}

	public ArrayList<Medico> getMedici() {
		return null;
	}

	public void createReport(String tipologia, Medico medico) {

	}
}
