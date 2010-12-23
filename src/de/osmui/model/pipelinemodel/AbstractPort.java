/**
 * 
 */
package de.osmui.model.pipelinemodel;

/**
 * This is the abstract base class for all port classes, ports are what pipes connect to. That is they are the inputs to a task
 * 
 * @author Niklas Schnelle
 *
 */
public abstract class AbstractPort {
	protected AbstractPipe incoming;
	protected AbstractTask parent;
	
	public AbstractPort(AbstractTask parent){
		this.incoming = null;
		this.parent = parent;
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
	 * Gets whether this port has an incoming pipe
	 * 
	 * @return true if connected, false otherwise
	 */
	public boolean isConnected(){
		return incoming == null;
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
		incoming = (!isConnected() && incoming.getTarget().equals(this))? incoming: null;
		return isConnected();
	}
	/**
	 * Disconnects this port from the incoming pipe if connected. Note this method takes care to disconnect
	 * on the other side as well
	 * 
	 */
	public void disconnect(){
		//Disconnect the incoming pipe
		if(incoming != null && incoming.isConnected()){
			//Little tricky but ensures disconnecting works on both sides
			AbstractPipe tempIncoming = incoming;
			incoming = null;
			tempIncoming.disconnect();
		} else {
			incoming = null;
		}
	}
	/**
	 * Gets the type of this port e.g. "entity" when this is an entity port that can connect to entity pipes
	 * 
	 * @return the type of this port
	 */
	public abstract String getType();

}
