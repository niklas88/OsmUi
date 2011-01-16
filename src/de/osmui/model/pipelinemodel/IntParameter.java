/**
 * 
 */
package de.osmui.model.pipelinemodel;

import de.osmui.model.osm.TParameter;

/**
 * Represents a paramater that is an Integer
 * 
 * @author Niklas Schnelle
 *
 * no tests, only getter and setter
 */
public class IntParameter extends AbstractParameter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4423983777968509408L;
	protected int value;

	/**
	 * 
	 */
	public IntParameter(TParameter desc, String value) {
		super(desc, value);
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractParameter#getCommandlineForm()
	 */
	@Override
	public String getCommandlineForm() {
		return this.getName()+"="+this.getValue();
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractParameter#getValue()
	 */
	@Override
	public String getValue() {
		return Integer.toString(value);
	}
	
	/**
	 * Gets the value as native integer
	 * 
	 * @return integer representation of the value
	 */
	public int getValueInteger(){
		return value;
	}

	/**
	 * Sets the value as native integer
	 * 
	 * @param value representation of the value
	 */
	public void setValueInteger(int value){
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractParameter#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String s) throws IllegalArgumentException {
		this.value = Integer.parseInt(s);

	}

}
