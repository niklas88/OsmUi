package de.osmui.model.osm;

import static org.junit.Assert.*;

import org.junit.Test;

/**
* @see TTaskGroup
*/
public class TTaskGroupTest {
	@Test public void setget(){
		TTaskGroup group = new TTaskGroup();
		group.setDescription("value");
		group.setFriendlyName("value");
		group.setId("value");
		
		String desc = group.getDescription();
		String frname = group.getFriendlyName();
		String id = group.getId();
		
		assertEquals("value", desc);
		assertEquals("value", frname);
		assertEquals("value", id);
	}
}
