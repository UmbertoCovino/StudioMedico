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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import Visite.GUIControllerPrenotazioni;
import Visite.Prenotazione;

@SuppressWarnings("serial")
public class ListaPrenotazioni extends Frame {
	public static final int MODIFY_OPERATION = 1,
							DELETE_OPERATION = 2,
							REGISTER_VISIT_OPERATION = 3;
	private int operationType;
	private Map<Integer, Prenotazione> prenotazioni;
	private Object pazienteName;
	
	private JLabel prenotazioniLabel;
	private JTable prenotazioniTable;
	private JScrollPane prenotazioniScrollPane;
	private JButton confirmButton;
	private JButton cancelButton;

	public ListaPrenotazioni(Frame parentFrame, int operationType, ArrayList<Prenotazione> prenotazioni) {
		super("Lista prenotazioni effettuate", parentFrame);
		
		this.operationType = operationType;
		this.prenotazioni = new TreeMap<Integer, Prenotazione>();
		this.pazienteName = ((prenotazioni.isEmpty()) ? "" : prenotazioni.get(0).getPaziente().getNome() + " " + prenotazioni.get(0).getPaziente().getCognome() + " ");
		
		for (Prenotazione prenotazione: prenotazioni) {
			this.prenotazioni.put(prenotazione.getId(), prenotazione);
		}
		
		setExtraFrameWidth(125);
		
		// dichiarazione elementi
		prenotazioniLabel = new JLabel();
		
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
		
		// per resizare le colonne
		resizeColumnWidth(prenotazioniTable);
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
			prenotazioniLabel.setText("Prenotazioni effettuate");
			confirmButton.setText("Modifica");
			
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIControllerPrenotazioni.getInstance().createFormModificaPrenotazione(getParentFrame(), prenotazioni.get(prenotazioniTable.getValueAt(prenotazioniTable.getSelectedRow(), 0)));

					closeFrameWithoutVisualizeParent();
				}
			});
		} else if (operationType == DELETE_OPERATION) {
			prenotazioniLabel.setText("Prenotazioni effettuate");
			confirmButton.setText("Elimina");
			
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (JOptionPane.showConfirmDialog(thisFrame,
		            		"Sei sicuro di voler eliminare la prenotazione alla visita?",
		            		"Attenzione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						GUIControllerPrenotazioni.getInstance().deletePrenotazione(prenotazioni.get(prenotazioniTable.getValueAt(prenotazioniTable.getSelectedRow(), 0)).getId());
	
						JOptionPane.showMessageDialog(thisFrame, "La prenotazione è stata cancellata con successo!", "Prenotazione cancellata", JOptionPane.INFORMATION_MESSAGE);
						closeFrame();
					}
				}
			});
		} else if (operationType == REGISTER_VISIT_OPERATION) {
			prenotazioniLabel.setText("Prenotazioni effettuate dal paziente " + pazienteName);
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
		   		.addComponent(prenotazioniScrollPane, 0, prenotazioniTable.getPreferredSize().width, Short.MAX_VALUE)
		   		.addGroup(layout.createSequentialGroup()
		   			.addComponent(cancelButton)
		   			.addComponent(confirmButton))
		);
		
		int tableHeight = prenotazioniTable.getPreferredSize().height + 23;
		if (tableHeight < 39)
			tableHeight = 39;
		else if (tableHeight > 500)
			tableHeight = 500;
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(prenotazioniLabel)
				.addComponent(prenotazioniScrollPane, 0, tableHeight, Short.MAX_VALUE)
				.addGap(getButtonsGap())
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(cancelButton)
					.addComponent(confirmButton))
		);
		
		getRootPane().setDefaultButton(confirmButton);
	}
}
