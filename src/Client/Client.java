package Client;

import GUI.FrameLogin;

public class Client { 

	public static void main(String[] args) {
		// inizializzazione database?
		
		new FrameLogin();
		
		// TEST - da cancellare
		new GUI.FrameMedico();
		GUI.Frame frame = new GUI.FramePaziente();
		new GUI.FormRegistrazionePaziente(frame);
		new GUI.FrameProprietario();
	}
}
