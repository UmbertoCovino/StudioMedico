package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Amministrazione.CalendarioDisponibilita;
import Amministrazione.CalendarioDisponibilitaEmptyException;
import Amministrazione.DisponibilitaGiornaliera;
import Amministrazione.OrariEmptyException;
import Utenti.Medico;
import Visite.GUIControllerPrenotazioni;
import Visite.Prenotazione;
import Visite.TipologiaVisita;

@SuppressWarnings({"serial", "deprecation"})
class Time extends Date {

	public Time(Date d) {
		super(d.getYear(), d.getMonth(), d.getDay(), d.getHours(), d.getMinutes(), d.getSeconds());
	}

	@Override
	public String toString() {
		return GUI.FramePaziente.TIME_SDF.format(this);
	}
}

@SuppressWarnings({"serial"})
public class FormPrenotazioneVisita extends Frame {
	private JLabel tipologiaVisitaLabel;
	private JLabel medicoLabel;
	private JLabel calendarioLabel;
	private JLabel orarioLabel;
	private JComboBox<Object> tipologiaVisitaComboBox;
	private JComboBox<Object> medicoComboBox;
	private JComboBox<Object> calendarioComboBox;
	private JComboBox<Object> orarioComboBox;
	private JButton confirmButton;
	private JButton cancelButton;
	
	protected ArrayList<Prenotazione> prenotazioniPerMedico;

	public FormPrenotazioneVisita(Frame parentFrame) {
		super("Prenotazione visita", parentFrame, "Sei sicuro di voler annullare la prenotazione alla visita?");
		
		// dichiarazione elementi
		tipologiaVisitaLabel = new JLabel("Tipologia visita");
		medicoLabel = new JLabel("Medico");
		calendarioLabel = new JLabel("Calendario disponibilità");
		orarioLabel = new JLabel("Orari disponibili per il giorno selezionato");
		
		tipologiaVisitaComboBox = new JComboBox<>();
		medicoComboBox = new JComboBox<>();
		calendarioComboBox = new JComboBox<>();
		orarioComboBox = new JComboBox<>();
		
		confirmButton = new JButton("Conferma");
		cancelButton = new JButton("Annulla");
		
		tipologiaVisitaComboBox.setEnabled(false);
		medicoComboBox.setEnabled(false);
		calendarioComboBox.setEnabled(false);
		orarioComboBox.setEnabled(false);
		
		// caricamento dati in tipologiaVisitaComboBox
		updateTipologieVisite(GUIControllerPrenotazioni.getInstance().getTipologieVisite());
		
		// aggiunta event handlers
		addingEventHandlers();
		
		// posizionamento elementi
		elementsPositioning();
		
		// visualizzazione frame
		showFrame();
	}

	private void updateTipologieVisite(ArrayList<TipologiaVisita> tipologieVisite) {
		fillComboBox(tipologiaVisitaComboBox, castArrayList(tipologieVisite));
		tipologiaVisitaComboBox.setEnabled(true);
	}

	@Override
	protected void addingEventHandlers() {
		Frame thisFrame = this;
		
		tipologiaVisitaComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tipologiaVisitaComboBox.getSelectedIndex() != 0) {
					ArrayList<Medico> medici = GUIControllerPrenotazioni.getInstance().getMedici(((TipologiaVisita) tipologiaVisitaComboBox.getSelectedItem()).getNome());
					updateMedici(medici);
					
					medicoComboBox.setEnabled(true);
				} else {
					((DefaultComboBoxModel<Object>) medicoComboBox.getModel()).removeAllElements();
					medicoComboBox.setEnabled(false);
				}
				
