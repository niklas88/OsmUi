package de.osmui.util;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import de.osmui.model.osm.TTask;
import de.osmui.model.pipelinemodel.AbstractTask;
import de.osmui.model.pipelinemodel.CommonTask;
import de.osmui.util.exceptions.TaskNameUnknownException;


public class TaskManagerTest {
	@Test public void createTask() throws TaskNameUnknownException{
		AbstractTask task = new CommonTask("tee");
		TaskManager manager = new TaskManager();
		AbstractTask s = manager.createTask("tee");
		assertEquals("Description==null",task.getName(), s.getName());
	}
	
	@Test public void getTaskDescription() throws TaskNameUnknownException{
		TTask result = new TTask();
		result.setName("tee");
		TaskManager manager = new TaskManager();
		TTask test = manager.getTaskDescription("tee");
		assertEquals(result.getName(), test.getName());		
	}
	
	@Test public void getTaskDescription2() throws TaskNameUnknownException{
		AbstractTask task = new CommonTask("tee");
		TaskManager manager = new TaskManager();
		TTask test = manager.getTaskDescription(task);
		assertEquals("tee", test.getName());
	}
	
	@Test public void getCompatibleTasks(){
		ArrayList<TTask> list = new ArrayList<TTask>();
		TaskManager manager = new TaskManager();
		String s = "tee";
		assertEquals(23, manager.getCompatibleTasks(s).size());
	}
}
