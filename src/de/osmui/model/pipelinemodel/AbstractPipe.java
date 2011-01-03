/**
 * 
 */
package de.osmui.model.pipelinemodel;

import java.io.Serializable;

/**
 * This class represents a Pipe within the pipelinemodel, that is an edge in the graph
 * 
 * @author Niklas Schnelle
 *
 */
public abstract class AbstractPipe implements Serializable{

	private static final long serialVersionUID = 7962518716366164284L;

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
	 * Gets whether this pipe is variable, so that new pipes can be created from it,
	 * 
	 * @return
	 */
	public boolean isVariable(){
		// Had to put it here because foo instanceof VariablePipe wouldn't work with the Decorator pattern
		return false;
	}
	/**
	 * Gets the referenced parameter if this is a VariablePipe else returns null,
	 * this has to be in the AbstractPipe because the decorator wouldn't allow access otherwise
	 * TODO find better solution
	 * @return
	 */
	public IntParameter getReferencedParam(){
		return null;
	}
	/**
	 * This method is used to create new Pipes, if this pipe is variable, if it's not it just returns null
	 *  this has to be in the AbstractPipe because the decorator wouldn't allow access otherwise
	 * @return
	 */
	public AbstractPipe createPipe() {
		return null;
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
	
	/**
	 * Disconnects this pipeline from it's target if it was connected, does nothing otherwise
	 */
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
