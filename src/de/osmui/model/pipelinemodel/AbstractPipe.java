/**
 * 
 */
package de.osmui.model.pipelinemodel;

/**
 * This class represents a Pipe within the pipelinemodel, that is an edge in the graph
 * 
 * @author Niklas Schnelle
 *
 */
public abstract class AbstractPipe {
	protected String name;
	protected AbstractTask source;
	//The target must be null if not connected
	protected AbstractPort target = null;
	
	/**
	 * Returns whether this pipe has a user defined name or a name should be auto generated on export
	 * 
	 * @return true if named false else
	 */
	public boolean isNamed(){
		return name==null;
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
	 * Gets whether this pipe is ready to be connected, this is
	 * usually the not of isConnected but differs for variable pipes, remember this
	 * when using this method
	 * 
	 * @return whether this pipe is ready to be connected
	 */
	public boolean isConnectable(){
		return !isConnected();
	}
	
	/**
	 * Sets the name of this pipe this name
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
	public boolean connect(AbstractPort target){
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
	
	public void disconnect(){
		if(target != null && target.isConnected()){
			// A little tricky as AbstractPorts disconnect also tries this disconnect method
			// this ensures disconnect on either end works
			AbstractPort tempTarget = target;
			target = null;
			tempTarget.disconnect();
		} else {
			target = null;
		}
	}
	
	public abstract String getType();

}
