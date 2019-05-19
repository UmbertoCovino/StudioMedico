package Visite;

import java.util.Date;

public class Pagamento {
	private Date dataPagamento;
	private String metodoPagamento;
	private Fattura fattura;
	
	public Date getDataPagamento() {
		return dataPagamento;
	}
	
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public String getMetodoPagamento() {
		return metodoPagamento;
	}
	
	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}
	
	public Fattura getFattura() {
		return fattura;
	}
	
	public void setFattura(Fattura fattura) {
		this.fattura = fattura;
	}
}
