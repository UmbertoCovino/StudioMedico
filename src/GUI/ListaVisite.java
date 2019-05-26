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
	protected static final int STORICO_VISITE_OPERATION = 1,
							   GENERATE_FATTURA_OPERATION = 2;
	private int operationType;

	private JLabel visiteLabel;
	private JList<Visita> visiteList;
	private JScrollPane visiteScrollPane;
	private JButton exitButton;

	public ListaVisite(Frame parentFrame, int operationType, ArrayList<Visita> visite) {
		super("Lista visite effettuate", parentFrame);
		
		setExtraFrameWidth(100);
		
		// dichiarazione elementi
		visiteLabel = new JLabel("Visite");
		
		visiteList = new JList<>(visite.toArray(new Visita[visite.size()]));
		
		visiteScrollPane = new JScrollPane(visiteList);
		
		exitButton = new JButton("Esci");
		
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
				.addGap(getButtonsGap())
				.addComponent(exitButton)
		);
	}
}
