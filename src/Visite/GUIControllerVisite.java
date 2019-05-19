package Visite;

public class GUIControllerVisite {
	private static GUIControllerVisite instance;
	private GestoreVisite gestoreVisite;

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

	public void createFormStoricoVisite() {

	}

	public void createFormStampaFattura() {

	}

	public void createListaStoricoVisite(String codiceFiscalePaziente) {

	}

	public void createListaVisite(String codiceFiscalePaziente) {

	}

	public void printFattura(Visita visita) {

	}

	public void createListaFatture(String codiceFiscalePaziente) {

	}

	public void createFormPagamento(Fattura fattura) {

	}

	public void notifyData(String metodoPagamento) {

	}

}
