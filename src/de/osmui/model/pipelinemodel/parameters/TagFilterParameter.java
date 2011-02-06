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
package de.osmui.model.pipelinemodel.parameters;

import de.osmui.model.osm.TParameter;

/**
 * This is pseudo parameter used to implement the second part of the tag-filter parameters
 * @author Niklas Schnelle
 *
 */
public class TagFilterParameter extends AbstractParameter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 338094253801214605L;
	
	private String value;

	public TagFilterParameter(TParameter desc, String value) {
		super(desc, value);
	}

	/* (non-Javadoc)
	 * @see de.osumi.model.pipelinemodel.AbstractParameter#getCommandlineForm()
	 */
	@Override
	public String getCommandlineForm() {
		return "";
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String s) throws IllegalArgumentException {
		value=s;		
	}

}
