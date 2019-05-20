package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import Utenti.Medico;
import Visite.TipologiaVisita;

public class FormModificaPrenotazione extends Frame { 
	private static final int MAX_FRAME_WIDTH = 99999,
			 				 EXTRA_FRAME_WIDTH = 100,
							 BUTTONS_GAP = 15;

	public FormModificaPrenotazione(Frame parentFrame) {
		super("Modifica prenotazione", parentFrame, "Sei sicuro di voler annullare la modifica alla prenotazione?");
		
		// dichiarazione elementi
		
		JLabel tipologiaVisitaLabel = new JLabel("Tipologia visita");
		JLabel medicoLabel = new JLabel("Medico");
		JLabel calendarioLabel = new JLabel("Calendario disponibilità");
		JLabel orarioLabel = new JLabel("Orari disponibili per il giorno selezionato");
		
		JComboBox<TipologiaVisita> tipologiaVisitaComboBox = new JComboBox<>();
		JComboBox<Medico> medicoComboBox = new JComboBox<>();
//		JCalendar calendarioCalendar = new JCalendar();
		JButton calendarioCalendar = new JButton(); calendarioCalendar.setSize(100, 100);
		JComboBox<Date> orarioComboBox = new JComboBox<>();
		
		JButton confirmButton = new JButton("Conferma");
		JButton cancelButton = new JButton("Annulla");
		
		// event handlers
		
		Frame thisFrame = this;
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 
				//JOptionPane.showMessageDialog(frame, "Messaggio.", "Attenzione", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
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
		   			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
			   			.addComponent(tipologiaVisitaLabel)
		   				.addComponent(medicoLabel)
		   				.addComponent(calendarioLabel)
		   				.addComponent(orarioLabel))
		   			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		   				.addComponent(tipologiaVisitaComboBox)
		   				.addComponent(medicoComboBox)
		   				.addComponent(calendarioCalendar)
		   				.addComponent(orarioComboBox)))
		   		.addGroup(layout.createSequentialGroup()
		   			.addComponent(cancelButton)
		   			.addComponent(confirmButton))
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(tipologiaVisitaLabel)
					.addComponent(tipologiaVisitaComboBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(medicoLabel)
					.addComponent(medicoComboBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(calendarioLabel)
					.addComponent(calendarioCalendar))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(orarioLabel)
					.addComponent(orarioComboBox))
				.addGap(BUTTONS_GAP)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(cancelButton)
					.addComponent(confirmButton))
		);
		
		// operazioni finali
		
		show(EXTRA_FRAME_WIDTH);
	}
}
