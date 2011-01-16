package de.osmui.model.osm;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author verena
 * 
 * @see TEnumValue
 * 
*/
public class TEnumValueTest {
	@Test public void set(){
		TEnumValue val = new TEnumValue();
		val.setDescription("value");
		val.setFriendlyName("value");
		val.setValue("value");

		String desc = val.getDescription();
		String name = val.getFriendlyName();
		String value = val.getValue();
		
		assertEquals("value", desc);
		assertEquals("value", name);
		assertEquals("value", value);
	}
}
