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
	protected AbstractTask target = null;
	
	/**
	 * Returns whether this pipe has a user defined name or a name should be auto generated on export
	 * 
	 * @return true if named false else
	 */
	public boolean isNamed(){
		return name==null;
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
	public AbstractTask getTarget(){
		return target;
	}
	
	/**
	 * Sets the target of this pipe
	 * 
	 * @param target
	 */
	public void setTarget(AbstractTask target){
		this.target = target;
	}
	
	public abstract String getType();

}
