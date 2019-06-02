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
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

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
	private JTable reportRigheTable;
	private JScrollPane reportRigheScrollPane;
	private JButton exitButton;

	public FormVisualizzazioneReport(Frame parentFrame, Report report) {
		super("Lista visite effettuate", parentFrame);
		
		setExtraFrameWidth(100);
		
		// dichiarazione elementi
		reportLabel = new JLabel("Visite");
		
		DefaultTableModel tableModel = new DefaultTableModel();
		reportRigheTable = new JTable(tableModel);
		buildTable(report);
		
		reportRigheScrollPane = new JScrollPane(reportRigheTable);
		
		exitButton = new JButton("Annulla");
		
		// aggiunta event handlers
		addingEventHandlers();
		
		// posizionamento elementi
		elementsPositioning();
		
		// visualizzazione frame
		showFrame();
	}

	private void buildTable(Report report) {
		reportRigheTable.setDefaultEditor(Object.class, null);
		reportRigheTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		reportRigheTable.getTableHeader().setReorderingAllowed(false);
//		reportRigheTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		DefaultTableModel tableModel = (DefaultTableModel) reportRigheTable.getModel();
		
		if (report instanceof ReportVisite) {
			ArrayList<RigaVisite> risultato = ((ReportVisite) report).getRisultato();
			
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
		} else if (report instanceof ReportVisitePerMedico) {
			ArrayList<RigaVisitePerMedico> risultato = ((ReportVisitePerMedico) report).getRisultato();
			
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
		} else if (report instanceof ReportMedici) {
			ArrayList<RigaMedici> risultato = ((ReportMedici) report).getRisultato();
			
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
		} else if (report instanceof ReportTipoVisite) {
			ArrayList<RigaTipoVisite> risultato = ((ReportTipoVisite) report).getRisultato();
			
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
		   		.addComponent(reportRigheScrollPane, 0, reportRigheTable.getPreferredScrollableViewportSize().width, Short.MAX_VALUE)
	   			.addComponent(exitButton)
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(reportLabel)
				.addComponent(reportRigheScrollPane, 0, reportRigheTable.getPreferredScrollableViewportSize().height - 150, Short.MAX_VALUE)
				.addGap(getButtonsGap())
				.addComponent(exitButton)
		);
	}
}
