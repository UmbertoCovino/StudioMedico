package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Visite.GUIControllerPrenotazioni;
import Visite.Prenotazione;
import Visite.Visita;

public class ListaPrenotazioni extends Frame {
	protected static final int MODIFY_OPERATION = 1,
							   DELETE_OPERATION = 2,
							   REGISTER_VISIT_OPERATION = 3;
	private int operationType;
	private Map<Integer, Prenotazione> prenotazioni;
	
	private JLabel prenotazioniLabel;
	private JTable prenotazioniTable;
	private JScrollPane prenotazioniScrollPane;
	private JButton confirmButton;
	private JButton cancelButton;

	public ListaPrenotazioni(Frame parentFrame, int operationType, ArrayList<Prenotazione> prenotazioni) {
		super("Lista prenotazioni effettuate", parentFrame);
		
		this.operationType = operationType;
		this.prenotazioni = new TreeMap<Integer, Prenotazione>();
		
		for (Prenotazione prenotazione: prenotazioni) {
			this.prenotazioni.put(prenotazione.getId(), prenotazione);
		}
		
		setExtraFrameWidth(300);
		
		// dichiarazione elementi
		prenotazioniLabel = new JLabel("Prenotazioni");
		
		DefaultTableModel tableModel = new DefaultTableModel();
		prenotazioniTable = new JTable(tableModel);
		buildTable(prenotazioni);
		
		prenotazioniScrollPane = new JScrollPane(prenotazioniTable);
		
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

	private void buildTable(ArrayList<Prenotazione> prenotazioni) {
		prenotazioniTable.setDefaultEditor(Object.class, null);
		prenotazioniTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		prenotazioniTable.getTableHeader().setReorderingAllowed(false);
//		prenotazioniTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		DefaultTableModel tableModel = (DefaultTableModel) prenotazioniTable.getModel();
		
		tableModel.addColumn("Id");
		tableModel.addColumn("Tipologia visita");
		tableModel.addColumn("Medico");
		tableModel.addColumn("Giorno");
		tableModel.addColumn("Ora");
		
		for (Prenotazione prenotazione: prenotazioni) {
			tableModel.addRow(new Object[]{prenotazione.getId(),
					prenotazione.getTipologiaVisita().getNome(),
					prenotazione.getMedico().getNome() + " " + prenotazione.getMedico().getCognome(),
					FramePaziente.DATE_SDF.format(prenotazione.getGiorno()),
					FramePaziente.TIME_SDF.format(prenotazione.getOra())});
		}
		
	}

	@Override
	protected void addingEventHandlers() {
		Frame thisFrame = this;
		
		prenotazioniTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!confirmButton.isEnabled())
					confirmButton.setEnabled(true);
			}
		});

		if (operationType == MODIFY_OPERATION) {
			confirmButton.setText("Modifica");
			
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIControllerPrenotazioni.getInstance().createFormModificaPrenotazione(getParentFrame(), prenotazioni.get(prenotazioniTable.getValueAt(prenotazioniTable.getSelectedRow(), 0)));

					closeFrameWithoutVisualizeParent();
				}
			});
		} else if (operationType == DELETE_OPERATION) {
			confirmButton.setText("Elimina");
			
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIControllerPrenotazioni.getInstance().deletePrenotazione(prenotazioni.get(prenotazioniTable.getValueAt(prenotazioniTable.getSelectedRow(), 0)).getId());

					JOptionPane.showMessageDialog(thisFrame, "La prenotazione è stata cancellata con successo!", "Prenotazione cancellata", JOptionPane.INFORMATION_MESSAGE);
					closeFrame();
				}
			});
		} else if (operationType == REGISTER_VISIT_OPERATION) {
			confirmButton.setText("Registra visita");
			
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIControllerPrenotazioni.getInstance().createFormRisultatoVisita(getParentFrame(), prenotazioni.get(prenotazioniTable.getValueAt(prenotazioniTable.getSelectedRow(), 0)));

					closeFrameWithoutVisualizeParent();
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
		   		.addComponent(prenotazioniScrollPane, 0, prenotazioniTable.getPreferredScrollableViewportSize().width, Short.MAX_VALUE)
		   		.addGroup(layout.createSequentialGroup()
		   			.addComponent(cancelButton)
		   			.addComponent(confirmButton))
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(prenotazioniLabel)
				.addComponent(prenotazioniScrollPane, 0, prenotazioniTable.getPreferredScrollableViewportSize().height - 150, Short.MAX_VALUE)
				.addGap(getButtonsGap())
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(cancelButton)
					.addComponent(confirmButton))
		);
		
		getRootPane().setDefaultButton(confirmButton);
	}
}
