package de.osmui.model.osm;

import static org.junit.Assert.*;

import org.junit.Test;

import de.osmui.i18n.I18N;

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
	}
}
