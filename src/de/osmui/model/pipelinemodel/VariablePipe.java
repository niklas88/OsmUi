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
 * This class implements a variable pipe, that is a pipe that when already connected clones itself
 * and sets up the connection with the clone, it also needs to adjust the task parameter indicating
 * it's variability number
 * 
 * @author Niklas Schnelle
 *
 *@see VariablePipeTest
 */
public class VariablePipe extends CommonPipe {

	private static final long serialVersionUID = 8630873926692554364L;

	protected IntParameter referencedParam;

	public VariablePipe(AbstractTask owner, IntParameter param, String type) {
		super(owner, type);
		referencedParam = param;
	}
	
	/**
	 * Gets the referenced parameter
	 * @return
	 */
	public IntParameter getReferencedParam(){
		return referencedParam;
	}
	
	/**
	 * This method creates a pipe of the same type as this variable pipe, if this
	 * pipe doesn't have any free pipes anymore
	 * 
	 */
	public AbstractPipe createPipe(){
		
		// Clone this pipe, and update the right parameter
		AbstractPipe newPipe = new VariablePipe(getSource(), referencedParam, getType());
		referencedParam.setValueInteger(referencedParam.getValueInteger()+1);
	
		return newPipe;
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
			getSource().getOutputPipes().remove(this);
			referencedParam.setValueInteger(referencedParam.getValueInteger()-1);
		}
	}
}
