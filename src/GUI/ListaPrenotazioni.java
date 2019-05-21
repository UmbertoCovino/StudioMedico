package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Visite.GUIControllerPrenotazioni;
import Visite.Prenotazione;

public class ListaPrenotazioni extends Frame {
	private static final int MAX_FRAME_WIDTH = 99999,
			 				 EXTRA_FRAME_WIDTH = 100,
							 BUTTONS_GAP = 15;
	protected static final int MODIFY_OPERATION = 1,
							   DELETE_OPERATION = 2;
	private int operationType;
	private JLabel prenotazioniLabel;
	private JList<Prenotazione> prenotazioniList;
	private JButton modifyOrDeleteButton;
	private JButton cancelButton;

	public ListaPrenotazioni(Frame parentFrame, int operationType, ArrayList<Prenotazione> prenotazioni) {
		super("Lista prenotazioni effettuate", parentFrame);
		
		// dichiarazione elementi
		prenotazioniLabel = new JLabel("Prenotazioni");
		
		prenotazioniList = new JList<>(prenotazioni.toArray(new Prenotazione[prenotazioni.size()]));
		
		modifyOrDeleteButton = new JButton();
		cancelButton = new JButton("Annulla");
		
		modifyOrDeleteButton.setEnabled(false);
		
		this.operationType = operationType;
		
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
		
		prenotazioniList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				//
			}
		});

		if (operationType == MODIFY_OPERATION) {
			modifyOrDeleteButton.setText("Modifica");
			
			modifyOrDeleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					GUIControllerPrenotazioni.getInstance().createFormModificaPrenotazione(prenotazioniList.getSelectedValue());
				}
			});
		} else if (operationType == DELETE_OPERATION) {
			modifyOrDeleteButton.setText("Elimina");
			
			modifyOrDeleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					GUIControllerPrenotazioni.getInstance().deletePrenotazione(prenotazioniList.getSelectedValue());
				}
			});
		}
		
		cancelButton.addActionListener(new ActionListener() {
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
				.addComponent(prenotazioniLabel, 0, 0, Short.MAX_VALUE)
		   		.addComponent(prenotazioniList, 0, 0, Short.MAX_VALUE)
		   		.addGroup(layout.createSequentialGroup()
		   			.addComponent(cancelButton)
		   			.addComponent(modifyOrDeleteButton))
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(prenotazioniLabel)
				.addComponent(prenotazioniList)
				.addGap(BUTTONS_GAP)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(cancelButton)
					.addComponent(modifyOrDeleteButton))
		);
	}
}
