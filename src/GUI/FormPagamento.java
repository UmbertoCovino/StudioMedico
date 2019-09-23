package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Visite.Fattura;
import Visite.GUIControllerVisite;

@SuppressWarnings("serial")
public class FormPagamento extends Frame {
	private static final String[] METODI_DI_PAGAMENTO = new String[]{"Contanti",
																  	 "Bonifico bancario", 
																  	 "Carta di credito",
															  		 "Assegno"};
	
	private JLabel metodoPagamentoLabel;
	private JComboBox<Object> metodoPagamentoComboBox;
	private JButton confirmButton;
	private JButton cancelButton;

	public FormPagamento(Frame parentFrame, Fattura fattura) {
		super("Registrazione pagamento fattura", parentFrame);
		
		setExtraFrameWidth(100);
		
		// dichiarazione elementi
		metodoPagamentoLabel = new JLabel("Metodo di pagamento");
		
		metodoPagamentoComboBox = new JComboBox<>();
		
		confirmButton = new JButton("Conferma");
		cancelButton = new JButton("Annulla");
		
		fillComboBox(metodoPagamentoComboBox, METODI_DI_PAGAMENTO);
		
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
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dataIsValid()) {
					if (JOptionPane.showConfirmDialog(thisFrame,
		            		"Confermi la registrazione dell'avvenuto pagamento della fattura?",
		            		"Attenzione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						GUIControllerVisite.getInstance().notifyData(((String) metodoPagamentoComboBox.getSelectedItem()).trim());
	
						JOptionPane.showMessageDialog(thisFrame, "Il pagamento è stato registrato con successo!", "Pagamento registrato", JOptionPane.INFORMATION_MESSAGE);
						closeFrame();
					}
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
			}
		});
	}

	protected boolean dataIsValid() {
		if (metodoPagamentoComboBox.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Bisogna selezionare un metodo di pagamento.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else
			return true;
		
		return false;
	}

	@Override
	protected void elementsPositioning() {
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.CENTER)
		   		.addGroup(layout.createSequentialGroup()
		   			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		   				.addComponent(metodoPagamentoLabel))
		   			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		   				.addComponent(metodoPagamentoComboBox)))
		   		.addGroup(layout.createSequentialGroup()
		   			.addComponent(cancelButton)
		   			.addComponent(confirmButton))
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(metodoPagamentoLabel)
					.addComponent(metodoPagamentoComboBox))
				.addGap(getButtonsGap())
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(cancelButton)
					.addComponent(confirmButton))
		);
		
		getRootPane().setDefaultButton(confirmButton);
	}
}
