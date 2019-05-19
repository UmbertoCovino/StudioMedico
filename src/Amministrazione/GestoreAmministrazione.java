package Amministrazione;

import Persistenza.GestoreDatabase;
import java.util.ArrayList;
import Utenti.Medico;

public class GestoreAmministrazione {
	private static GestoreAmministrazione instance;
	private GestoreDatabase gestoreDB;

	private GestoreAmministrazione() { }

	public static GestoreAmministrazione getInstance() {
		if(instance == null)
			instance = new GestoreAmministrazione();
		return instance;
	}

	public GestoreDatabase getGestoreDB() {
		return gestoreDB;
	}

	public void setGestoreDB(GestoreDatabase gestoreDB) {
		this.gestoreDB = gestoreDB;
	}

	public Report createReport(String tipologia) {
		return null;
	}

	public ArrayList<Medico> getMedici() {
		return null;
	}

	public void createReport(String tipologia, Medico medico) {

	}
}
