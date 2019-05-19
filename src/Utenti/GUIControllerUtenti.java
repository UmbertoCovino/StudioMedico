package Utenti;

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

	public void createFormRegistrazionePaziente() {

	}

	public Utente notifyCredentials(String email, String password) {
		return null;
	}

	public void createFrameMedico(Medico medico) {

	}

	public void createFramePaziente(Paziente paziente) {

	}

	public void createFrameProprietario(Utente utente) {

	}

	public void notifyData(String nome, String cognome, String email, String password, String codiceFiscale) {

	}

	public Paziente createFormRichiestaPaziente() {
		return null;
	}

	public void getPaziente(String codiceFiscale) {

	}

}
