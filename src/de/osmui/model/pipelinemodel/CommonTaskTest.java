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

package de.osmui.model.pipelinemodel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena Käfer
 * 
 * @see CommonTask
 * 
*/
public class CommonTaskTest {
	@Test public void getCommandLineForm(){
		CommonTask task = new CommonTask("name");
		String s;
		s = task.getCommandlineForm();
		
		assertEquals("--name ", s);
	}
}
