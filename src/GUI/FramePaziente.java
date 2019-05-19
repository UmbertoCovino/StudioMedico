package GUI;

import java.awt.Dimension;
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
		
		prenotaVisitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 
				//JOptionPane.showMessageDialog(frame, "Messaggio.", "Attenzione", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		modificaPrenotazioneVisitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 
			}
		});
		
		eliminaPrenotazioneVisitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 
			}
		});
		
		visualizzaStoricoVisiteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 
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
		
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setSize(new Dimension(getWidth() + EXTRA_FRAME_WIDTH, getHeight()));
		setVisible(true);
	}
}
