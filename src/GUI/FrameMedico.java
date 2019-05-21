package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;

public class FrameMedico extends Frame { 
	private static final int MAX_FRAME_WIDTH = 99999,
			 				 EXTRA_FRAME_WIDTH = 50,
							 BUTTONS_GAP = 15;
	private JButton ricercaPazienteButton;
	private JButton registraVisitaButton;
	private JButton generaFatturaButton;
	private JButton registraPagamentoVisitaButton;

	public FrameMedico() {
		super("Area medico", true);
		
		// dichiarazione elementi
		ricercaPazienteButton = new JButton("Ricerca paziente");
		registraVisitaButton = new JButton("Registra visita");
		generaFatturaButton = new JButton("Genera fattura");
		registraPagamentoVisitaButton = new JButton("Registra pagamento visita");
		
		registraVisitaButton.setEnabled(false);
		generaFatturaButton.setEnabled(false);
		registraPagamentoVisitaButton.setEnabled(false);
		
		// aggiunta event handlers
		addingEventHandlers();
		
		// posizionamento elementi
		elementsPositioning();
		
		// visualizzazione frame
		showFrame(EXTRA_FRAME_WIDTH);
	}

	@Override
	protected void addingEventHandlers() {
		ricercaPazienteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 
				//JOptionPane.showMessageDialog(frame, "Messaggio.", "Attenzione", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		registraVisitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 
			}
		});
		
		generaFatturaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 
			}
		});
		
		registraPagamentoVisitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 
			}
		});
	}

	@Override
	protected void elementsPositioning() {
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		// VALUTARE SE AGGIUNGERE LA VISUALIZZAZIONE DEL PAZIENTE UNA VOLTA RICERCATO
		
		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addComponent(ricercaPazienteButton)
					.addGap(0, 0, Short.MAX_VALUE))
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
				.addGap(BUTTONS_GAP)
	   			.addComponent(registraVisitaButton)
	   			.addComponent(generaFatturaButton)
	   			.addComponent(registraPagamentoVisitaButton)
		);
	}
}
