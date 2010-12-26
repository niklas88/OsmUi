/**
 * 
 */
package de.osmui.model.pipelinemodel;

/**
 * This class implements the common osmosis pipe, the class hierarchy would easily allow to create
 * extra classes for EntityPipes but this is easier
 * 
 * @author Niklas Schnelle
 *
 */
public class CommonPipe extends AbstractPipe {

	private static final long serialVersionUID = 2265995161991321428L;

	protected String type;
	
	public CommonPipe(AbstractTask owner, String type){
		this.source = owner;
		this.type = type;
	}

	@Override
	public String getType() {
		return type;
	}

}
