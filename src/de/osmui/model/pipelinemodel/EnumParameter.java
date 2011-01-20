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


import java.util.List;

import de.osmui.model.osm.TEnumValue;
import de.osmui.model.osm.TParameter;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 *
 */
public class EnumParameter extends AbstractParameter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4056315894183855778L;
	
	// The currently set value of this parameter
	protected String value;
	protected List<TEnumValue> enumeration;

	/**
	 * @param desc
	 * @param value
	 */
	public EnumParameter(TParameter desc, String value) {
		super(desc, value);
		enumeration = desc.getEnumValue();
		setValue(value);
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractParameter#getCommandlineForm()
	 */
	@Override
	public String getCommandlineForm() {
		return this.getName()+"="+this.getValue();
	}

	/**
	 * Returns a list of all possible values for this parameter
	 * @param tryFriendly
	 * @return
	 */
	public List<TEnumValue> getEnumerationValues(){
		return enumeration;
	}
	
	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractParameter#getValue()
	 */
	@Override
	public String getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractParameter#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String s) throws IllegalArgumentException {
		// Test whether the requested value is in the Enumeration
		for(TEnumValue val : enumeration){
			if(val.getValue().equals(s)){
				value = s;
				return;
			}
		}		
	}

}
