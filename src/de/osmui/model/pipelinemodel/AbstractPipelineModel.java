/**
 * 
 */
package de.osmui.model.pipelinemodel;

import java.util.List;
import java.util.Observable;

import de.osmui.model.exceptions.TasksNotCompatibleException;


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
	 * to a compatible input of the child. If there are no compatible pipes a TasksNotCompatibleException will be thrown
	 * 
	 * @param parent
	 * @param child
	 * @throws TasksNotCompatibleException
	 */
	public abstract void addTask(AbstractTask parent, AbstractTask child) throws TasksNotCompatibleException;
	
	/**
	 * Removes the given task from the model, if successfull returns true false otherwise
	 * 
	 * @param task
	 * @return boolean indicating success
	 */
	public abstract boolean removeTask(AbstractTask task);
	
	/**
	 * Connects the given tasks using the first parameter as parent task of the second parameter. If there is no 
	 * compatible pipe left a TasksNotCompatibleException will be thrown
	 * 
	 * @param parent
	 * @param child
	 * @throws TasksNotCompatibleException
	 */
	public abstract void connectTasks(AbstractTask parent, AbstractTask child) throws TasksNotCompatibleException;
	
	/**
	 * Disconnects the two tasks if they were connected, does nothing otherwise
	 * 
	 * @param parent
	 * @param child
	 */
	public abstract void disconnectTasks(AbstractTask parent, AbstractTask child);
	
	/**
	 * Gets whether this model currently represents a executable pipeline that is a pipeline that can be executed by osmosis
	 * 
	 * @return true if executable false otherwise
	 */
	public abstract boolean isExecutable();
	
}
