package Client;

import java.util.ArrayList;
import java.util.Date;

import GUI.FrameLogin;
import Visite.Prenotazione;

public class Client { 

	public static void main(String[] args) {
		// inizializzazione database?
		
		new FrameLogin();
		
		// TEST - da cancellare
		new GUI.FrameMedico();
		GUI.Frame frame = new GUI.FramePaziente(null);
		new GUI.FormRegistrazionePaziente(frame);
		new GUI.FrameProprietario();
		
		ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
		
		Prenotazione p1 = new Prenotazione();
		p1.setId(1);
		p1.setGiorno(new Date());
		
		Prenotazione p2 = new Prenotazione();
		p2.setId(2);
		p2.setGiorno(new Date());
		
		
		prenotazioni.add(p1);
		prenotazioni.add(p2);
		new GUI.ListaPrenotazioni(frame, 1, prenotazioni);
	}
}