				((DefaultComboBoxModel<Object>) calendarioComboBox.getModel()).removeAllElements();
				calendarioComboBox.setEnabled(false);
				((DefaultComboBoxModel<Object>) orarioComboBox.getModel()).removeAllElements();
				orarioComboBox.setEnabled(false);
				refreshFrameDims();
			}
		});
				
		medicoComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tipologiaVisitaComboBox.getSelectedIndex() != 0 && medicoComboBox.getSelectedIndex() != 0) {
					CalendarioDisponibilita calendarioDisponibilita = GUIControllerPrenotazioni.getInstance().getCalendarioDisponibilitaFiltered(
							((Medico) medicoComboBox.getSelectedItem()).getCodice(),
							((TipologiaVisita) tipologiaVisitaComboBox.getSelectedItem()).getNome());
					
					try {
						if (calendarioDisponibilita == null)
							throw new CalendarioDisponibilitaEmptyException("");
						
						updateCalendarioDisponibilita(calendarioDisponibilita);
		
						calendarioComboBox.setEnabled(true);
					} catch (CalendarioDisponibilitaEmptyException e1) {
						((DefaultComboBoxModel<Object>) calendarioComboBox.getModel()).removeAllElements();
						calendarioComboBox.setEnabled(false);
						JOptionPane.showMessageDialog(thisFrame, "Spiacenti, non sono attualmente presenti disponibilità per il medico selezionato.", "Attenzione", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					((DefaultComboBoxModel<Object>) calendarioComboBox.getModel()).removeAllElements();
					calendarioComboBox.setEnabled(false);
				}
				
				((DefaultComboBoxModel<Object>) orarioComboBox.getModel()).removeAllElements();
				orarioComboBox.setEnabled(false);
				refreshFrameDims();
			}
		});
		
		calendarioComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tipologiaVisitaComboBox.getSelectedIndex() != 0 && medicoComboBox.getSelectedIndex() != 0 && calendarioComboBox.getSelectedIndex() != 0) {
					DisponibilitaGiornaliera disponibilitaSelected = ((DisponibilitaGiornaliera) calendarioComboBox.getSelectedItem());
					
					if (disponibilitaSelected != null) {
						ArrayList<Date> orariFiltratiToCast = GUIControllerPrenotazioni.getInstance().getOrariFiltered(disponibilitaSelected,
								((Medico) medicoComboBox.getSelectedItem()).getCodice(),
								((TipologiaVisita) tipologiaVisitaComboBox.getSelectedItem()).getNome());
						
						try {
							if (orariFiltratiToCast.isEmpty())
								throw new OrariEmptyException("");
								
							ArrayList<Time> orariFiltrati = new ArrayList<>();
							
							for (Date orario : orariFiltratiToCast) {
								orariFiltrati.add(new Time(orario));
							}
							
							updateOrari(orariFiltrati);
							
							orarioComboBox.setEnabled(true);
						} catch (OrariEmptyException e1) {
							((DefaultComboBoxModel<Object>) orarioComboBox.getModel()).removeAllElements();
							orarioComboBox.setEnabled(false);
							JOptionPane.showMessageDialog(thisFrame, "Spiacenti, non sono attualmente presenti orari liberi per il giorno selezionato.", "Attenzione", JOptionPane.WARNING_MESSAGE);
						}
					}
				} else {
					((DefaultComboBoxModel<Object>) orarioComboBox.getModel()).removeAllElements();
					orarioComboBox.setEnabled(false);
				}
				
				refreshFrameDims();
			}
		});
		
		orarioComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tipologiaVisitaComboBox.getSelectedIndex() != 0 && medicoComboBox.getSelectedIndex() != 0 && calendarioComboBox.getSelectedIndex() != 0 && orarioComboBox.getSelectedIndex() != 0) {
					confirmButton.setEnabled(true);
				}
			}
		});
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dataIsValid()) {
					if (JOptionPane.showConfirmDialog(thisFrame,
		            		"Confermi la prenotazione alla visita?",
		            		"Attenzione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						GUIControllerPrenotazioni.getInstance().notifyData(
								((DisponibilitaGiornaliera) calendarioComboBox.getSelectedItem()).getGiorno(),
								(Date) orarioComboBox.getSelectedItem(),
								(TipologiaVisita) tipologiaVisitaComboBox.getSelectedItem(),
								(Medico) medicoComboBox.getSelectedItem());

						JOptionPane.showMessageDialog(thisFrame, "La prenotazione è stata aggiunta con successo!", "Prenotazione aggiunta", JOptionPane.INFORMATION_MESSAGE);
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

	protected void updateMedici(ArrayList<Medico> medici) {
		fillComboBox(medicoComboBox, castArrayList(medici));
	}

	protected void updateCalendarioDisponibilita(CalendarioDisponibilita calendarioDisponibilita) {
		fillComboBox(calendarioComboBox, castArrayList(calendarioDisponibilita.getOrari()));
	}

	protected void updateOrari(ArrayList<Time> orari) {
		fillComboBox(orarioComboBox, castArrayList(orari));
	}

	protected boolean dataIsValid() {
		if (tipologiaVisitaComboBox.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Bisogna selezionare una tipologia di visita.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (medicoComboBox.getSelectedIndex() == 0 || !medicoComboBox.isEnabled()) {
			JOptionPane.showMessageDialog(this, "Bisogna selezionare un medico.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (calendarioComboBox.getSelectedIndex() == 0 || !calendarioComboBox.isEnabled()) {
			JOptionPane.showMessageDialog(this, "Bisogna selezionare un giorno.", "Attenzione", JOptionPane.WARNING_MESSAGE);
		} else if (orarioComboBox.getSelectedIndex() == 0 || !orarioComboBox.isEnabled()) {
			JOptionPane.showMessageDialog(this, "Bisogna selezionare un orario.", "Attenzione", JOptionPane.WARNING_MESSAGE);
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
			   			.addComponent(tipologiaVisitaLabel)
		   				.addComponent(medicoLabel)
		   				.addComponent(calendarioLabel)
		   				.addComponent(orarioLabel))
		   			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		   				.addComponent(tipologiaVisitaComboBox)
		   				.addComponent(medicoComboBox)
		   				.addComponent(calendarioComboBox)
		   				.addComponent(orarioComboBox)))
		   		.addGroup(layout.createSequentialGroup()
		   			.addComponent(cancelButton)
		   			.addComponent(confirmButton))
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(tipologiaVisitaLabel)
					.addComponent(tipologiaVisitaComboBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(medicoLabel)
					.addComponent(medicoComboBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(calendarioLabel)
					.addComponent(calendarioComboBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(orarioLabel)
					.addComponent(orarioComboBox))
				.addGap(getButtonsGap())
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(cancelButton)
					.addComponent(confirmButton))
		);
		
		getRootPane().setDefaultButton(confirmButton);
	}
}
