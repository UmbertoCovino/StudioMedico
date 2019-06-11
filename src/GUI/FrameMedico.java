package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Utenti.GUIControllerUtenti;
import Utenti.Medico;
import Utenti.Paziente;
import Visite.GUIControllerPrenotazioni;
import Visite.GUIControllerVisite;

@SuppressWarnings("serial")
public class FrameMedico extends Frame {
	private Medico medico;
	private Paziente paziente;

	private JLabel medicoLabel;
	private JButton ricercaPazienteButton;
	private JLabel pazienteLabel;
	private JPanel pazientePanel;
	private JButton registraVisitaButton;
	private JButton generaFatturaButton;
	private JButton registraPagamentoVisitaButton;

	public FrameMedico(Medico medico) {
		super("Area medico", true);
		
		this.medico = medico;
		
		setExtraFrameWidth(25);
		
		// dichiarazione elementi
		medicoLabel = new JLabel("<html><center><h3>Salve dott. " + medico.getNome() + " " + medico.getCognome() + "!</h3></center></html>", SwingConstants.CENTER);
		
		ricercaPazienteButton = new JButton("Ricerca paziente");
		registraVisitaButton = new JButton("Registra visita");
		generaFatturaButton = new JButton("Genera fattura");
		registraPagamentoVisitaButton = new JButton("Registra pagamento visita");
		
		pazienteLabel = new JLabel();
		pazienteLabel.setFont(pazienteLabel.getFont().deriveFont(pazienteLabel.getFont().getStyle() & ~Font.BOLD));
		
		pazientePanel = new JPanel();
		pazientePanel.setBorder(BorderFactory.createTitledBorder("Dati paziente trovato"));
		pazientePanel.add(pazienteLabel);
		pazientePanel.setVisible(false);
		
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
				GUIControllerUtenti.getInstance().createFormRichiestaPaziente(thisFrame);
			}
		});
		
		registraVisitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIControllerPrenotazioni.getInstance().createListaPrenotazioni(thisFrame, paziente.getCodiceFiscale(), medico.getCodice(), ListaPrenotazioni.REGISTER_VISIT_OPERATION);
			}
		});
		
		generaFatturaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIControllerVisite.getInstance().createListaVisite(thisFrame, paziente.getCodiceFiscale(), ListaVisite.GENERATE_FATTURA_OPERATION);
			}
		});
		
		registraPagamentoVisitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIControllerVisite.getInstance().createListaFatture(thisFrame, paziente.getCodiceFiscale());
			}
		});
	}
	
	public void setPazienteFound(Paziente paziente) {
		this.paziente = paziente;
		
		if (paziente != null) {
			pazienteLabel.setText("<html>"
								+ "	<table>"
								+ "		<tr><td><b>Nome:		  </b></td><td>" + paziente.getNome()			+ "</td></tr>"
								+ "		<tr><td><b>Cognome:	   	  </b></td><td>" + paziente.getCognome()		+ "</td></tr>"
								+ "		<tr><td><b>Codice fiscale:</b></td><td>" + paziente.getCodiceFiscale()	+ "</td></tr>"
								+ "		<tr><td><b>Email:		  </b></td><td>" + paziente.getEmail()			+ "</td></tr>"
								+ "	</table>"
								+ "</html>");
			pazientePanel.setVisible(true);
			refreshFrameDims();
			
			registraVisitaButton.setEnabled(true);
			generaFatturaButton.setEnabled(true);
			registraPagamentoVisitaButton.setEnabled(true);
		}
	}

	@Override
	protected void elementsPositioning() {
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(medicoLabel)
				.addGroup(layout.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addComponent(ricercaPazienteButton)
					.addGap(0, 0, Short.MAX_VALUE))
				.addComponent(pazientePanel)
	   			.addComponent(registraVisitaButton)
	   			.addComponent(generaFatturaButton)
	   			.addComponent(registraPagamentoVisitaButton)
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(medicoLabel)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addGap(0)
					.addComponent(ricercaPazienteButton)
					.addGap(0))
				.addComponent(pazientePanel)
				.addGap(getButtonsGap())
	   			.addComponent(registraVisitaButton)
	   			.addComponent(generaFatturaButton)
	   			.addComponent(registraPagamentoVisitaButton)
		);
	}
}
