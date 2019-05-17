package Visite;

import Persistenza.GestoreDatabase;
import java.util.ArrayList;
import java.util.Date;

public class GestoreVisite {

	private static GestoreVisite instance;

	private GestoreDatabase gestoreDB;

	private Visita visita;

	private GestoreVisite() {

	}

	public static GestoreVisite getInstance() {
		return null;
	}

	public ArrayList<Visita> getVisite(String codiceFiscalePaziente) {
		return null;
	}

	public void printFattura(Visita visita) {

	}

	public ArrayList<Fattura> getFatture(String codiceFiscalePaziente) {
		return null;
	}

	public void createPagamento(Fattura fattura, String metodoPagamento, Date dataPagamento) {

	}

	public ArrayList<Pagamento> getPagamentiFatture(ArrayList<Integer> idFatture) {
		return null;
	}

}
