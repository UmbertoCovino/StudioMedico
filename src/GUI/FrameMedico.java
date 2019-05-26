package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import Utenti.GUIControllerUtenti;
import Utenti.Medico;
import Utenti.Paziente;
import Visite.GUIControllerPrenotazioni;
import Visite.GUIControllerVisite;

public class FrameMedico extends Frame {
	private Medico medico;
	private Paziente paziente;
	
	private JButton ricercaPazienteButton;
	private JLabel pazienteLabel;
	private JButton registraVisitaButton;
	private JButton generaFatturaButton;
	private JButton registraPagamentoVisitaButton;

	public FrameMedico(Medico medico) {
		super("Area medico", true);
		
		this.medico = medico;
		
		setExtraFrameWidth(50);
		
		// dichiarazione elementi
		ricercaPazienteButton = new JButton("Ricerca paziente");
		registraVisitaButton = new JButton("Registra visita");
		generaFatturaButton = new JButton("Genera fattura");
		registraPagamentoVisitaButton = new JButton("Registra pagamento visita");
		
		pazienteLabel.setVisible(false);
		
		registraVisitaButton.setEnabled(false);
		generaFatturaButton.setEnabled(false);
		registraPagamentoVisitaButton.setEnabled(false);
		
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
		
		ricercaPazienteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				paziente = GUIControllerUtenti.getInstance().createFormRichiestaPaziente(thisFrame);
//				
//				if (paziente != null) {
//					pazienteLabel.setText("Dati paziente ricercato: " + paziente.getNome() + " " + paziente.getCognome() + ", " + paziente.getCodiceFiscale() + ", " + paziente.getEmail());
//					pazienteLabel.setVisible(true);
//				}
			}
		});
		
		registraVisitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				GUIControllerPrenotazioni.getInstance().createListaPrenotazioni(thisFrame, paziente.getCodiceFiscale(), ListaPrenotazioni.REGISTER_VISIT_OPERATION);
			}
		});
		
		generaFatturaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				GUIControllerVisite.getInstance().createListaVisite(thisFrame, paziente.getCodiceFiscale(), ListaVisite.GENERATE_FATTURA_OPERATION);
			}
		});
		
		registraPagamentoVisitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				GUIControllerVisite.getInstance().createListaFatture(thisFrame, paziente.getCodiceFiscale());
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
					.addComponent(ricercaPazienteButton)
					.addGap(0, 0, Short.MAX_VALUE))
				.addComponent(pazienteLabel)
	   			.addComponent(registraVisitaButton)
	   			.addComponent(generaFatturaButton)
	   			.addComponent(registraPagamentoVisitaButton)
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addGap(0)
					.addComponent(ricercaPazienteButton)
					.addGap(0))
				.addComponent(pazienteLabel)
				.addGap(getButtonsGap())
	   			.addComponent(registraVisitaButton)
	   			.addComponent(generaFatturaButton)
	   			.addComponent(registraPagamentoVisitaButton)
		);
	}
}
