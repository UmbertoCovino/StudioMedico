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

	private GUIControllerVisite() { }

	public static GUIControllerVisite getInstance() {
		if(instance == null)
			instance = new GUIControllerVisite();
		return instance;
	}
	
	public GestoreVisite getGestoreVisite() {
		return gestoreVisite;
	}

	public void setGestoreVisite(GestoreVisite gestoreVisite) {
		this.gestoreVisite = gestoreVisite;
	}

	public void createListaStoricoVisite(String codiceFiscalePaziente) {
		ArrayList<Visita> visite = gestoreVisite.getVisite(codiceFiscalePaziente);
		ListaVisite list = new ListaVisite(, visite);
	}

	public void createListaVisite(String codiceFiscalePaziente) {
		ArrayList<Visita> visite = gestoreVisite.getVisite(codiceFiscalePaziente);
		ListaVisite list = new ListaVisite(, visite);
	}

	public void printFattura(Visita visita) {
		gestoreVisite.printFattura(visita);
	}

	public void createListaFatture(String codiceFiscalePaziente) {
		ListaFatture list = new ListaFatture();
		ArrayList<Fattura> fatture = gestoreVisite.getFatture(codiceFiscalePaziente);
	}

	public void createFormPagamento(Fattura fattura) {
		this.fattura = fattura;
		new FormPagamento();
	}

	public void notifyData(String metodoPagamento) {
		gestoreVisite.createPagamento(this.fattura, metodoPagamento, new Date());
	}

}
