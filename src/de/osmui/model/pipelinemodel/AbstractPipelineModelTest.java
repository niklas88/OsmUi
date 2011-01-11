package de.osmui.model.pipelinemodel;

import static org.junit.Assert.*;

import org.junit.Test;

import de.osmui.model.exceptions.TasksNotCompatibleException;
import de.osmui.model.exceptions.TasksNotInModelException;

/**
* @see AbstractPiplineModel
*/
public class AbstractPipelineModelTest {
	@Test public void connectTaska() throws TasksNotCompatibleException, TasksNotInModelException{
		AbstractTask parent = new CommonTask("tee");
		AbstractTask child = new CommonTask("tee");
		JGTaskDecorator dec = new JGTaskDecorator(parent);
		JGTaskDecorator deco = new JGTaskDecorator(child);
		AbstractPipelineModel model = new JGPipelineModel();
		model.addTask(dec);
		model.addTask(deco);
		model.connectTasks(dec, deco);
		
	}
	
	@Test public void connectTaska2() throws TasksNotCompatibleException, TasksNotInModelException{
		AbstractTask parent = new CommonTask("name");
		JGTaskDecorator dec = new JGTaskDecorator(parent);
		AbstractTask child = new CommonTask("name");
		JGTaskDecorator deco = new JGTaskDecorator(child);
		JGPipelineModel model = new JGPipelineModel();
		AbstractPipe pipe = new CommonPipe(parent, "type");
		model.addTask(dec);
		model.addTask(deco);
		pipe.source = dec;
		pipe = model.connectTasks(dec, deco);
		assertEquals(dec.name, pipe.source.name);
	}
	
	@Test public void connectb() throws TasksNotCompatibleException, TasksNotInModelException{
		AbstractTask task = new CommonTask("name");
		JGTaskDecorator dec = new JGTaskDecorator(task);
		AbstractTask child = new CommonTask("name");
		JGTaskDecorator deco = new JGTaskDecorator(child);
		AbstractPipe output = new CommonPipe(task, "type");
		JGPipeDecorator pi = new JGPipeDecorator(output);
		pi.source = dec;
		AbstractPort input = new CommonPort(deco, "type");
		input.parent = deco;
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(dec);
		model.addTask(deco);
		model.connectTasks(pi, input);
		
	}
	
	@Test public void disconnectTaskb1() throws TasksNotInModelException, TasksNotCompatibleException{
		
		CommonTask parent = new CommonTask("name");
		CommonTask child = new CommonTask ("name");
		JGTaskDecorator dec = new JGTaskDecorator(parent);
		JGTaskDecorator deco = new JGTaskDecorator(child);
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(dec);
		model.addTask(deco);
		model.connectTasks(dec, deco);
		assertEquals("name", model.disconnectTasks(dec, deco).source.name);
	}
	
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
