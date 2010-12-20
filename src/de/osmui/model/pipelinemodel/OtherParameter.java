/**
 * 
 */
package de.osmui.model.pipelinemodel;

/**
 * This class is used to store all Parameters of types not covered by another specific class
 * 
 * @author Niklas Schnelle
 *
 */
public class OtherParameter extends AbstractParameter {
	protected String value;

	/* (non-Javadoc)
	 * @see de.osumi.model.pipelinemodel.AbstractParameter#getCommandlineForm()
	 */
	@Override
	public String getCommandlineForm() {
		return this.getName()+"="+this.getValue();
	}

	/* (non-Javadoc)
	 * @see de.osumi.model.pipelinemodel.AbstractParameter#getValue()
	 */
	@Override
	public String getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see de.osumi.model.pipelinemodel.AbstractParameter#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String s) throws IllegalArgumentException {
		this.value=s;
	}

}
