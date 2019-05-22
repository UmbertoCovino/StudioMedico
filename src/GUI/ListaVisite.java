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

import Visite.Visita;

public class ListaVisite extends Frame {
	private static final int MAX_FRAME_WIDTH = 99999,
			 				 EXTRA_FRAME_WIDTH = 100,
							 BUTTONS_GAP = 15;
	private JLabel visiteLabel;
	private JList<Visita> visiteList;
	private JScrollPane visiteScrollPane;
	private JButton exitButton;

	public ListaVisite(Frame parentFrame, ArrayList<Visita> visite) {
		super("Lista visite effettuate", parentFrame);
		
		// dichiarazione elementi
		visiteLabel = new JLabel("Visite");
		
		visiteList = new JList<>(visite.toArray(new Visita[visite.size()]));
		
		visiteScrollPane = new JScrollPane(visiteList);
		
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
				.addComponent(visiteLabel, 0, 0, Short.MAX_VALUE)
		   		.addComponent(visiteScrollPane, 0, 0, Short.MAX_VALUE)
	   			.addComponent(exitButton)
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(visiteLabel)
				.addComponent(visiteScrollPane)
				.addGap(BUTTONS_GAP)
				.addComponent(exitButton)
		);
	}
}
