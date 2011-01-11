package de.osmui.model.pipelinemodel;

import static org.junit.Assert.*;

import org.junit.Test;

import de.osmui.model.osm.TParameter;

/**
* @see VariablePort
*/
public class VariablePortTest {
	@Test public void createPort(){
		AbstractTask task = new CommonTask("name");
		TParameter Tparam = new TParameter();
		IntParameter param = new IntParameter(Tparam, "9");
		AbstractPort test = new VariablePort(task, param, "type");
		VariablePort port = new VariablePort(task, param, "type");
		AbstractPort newPort = port.createPort();
		assertEquals(test.parent, newPort.parent);
		assertEquals(test.getType(), newPort.getType());
	}
}
