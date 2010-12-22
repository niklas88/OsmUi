/**
 * 
 */
package de.osmui.model.pipelinemodel;

/**
 * This class represents a change pipe, that is a connection between to tasks carrying an change stream
 * 
 * @author Niklas Schnelle
 *
 */
public class ChangePipe extends AbstractPipe {

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractPipe#getType()
	 */
	@Override
	public String getType() {
		return "change";
	}

}
