/*OsmUi is a user interface for Osmosis
    Copyright (C) 2011  Verena Käfer, Peter Vollmer, Niklas Schnelle

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or 
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 *
 *no tests, only getter and setter
 *
 */
public abstract class AbstractParameter implements Serializable{

	private static final long serialVersionUID = 7146601864666521078L;

	protected String name;
	protected transient TParameter description;
	
	
	public AbstractParameter(TParameter desc, String value){
		this.description = desc;
		this.name = desc.getName();
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
		String  defaultValue = description.getDefaultValue();
		return (defaultValue != null)? defaultValue.equals(this.getValue()) : false;
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
