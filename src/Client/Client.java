package Client;

import java.util.ArrayList;
import java.util.Date;

import GUI.FrameLogin;

public class Client { 

	public static void main(String[] args) {
		// inizializzazione database?
		
		new FrameLogin();
		
		// TEST - da cancellare
		new GUI.FrameMedico();
		GUI.Frame frame = new GUI.FramePaziente(null);
		new GUI.FormRegistrazionePaziente(frame);
		new GUI.FrameProprietario();
		new GUI.FormPrenotazioneVisita(frame);
		new GUI.ListaVisite(frame, new ArrayList<Visite.Visita>());
	}
}
