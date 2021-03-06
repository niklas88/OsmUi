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
package de.osmui.model.pipelinemodel.parameters;


import java.util.List;

import de.osmui.i18n.I18N;
import de.osmui.model.osm.TEnumValue;
import de.osmui.model.osm.TParameter;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 *
 *@see EnumParameterTest
 */
public class EnumParameter extends AbstractParameter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3403811475288004725L;
	
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
	 * Returns a list of all possible valuevalues for this parameter
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
	
	@Override
	public String getDefaultValue(){
		String ret = super.getDefaultValue();
		if(ret==null){
			return enumeration.get(0).getValue();
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractParameter#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String s) throws IllegalArgumentException {
		// Check whether s is null and set to a sensible value
		if(s == null){
			value = enumeration.get(0).getValue();
			return;
		}
		// Test whether the requested value is in the Enumeration
		for(TEnumValue val : enumeration){
			if(val.getValue().equals(s)){
				value = s;
				return;
			}
		}
		throw new IllegalArgumentException(I18N.getString("EnumParameter.ValueNotInEnumeration",s));
	}

}
