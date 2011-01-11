package de.osmui.model.pipelinemodel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.osmui.model.exceptions.TasksNotCompatibleException;
import de.osmui.model.exceptions.TasksNotInModelException;

/**
* @see JGPipelineModel
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
	
	@Test public void addTask1() throws TasksNotCompatibleException, TasksNotInModelException{
		AbstractTask parent = new CommonTask("name");
		AbstractTask child = new CommonTask("name");
		JGPipelineModel model = new JGPipelineModel();
		JGTaskDecorator dec = new JGTaskDecorator(parent);
		JGTaskDecorator deco = new JGTaskDecorator(child);
		model.addTask(dec);
		model.addTask(dec, deco);
		JGPipelineModel test = new JGPipelineModel();
		test.addTask(dec);
		test.addTask(dec, deco);
		assertEquals(model.tasks.size(), test.tasks.size());
	}

	@Test public void removeTask() throws TasksNotInModelException, TasksNotCompatibleException{
		AbstractTask task = new CommonTask("name");
		AbstractTask task1 = new CommonTask("name");
		JGPipelineModel model = new JGPipelineModel();
		JGTaskDecorator dec = new JGTaskDecorator(task);
		JGTaskDecorator dec1 = new JGTaskDecorator(task1);
		model.addTask(dec);
		model.addTask(dec1);
		model.connectTasks(dec, dec1);
		assertEquals(true, model.removeTask(dec));
	}
	
	@Test public void connectTasks() throws TasksNotCompatibleException, TasksNotInModelException{
		AbstractTask parent = new CommonTask("name");
		AbstractTask child = new CommonTask("name");
		JGPipelineModel model = new JGPipelineModel();
		JGTaskDecorator dec = new JGTaskDecorator(parent);
		JGTaskDecorator deco = new JGTaskDecorator(child);
		model.addTask(dec);
		model.addTask(dec, deco);
		ArrayList<JGTaskDecorator> list = new ArrayList<JGTaskDecorator>();
		JGTaskDecorator e = new JGTaskDecorator(parent);
		JGTaskDecorator f = new JGTaskDecorator(child);
		list.add(e);
		list.add(f);
		assertEquals(list.size(), model.tasks.size());
	}
	
	@Test public void connectTasks1() throws TasksNotCompatibleException, TasksNotInModelException{
		AbstractTask task = new CommonTask("name");
		AbstractTask task1 = new CommonTask("name");
		JGTaskDecorator dec = new JGTaskDecorator(task);
		JGTaskDecorator deco = new JGTaskDecorator(task1);
		AbstractPipe output = new CommonPipe(dec, "type");
		JGPipeDecorator pi = new JGPipeDecorator(output);
		AbstractPort input = new CommonPort(deco, "type");
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(dec);
		model.addTask(deco);
		model.connectTasks(pi, input);
		ArrayList<JGTaskDecorator> list = model.tasks;
		ArrayList<JGTaskDecorator> test = new ArrayList<JGTaskDecorator>();
		JGTaskDecorator e = new JGTaskDecorator(task1);
		test.add(e );
		JGTaskDecorator f = new JGTaskDecorator(task);
		test.add(f);
		assertEquals(test, list);
	}
	
	@Test public void disconnectTasks1() throws TasksNotCompatibleException, TasksNotInModelException{
		AbstractTask parent = new CommonTask("name");
		AbstractTask child = new CommonTask("name");
		JGTaskDecorator dec = new JGTaskDecorator(parent);
		JGTaskDecorator deco = new JGTaskDecorator(child);
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(deco);
		model.addTask(dec);
		model.connectTasks(dec, deco);
		model.disconnectTasks(dec, deco);
		
		AbstractTask task = new CommonTask("name");
		AbstractTask task1 = new CommonTask("name");
		ArrayList<JGTaskDecorator> list = model.tasks;
		ArrayList<JGTaskDecorator> test = new ArrayList<JGTaskDecorator>();
		JGTaskDecorator e = new JGTaskDecorator(task1);
		test.add(e );
		JGTaskDecorator f = new JGTaskDecorator(task);
		test.add(f);
		assertEquals(test.size(), list.size());
	}
}
