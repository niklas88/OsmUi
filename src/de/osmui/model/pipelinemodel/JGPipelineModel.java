/**
 * 
 */
package de.osmui.model.pipelinemodel;

import java.util.ArrayList;
import java.util.List;

import de.osmui.model.exceptions.TasksNotCompatibleException;
import de.osmui.model.exceptions.TasksNotInModelException;

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
			if(task.getInputPorts().isEmpty()){
				// We return the Task objects without their decorator here so that
				// subclass functionality might be accessed
				sourceTasks.add(task);
			} else {
				break;
			}
		}
		
		return sourceTasks;
	}
	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractModel#addTask(de.osmui.model.pipelinemodel.AbstractTask)
	 */
	@Override
	public void addTask(AbstractTask task){
		//Let's decorate the task very christmas like so they can be used by this JGPipelineModel
		JGTaskDecorator jgtask = new JGTaskDecorator(task);
		
		//Add to list of tasks, if it's a sourceTasks add to the beginning, this speeds up getting sourceTasks
		if(jgtask.getInputPorts().isEmpty()){
			tasks.add(0, jgtask);
		} else {
			tasks.add(jgtask);
		}
		jgtask.setModel(this);
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
		notifyObservers(jgtask);
	}



	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractModel#addTask(de.osmui.model.pipelinemodel.AbstractTask, de.osmui.model.pipelinemodel.AbstractTask)
	 */
	@Override
	public void addTask(AbstractTask parent, AbstractTask child)
			throws TasksNotCompatibleException, TasksNotInModelException {
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
			throw new TasksNotInModelException("parent not in model");
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
	public AbstractPipe connectTasks(AbstractTask parent, AbstractTask child)
			throws TasksNotCompatibleException, TasksNotInModelException {

		// Make the normal connection, the cast here is legal because otherwise TasksNotInModel would be thrown
		JGPipeDecorator jgpipe = (JGPipeDecorator) super.connectTasks(parent, child);	
		//The tasks are in the model therefore we can cast them
		JGTaskDecorator jgparent = (JGTaskDecorator) parent;
		JGTaskDecorator jgchild  = (JGTaskDecorator) child;
		
		// Setup the jgraphx madness
		Object graphparent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try{			
			jgpipe.setCell((mxCell) graph.insertEdge(graphparent, null, jgpipe, jgparent.getCell(), jgchild.getCell()));
		}
		finally{
			graph.getModel().endUpdate();
		}
		return jgpipe;
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractModel#disconnectTasks(de.osmui.model.pipelinemodel.AbstractTask, de.osmui.model.pipelinemodel.AbstractTask)
	 */
	@Override
	public AbstractPipe disconnectTasks(AbstractTask parent, AbstractTask child) throws TasksNotInModelException {
		return super.disconnectTasks(parent, child);
		//TODO
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
