package Visite;

import Utenti.Paziente;
import java.util.Date;
import Utenti.Medico;
import Amministrazione.CalendarioDisponibilita;
import GUI.FormModificaPrenotazione;
import GUI.FormPrenotazioneVisita;
import GUI.FormRisultatoVisita;
import GUI.ListaPrenotazioni;

import java.util.ArrayList;

public class GUIControllerPrenotazioni {
	private static GUIControllerPrenotazioni instance;
	private GestorePrenotazioni gestorePrenotazioni;
	private Paziente paziente;

	private GUIControllerPrenotazioni() { }

	public static GUIControllerPrenotazioni getInstance() {
		if(instance == null)
			instance = new GUIControllerPrenotazioni();
		return instance;
	}

	public GestorePrenotazioni getGestorePrenotazioni() {
		return gestorePrenotazioni;
	}

	public void setGestorePrenotazioni(GestorePrenotazioni gestorePrenotazioni) {
		this.gestorePrenotazioni = gestorePrenotazioni;
	}

	public void createFormPrenotazioneVisita(Paziente paziente) {
		this.paziente = paziente;
		new FormPrenotazioneVisita(new GUI.FramePaziente());
	}

	public void createListaPrenotazioni(String codiceFiscalePaziente) {
		//ListaPrenotazioni list = new ListaPrenotazioni(parentFrame, operationType);
		ArrayList<Prenotazione> prenotazioni = gestorePrenotazioni.getPrenotazioni(codiceFiscalePaziente);
	}

	public void createFormModificaPrenotazione(Prenotazione prenotazione) {
		new FormModificaPrenotazione(new GUI.FramePaziente());
	}

	public void createFormRisultatoVisita(Prenotazione prenotazione) {
		new FormRisultatoVisita();
	}

	public void notifyData(Date giorno, Date ora, TipologiaVisita tipologiaVisita, Medico medico) {
		gestorePrenotazioni.createPrenotazione(giorno, ora, tipologiaVisita, medico, this.paziente);
	}

	public void deletePrenotazione(Prenotazione prenotazione) {
		gestorePrenotazioni.deletePrenotazione(prenotazione.getId());
	}

	public void notifyData(Prenotazione prenotazione, String diagnosi, String terapia) {
		gestorePrenotazioni.createVisita(prenotazione, diagnosi, terapia);
	}

	public CalendarioDisponibilita getCalendarioDisponibilita(int codiceMedico, String nomeTipologiaVisita) {
		return gestorePrenotazioni.getCalendarioDisponibilita(codiceMedico, nomeTipologiaVisita);
	}

	public ArrayList<Medico> getMedici(String nomeTipologiaVisita) {
		return gestorePrenotazioni.getMedici(nomeTipologiaVisita);
	}

	public ArrayList<TipologiaVisita> getTipologieVisite() {
		return gestorePrenotazioni.getTipologieVisite();
	}

}
