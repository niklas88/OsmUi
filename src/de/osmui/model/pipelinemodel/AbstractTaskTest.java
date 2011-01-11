package de.osmui.model.pipelinemodel;

import static org.junit.Assert.*;

import org.junit.Test;


public class AbstractTaskTest {
	@Test public void isConnectable(){
		CommonTask task = new CommonTask("name");	
		CommonPipe pipe1 = new CommonPipe(task, "name");	
		CommonPipe pipe2 = new CommonPipe(task, "name");	
		task.outputPipes.add(pipe1);
		task.outputPipes.add(pipe2);
		assertEquals("isConnectable", true, task.isConnectable());
		
		CommonPort port1 = new CommonPort(task, "name");	
		CommonPort port2 = new CommonPort(task, "name");	
		task.inputPorts.add(port1);
		task.inputPorts.add(port2);
		assertEquals("isConnectable", true, task.isConnectable());
		
		
	}
}
