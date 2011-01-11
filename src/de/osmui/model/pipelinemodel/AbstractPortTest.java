package de.osmui.model.pipelinemodel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.osmui.i18n.I18N;

/**
 * 
 * @author verena
 * 
 * @see AbstractPort
 * 
*/
public class AbstractPortTest {
	@Test public void connect(){
		CommonTask task = new CommonTask("name");
		CommonPipe pipe = new CommonPipe(task, "name");
		CommonPort port = new CommonPort(task, "name");
		CommonPort test = new CommonPort(task, "name");
		pipe.target = port;
		assertEquals("target connected", true, port.connect(pipe));
		
		pipe.target = test;
		assertEquals("target disconnected", false, port.connect(pipe));
		
	}
	
	@Test public void disconnect(){
		CommonTask task = new CommonTask("name");
		CommonPipe pipe = new CommonPipe(task, "name");
		CommonPort port = new CommonPort(task, "name");
		pipe.connect(port);
		port.incoming = null;
		port.disconnect();
		assertEquals ("incoming==null", null, port.incoming);
		
		port.incoming = pipe;
		assertEquals ("incomming /= null", null, port.incoming.name);
	}
}
