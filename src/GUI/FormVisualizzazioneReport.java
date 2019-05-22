package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import Amministrazione.Report;
import Visite.Visita;

public class FormVisualizzazioneReport extends Frame {
	private static final int MAX_FRAME_WIDTH = 99999,
			 				 EXTRA_FRAME_WIDTH = 100,
							 BUTTONS_GAP = 15;
	private JLabel reportLabel;
	private JList<Riga> reportRigheList;
	private JScrollPane reportRigheScrollPane;
	private JButton exitButton;

	public FormVisualizzazioneReport(Frame parentFrame, Report report) {
		super("Lista visite effettuate", parentFrame);
		
		// dichiarazione elementi
		reportLabel = new JLabel("Visite");
		
		reportRigheList = new JList<>(report.getRisultato().toArray(new Riga[visite.size()]));
		
		reportRigheScrollPane = new JScrollPane(reportRigheList);
		
		exitButton = new JButton("Annulla");
		
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
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
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
				.addComponent(reportLabel, 0, 0, Short.MAX_VALUE)
		   		.addComponent(reportRigheScrollPane, 0, 0, Short.MAX_VALUE)
	   			.addComponent(exitButton)
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(reportLabel)
				.addComponent(reportRigheScrollPane)
				.addGap(BUTTONS_GAP)
				.addComponent(exitButton)
		);
	}
}
