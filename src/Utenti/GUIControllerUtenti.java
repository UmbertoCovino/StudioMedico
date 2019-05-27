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
	
	private Paziente paziente;

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

	public Paziente createFormRichiestaPaziente(Frame parentFrame) {
		FormRichiestaPaziente f = new FormRichiestaPaziente(parentFrame);
		
		//dobbiamo fare un lock mentre aspettiamo il paziente da getPaz..()
		waitUntilPazienteReturns();
		
		return paziente;
	}

	private void waitUntilPazienteReturns() {
		//lock
	}

	public void getPaziente(String codiceFiscale) {
		paziente = gestoreUtenti.getPaziente(codiceFiscale);
		
		//unlock
	}
}
