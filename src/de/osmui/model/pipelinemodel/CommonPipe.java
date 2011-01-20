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
 * This class implements the common osmosis pipe, the class hierarchy would easily allow to create
 * extra classes for EntityPipes but this is easier
 * 
 * @author Niklas Schnelle
 * 
 * no tests, only getter and setter
 */
public class CommonPipe extends AbstractPipe {

	private static final long serialVersionUID = 2265995161991321428L;

	protected String type;
	
	public CommonPipe(AbstractTask owner, String type){
		this.source = owner;
		this.type = type;
	}

	@Override
	public String getType() {
		return type;
	}

}
