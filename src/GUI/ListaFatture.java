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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Visite.Fattura;
import Visite.GUIControllerVisite;

@SuppressWarnings("serial")
public class ListaFatture extends Frame {
	private Map<Integer, Fattura> fatture;
	
	private JLabel fattureLabel;
	private JTable fattureTable;
	private JScrollPane fattureScrollPane;
	private JButton registraPagamentoButton;
	private JButton cancelButton;

	public ListaFatture(Frame parentFrame, ArrayList<Fattura> fatture) {
		super("Lista fatture delle visite in sospeso", parentFrame);
		
		this.fatture = new TreeMap<Integer, Fattura>();
		
		for (Fattura fattura: fatture) {
			this.fatture.put(fattura.getId(), fattura);
		}
		
		setExtraFrameWidth(125);
		
		// dichiarazione elementi
		fattureLabel = new JLabel("Fatture delle visite in sospeso per il paziente " + ((fatture.isEmpty()) ? "" : fatture.get(0).getPaziente().getNome() + " " +  fatture.get(0).getPaziente().getCognome()));
		
		DefaultTableModel tableModel = new DefaultTableModel();
		fattureTable = new JTable(tableModel);
		buildTable(fatture);
		
		fattureScrollPane = new JScrollPane(fattureTable);
		
		registraPagamentoButton = new JButton("Registra pagamento");
		cancelButton = new JButton("Annulla");
		
		registraPagamentoButton.setEnabled(false);
		
		// aggiunta event handlers
		addingEventHandlers();
		
		// posizionamento elementi
		elementsPositioning();
		
		// visualizzazione frame
		showFrame();
		
		// per resizare le colonne
		resizeColumnWidth(fattureTable);
	}

	private void buildTable(ArrayList<Fattura> fatture) {
		fattureTable.setDefaultEditor(Object.class, null);
		fattureTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fattureTable.getTableHeader().setReorderingAllowed(false);
//		fattureTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		DefaultTableModel tableModel = (DefaultTableModel) fattureTable.getModel();
		
		tableModel.addColumn("Id");
		tableModel.addColumn("Tipologia visita");
		tableModel.addColumn("Medico");
		tableModel.addColumn("Giorno");
		tableModel.addColumn("Ora");
		tableModel.addColumn("Importo");
		
		for (Fattura fattura: fatture) {
			tableModel.addRow(new Object[]{fattura.getId(),
					fattura.getVisita().getTipologiaVisita().getNome(),
					fattura.getVisita().getMedico().getNome() + " " + fattura.getVisita().getMedico().getCognome(),
					FramePaziente.DATE_SDF.format(fattura.getVisita().getGiorno()),
					FramePaziente.TIME_SDF.format(fattura.getVisita().getOra()),
					String.format("� %.2f", fattura.getImporto())});
		}
		
	}

	@Override
	protected void addingEventHandlers() {
		Frame thisFrame = this;
		
		fattureTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!registraPagamentoButton.isEnabled())
					registraPagamentoButton.setEnabled(true);
			}
		});

		registraPagamentoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIControllerVisite.getInstance().createFormPagamento(getParentFrame(), fatture.get(fattureTable.getValueAt(fattureTable.getSelectedRow(), 0)));

				closeFrameWithoutVisualizeParent();
			}
		});
		
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
				.addComponent(fattureLabel, 0, 0, Short.MAX_VALUE)
		   		.addComponent(fattureScrollPane, 0, fattureTable.getPreferredSize().width, Short.MAX_VALUE)
		   		.addGroup(layout.createSequentialGroup()
		   			.addComponent(cancelButton)
		   			.addComponent(registraPagamentoButton))
		);
		
		int tableHeight = fattureTable.getPreferredSize().height + 23;
		if (tableHeight < 39)
			tableHeight = 39;
		else if (tableHeight > 500)
			tableHeight = 500;
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(fattureLabel)
				.addComponent(fattureScrollPane, 0, tableHeight, Short.MAX_VALUE)
				.addGap(getButtonsGap())
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(cancelButton)
					.addComponent(registraPagamentoButton))
		);
		
		getRootPane().setDefaultButton(registraPagamentoButton);
	}
}
