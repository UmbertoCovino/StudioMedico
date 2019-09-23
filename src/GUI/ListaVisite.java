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

import Visite.GUIControllerVisite;
import Visite.Visita;

@SuppressWarnings("serial")
public class ListaVisite extends Frame {
	protected static final int STORICO_VISITE_OPERATION = 1,
							   GENERATE_FATTURA_OPERATION = 2;
	private int operationType;
	private Map<Integer, Visita> visite;
	private String pazienteName;

	private JLabel visiteLabel;
	private JTable visiteTable;
	private JScrollPane visiteScrollPane;
	private JButton confirmButton;
	private JButton cancelButton;

	public ListaVisite(Frame parentFrame, int operationType, ArrayList<Visita> visite) {
		super("Lista visite effettuate", parentFrame);
		
		this.operationType = operationType;
		this.visite = new TreeMap<Integer, Visita>();
		this.pazienteName = ((visite.isEmpty()) ? "" : visite.get(0).getPaziente().getNome() + " " + visite.get(0).getPaziente().getCognome() + " ");
		
		for (Visita visita: visite) {
			this.visite.put(visita.getId(), visita);
		}
		
		setExtraFrameWidth(350);
		
		// dichiarazione elementi
		visiteLabel = new JLabel();
		
		DefaultTableModel tableModel = new DefaultTableModel();
		visiteTable = new JTable(tableModel);
		buildTable(visite);
		
		visiteScrollPane = new JScrollPane(visiteTable);
		
		confirmButton = new JButton();
		cancelButton = new JButton();
		
		confirmButton.setEnabled(false);
		
		// aggiunta event handlers
		addingEventHandlers();
		
		// posizionamento elementi
		elementsPositioning();
		
		// visualizzazione frame
		showFrame();
		
		// per resizare le colonne
		resizeColumnWidth(visiteTable);
	}

	private void buildTable(ArrayList<Visita> visite) {
		visiteTable.setDefaultEditor(Object.class, null);
		visiteTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		visiteTable.getTableHeader().setReorderingAllowed(false);
//		visiteTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		DefaultTableModel tableModel = (DefaultTableModel) visiteTable.getModel();
		
		tableModel.addColumn("Id");
		tableModel.addColumn("Tipologia visita");
		tableModel.addColumn("Medico");
		tableModel.addColumn("Giorno");
		tableModel.addColumn("Ora");
		tableModel.addColumn("Diagnosi");
		tableModel.addColumn("Terapia");
		
		for (Visita visita: visite) {
			tableModel.addRow(new Object[]{visita.getId(),
					visita.getTipologiaVisita().getNome(),
					visita.getMedico().getNome() + " " + visita.getMedico().getCognome(),
					FramePaziente.DATE_SDF.format(visita.getGiorno()),
					FramePaziente.TIME_SDF.format(visita.getOra()),
					visita.getDiagnosi(),
					visita.getTerapia()});
		}
	}

	@Override
	protected void addingEventHandlers() {
		Frame thisFrame = this;
		
		if (operationType == STORICO_VISITE_OPERATION) {
			visiteLabel.setText("Visite effettuate");
			cancelButton.setText("Esci");
			confirmButton.setVisible(false);
		} else if (operationType == GENERATE_FATTURA_OPERATION) {
			visiteLabel.setText("Visite effettuate dal paziente " + pazienteName + "con fattura da generare");
			confirmButton.setText("Genera fattura");
			cancelButton.setText("Annulla");
			
			visiteTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if (!confirmButton.isEnabled())
						confirmButton.setEnabled(true);
				}
			});
			
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (JOptionPane.showConfirmDialog(thisFrame,
		            		"Confermi di voler generare la fattura della visita?",
		            		"Attenzione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						GUIControllerVisite.getInstance().printFattura(getParentFrame(), visite.get(visiteTable.getValueAt(visiteTable.getSelectedRow(), 0)));
	
						closeFrame();
					}
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
				.addComponent(visiteLabel, 0, 0, Short.MAX_VALUE)
		   		.addComponent(visiteScrollPane, 0, visiteTable.getPreferredSize().width, Short.MAX_VALUE)
		   		.addGroup(layout.createSequentialGroup()
			   			.addComponent(cancelButton)
			   			.addComponent(confirmButton))
		);
		
		int tableHeight = visiteTable.getPreferredSize().height + 23;
		if (tableHeight < 39)
			tableHeight = 39;
		else if (tableHeight > 500)
			tableHeight = 500;
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(visiteLabel)
				.addComponent(visiteScrollPane, 0, tableHeight, Short.MAX_VALUE)
				.addGap(getButtonsGap())
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(cancelButton)
						.addComponent(confirmButton))
		);
		
		getRootPane().setDefaultButton(confirmButton);
	}
}
