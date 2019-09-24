package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;

import Amministrazione.GUIControllerAmministrazione;
import Utenti.GUIControllerUtenti;
import Utenti.Utente;

@SuppressWarnings("serial")
public class FrameProprietario extends Frame {
	@SuppressWarnings("unused")
	private Utente proprietario;
	
	private JButton creaReportButton;
	private JButton logoutButton;

	public FrameProprietario(Utente proprietario) {
		super("Area proprietario", true);
		
		this.proprietario = proprietario;
		
		setExtraFrameWidth(150);
		
		// dichiarazione elementi
		creaReportButton = new JButton("Crea report");
		logoutButton = new JButton("Logout");
		
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
		
		creaReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIControllerAmministrazione.getInstance().createFormCreazioneReport(thisFrame);
			}
		});
		
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIControllerUtenti.getInstance().createFrameLogin();
				closeFrame();
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
					.addGap(0, 0, Short.MAX_VALUE)
					.addComponent(creaReportButton)
					.addGap(0, 0, Short.MAX_VALUE))
	   			.addComponent(logoutButton)
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addGap(0)
					.addComponent(creaReportButton)
					.addGap(0))
	   			.addComponent(logoutButton)
		);
	}
}
