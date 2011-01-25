/*OsmUi is a user interface for Osmosis
    Copyright (C) 2011  Verena Käfer, Peter Vollmer, Niklas Schnelle

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or 
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 * 
 * @see OsmosisTaskDescription
 * 
 */
package de.osmui.model.osm;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
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
