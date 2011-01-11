package de.osmui.model.osm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
* @see TParameter
*/
public class TParameterTest {
	@Test public void getEnumVal(){
		List<TEnumValue> arr = new ArrayList<TEnumValue>();
		TParameter param = new TParameter();
		assertEquals("getEnumValue",param.getEnumValue(),arr);
	}
	
	@Test public void isRequir(){
		TParameter param = new TParameter();
		Boolean boole = null;
		boole = param.isRequired();
		assertEquals("required==null", boole, false);
		param.required = true;
		boole = param.isRequired();
		assertEquals("required==null", boole, true);
	}
	
	@Test public void setget(){
		TParameter param = new TParameter();
		param.setBooleanEncoding("value");
		param.setDefaultValue("value");
		param.setDescription("value");
		param.setFriendlyName("name");
		param.setName("name");
		param.setType("type");
		
		String enc = param.getBooleanEncoding();
		String defval = param.getDefaultValue();
		String desc = param.getDescription();
		String frname = param.getFriendlyName();
		String name = param.getName();
		String type = param.getType();
		
		assertEquals("value", enc);
		assertEquals("value", defval);
		assertEquals("value", desc);
		assertEquals("name", frname);
		assertEquals("name", name);
		assertEquals("type", type);
	}
}
