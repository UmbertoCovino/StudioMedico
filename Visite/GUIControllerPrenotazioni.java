package Visite;

import Utenti.Paziente;
import java.util.Date;
import Utenti.Medico;
import Amministrazione.CalendarioDisponibilita;
import java.util.ArrayList;

public class GUIControllerPrenotazioni {

	private static GUIControllerPrenotazioni instance;

	private GestorePrenotazioni gestorePrenotazioni;

	private GestorePrenotazioni gestorePrenotazioni;

	private GUIControllerPrenotazioni() {

	}

	public static GUIControllerPrenotazioni getInstance() {
		return null;
	}

	public void createFormPrenotazioneVisita(Paziente paziente) {

	}

	public void createListaPrenotazioni(String codiceFiscalePaziente) {

	}

	public void createFormModificaPrenotazione(Prenotazione prenotazione) {

	}

	public void createFormRisultatoVisita(Prenotazione prenotazione) {

	}

	public void notifyData(Date giorno, Date ora, TipologiaVisita tipologiaVisita, Medico medico) {

	}

	public void deletePrenotazione(Prenotazione prenotazione) {

	}

	public void notifyData(Prenotazione prenotazione, String diagnosi, String terapia) {

	}

	public CalendarioDisponibilita getCalendarioDisponibilita(int codiceMedico, String nomeTipologiaVisita) {
		return null;
	}

	public ArrayList<Medico> getMedici(String nomeTipologiaVisita) {
		return null;
	}

	public ArrayList<TipologiaVisita> getTipologieVisite() {
		return null;
	}

}
