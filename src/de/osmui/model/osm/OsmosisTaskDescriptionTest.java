package de.osmui.model.osm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.osmui.i18n.I18N;

/**
 * 
 * @author verena
 * 
 * @see OsmosisTaskDescription
 * 
 */
public class OsmosisTaskDescriptionTest {
	
	@Test public void getTaskGroup(){
		Object taskgroup = null;
		 ArrayList<TTaskGroup> group = new ArrayList<TTaskGroup>();
		 OsmosisTaskDescription desc = new OsmosisTaskDescription();
		 taskgroup = desc.getTaskGroup();
		 assertEquals("getTaskGroup",taskgroup , group);
	};
	
	@Test public void getFormatVersion(){
		OsmosisTaskDescription desc = new OsmosisTaskDescription();
		desc.setFormatVersion("text");
		String test = desc.getFormatVersion();
		assertEquals("getFormatVersion. ", test, "text");
	};
	
	@Test  public void setFormatVersion(){
		OsmosisTaskDescription desc = new OsmosisTaskDescription();
		desc.setFormatVersion("text");
		String test = desc.getFormatVersion();
		assertEquals("getFormatVersion. ", test, "text");
	};
	
	@Test public void setOsmosisVersion(){
		OsmosisTaskDescription desc = new OsmosisTaskDescription();
		desc.setOsmosisVersion("text");
		String test = desc.getOsmosisVersion();
		assertEquals("setOsmosisVersion: ", test, "text");
    }
}
