/**
 * 
 */
package de.osmui.model.pipelinemodel;

import de.osmui.model.osm.TParameter;

/**
 * This class is used to store all Parameters of types not covered by another specific class
 * 
 * @author Niklas Schnelle
 *
 */
public class OtherParameter extends AbstractParameter {
	protected String value;

	public OtherParameter(TParameter desc, String value){
		super(desc, value);
	}
	
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
