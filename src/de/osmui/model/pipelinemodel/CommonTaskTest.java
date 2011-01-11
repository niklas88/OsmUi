package de.osmui.model.pipelinemodel;

import static org.junit.Assert.*;

import org.junit.Test;

/**
* @see CommonTask
*/
public class CommonTaskTest {
	@Test public void getCommandLineForm(){
		CommonTask task = new CommonTask("name");
		String s;
		s = task.getCommandlineForm();
		
		assertEquals("--name ", s);
	}
}
