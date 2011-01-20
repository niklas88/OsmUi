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

/**
 * This class represents a Pipe within the pipelinemodel, that is an edge in the graph
 * 
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 *
 *@see AbstractPipeTest
 *
 */
public abstract class AbstractPipe implements Serializable, Identifiable {

	private static final long serialVersionUID = 7962518716366164284L;
	
	protected static long idSeed = 0;
	
	protected String name;
	protected AbstractTask source;
	//The target must be null if not connected
	protected AbstractPort target = null;
	protected long myId = 0;
	
	/**
	 * Implement getID from Identifiable
	 */
	public long getID(){
		if(myId == 0){
			return myId = ++idSeed;
		} else {
			return myId;
		}
	}
	
	/**
	 * Returns whether this pipe has a user defined name or a name should be auto generated on export
	 * 
	 * @return true if named false else
	 */
	public boolean isNamed(){
		return name != null;
	}
	
	/**
	 * Gets whether this pipe is connected to a port
	 * 
	 * @return true if connected, false otherwise
	 */
	public boolean isConnected(){
		return target != null;
	}
	
	/**
	 * Sets the name of this pipe
	 * 
	 * @param name to set for this pipe
	 */
	public void setName(String name){
		this.name=name;
	}
	
	/**
	 * Gets the name associated with this Pipe, null if unnamed
	 * 
	 * @return name, null if unnamed
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Gets the source of this pipe, there is no setter, the source should be set in the constructor
	 * 
	 * @return source
	 */
	public AbstractTask getSource(){
		return source;
	}
	
	/**
	 * Gets the target of this pipe
	 * 
	 * @return target may be null
	 */
	public AbstractPort getTarget(){
		return target;
	}
	

	
	/**
	 * If possible (i.e. types match) connects this pipe with the given target port, returns true if successful, false otherwise.
	 * 
	 * @param target
	 * @return true if connect was successful, false otherwise
	 */
	boolean connect(AbstractPort target){
		if(target != null && this.getType().equals(target.getType())){
			this.target = target;
			if(target.connect(this)){
				return true;
			} else {
				this.target = null;
				return false;
			}
		} else {
			return false;
		}
		
	}
	
	/**
	 * Disconnects this pipeline from it's target if it was connected, does nothing otherwise
	 */
	void disconnect(){
		if(target != null){
			// A little tricky as AbstractPorts disconnect also tries this disconnect method
			// this ensures disconnect on either end works
			AbstractPort tempTarget = target;
			target = null;
			tempTarget.disconnect();
		} 
	}
	
	/**
	 * Gets the type of this pipe e.g. "entity"
	 * @return
	 */
	public abstract String getType();
	
	
	@Override
	public String toString(){
		return (isNamed())?this.getName()+": " +this.getType(): this.getType();
	}



}
