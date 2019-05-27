package Visite;

import Persistenza.GestoreDatabase;

import java.util.ArrayList;
import java.util.Date;

public class GestoreVisite {
	private static GestoreVisite instance;
	private GestoreDatabase gestoreDB;
	private Visita visita;

	private GestoreVisite() {
		gestoreDB = GestoreDatabase.getInstance();
	}

	public static GestoreVisite getInstance() {
		if(instance == null)
			instance = new GestoreVisite();
		return instance;
	}

	public Visita getVisita() {
		return visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}

	public ArrayList<Visita> getVisite(String codiceFiscalePaziente) {
		return gestoreDB.getVisite(codiceFiscalePaziente);
	}

	public void printFattura(Visita visita) {
		Fattura fattura = new Fattura(visita);
		gestoreDB.insertFattura(fattura);
		GUIControllerVisite.getInstance().visualizzaFattura(fattura);
	}

	public ArrayList<Fattura> getFatture(String codiceFiscalePaziente) {
		return gestoreDB.getFatture(codiceFiscalePaziente);
	}

	public void createPagamento(Fattura fattura, String metodoPagamento, Date dataPagamento) {
		Pagamento pagamento = new Pagamento(fattura, metodoPagamento, dataPagamento);
		gestoreDB.insertPagamento(pagamento);
	}

	public ArrayList<Pagamento> getPagamentiFatture(ArrayList<Integer> idFatture) {
		return gestoreDB.getPagamentiFatture(idFatture);
	}

}
