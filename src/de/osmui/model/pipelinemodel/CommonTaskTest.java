package de.osmui.model.pipelinemodel;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import de.osmui.i18n.I18N;

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
