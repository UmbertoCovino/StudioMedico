package Utenti;

import GUI.FormRegistrazionePaziente;
import GUI.FormRichiestaPaziente;
import GUI.Frame;
import GUI.FrameMedico;
import GUI.FramePaziente;
import GUI.FrameProprietario;

public class GUIControllerUtenti {
	private static GUIControllerUtenti instance;
	private GestoreUtenti gestoreUtenti;

	private GUIControllerUtenti() { }

	public static GUIControllerUtenti getInstance() {
		if(instance == null)
			instance = new GUIControllerUtenti();
		return instance;
	}

	public GestoreUtenti getGestoreUtenti() {
		return gestoreUtenti;
	}

	public void setGestoreUtenti(GestoreUtenti gestoreUtenti) {
		this.gestoreUtenti = gestoreUtenti;
	}

	public void createFormRegistrazionePaziente(Frame parentFrame) {
		new FormRegistrazionePaziente(parentFrame);
	}

	public Utente notifyCredentials(String email, String password) {
		return gestoreUtenti.authentication(email, password);
	}

	public void createFrameMedico(Medico medico) {
		new FrameMedico();
	}

	public void createFramePaziente(Paziente paziente) {
		new FramePaziente();
	}

	public void createFrameProprietario(Utente utente) {
		new FrameProprietario();
	}

	public void notifyData(String nome, String cognome, String email, String password, String codiceFiscale) {
		gestoreUtenti.registerPaziente(nome, cognome, email, password, codiceFiscale);
	}

	public Paziente createFormRichiestaPaziente() {
		FormRichiestaPaziente f = new FormRichiestaPaziente();
		return null;
	}

	public void getPaziente(String codiceFiscale) {
		gestoreUtenti.getPaziente(codiceFiscale);
	}
}
