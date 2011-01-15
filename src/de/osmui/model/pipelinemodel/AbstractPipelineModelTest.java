package de.osmui.model.pipelinemodel;

import static org.junit.Assert.*;

import org.junit.Test;

import de.osmui.model.exceptions.TasksNotCompatibleException;
import de.osmui.model.exceptions.TasksNotInModelException;

/**
 * 
 * @author verena
 * 
 * @see AbstractPiplineModel
 * 
*/
public class AbstractPipelineModelTest {
	/**
	 * @see AbstractPipelineModel#connectTasks(AbstractTask, AbstractTask)
	 * 
	 * @throws TasksNotCompatibleException
	 * @throws TasksNotInModelException
	 */
	@Test public void connectTaska() throws TasksNotCompatibleException, TasksNotInModelException{
		AbstractTask parent = new CommonTask("tee");
		AbstractTask child = new CommonTask("tee");
		AbstractPipelineModel model = new JGPipelineModel();
		model.addTask(parent);
		model.addTask(child);
		model.connectTasks(parent, child);
		
	}
	
	/**
	 * @see AbstractPipelineModel#connectTasks(AbstractTask, AbstractTask)
	 * 
	 * @throws TasksNotCompatibleException
	 * @throws TasksNotInModelException
	 */
	@Test public void connectTaska2() throws TasksNotCompatibleException, TasksNotInModelException{
		AbstractTask parent = new CommonTask("name");
		AbstractPipe pipe = new CommonPipe (parent, "name");
		AbstractTask child = new CommonTask("name");
		AbstractPort port = new CommonPort(child, "name");
		port.parent = child;
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(parent);
		model.addTask(child);
		pipe.source = parent;
		pipe = model.connectTasks(parent, child);
		assertEquals(parent.name, pipe.source.name);
	}
	
	/**
	 * @see AbstractPipelineModel#connectTasks(AbstractPipe, AbstractPort)
	 * 
	 * @throws TasksNotCompatibleException
	 * @throws TasksNotInModelException
	 */
	@Test public void connectb() throws TasksNotCompatibleException, TasksNotInModelException{
		AbstractTask task = new CommonTask("name");
		AbstractTask child = new CommonTask("name");
		AbstractPipe output = new CommonPipe(task, "type");
		/*pi.source = child;
		AbstractPort input = new CommonPort(parent, "type");
		input.parent = parent;
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(dec);
		model.addTask(deco);
		model.connectTasks(pi, input);*/
		
	}
	
	/**
	 * @see AbstractPipelineModel#connectTasks(AbstractPipe, AbstractPort)
	 * 
	 * @throws TasksNotCompatibleException
	 * @throws TasksNotInModelException
	 */
	@Test public void disconnectTaskb1() throws TasksNotInModelException, TasksNotCompatibleException{
		
		CommonTask parent = new CommonTask("name");
		AbstractPipe pipe = new CommonPipe (parent, "name");
		pipe.source = parent;
		CommonTask child = new CommonTask ("name");
		AbstractPort port = new CommonPort(child, "name");
		port.parent = child;
		JGPipelineModel model = new JGPipelineModel();
		//model.addTask(dec);
		//model.addTask(deco);
		pipe.target = port;
		//assertEquals("name", model.disconnectTasks(dec, deco).name);
	}
	
	/**
	 * @see AbstractPipelineModel#connectTasks(AbstractPipe, AbstractPort)
	 * 
	 * @throws TasksNotCompatibleException
	 * @throws TasksNotInModelException
	 */
	@Test (expected=TasksNotInModelException.class) 
		public void disconnectTaskb2() throws TasksNotInModelException{
		AbstractTask child = new CommonTask("name");
		AbstractPipe out = new CommonPipe(child, "type");
		AbstractTask parent = new CommonTask("name");
		out.source = parent;
		AbstractPort port = new CommonPort(child, "name");
		out.target = port;
		AbstractPipelineModel model = new JGPipelineModel();
		model.addTask(parent);
		model.addTask(child);
		AbstractPipelineModel model2 = new JGPipelineModel();
		model2.addTask(parent);
		model2.addTask(child);
		model.disconnectTasks(parent, child);
		assertEquals(model, model2);
	}
}
