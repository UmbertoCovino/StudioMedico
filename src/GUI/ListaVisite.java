package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Visite.GUIControllerVisite;
import Visite.Visita;

public class ListaVisite extends Frame {
	protected static final int STORICO_VISITE_OPERATION = 1,
							   GENERATE_FATTURA_OPERATION = 2;
	private int operationType;

	private JLabel visiteLabel;
//	private JList<Visita> visiteList;
	private JTable visiteList;
	private JScrollPane visiteScrollPane;
	private JButton confirmButton;
	private JButton cancelButton;

	public ListaVisite(Frame parentFrame, int operationType, ArrayList<Visita> visite) {
		super("Lista visite effettuate", parentFrame);
		
		this.operationType = operationType;
		
		setExtraFrameWidth(100);
		
		// dichiarazione elementi
		visiteLabel = new JLabel("Visite");
		
//		visiteList = new JList<>(visite.toArray(new Visita[visite.size()]));
//		
//		visiteScrollPane = new JScrollPane(visiteList);
		
		//////// ATTTEEEENZIONNEEEEE ///////////////////////////////////////////////////
		
		DefaultTableModel tableModel = new DefaultTableModel();
		visiteList = new JTable(tableModel);
		visiteList.setDefaultEditor(Object.class, null);
		visiteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		//visiteList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		tableModel.addColumn("Nome tipologia visita");
		tableModel.addColumn("Nome medico");
		tableModel.addColumn("Giorno");
		tableModel.addColumn("Ora");
		tableModel.addColumn("Diagnosi");
		tableModel.addColumn("Terapia");
		
		for (Visita visita: visite) {
			tableModel.addRow(new Object[]{visita.getTipologiaVisita().getNome(),
					visita.getMedico().getNome() + " " + visita.getMedico().getCognome(),
					visita.getGiorno(),
					visita.getOra(),
					visita.getDiagnosi(),
					visita.getTerapia()});
		}
		
		visiteScrollPane = new JScrollPane(visiteList);
		
		////////ATTTEEEENZIONNEEEEE ///////////////////////////////////////////////////
		
		confirmButton = new JButton();
		cancelButton = new JButton();
		
		confirmButton.setVisible(false);
		
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
		
		if (operationType == STORICO_VISITE_OPERATION) {
			cancelButton.setText("Esci");
			
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// non fa nulla
				}
			});
		} else if (operationType == GENERATE_FATTURA_OPERATION) {
			confirmButton.setText("Genera fattura");
			cancelButton.setText("Annulla");
			confirmButton.setVisible(true);
			
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIControllerVisite.getInstance().printFattura(getParentFrame(), visiteList.getSelectedValue());

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
				.addComponent(visiteLabel, 0, 0, Short.MAX_VALUE)
		   		.addComponent(visiteScrollPane, 0, 0, Short.MAX_VALUE)
		   		.addGroup(layout.createSequentialGroup()
			   			.addComponent(cancelButton)
			   			.addComponent(confirmButton))
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(visiteLabel)
				.addComponent(visiteScrollPane)
				.addGap(getButtonsGap())
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(cancelButton)
						.addComponent(confirmButton))
		);
		
		getRootPane().setDefaultButton(confirmButton);
	}
}
