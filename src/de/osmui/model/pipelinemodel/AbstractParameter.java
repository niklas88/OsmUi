/**
 * 
 */
package de.osmui.model.pipelinemodel;

import java.io.Serializable;

import de.osmui.model.osm.TParameter;

/**
 * This is the abstract parameter class all parameter classes inherit from and that should be used
 * when handling non specific parameters
 * 
 * @author Niklas Schnelle
 *
 */
public abstract class AbstractParameter implements Serializable{

	private static final long serialVersionUID = 7146601864666521078L;

	protected String name;
	protected transient TParameter description;
	
	
	public AbstractParameter(TParameter desc, String value){
		this.description = desc;
		this.name = desc.getName();
		setValue(value);
	}
	
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
	 * Gets whether this parameter is the default parameter of it's parent task
	 * 
	 * @return
	 */
	public boolean isDefaultParam(){
		return description.isDefaultParameter();
	}
	
	/**
	 * Returns whether this parameter's value differs from the default, allows leaving it away otherwise
	 * @return true if this parameter's value equals the default
	 */
	public boolean isDefaultValue() {
		return description.getDefaultValue().equals(this.getValue());
	}
	
	/**
	 * Returns the default value for this Parameter
	 * @return
	 */
	public String getDefaultValue(){
		return description.getDefaultValue();
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
