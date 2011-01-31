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

/**
 * This class implements the ports see AbstractPort for details
 * 
 * @author Niklas Schnelle, Peter Vollmer, Verena Käfer
 *
 * no tests, only getter and setter
 * 
 */
public class CommonPort extends AbstractPort {

	private static final long serialVersionUID = 1559841158133665484L;

	protected String type;
	
	/**
	 * @param parent
	 * @param type
	 */
	public CommonPort(AbstractTask parent, String type) {
		super(parent);
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractPort#getType()
	 */
	@Override
	public String getType() {
		return type;
	}

}
