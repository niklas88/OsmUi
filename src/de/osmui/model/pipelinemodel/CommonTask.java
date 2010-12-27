/**
 * 
 */
package de.osmui.model.pipelinemodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a general implementation of an AbstractTask
 * 
 * @author Niklas Schnelle
 *
 */
public class CommonTask extends AbstractTask {


	private static final long serialVersionUID = -5302045303266284628L;
	
	protected HashMap<String, AbstractParameter> parameters;
	protected ArrayList<AbstractPort> inputPorts;
	protected ArrayList<AbstractPipe> outputPipes;

	public CommonTask() {
		parameters = new HashMap<String, AbstractParameter>();
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractTask#getParameters()
	 */
	@Override
	public Map<String, AbstractParameter> getParameters() {
		return parameters;
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractTask#getInputPorts()
	 */
	@Override
	public List<AbstractPort> getInputPorts() {		
		return inputPorts;
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractTask#getOutputPipes()
	 */
	@Override
	public List<AbstractPipe> getOutputPipes() {
		return outputPipes;
	}

}
