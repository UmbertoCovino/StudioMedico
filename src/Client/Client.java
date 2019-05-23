package Client;

import java.util.ArrayList;
import java.util.Date;

import GUI.FormRichiestaPaziente;
import GUI.FrameLogin;
import Utenti.GUIControllerUtenti;
import Visite.Prenotazione;

public class Client { 

	public static void main(String[] args) {
		// inizializzazione database?
		//GUIControllerUtenti.getInstance().createFrameLogin();
		
		new FrameLogin();
		
		// TEST - da cancellare
		//new GUI.FrameMedico();
		GUI.Frame frame = new GUI.FramePaziente(null);
		new GUI.FormRegistrazionePaziente(frame);
		//new GUI.FrameProprietario();
		new GUI.FormPrenotazioneVisita(frame);
		new GUI.ListaVisite(frame, new ArrayList<Visite.Visita>());
		
		new GUI.FormRisultatoVisita(frame, new Prenotazione());
		
		new GUI.FormCreazioneReport(frame);
	}
}
