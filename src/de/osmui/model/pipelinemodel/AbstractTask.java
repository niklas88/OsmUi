/**
 * 
 */
package de.osmui.model.pipelinemodel;

import java.util.List;
import java.util.Map;



/**
 * This is the Task class, it's object represent a single task in a pipeline with it's associated parameters
 * and pipes. It also allows navigating the pipeline graph, normally beginning at the source tasks;
 * 
 * @author Niklas Schnelle
 *
 */
public abstract class AbstractTask {
	protected String name;
	protected AbstractPipelineModel model;
	
	/**
	 * Gets the model to which this task belongs, null if the task has not yet been added
	 * to a model or has been removed from a model.
	 * 
	 * @return model this task belongs to
	 */
	public AbstractPipelineModel getModel(){
		return model;
	}
	
	/**
	 * Sets the model to which this task belongs
	 * 
	 * @param model
	 */
	public void setModel(AbstractPipelineModel model){
		this.model = model;
	}
	

	/**
	 * Gets the name of this task e.g. 'read-xml'
	 * @return the name of this task
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * @param the new name
	 */
	public void setName(String s){
		this.name=s;
	}
	
	/**
	 * Gets the list of Parameters associated with this task 
	 * @return the parameter list or null if no parameters exist
	 */
	public abstract Map<String, AbstractParameter> getParameters();
	
	/**
	 * Gets the list of both connected and unconnected inputPorts
	 * @return list of inputPorts null if this is a source
	 */
	public abstract List<AbstractPort> getInputPorts();
	
	/**
	 * Gets the list of both connected and unconnected outputPipes
	 * @return list of inputPipes null if this is a drain
	 */
	public abstract List<AbstractPipe> getOutputPipes();

}
