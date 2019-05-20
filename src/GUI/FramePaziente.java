package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;

public class FramePaziente extends Frame { 
	private static final int MAX_FRAME_WIDTH = 99999,
			 				 EXTRA_FRAME_WIDTH = 50;

	public FramePaziente() {
		super("Area paziente", true);
		
		// dichiarazione elementi
						
		JButton prenotaVisitaButton = new JButton("Prenota visita");
		JButton modificaPrenotazioneVisitaButton = new JButton("Modifica prenotazione visita");
		JButton eliminaPrenotazioneVisitaButton = new JButton("Elimina prenotazione visita");
		JButton visualizzaStoricoVisiteButton = new JButton("Visualizza storico visite");
		
		// event handlers
		
		Frame thisFrame = this;
		
		prenotaVisitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FormPrenotazioneVisita(thisFrame);
			}
		});
		
		modificaPrenotazioneVisitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ListaPrenotazioni(thisFrame, ListaPrenotazioni.MODIFY_OPERATION);
				//new FormModificaPrenotazione(thisFrame); 
			}
		});
		
		eliminaPrenotazioneVisitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ListaPrenotazioni(thisFrame, ListaPrenotazioni.DELETE_OPERATION);
			}
		});
		
		visualizzaStoricoVisiteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new ListaVisite(thisFrame);
			}
		});
		
		// posizionamento elementi
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addComponent(prenotaVisitaButton)
					.addGap(0, 0, Short.MAX_VALUE))
	   			.addComponent(modificaPrenotazioneVisitaButton)
	   			.addComponent(eliminaPrenotazioneVisitaButton)
	   			.addComponent(visualizzaStoricoVisiteButton)
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addGap(0)
					.addComponent(prenotaVisitaButton)
					.addGap(0))
	   			.addComponent(modificaPrenotazioneVisitaButton)
	   			.addComponent(eliminaPrenotazioneVisitaButton)
	   			.addComponent(visualizzaStoricoVisiteButton)
		);
		
		// operazioni finali
		
		show(EXTRA_FRAME_WIDTH);
	}
}
