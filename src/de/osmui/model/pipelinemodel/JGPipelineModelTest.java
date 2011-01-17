package de.osmui.model.pipelinemodel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.osmui.i18n.I18N;
import de.osmui.model.exceptions.TasksNotCompatibleException;
import de.osmui.model.exceptions.TasksNotInModelException;

/**
 * 
 * @author verena
 * 
 * @see JGPipelineModel
 * 
*/
public class JGPipelineModelTest {	
	@Test public void getSourceTasks(){
		JGPipelineModel model = new JGPipelineModel();
		ArrayList<AbstractTask> sourceTasks = new ArrayList<AbstractTask>();
		AbstractTask source1 = new CommonTask("name");
		sourceTasks.add(source1);
		model.addTask(source1);
		List<AbstractTask> test = model.getSourceTasks();
		assertEquals(sourceTasks.size(), test.size());
	}
	
	@Test public void addTask() throws TasksNotCompatibleException, TasksNotInModelException{
		CommonTask parent = new CommonTask("name");
		CommonTask child = new CommonTask("name");
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(parent);
		model.addTask(parent, child);
		JGPipelineModel test = new JGPipelineModel();
		test.addTask(parent);
		test.addTask(parent, child);
		assertEquals(model.tasks.size(), test.tasks.size());
	}

	@Test public void removeTask() throws TasksNotInModelException, TasksNotCompatibleException{
		AbstractTask task = new CommonTask("name");
		AbstractPort port = new CommonPort(task, "name");
		port.parent = task;
		AbstractTask task1 = new CommonTask("name");
		AbstractPipe pipe = new CommonPipe(task1, "name");
		pipe.source = task1;
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(task1);
		model.addTask(task);
		model.connectTasks(task, task1);
		assertEquals(true, model.removeTask(task));
	}
	
	/**
	 * @see JGPipelineModel#connectTasks(AbstractTask, AbstractTask)
	 * 
	 * @throws TasksNotCompatibleException
	 * @throws TasksNotInModelException
	 */
	@Test public void connectTasks() throws TasksNotCompatibleException, TasksNotInModelException{
		AbstractTask parent = new CommonTask("name");
		AbstractTask child = new CommonTask("name");
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(parent);
		model.addTask(parent, child);
		ArrayList<AbstractTask> list = new ArrayList<AbstractTask>();
		list.add(parent);
		list.add(child);
		assertEquals(3, model.tasks.size());
	}
	
	/**
	 * @see JGPipelineModel#connectTasks(AbstractPipe, AbstractPort)
	 * 
	 * @throws TasksNotCompatibleException
	 * @throws TasksNotInModelException
	 */
	@Test public void connectTasks1() throws TasksNotCompatibleException, TasksNotInModelException{
		AbstractTask task = new CommonTask("name");
		AbstractTask task1 = new CommonTask("name");
		AbstractPipe output = new CommonPipe(task, "type");
		AbstractPort input = new CommonPort(task1, "type");
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(task);
		model.addTask(task1);
		model.connectTasks(output, input);
		ArrayList<AbstractTask> list = model.tasks;
		ArrayList<AbstractTask> test = new ArrayList<AbstractTask>();
		AbstractTask e = new CommonTask("name");
		test.add(e );
		AbstractTask f = new CommonTask("name");
		test.add(f);
		assertEquals(test.size(), list.size());
	}
	
	@Test public void disconnectTasks() throws TasksNotCompatibleException, TasksNotInModelException{
		AbstractTask parent = new CommonTask("name");
		AbstractPipe pipe = new CommonPipe(parent, "name");
		pipe.source = parent;
		AbstractTask child = new CommonTask("name");
		AbstractPort port = new CommonPort(child, "name");
		port.parent = child;
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(parent);
		model.addTask(child);
		model.connectTasks(parent, child);
		model.disconnectTasks(parent, child);
		
		ArrayList<AbstractTask> list = model.tasks;
		ArrayList<AbstractTask> test = new ArrayList<AbstractTask>();
		AbstractTask e = new CommonTask("name");
		test.add(e );
		AbstractTask f = new CommonTask("name");
		test.add(f);
		assertEquals(test.size(), list.size());
	}
}
