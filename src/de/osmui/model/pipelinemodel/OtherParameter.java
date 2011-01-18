/**
 * 
 */
package de.osmui.model.pipelinemodel;

import java.io.Serializable;

import de.osmui.model.osm.TParameter;

/**
 * This class is used to store all Parameters of types not covered by another specific class
 * 
 * @author Niklas Schnelle
 * 
 * no tests, only getter and setter
 */
public class OtherParameter extends AbstractParameter implements Serializable {
	
	private static final long serialVersionUID = -2583615666697475187L;
	
	protected String value;

	public OtherParameter(TParameter desc, String value){
		super(desc, value);
		setValue(value);
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
