package Visite;

import Utenti.Paziente;
import java.util.Date;
import Utenti.Medico;
import Amministrazione.CalendarioDisponibilita;
import GUI.FormModificaPrenotazione;
import GUI.FormPrenotazioneVisita;
import GUI.FormRisultatoVisita;
import GUI.Frame;
import GUI.ListaPrenotazioni;

import java.util.ArrayList;

public class GUIControllerPrenotazioni {
	private static GUIControllerPrenotazioni instance;
	private GestorePrenotazioni gestorePrenotazioni;
	private Paziente paziente;

	private GUIControllerPrenotazioni() {
		gestorePrenotazioni = GestorePrenotazioni.getInstance();
	}

	public static GUIControllerPrenotazioni getInstance() {
		if(instance == null)
			instance = new GUIControllerPrenotazioni();
		return instance;
	}

	public void createFormPrenotazioneVisita(Frame parentFrame, Paziente paziente) {
		this.paziente = paziente;
		new FormPrenotazioneVisita(parentFrame);
	}

	public void createListaPrenotazioni(Frame parentFrame, String codiceFiscalePaziente, int codiceMedico, int operationType) {
		ArrayList<Prenotazione> prenotazioni = gestorePrenotazioni.getPrenotazioniFromDate(codiceFiscalePaziente, codiceMedico, new Date());
		new ListaPrenotazioni(parentFrame, operationType, prenotazioni);
	}

	public void createFormModificaPrenotazione(Frame parentFrame, Prenotazione prenotazione) {
		new FormModificaPrenotazione(parentFrame, prenotazione.getId(), prenotazione.getTipologiaVisita(),
										prenotazione.getMedico(), prenotazione.getGiorno(), prenotazione.getOra());
	}

	public void createFormRisultatoVisita(Frame parentFrame, Prenotazione prenotazione) {
		new FormRisultatoVisita(parentFrame, prenotazione);
	}

	public void notifyData(Date giorno, Date ora, TipologiaVisita tipologiaVisita, Medico medico) {
		gestorePrenotazioni.createPrenotazione(giorno, ora, tipologiaVisita, medico, this.paziente);
	}
	
	public void notifyData(int id, Date giorno, Date ora, TipologiaVisita tipologiaVisita, Medico medico) {
		gestorePrenotazioni.modifyPrenotazione(id, giorno, ora, tipologiaVisita, medico);
	}

	public void deletePrenotazione(int id) {
		gestorePrenotazioni.deletePrenotazione(id);
	}

	public void notifyData(Prenotazione prenotazione, String diagnosi, String terapia) {
		gestorePrenotazioni.createVisita(prenotazione, diagnosi, terapia);
	}

	public CalendarioDisponibilita getCalendarioDisponibilita(int codiceMedico) {
		return gestorePrenotazioni.getCalendarioDisponibilita(codiceMedico);
	}
	
	public ArrayList<Prenotazione> getPrenotazioni(int codiceMedico, String nomeTipologiaVisita){
		return gestorePrenotazioni.getPrenotazioni(codiceMedico, nomeTipologiaVisita);
	}

	public ArrayList<Medico> getMedici(String nomeTipologiaVisita) {
		return gestorePrenotazioni.getMedici(nomeTipologiaVisita);
	}

	public ArrayList<TipologiaVisita> getTipologieVisite() {
		return gestorePrenotazioni.getTipologieVisite();
	}
}
