package de.osmui.ui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

import de.osmui.model.pipelinemodel.AbstractPipelineModel;
import de.osmui.model.pipelinemodel.AbstractTask;
import de.osmui.util.CommandlineTranslator;


public class CopyBox extends JScrollPane implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2196616949819474887L;
	
	private final CommandlineTranslator trans;
	
	private final AbstractPipelineModel model;
	
	private JTextField copyBoxTextField; 
	
	public CopyBox(AbstractPipelineModel m) {
		copyBoxTextField = new JTextField();
		copyBoxTextField.setEditable(false);
		trans = CommandlineTranslator.getInstance();
		model = m;
		this.setViewportView(copyBoxTextField);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof AbstractTask){
			copyBoxTextField.setText(trans.exportLine(model));
		}
	}

}
