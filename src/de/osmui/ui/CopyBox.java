package de.osmui.ui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

import de.osmui.model.pipelinemodel.AbstractPipelineModel;
import de.osmui.model.pipelinemodel.AbstractTask;
import de.osmui.util.CommandlineTranslator;


public class CopyBox extends JTextField implements Observer{

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
