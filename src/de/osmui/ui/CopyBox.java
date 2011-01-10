package de.osmui.ui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

/**
 * @author Peter Vollmer
 * 
 */

public class CopyBox extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2196616949819474887L;
	
	private final CommandlineTranslator trans;
	
	private final AbstractPipelineModel model;
	
	public CopyBox(AbstractPipelineModel m) {
		this.setEditable(false);
		trans = CommandlineTranslator.getInstance();
		model = m;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof AbstractTask){
			setText(trans.exportLine(model));
		}
	}

}
