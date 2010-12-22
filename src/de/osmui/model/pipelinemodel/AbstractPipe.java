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
	protected AbstractTask target;
	
	/**
	 * Constructor all subclasses must define that takes the source of this pipe,
	 * all pipes must have a fixed source that is the task they belong to, the target is not fixed
	 * 
	 * @param source for this pipe
	 */
	protected AbstractPipe(AbstractTask source){
		this.name=null;
		this.target=null;
		this.source=source;
	}
	
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
	 * Gets the source of this pipe
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
	

}
