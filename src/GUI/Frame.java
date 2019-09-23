package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public abstract class Frame extends JFrame {
	private int maxFrameWidth;
	private int extraFrameWidth;
	private int buttonsGap;
	private Frame parentFrame;
	
	protected Frame(String title) {
		super(title);
		
		maxFrameWidth = 9999;
		extraFrameWidth = 0;
		buttonsGap = 15;
	}
	
	public Frame(String title, boolean isRootWindow) {
		this(title);
	    
	    if (isRootWindow) {
	    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    	Frame thisFrame = this;
		    
		    addWindowListener(new java.awt.event.WindowAdapter() {
		        @Override
		        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		            if (JOptionPane.showConfirmDialog(thisFrame,
		            		"Sei sicuro di voler terminare l'applicazione?",
		            		"Attenzione",
		            		JOptionPane.YES_NO_OPTION,
		            		JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
		                System.exit(0);
		            }
		        }
		    });
	    } else
	    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	// has parent frame dependency without message on close; is not a root window
	public Frame(String title, Frame parentFrame) {
		this(title);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.parentFrame = parentFrame;
		parentFrame.setVisible(false);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
	        @Override
	        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				closeFrame();
	        }
	    });
	}
	
	// has parent frame dependency with message on close; is not a root window
	public Frame(String title, Frame parentFrame, String dialogMessage) {
		this(title);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.parentFrame = parentFrame;
		parentFrame.setVisible(false);
    	
		Frame thisFrame = this;
		
		addWindowListener(new java.awt.event.WindowAdapter() {
	        @Override
	        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	        	if (JOptionPane.showConfirmDialog(thisFrame,
	        			dialogMessage,
		        		"Attenzione",
		        		JOptionPane.YES_NO_OPTION,
		        		JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
	        		closeFrame();
		        }
	        }
	    });
	}

	public int getMaxFrameWidth() {
		return maxFrameWidth;
	}

	public void setMaxFrameWidth(int maxFrameWidth) {
		this.maxFrameWidth = maxFrameWidth;
	}

	public int getExtraFrameWidth() {
		return extraFrameWidth;
	}

	public void setExtraFrameWidth(int extraFrameWidth) {
		this.extraFrameWidth = extraFrameWidth;
	}

	public int getButtonsGap() {
		return buttonsGap;
	}

	public void setButtonsGap(int buttonsGap) {
		this.buttonsGap = buttonsGap;
	}

	public Frame getParentFrame() {
		return parentFrame;
	}

	public void setParentFrame(Frame parentFrame) {
		this.parentFrame = parentFrame;
	}

	protected void closeFrame() {
		if (parentFrame != null)
			parentFrame.setVisible(true);
		
		this.dispose();
	}

	protected void closeFrameWithoutVisualizeParent() {
		this.dispose();
	}

	protected void showFrame() {
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setSize(new Dimension(getWidth() + extraFrameWidth, getHeight()));
		setLocationRelativeTo(null);
		setVisible(true);
	}

	protected void refreshFrameDims() {
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setSize(new Dimension(getWidth() + extraFrameWidth, getHeight()));
	}

	protected void fillComboBox(JComboBox<Object> comboBox, Object[] array) {
		DefaultComboBoxModel<Object> dcbm = new DefaultComboBoxModel<Object>();
		
		dcbm.addElement("<html><span style='font-weight:normal'><i>Seleziona un elemento...</i></span></html>");
		
		for (Object object: array)
			dcbm.addElement(object);
		
		comboBox.setModel(dcbm);
	}

	protected void fillComboBox(JComboBox<Object> comboBox, ArrayList<Object> arrayList) {
		DefaultComboBoxModel<Object> dcbm = new DefaultComboBoxModel<Object>();
		
		dcbm.addElement("<html><span style='font-weight:normal'><i>Seleziona un elemento...</i></span></html>");
		
		for (Object object: arrayList)
			dcbm.addElement(object);
		
		comboBox.setModel(dcbm);
	}
	
	@SuppressWarnings("unchecked")
	public static <newType, oldType> ArrayList<newType> castArrayList(ArrayList<oldType> list) {
	    ArrayList<newType> newlyCastedArrayList = new ArrayList<newType>();
	    
	    for (oldType listObject : list)
	        newlyCastedArrayList.add((newType) listObject);
	    
	    return newlyCastedArrayList;
	}
	
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 15;
	        
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width + 1, width);
	        }
	        
	        if (width > 300)
	            width = 300;
	        
	        columnModel.getColumn(column).setPreferredWidth(width + width);
	    }
	    
	    setMinimumSize(new Dimension(getWidth() + 50, getHeight()));
		setSize(new Dimension(getWidth() + 50, getHeight()));
	}

	protected abstract void addingEventHandlers();

	protected abstract void elementsPositioning();
}
