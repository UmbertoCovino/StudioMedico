package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;

public class FrameProprietario extends Frame {
	private static final int MAX_FRAME_WIDTH = 99999,
			 				 EXTRA_FRAME_WIDTH = 150;

	public FrameProprietario() {
		super("Area proprietario", true);
		
		// dichiarazione elementi
						
		JButton creaReportButton = new JButton("Crea report");
		
		// event handlers
		
		creaReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 
				//JOptionPane.showMessageDialog(frame, "Messaggio.", "Attenzione", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		// posizionamento elementi
		
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
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addGap(0)
					.addComponent(creaReportButton)
					.addGap(0))
		);
		
		// operazioni finali
		
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setSize(new Dimension(getWidth() + EXTRA_FRAME_WIDTH, getHeight()));
		setVisible(true);
	}
}
