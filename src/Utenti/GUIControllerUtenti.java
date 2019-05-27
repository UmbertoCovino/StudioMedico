package Utenti;

import GUI.FormRegistrazionePaziente;
import GUI.FormRichiestaPaziente;
import GUI.Frame;
import GUI.FrameLogin;
import GUI.FrameMedico;
import GUI.FramePaziente;
import GUI.FrameProprietario;

public class GUIControllerUtenti {
	private static GUIControllerUtenti instance;
	private GestoreUtenti gestoreUtenti;

	private GUIControllerUtenti() {
		gestoreUtenti = GestoreUtenti.getInstance();
	}

	public static GUIControllerUtenti getInstance() {
		if(instance == null)
			instance = new GUIControllerUtenti();
		return instance;
	}
	
	public void createFrameLogin() {
		new FrameLogin();
	}

	public void createFormRegistrazionePaziente(Frame parentFrame) {
		new FormRegistrazionePaziente(parentFrame);
	}

	public Utente notifyCredentials(String email, String password) {
		return gestoreUtenti.authentication(email, password);
	}

	public void createFrameMedico(Medico medico) {
		new FrameMedico(medico);
	}

	public void createFramePaziente(Paziente paziente) {
		new FramePaziente(paziente);
	}

	public void createFrameProprietario(Utente utente) {
		new FrameProprietario(utente);
	}

	public void notifyData(String nome, String cognome, String email, String password, String codiceFiscale) {
		gestoreUtenti.registerPaziente(nome, cognome, email, password, codiceFiscale);
	}

	public void createFormRichiestaPaziente(Frame parentFrame) {
		new FormRichiestaPaziente(parentFrame);
	}

	public Paziente getPaziente(String codiceFiscale) {
		return gestoreUtenti.getPaziente(codiceFiscale);
	}

	public void setPazienteFound(FrameMedico frameMedico, Paziente paziente) {
		frameMedico.setPazienteFound(paziente);
	}
}
