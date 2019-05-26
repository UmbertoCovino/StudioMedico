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
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Visite.Fattura;
import Visite.GUIControllerPrenotazioni;
import Visite.GUIControllerVisite;
import Visite.Prenotazione;

public class ListaFatture extends Frame {
	private Frame parentFrame;
	
	private JLabel fattureLabel;
	private JList<Fattura> fattureList;
	private JScrollPane fattureScrollPane;
	private JButton registraPagamentoButton;
	private JButton cancelButton;

	public ListaFatture(Frame parentFrame, ArrayList<Fattura> fatture) {
		super("Lista fatture delle visite in sospeso", parentFrame);
		
		this.parentFrame = parentFrame;
		
		setExtraFrameWidth(100);
		
		// dichiarazione elementi
		fattureLabel = new JLabel("Fatture delle visite in sospeso");
		
		fattureList = new JList<>(fatture.toArray(new Fattura[fatture.size()]));
		
		fattureScrollPane = new JScrollPane(fattureList);
		
		registraPagamentoButton = new JButton("Registra pagamento");
		cancelButton = new JButton("Annulla");
		
		registraPagamentoButton.setEnabled(false);
		
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
		
		fattureList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				registraPagamentoButton.setEnabled(true);
			}
		});

		registraPagamentoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIControllerVisite.getInstance().createFormPagamento(parentFrame, fattureList.getSelectedValue());
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
		   		.addComponent(fattureScrollPane, 0, 0, Short.MAX_VALUE)
		   		.addGroup(layout.createSequentialGroup()
		   			.addComponent(cancelButton)
		   			.addComponent(registraPagamentoButton))
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(fattureLabel)
				.addComponent(fattureScrollPane)
				.addGap(getButtonsGap())
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(cancelButton)
					.addComponent(registraPagamentoButton))
		);
	}
}
