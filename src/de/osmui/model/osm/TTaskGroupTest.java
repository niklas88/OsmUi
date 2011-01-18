package de.osmui.model.osm;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * 
 * @author verena
 * 
 * @see TTaskGroup
 * 
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
		 ArrayList<TTask> list = new ArrayList<TTask>();
		assertEquals(list.size(), group.getTask().size());
	}
}
