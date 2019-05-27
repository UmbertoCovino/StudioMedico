package Utenti;

import java.sql.SQLException;

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

	private GUIControllerUtenti() throws ClassNotFoundException, SQLException {
		gestoreUtenti = GestoreUtenti.getInstance();
	}

	public static GUIControllerUtenti getInstance() throws ClassNotFoundException, SQLException {
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
		return null;
	}

	public void getPaziente(String codiceFiscale) {
		gestoreUtenti.getPaziente(codiceFiscale);
	}
}
