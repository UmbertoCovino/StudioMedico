package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Utenti.GUIControllerUtenti;
import Visite.Fattura;
import Visite.GUIControllerVisite;

public class FormPagamento extends Frame {
	private JLabel metodoPagamentoLabel;
	private JTextField metodoPagamentoTextField;
	private JButton confirmButton;
	private JButton cancelButton;

	public FormPagamento(Frame parentFrame, Fattura fattura) {
		super("Registrazione pagamento fattura", parentFrame);
		
		setExtraFrameWidth(100);
		
		// dichiarazione elementi
		metodoPagamentoLabel = new JLabel("Metodo di pagamento");
		
		metodoPagamentoTextField = new JTextField();
		
		confirmButton = new JButton("Conferma");
		cancelButton = new JButton("Annulla");
		
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
//					GUIControllerVisite.getInstance().notifyData(metodoPagamentoTextField.getText().trim());
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
		if (metodoPagamentoTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Il campo metodo di pagamento non può essere vuoto.", "Attenzione", JOptionPane.WARNING_MESSAGE);
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
		   				.addComponent(metodoPagamentoTextField)))
		   		.addGroup(layout.createSequentialGroup()
		   			.addComponent(cancelButton)
		   			.addComponent(confirmButton))
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(metodoPagamentoLabel)
					.addComponent(metodoPagamentoTextField))
				.addGap(getButtonsGap())
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(cancelButton)
					.addComponent(confirmButton))
		);
	}
}
