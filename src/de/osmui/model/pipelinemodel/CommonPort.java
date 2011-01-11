/**
 * 
 */
package de.osmui.model.pipelinemodel;

/**
 * This class implements the ports see AbstractPort for details
 * 
 * @author Niklas Schnelle
 *
 *wird nicht getestet, da nur getter und setter
 */
public class CommonPort extends AbstractPort {

	private static final long serialVersionUID = 1559841158133665484L;

	protected String type;
	
	/**
	 * @param parent
	 * @param type
	 */
	public CommonPort(AbstractTask parent, String type) {
		super(parent);
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractPort#getType()
	 */
	@Override
	public String getType() {
		return type;
	}

}
