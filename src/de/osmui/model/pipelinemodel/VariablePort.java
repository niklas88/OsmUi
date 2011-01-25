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

import de.osmui.model.pipelinemodel.parameters.IntParameter;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 *
 *@see VariablePortTest
 *
 */
public class VariablePort extends CommonPort {
	

	private static final long serialVersionUID = 1526665480238004437L;

	protected IntParameter referencedParam;

	public VariablePort(AbstractTask owner, IntParameter param, String type) {
		super(owner, type);
		referencedParam = param;
		referencedParam.reference();
	}
	
	/**
	 * Gets the referenced parameter that specifies the number of ports
	 * @return
	 */
	public IntParameter getReferencedParam(){
		return referencedParam;
	}
	
	
	/**
	 * This method creates a pipe of the same type as this variable port
	 */
	public AbstractPort createPort(){
		
		// Clone this pipe, and update the right parameter
		AbstractPort newPort = new VariablePort(getParent(), referencedParam, getType());
		referencedParam.setValueInteger(referencedParam.getValueInteger()+1);
	
		return newPort;
	}
	
	/**
	 * We override disconnect so that it removes this variable instance as long as
	 * there are more then the default value variable instances
	 */
	@Override
	public void disconnect(){
		super.disconnect();
		// As long as the referencedParam is not down to it's default we remove this pipe
		// as it's not longer needed
		if(!referencedParam.isDefaultValue()){
			getParent().getOutputPipes().remove(this);
			
			referencedParam.setValueInteger(referencedParam.getValueInteger()-1);
		}
	}
}
