package Client;

import java.util.ArrayList;
import java.util.Date;

import GUI.FormRichiestaPaziente;
import GUI.FrameLogin;
import Visite.Prenotazione;

public class Client { 

	public static void main(String[] args) {
		// inizializzazione database?
		
		new FrameLogin();
		// oppure GUIControllerUtenti.getInstance().createFrameLogin()???
		
		// TEST - da cancellare
		//new GUI.FrameMedico();
		GUI.Frame frame = new GUI.FramePaziente(null);
		new GUI.FormRegistrazionePaziente(frame);
		//new GUI.FrameProprietario();
		new GUI.FormPrenotazioneVisita(frame);
		new GUI.ListaVisite(frame, new ArrayList<Visite.Visita>());
		
		new GUI.FormRisultatoVisita(frame, new Prenotazione());
	}
}
