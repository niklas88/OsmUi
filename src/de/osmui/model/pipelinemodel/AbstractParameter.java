/**
 * 
 */
package de.osmui.model.pipelinemodel;

import de.osmui.model.osm.TParameter;

/**
 * This is the abstract parameter class all parameter classes inherit from and that should be used
 * when handling non specific parameters
 * 
 * @author Niklas Schnelle
 *
 */
public abstract class AbstractParameter {
	protected String name;
	protected TParameter description;
	
	/**
	 * Gets the parameter's name in it's standard for e.g. 'file'
	 * @return the paramete's name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Gets the TParameter Object describing this Parameter
	 * @return
	 */
	public TParameter getDescription(){
		return this.description;
	}
	
	/**
	 * Returns whether this parameter's value differs from the default, allows leaving it awat otherwise
	 * @return true if this parameter's value equals the default
	 */
	public boolean isDefaultValue() {
		return description.getDefaultValue().equals(this.getValue());
	}

	/**
	 * Gets the Parameter as a command line argument e.g. 'file=foo.osm'
	 * @return the String representation
	 */
	public abstract String getCommandlineForm();
	

	
	/**
	 * Gets the String representation of the parameter's value
	 * @return the String representing the parameter's value
	 */
	public abstract String getValue();
	
	/**
	 * Sets the value of this parameter from the specified String
	 * @param the String representing the new value
	 */
	public abstract void setValue(String s) throws IllegalArgumentException;
}
