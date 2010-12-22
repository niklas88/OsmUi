/**
 * 
 */
package de.osmui.model.pipelinemodel;

import java.util.ArrayList;
import java.util.List;

import de.osmui.model.exceptions.TasksNotCompatibleException;

import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;
/**
 * This class implements a pipeline model using an mxGraph as a backing store
 * 
 * @author Niklas Schnelle
 *
 */
public class JGPipelineModel extends AbstractPipelineModel {
	protected ArrayList<JGTaskDecorator> tasks;
	protected mxGraph graph;
	
	public JGPipelineModel(){
		tasks=new ArrayList<JGTaskDecorator>();
		graph=new mxGraph();
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractModel#getSourceTasks()
	 */
	@Override
	public List<AbstractTask> getSourceTasks() {
		
		ArrayList<AbstractTask> sourceTasks=new ArrayList<AbstractTask>();
		//Remember we always add sourceTasks to the front so we can break after
		//finding the first non sourceTask
		for(JGTaskDecorator task: tasks){
			if(task.getInputPipes().isEmpty()){
				// We return the Task objects without their decorator here so that
				// subclass functionality might be accessed
				sourceTasks.add(task.undecorate());
			} else {
				break;
			}
		}
		
		return sourceTasks;
	}
	
	private void addTask(AbstractTask task){
		//Let's decorate the task very christmas like so they can be used by this JGPipelineModel
		JGTaskDecorator jgtask = new JGTaskDecorator(task);
		
		//Add to the front this speeds up getting the sourceTasks
		tasks.add(0, jgtask);
		//Add the task to the underling mxGraph model
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{
			jgtask.setCell((mxCell) graph.insertVertex(parent, null, jgtask, 20, 20, 80,
					30));
		}
		finally
		{
			graph.getModel().endUpdate();
		}
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractModel#addSourceTask(de.osmui.model.pipelinemodel.AbstractTask)
	 */
	@Override
	public void addSourceTask(AbstractTask task)
			throws TasksNotCompatibleException {
		
		
		if(!task.getInputPipes().isEmpty()){
			addTask(task);
			notifyObservers(task);
		} else {
			throw new TasksNotCompatibleException("Not a source task");
		}

	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractModel#addTask(de.osmui.model.pipelinemodel.AbstractTask, de.osmui.model.pipelinemodel.AbstractTask)
	 */
	@Override
	public void addTask(AbstractTask parent, AbstractTask child)
			throws TasksNotCompatibleException {
		boolean taskInList = false;
		
		//Find the parent task
		for(AbstractTask task : tasks){
			if(task.equals(parent)){
				taskInList = true;
				break;
			}
		}
		
		if(taskInList){
			//First add the child and then use our internal connect method to wire things up
			addTask(child);
			connectTasks(parent, child);
			
			notifyObservers(child);
		} else {
			throw new TasksNotCompatibleException("Parent task not in model");
		} 

	}


	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractModel#removeTask(de.osmui.model.pipelinemodel.AbstractTask)
	 */
	@Override
	public boolean removeTask(AbstractTask task) {
		//Find the task
		return tasks.remove(task);
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractModel#connectTasks(de.osmui.model.pipelinemodel.AbstractTask, de.osmui.model.pipelinemodel.AbstractTask)
	 */
	@Override
	public void connectTasks(AbstractTask parent, AbstractTask child)
			throws TasksNotCompatibleException {
		//TODO
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractModel#disconnectTasks(de.osmui.model.pipelinemodel.AbstractTask, de.osmui.model.pipelinemodel.AbstractTask)
	 */
	@Override
	public void disconnectTasks(AbstractTask parent, AbstractTask child) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractModel#isExecutable()
	 */
	@Override
	public boolean isExecutable() {
		// TODO Auto-generated method stub
		return false;
	}


}
