/**
 * 
 */
package de.osmui.model.pipelinemodel;

import java.io.Serializable;

/**
 * This is the abstract base class for all port classes, ports are what pipes connect to. That is they are the inputs to a task
 * 
 * @author Niklas Schnelle
 *
 *@see AbstractPortTest
 */
public abstract class AbstractPort implements Serializable{

	private static final long serialVersionUID = 4926805105607543325L;
	
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
	 * Gets whether this port is variable
	 * 
	 * @return false
	 */
	public boolean isVariable() {
		return false;
	}

	/**
	 * Gets the referenced parameter that specifies the number of ports if this is a variable port
	 * is in AbstractPort because of keeping it the same as AbstractPipe which needs
	 * it here because of the Decorator pattern TODO find better solution
	 * @return
	 */
	public IntParameter getReferencedParam(){
		return null;
	}
	/**
	 * If this pipe is variable returns a new Port, otherwise returns null
	 * 
	 * @return null
	 */
	public AbstractPort createPort() {
		return null;
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
