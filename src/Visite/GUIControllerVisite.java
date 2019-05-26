package Visite;

import java.util.ArrayList;
import java.util.Date;

import GUI.FormPagamento;
import GUI.Frame;
import GUI.ListaFatture;
import GUI.ListaVisite;

public class GUIControllerVisite {
	private static GUIControllerVisite instance;
	private GestoreVisite gestoreVisite;
	private Fattura fattura;

	private GUIControllerVisite() {
		gestoreVisite = GestoreVisite.getInstance();
	}

	public static GUIControllerVisite getInstance() {
		if(instance == null)
			instance = new GUIControllerVisite();
		return instance;
	}

	public void createListaStoricoVisite(Frame parentFrame, int operationType, String codiceFiscalePaziente) {
		ArrayList<Visita> visite = gestoreVisite.getVisite(codiceFiscalePaziente);
		new ListaVisite(parentFrame, operationType, visite);
	}

	public void createListaVisite(Frame parentFrame, int operationType, String codiceFiscalePaziente) {
		ArrayList<Visita> visite = gestoreVisite.getVisite(codiceFiscalePaziente);
		new ListaVisite(parentFrame, operationType, visite);
	}

	public void printFattura(Visita visita) {
		gestoreVisite.printFattura(visita);
	}

	public void createListaFatture(Frame parentFrame, String codiceFiscalePaziente) {
		ArrayList<Fattura> fatture = gestoreVisite.getFatture(codiceFiscalePaziente);
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
