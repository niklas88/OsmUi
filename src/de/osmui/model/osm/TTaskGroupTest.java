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
 * @see TTaskGroup
 * 
*/

package de.osmui.model.osm;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

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
