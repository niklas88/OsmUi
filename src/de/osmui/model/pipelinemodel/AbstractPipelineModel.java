/**
 * 
 */
package de.osmui.model.pipelinemodel;

import java.util.List;
import java.util.Observable;

import de.osmui.model.exceptions.TasksNotCompatibleException;
import de.osmui.model.exceptions.TasksNotInModelException;


/**
 * This is the abstract base class for all pipeline models
 * 
 * @author Niklas Schnelle
 *
 */
public abstract class AbstractPipelineModel extends Observable{
	
	/**
	 * Gets all Source Tasks in the model, that is the Tasks without any inputPipes one
	 * normally traverses the model from the SourceTasks to the drains using the Task objects
	 * 
	 * @return the list of SourceTasks
	 */
	public abstract List<AbstractTask> getSourceTasks();
	
	/**
	 * Adds the given task as SourceTask to the model, if the task requires inputPipes this method
	 * will throw a TasksNotCompatibleException
	 * 
	 * @param task
	 * @throws TasksNotCompatibleException
	 */
	public abstract void addSourceTask(AbstractTask task) throws TasksNotCompatibleException;
	
	/**
	 * Adds the the child Task to the model using the parent task as parent, that is connecting a compatible output of the parent
	 * to a compatible input of the child. If there are no compatible pipes a TasksNotCompatibleException will be thrown.
	 * If the parent is not yet in the model TasksNotInModelException will be thrown
	 * 
	 * @param parent
	 * @param child
	 * @throws TasksNotCompatibleException
	 * @throws TasksNotInModelException 
	 */
	public abstract void addTask(AbstractTask parent, AbstractTask child) throws TasksNotCompatibleException, TasksNotInModelException;
	
	/**
	 * Removes the given task from the model, if successfull returns true false otherwise
	 * 
	 * @param task
	 * @return boolean indicating success
	 */
	public abstract boolean removeTask(AbstractTask task);
	
	/**
	 * Connects the given tasks using the first parameter as parent task of the second parameter. If there is no 
	 * compatible pipe left a TasksNotCompatibleException will be thrown.
	 * The first unconnected outputPipe of the parent that has an unconnected and compatible port on the child
	 * will be connected with this port. The pipe with which the connection was made is returned
	 * 
	 * @param parent
	 * @param child
	 * @throws TasksNotCompatibleException
	 */
	public AbstractPipe connectTasks(AbstractTask parent, AbstractTask child) throws TasksNotCompatibleException, TasksNotInModelException {
		if(parent != null && child != null){
			//Test outputPipes of the parent for compatibility with ports on the child
			List<AbstractPipe> pipes = parent.getOutputPipes();
			List<AbstractPort> ports = child.getInputPorts();
			for(AbstractPipe out : pipes){
				for(AbstractPort in : ports){
					//Just try to connect and stop if successful
					if(out.connect(in)){
						return out;
					}
				}
			}
			//We haven't returned in the loop so no compatible pipe/port was found throw
			throw new TasksNotCompatibleException("The given tasks weren't compatible");
		} else {
			throw new TasksNotCompatibleException("parent or child is null");
		}
	}
	
	/**
	 * Disconnects the two tasks if they were connected, does nothing otherwise
	 * 
	 * @param parent
	 * @param child
	 * @return the pipe that was unconnected, null if nothing was disconnected
	 */
	public AbstractPipe disconnectTasks(AbstractTask parent, AbstractTask child){
		//First we need to find the connection between the two and then remove it
		List<AbstractPipe> pipes = parent.getOutputPipes();
		
		for(AbstractPipe out : pipes){
			if(out.getTarget().getParent().equals(child)){
				out.disconnect();
				return out;
			}
		}
		return null;
	}
	
	/**
	 * Gets whether this model currently represents a executable pipeline that is a pipeline that can be executed by osmosis
	 * 
	 * @return true if executable false otherwise
	 */
	public abstract boolean isExecutable();
	
}
