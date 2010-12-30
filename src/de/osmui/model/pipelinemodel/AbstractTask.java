/**
 * 
 */
package de.osmui.model.pipelinemodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;



/**
 * This is the Task class, it's object represent a single task in a pipeline with it's associated parameters
 * and pipes. It also allows navigating the pipeline graph, normally beginning at the source tasks;
 * 
 * @author Niklas Schnelle
 *
 */
public abstract class AbstractTask implements Serializable{

	private static final long serialVersionUID = 5857409627267578769L;

	protected String name;
	protected AbstractPipelineModel model;
	protected AbstractParameter defaultParameter;
	
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
	 * Sets the default parameter for this task
	 * @param p
	 */
	public void setDefaultParameter(AbstractParameter p){
		defaultParameter = p;
	}
	
	/**
	 * Gets the default parameter for this task
	 * @return the default parameter
	 */
	public AbstractParameter getDefaultParameter(){
		return defaultParameter;
	}
	
	/**
	 * Gets the string representation of this task as used by osmosis' command line, however
	 * the pipe connections are not included here use CommandlineTranslator
	 * @return 
	 */
	public abstract String getCommandlineForm();
	
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
	
	@Override
	public String toString(){
		return getName();
	}

}
