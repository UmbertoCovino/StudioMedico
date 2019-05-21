package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;

public class FramePaziente extends Frame { 
	private static final int MAX_FRAME_WIDTH = 99999,
			 				 EXTRA_FRAME_WIDTH = 50;
	private JButton prenotaVisitaButton;
	private JButton modificaPrenotazioneVisitaButton;
	private JButton eliminaPrenotazioneVisitaButton;
	private JButton visualizzaStoricoVisiteButton;

	public FramePaziente() {
		super("Area paziente", true);
		
		// dichiarazione elementi
		prenotaVisitaButton = new JButton("Prenota visita");
		modificaPrenotazioneVisitaButton = new JButton("Modifica prenotazione visita");
		eliminaPrenotazioneVisitaButton = new JButton("Elimina prenotazione visita");
		visualizzaStoricoVisiteButton = new JButton("Visualizza storico visite");
		
		// aggiunta event handlers
		addingEventHandlers();
		
		// posizionamento elementi
		elementsPositioning();
		
		// visualizzazione frame
		showFrame(EXTRA_FRAME_WIDTH);
	}

	@Override
	protected void addingEventHandlers() {
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
	}

	@Override
	protected void elementsPositioning() {
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
	}
}
