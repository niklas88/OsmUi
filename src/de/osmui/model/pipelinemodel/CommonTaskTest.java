package de.osmui.model.pipelinemodel;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;


public class CommonTaskTest {
	@Test public void getCommandLineForm(){
		CommonTask task = new CommonTask("name");
		String s;
		s = task.getCommandlineForm();
		
		assertEquals("--name", s);
	}
}
