package Client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import Persistenza.GestoreDatabase;
import Utenti.GestoreUtenti;
import Utenti.Medico;
import Visite.Fattura;
import Visite.Pagamento;
import Visite.Prenotazione;
import Visite.TipologiaVisita;
import Visite.Visita;

public class ClientProvaDB { 

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws SQLException, ClassNotFoundException{
/*
	//SD Report1
		GestoreDatabase.getInstance().getReportVisite();
		
		
	//SD Report2
		GestoreDatabase.getInstance().getReportVisitePerMedico(0);
			
		
	//SD Report3 
		GestoreDatabase.getInstance().getReportMedici();
				
		
	//SD Report4
		GestoreDatabase.getInstance().getReportTipologieVisite();
					
		
	//SD Autenticazione		TEST OK
		GestoreDatabase.getInstance().getUtente("doc1@sm.com").print(System.out);
		System.out.println();
		GestoreDatabase.getInstance().getUtente("mc@sm.com").print(System.out);
		System.out.println();
		GestoreDatabase.getInstance().getUtente("admin@sm.com").print(System.out);
		
		
	//SD Crea report		TEST OK
		ArrayList<Medico> array = GestoreDatabase.getInstance().getMedici();
		if(array.isEmpty())
			System.out.print("Empty array");
		Iterator<Medico> i = array.iterator();
		while(i.hasNext())
			i.next().print(System.out);


	//SD Elimina prenotazione visita	TESTATE DELETE
		ArrayList<Prenotazione> array = GestoreDatabase.getInstance().getPrenotazioni("CF1");
		if(array.isEmpty())
			System.out.print("Empty array");
		Iterator<Prenotazione> i = array.iterator();
		while(i.hasNext())
			i.next().print(System.out);
		
		GestoreDatabase.getInstance().deletePrenotazione(0);


	//SD Esegue visita		TESTARE INSERT
		ArrayList<Prenotazione> array = GestoreDatabase.getInstance().getPrenotazioniFromDate("CF1", new Date(2019, 12, 12));
		if(array.isEmpty())
			System.out.print("Empty array");
		Iterator<Prenotazione> i = array.iterator();
		while(i.hasNext())
			i.next().print(System.out);

		GestoreDatabase.getInstance().insertVisita(null);


	//SD Genera fattura		TESTARE INSERT
		ArrayList<Visita> array = GestoreDatabase.getInstance().getVisite("CF1");
		if(array.isEmpty())
			System.out.print("Empty array");
		Iterator<Visita> i = array.iterator();
		while(i.hasNext())
			i.next().print(System.out);

		GestoreDatabase.getInstance().insertFattura(null);

		
	//SD Modifica prenotazione visita		TESTARE UPDATE
		ArrayList<Prenotazione> array = GestoreDatabase.getInstance().getPrenotazioni("CF1");
		if(array.isEmpty())
			System.out.print("Empty array");
		Iterator<Prenotazione> i = array.iterator();
		while(i.hasNext())
			i.next().print(System.out);
		
		GestoreDatabase.getInstance().updatePrenotazione(0, null, null, null, null);

		
	//SD Paga visita 	TESTARE INSERT
		ArrayList<Fattura> array1 = GestoreDatabase.getInstance().getFatture("CF1");
		if(array1.isEmpty())
			System.out.print("Empty array");
		ArrayList<Integer> idsFatture = new ArrayList<Integer>();
		Iterator<Fattura> i1 = array1.iterator();
		while(i1.hasNext()) 
			idsFatture.add(i1.next().getId());
					
		ArrayList<Pagamento> array2 = GestoreDatabase.getInstance().getPagamentiFatture(idsFatture);
		if(array2.isEmpty())
			System.out.print("Empty array");
		Iterator<Pagamento> i2 = array2.iterator();
		while(i2.hasNext())
			i2.next().print(System.out);

		GestoreDatabase.getInstance().insertPagamento(null);

		
	//SD Prenota visita		TESTARE ULTIMI DUE GET E INSERT
		ArrayList<TipologiaVisita> array = GestoreDatabase.getInstance().getTipologieVisite();
		if(array.isEmpty())
			System.out.print("Empty array");
		Iterator<TipologiaVisita> i = array.iterator();
		while(i.hasNext())
			i.next().print(System.out);
		
		ArrayList<Medico> array = GestoreDatabase.getInstance().getMedici("Elettrocardiogramma");
		if(array.isEmpty())
			System.out.print("Empty array");
		Iterator<Medico> i = array.iterator();
		while(i.hasNext())
			i.next().print(System.out);

		GestoreDatabase.getInstance().getCalendarioDisponibilita(0, null);
		GestoreDatabase.getInstance().getPrenotazioni(0, null);
		GestoreDatabase.getInstance().insertPrenotazione(null);

		
	//SD Registrazione		TESTARE INSERT
		System.out.println("Proprietario "+GestoreDatabase.getInstance().isUtenteGiaPresente("admin@sm.com"));
		System.out.println("Doc1 "+GestoreDatabase.getInstance().isUtenteGiaPresente("doc1@sm.com"));
		System.out.println("MC "+GestoreDatabase.getInstance().isUtenteGiaPresente("mc@sm.com"));
		System.out.println("Wrong mail "+GestoreDatabase.getInstance().isUtenteGiaPresente("sss"));
		System.out.println();
		System.out.println("MC "+GestoreDatabase.getInstance().isUtenteGiaPresente("mc@sm.com"));
		System.out.println("Wrong mail "+GestoreDatabase.getInstance().isUtenteGiaPresente("sss"));

		GestoreDatabase.getInstance().insertPaziente(null);

		
	//SD Ricerca paziente		TEST OK
		GestoreDatabase.getInstance().getPaziente("CF1").print(System.out);

		
	//SD Visualizza storico visite		TEST OK
		ArrayList<Visita> array = GestoreDatabase.getInstance().getVisite("CF1");
		if(array.isEmpty())
			System.out.print("Empty array");
		Iterator<Visita> i = array.iterator();
		while(i.hasNext())
			i.next().print(System.out);
*/
	}
}