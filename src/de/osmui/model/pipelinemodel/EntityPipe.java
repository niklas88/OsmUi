/**
 * 
 */
package de.osmui.model.pipelinemodel;

/**
 * This class represents a entity pipe, that is a connection between to tasks carrying an entity stream
 * 
 * @author Niklas Schnelle
 *
 */
public class EntityPipe extends AbstractPipe {

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractPipe#getType()
	 */
	@Override
	public String getType() {
		return "entity";
	}

}
