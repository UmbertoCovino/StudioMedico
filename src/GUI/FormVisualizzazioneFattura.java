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

public class FormVisualizzazioneFattura extends Frame {
	private JLabel idLabel;
	private JLabel idValueLabel;
	private JLabel importoLabel;
	private JLabel importoValueLabel;
	private JLabel visitaLabel;
	private JLabel visitaValueLabel;
	private JLabel pazienteLabel;
	private JLabel pazienteValueLabel;
	private JButton cancelButton;

	public FormVisualizzazioneFattura(Frame parentFrame, Fattura fattura) {
		super("Visualizzazione fattura", parentFrame);
		
		setExtraFrameWidth(100);
		
		// dichiarazione elementi
		idLabel = new JLabel("Fattura N.");
		importoLabel = new JLabel("Importo");
		visitaLabel = new JLabel("Visita");
		pazienteLabel = new JLabel("Intestata a");
		
		idValueLabel = new JLabel(fattura.getId() + "");
		importoValueLabel = new JLabel(fattura.getImporto() + "");
		visitaValueLabel = new JLabel(fattura.getVisita().getTipologiaVisita().getNome() + ", il " +
				fattura.getVisita().getGiorno() + " alle " +
				fattura.getVisita().getOra() + ", dal medico " +
				fattura.getVisita().getMedico().getNome() + " " +
				fattura.getVisita().getMedico().getCognome());
		pazienteValueLabel = new JLabel(fattura.getPaziente().getNome() + " " +
				fattura.getPaziente().getCognome() + ", " +
				fattura.getPaziente().getCodiceFiscale() + ", " +
				fattura.getPaziente().getEmail());
		
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
		   		.addGroup(layout.createSequentialGroup()
		   			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		   				.addComponent(idLabel)
		   				.addComponent(importoLabel)
		   				.addComponent(visitaLabel)
		   				.addComponent(pazienteLabel))
		   			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		   				.addComponent(idValueLabel)
		   				.addComponent(importoValueLabel)
		   				.addComponent(visitaValueLabel)
		   				.addComponent(pazienteValueLabel)))
		   		.addGroup(layout.createSequentialGroup()
		   			.addComponent(cancelButton))
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(idLabel)
					.addComponent(idValueLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(importoLabel)
					.addComponent(importoValueLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(visitaLabel)
					.addComponent(visitaValueLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(pazienteLabel)
					.addComponent(pazienteValueLabel))
				.addGap(getButtonsGap())
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(cancelButton))
		);
	}
}
