package de.osmui.model.pipelinemodel;

import static org.junit.Assert.*;

import org.junit.Test;

import de.osmui.model.osm.TParameter;

/**
* @see BooleanParameter
*/
public class BooleanParameterTest {
	@Test public void setgetValue(){
		TParameter desc = new TParameter();
		desc.setName("name");
		BooleanParameter param = new BooleanParameter(desc, "true");
		String s = "true";
		param.setValue(s);
		s = param.getValue();
		assertEquals("s==true", "yes", s);
		
		s = "false";
		param.setValue(s);
		s = param.getValue();
		assertEquals("s==false", "no", s);
		
		s = "yes";
		param.setValue(s);
		s = param.getValue();
		assertEquals("s==yes", s, "yes");
		
		s = "no";
		param.setValue(s);
		s = param.getValue();
		assertEquals("s==no", s, "no");
	}
	
	@Test public void isDefaultValue(){
		String s;
		TParameter desc = new TParameter();
		desc.setName("name");
		BooleanParameter param = new BooleanParameter(desc, "yes");
		s = "yes";
		param.description.setDefaultValue(s);
		assertEquals(param.getDefaultValue(), s);
		s = "no";
		param.description.setDefaultValue(s);
		assertEquals(param.getDefaultValue(), s);
		s = "true";
		param.description.setDefaultValue(s);
		assertEquals(param.getDefaultValue(), s);
		s = "false";
		param.description.setDefaultValue(s);
		assertEquals(param.getDefaultValue(), s);
	}
}