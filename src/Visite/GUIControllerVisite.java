package Visite;

import java.util.ArrayList;
import java.util.Date;

import GUI.FormPagamento;
import GUI.FormVisualizzazioneFattura;
import GUI.Frame;
import GUI.ListaFatture;
import GUI.ListaVisite;

public class GUIControllerVisite {
	private static GUIControllerVisite instance;
	private GestoreVisite gestoreVisite;
	
	private Fattura fattura;
	private Frame parentFrame;

	private GUIControllerVisite() {
		gestoreVisite = GestoreVisite.getInstance();
	}

	public static GUIControllerVisite getInstance() {
		if(instance == null)
			instance = new GUIControllerVisite();
		return instance;
	}

	public void createListaStoricoVisite(Frame parentFrame, String codiceFiscalePaziente, int operationType) {
		ArrayList<Visita> visite = gestoreVisite.getVisite(codiceFiscalePaziente);
		new ListaVisite(parentFrame, operationType, visite);
	}

	public void createListaVisite(Frame parentFrame, String codiceFiscalePaziente, int operationType) {
		ArrayList<Visita> visite = gestoreVisite.getVisite(codiceFiscalePaziente);
		new ListaVisite(parentFrame, operationType, visite);
	}
	
	public void createListaVisite(Frame parentFrame, String codiceFiscalePaziente, int codiceMedico, int operationType) {
		ArrayList<Visita> visite = gestoreVisite.getVisiteByMedico(codiceFiscalePaziente, codiceMedico);
		new ListaVisite(parentFrame, operationType, visite);
	}

	public void printFattura(Frame parentFrame, Visita visita) {
		this.parentFrame = parentFrame;
		gestoreVisite.printFattura(visita);
	}

	public void visualizzaFattura(Fattura fattura) {
		new FormVisualizzazioneFattura(parentFrame, fattura);
	}

	public void createListaFatture(Frame parentFrame, String codiceFiscalePaziente, int codiceMedico) {
		ArrayList<Fattura> fatture = gestoreVisite.getFatture(codiceFiscalePaziente, codiceMedico);
		new ListaFatture(parentFrame, fatture);
	}

	public void createFormPagamento(Frame parentFrame, Fattura fattura) {
		this.fattura = fattura;
		new FormPagamento(parentFrame, fattura);
	}

	public void notifyData(String metodoPagamento) {
		gestoreVisite.createPagamento(this.fattura, metodoPagamento, new Date());
	}
}
