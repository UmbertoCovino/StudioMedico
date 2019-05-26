package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout;
import javax.swing.JButton;

import Utenti.Paziente;
import Visite.GUIControllerPrenotazioni;
import Visite.GUIControllerVisite;

public class FramePaziente extends Frame {
	public static final SimpleDateFormat DATE_SDF = new SimpleDateFormat("EEEEEEEEEE d MMMMMMMMMMMMM yyyy");
	public static final SimpleDateFormat TIME_SDF = new SimpleDateFormat("HH:mm");
	
	private Paziente paziente;
	
	private JButton prenotaVisitaButton;
	private JButton modificaPrenotazioneVisitaButton;
	private JButton eliminaPrenotazioneVisitaButton;
	private JButton visualizzaStoricoVisiteButton;

	public FramePaziente(Paziente paziente) {
		super("Area paziente", true);
		
		this.paziente = paziente;
		
		setExtraFrameWidth(50);
		
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
		showFrame();
	}

	@Override
	protected void addingEventHandlers() {
		Frame thisFrame = this;
		
		prenotaVisitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIControllerPrenotazioni.getInstance().createFormPrenotazioneVisita(thisFrame, paziente);
			}
		});
		
		modificaPrenotazioneVisitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIControllerPrenotazioni.getInstance().createListaPrenotazioni(thisFrame, paziente.getCodiceFiscale(), ListaPrenotazioni.MODIFY_OPERATION);
			}
		});
		
		eliminaPrenotazioneVisitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIControllerPrenotazioni.getInstance().createListaPrenotazioni(thisFrame, paziente.getCodiceFiscale(), ListaPrenotazioni.DELETE_OPERATION);
			}
		});
		
		visualizzaStoricoVisiteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIControllerVisite.getInstance().createListaStoricoVisite(thisFrame, paziente.getCodiceFiscale(), ListaVisite.STORICO_VISITE_OPERATION);
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
				.addComponent(prenotaVisitaButton)
	   			.addComponent(modificaPrenotazioneVisitaButton)
	   			.addComponent(eliminaPrenotazioneVisitaButton)
	   			.addComponent(visualizzaStoricoVisiteButton)
		);
	}
}
