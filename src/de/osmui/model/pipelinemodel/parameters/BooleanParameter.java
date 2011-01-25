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

import de.osmui.model.osm.TParameter;

/**
 * This class is used to represent parameters with boolean values
 * 
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 *
 *@see BooleanParameterTest
 *
 */
public class BooleanParameter extends AbstractParameter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 454948888938504780L;
	private boolean value;
	
	public BooleanParameter(TParameter desc, String value){
		super(desc, value);
		setValue(value);
	}

	/* (non-Javadoc)
	 * @see de.osumi.model.pipelinemodel.AbstractParameter#getCommandlineForm()
	 */
	@Override
	public String getCommandlineForm() {
		return this.getName()+'='+this.getValue();
	}

	/* (non-Javadoc)
	 * @see de.osumi.model.pipelinemodel.AbstractParameter#getValue()
	 */
	@Override
	public String getValue() {
		String booleanEncoding = description.getBooleanEncoding();
		
		if(booleanEncoding.equals("truefalse")){
			return (value)?"true":"false";
		} else {			
			return (value)?"yes":"no";
		}
	}
	
	/* (non-Javadoc)
	 * @see de.osumi.model.pipelinemodel.AbstractParameter#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String s) throws IllegalArgumentException {
		//Default values can be 'true' even if encoding is 'yesno' so be lax
		if(s.equals("true")) {
			value=true;
		} else if (s.equals("false")){
			value=false;
		} else if(s.equals("yes")) {
			value=true;
		} else if (s.equals("no")){
			value=false;
		} else {
			throw new IllegalArgumentException(s);
		}
		
	}
	/**
	 * We have to override this here because we need to consider default value true=yes, false=no
	 * because this is used throughout osmosis-tasks-xml
	 */
	@Override
	public boolean isDefaultValue() {
		String defVal = description.getDefaultValue();
		// :-)
		return 	(value && (defVal.equals("yes") || defVal.equals("true")))
				|| (!value && (defVal.equals("no") || defVal.equals("false")));
	}
	
	/**
	 * Sets the value to the specified boolean
	 * @param value the value to set
	 */
	public void setValueBoolean(boolean value) {
		this.value = value;
	}

	/**
	 * Returns the value as native boolean
	 * @return the value
	 */
	public boolean getValueBoolean() {
		return value;
	}



}
