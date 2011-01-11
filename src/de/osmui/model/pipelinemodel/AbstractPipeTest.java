package de.osmui.model.pipelinemodel;

import static org.junit.Assert.*;

import org.junit.Test;


public class AbstractPipeTest {
	@Test public void connect(){
		CommonTask task = new CommonTask("name");
		CommonPipe pipe = new CommonPipe(task, "name");
		CommonPort target = new CommonPort(null, null);
		assertEquals("target==null", false, pipe.connect(target));
		
		target.type = "name";
		assertEquals("target/=null", true, pipe.connect(target));
	}
	
	@Test public void disconnect(){
		CommonTask task = new CommonTask("name");
		CommonPipe pipe = new CommonPipe(task, "name");
		CommonPort target = new CommonPort(task, "type");
		pipe.target = target;
		target.connect(pipe);
		pipe.disconnect();
		assertEquals("target/=null", null, pipe.target);
		
		pipe.disconnect();
		assertEquals("target=null", null, pipe.target);
		
	}
	
	@Test public void ToString(){
		CommonTask task = new CommonTask("name");
		task.name = "name";
		CommonPipe pipe = new CommonPipe(task, "name");
		String text = pipe.toString();
		assertEquals("toString",text,"name");
		
		
	}
}