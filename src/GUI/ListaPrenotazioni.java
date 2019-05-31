package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Visite.GUIControllerPrenotazioni;
import Visite.Prenotazione;

public class ListaPrenotazioni extends Frame {
	protected static final int MODIFY_OPERATION = 1,
							   DELETE_OPERATION = 2,
							   REGISTER_VISIT_OPERATION = 3;
	private int operationType;
	
	private JLabel prenotazioniLabel;
	private JList<Prenotazione> prenotazioniList;
	private JScrollPane prenotazioniScrollPane;
	private JButton confirmButton;
	private JButton cancelButton;

	public ListaPrenotazioni(Frame parentFrame, int operationType, ArrayList<Prenotazione> prenotazioni) {
		super("Lista prenotazioni effettuate", parentFrame);
		
		this.operationType = operationType;
		
		setExtraFrameWidth(100);
		
		// dichiarazione elementi
		prenotazioniLabel = new JLabel("Prenotazioni");
		
		prenotazioniList = new JList<>(prenotazioni.toArray(new Prenotazione[prenotazioni.size()]));
		
		prenotazioniScrollPane = new JScrollPane(prenotazioniList);
		
		confirmButton = new JButton();
		cancelButton = new JButton("Annulla");
		
		confirmButton.setEnabled(false);
		
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
		
		prenotazioniList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				confirmButton.setEnabled(true);
			}
		});

		if (operationType == MODIFY_OPERATION) {
			confirmButton.setText("Modifica");
			
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIControllerPrenotazioni.getInstance().createFormModificaPrenotazione(getParentFrame(), prenotazioniList.getSelectedValue());

					closeFrame();
				}
			});
		} else if (operationType == DELETE_OPERATION) {
			confirmButton.setText("Elimina");
			
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIControllerPrenotazioni.getInstance().deletePrenotazione(prenotazioniList.getSelectedValue().getId());

					JOptionPane.showMessageDialog(thisFrame, "La prenotazione è stata cancellata con successo!", "Prenotazione cancellata", JOptionPane.INFORMATION_MESSAGE);
					closeFrame();
				}
			});
		} else if (operationType == REGISTER_VISIT_OPERATION) {
			confirmButton.setText("Registra visita");
			
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIControllerPrenotazioni.getInstance().createFormRisultatoVisita(getParentFrame(), prenotazioniList.getSelectedValue());

					closeFrame();
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
		   		.addComponent(prenotazioniScrollPane, 0, 0, Short.MAX_VALUE)
		   		.addGroup(layout.createSequentialGroup()
		   			.addComponent(cancelButton)
		   			.addComponent(confirmButton))
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(prenotazioniLabel)
				.addComponent(prenotazioniScrollPane)
				.addGap(getButtonsGap())
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(cancelButton)
					.addComponent(confirmButton))
		);
		
		getRootPane().setDefaultButton(confirmButton);
	}
}
