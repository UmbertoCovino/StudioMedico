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

import Amministrazione.Report;
import Amministrazione.ReportMedici;
import Amministrazione.ReportTipoVisite;
import Amministrazione.ReportVisite;
import Amministrazione.ReportVisitePerMedico;
import Amministrazione.RigaMedici;
import Amministrazione.RigaTipoVisite;
import Amministrazione.RigaVisite;
import Amministrazione.RigaVisitePerMedico;
import Visite.Visita;

public class FormVisualizzazioneReport extends Frame {
	private JLabel reportLabel;
	private JList<Object> reportRigheList;
	private JScrollPane reportRigheScrollPane;
	private JButton exitButton;

	public FormVisualizzazioneReport(Frame parentFrame, Report report) {
		super("Lista visite effettuate", parentFrame);
		
		setExtraFrameWidth(100);
		
		// dichiarazione elementi
		reportLabel = new JLabel("Visite");
		
		if (report instanceof ReportVisite) {
			ArrayList<RigaVisite> risultato = ((ReportVisite) report).getRisultato();
			reportRigheList = new JList<>(risultato.toArray(new RigaVisite[risultato.size()]));
			
		} else if (report instanceof ReportVisitePerMedico) {
			ArrayList<RigaVisitePerMedico> risultato = ((ReportVisitePerMedico) report).getRisultato();
			reportRigheList = new JList<>(risultato.toArray(new RigaVisitePerMedico[risultato.size()]));
			
		} else if (report instanceof ReportMedici) {
			ArrayList<RigaMedici> risultato = ((ReportMedici) report).getRisultato();
			reportRigheList = new JList<>(risultato.toArray(new RigaMedici[risultato.size()]));
			
		} else if (report instanceof ReportTipoVisite) {
			ArrayList<RigaTipoVisite> risultato = ((ReportTipoVisite) report).getRisultato();
			reportRigheList = new JList<>(risultato.toArray(new RigaTipoVisite[risultato.size()]));
		}
		
		reportRigheScrollPane = new JScrollPane(reportRigheList);
		
		exitButton = new JButton("Annulla");
		
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
		
		exitButton.addActionListener(new ActionListener() {
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
				.addComponent(reportLabel, 0, 0, Short.MAX_VALUE)
		   		.addComponent(reportRigheScrollPane, 0, 0, Short.MAX_VALUE)
	   			.addComponent(exitButton)
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(reportLabel)
				.addComponent(reportRigheScrollPane)
				.addGap(getButtonsGap())
				.addComponent(exitButton)
		);
	}
}
