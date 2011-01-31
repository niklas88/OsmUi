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
 * This is the abstract base class for all port classes, ports are what pipes connect to. That is they are the inputs to a task
 * 
 * @author Niklas Schnelle, Peter Vollmer, Verena Käfer
 *
 * @see AbstractPortTest
 * 
 */
public abstract class AbstractPort implements Serializable, Identifiable {

	private static final long serialVersionUID = 4926805105607543325L;
	
	protected static long idSeed = 0;
	
	protected long myId = 0;
	
	protected AbstractPipe incoming;
	protected AbstractTask parent;
	
	public AbstractPort(AbstractTask parent){
		this.incoming = null;
		this.parent = parent;
	}
	
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
	 * Gets the task object this port belongs to
	 * 
	 * @return task object
	 */
	public AbstractTask getParent(){
		return parent;
	}
	
	/**
	 * Gets the incoming pipe or null if not connected
	 * 
	 * @return
	 */
	public AbstractPipe getIncoming(){
		return incoming;
	}
	
	/**
	 * Gets whether this port has an incoming pipe
	 * 
	 * @return true if connected, false otherwise
	 */
	public boolean isConnected(){
		return incoming != null;
	}

	
	/**
	 * If the given pipe has this port as target set connected otherwise leave unconnected.
	 *
	 * 
	 * @param incoming
	 * @return true if the connect was successful, false otherwise
	 */
	public boolean connect(AbstractPipe incoming){
		//If not already connected and target of incoming correctly set then set incomming
		this.incoming = (!isConnected() && incoming.getTarget().equals(this))? incoming: null;
		return isConnected();
	}
	/**
	 * Disconnects this port from the incoming pipe if connected. Note this method takes care to disconnect
	 * on the other side as well
	 * 
	 */
	public void disconnect(){
		//Disconnect the incoming pipe
		if(incoming != null){
			//Little tricky but ensures disconnecting works on both sides
			AbstractPipe tempIncoming = incoming;
			incoming = null;
			tempIncoming.disconnect();
		}
	}
	/**
	 * Gets the type of this port e.g. "entity" when this is an entity port that can connect to entity pipes
	 * 
	 * @return the type of this port
	 */
	public abstract String getType();

}
