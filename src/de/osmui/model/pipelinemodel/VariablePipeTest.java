package de.osmui.model.pipelinemodel;

import static org.junit.Assert.*;

import org.junit.Test;

import de.osmui.i18n.I18N;
import de.osmui.model.osm.TParameter;

/**
 * 
 * @author verena
 * 
 * @see VariablePipe
 * 
*/
public class VariablePipeTest {
	@Test public void createPipe(){
		AbstractTask task = new CommonTask("name");
		TParameter Tparam = new TParameter();
		IntParameter param = new IntParameter(Tparam, "9");
		VariablePipe pipe = new VariablePipe(task, param, "type");
		pipe.createPipe();
		AbstractPipe test= new CommonPipe(task, "type");
		assertEquals(pipe.type, test.getType());
	}
	
	@Test public void disconnect(){
		AbstractTask task = new CommonTask("name");
		TParameter Tparam = new TParameter();
		IntParameter param = new IntParameter(Tparam, "9");
		VariablePipe pipe = new VariablePipe(task, param, "type");
		pipe.referencedParam = param;
		pipe.disconnect();
		assertEquals(false, pipe.isConnected());
	}
}
