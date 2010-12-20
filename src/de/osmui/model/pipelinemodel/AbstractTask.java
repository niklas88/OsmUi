/**
 * 
 */
package de.osmui.model.pipelinemodel;

import java.util.List;

import de.osmui.model.osm.TTask;


/**
 * This is the Task class, it's object represent a single task in a pipeline with it's associated parameters
 * and pipes. It also allows navigating the pipeline graph, normally beginning at the source tasks;
 * 
 * @author Niklas Schnelle
 *
 */
public abstract class AbstractTask {
	protected String name;
	protected TTask description;
	
	/**
	 * @return the description
	 */
	public TTask getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(TTask description) {
		this.description = description;
	}

	/**
	 * Gets the name of this task e.g. 'read-xml'
	 * @return the name of this task
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Sets the name of this task e.g. 'read-xml'
	 * @param the new name
	 */
	public void setName(String s){
		this.name=s;
	}
	
	/**
	 * Gets the list of Parameters associated with this task 
	 * @return the parameter list or null if no parameters exist
	 */
	public abstract List<AbstractParameter> getParameters();
	
	/**
	 * Gets the list of both connected and unconnected inputPipes
	 * @return list of inputPipes null if this is a source
	 */
	public abstract List<AbstractPipe> getInputPipes();
	
	/**
	 * Gets the list of both connected and unconnected outputPipes
	 * @return list of inputPipes null if this is a drain
	 */
	public abstract List<AbstractPipe> getOutputPipes();

}
