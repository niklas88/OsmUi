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
		if(value == null){
			// This happens when there is no default value
			setValue("0");
		}
		setValue(value);
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
