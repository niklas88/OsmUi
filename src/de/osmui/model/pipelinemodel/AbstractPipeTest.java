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
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 * 
 * @see AbstractPipe
 * 
*/
public class AbstractPipeTest {
	@Test public void connect(){
		CommonTask task = new CommonTask("name");
		CommonPipe pipe = new CommonPipe(task, "name");
		CommonPort target = new CommonPort(null, null);
		assertEquals("target==null", false, pipe.connect(target));
		
		target.type = "name";
		assertEquals("target/=null and connected", true, pipe.connect(target));
		
		target.disconnect();
		assertEquals("target/=null and not connected", true, pipe.connect(target));
	}
	
	@Test public void disconnect(){
		CommonTask task = new CommonTask("name");
		CommonPipe pipe = new CommonPipe(task, "name");
		CommonPort target = new CommonPort(task, "type");
		pipe.target = target;
		target.connect(pipe);
		pipe.disconnect();
		assertEquals("target/=null", null, pipe.target);
		
		pipe.disconnect();
		assertEquals("target=null", null, pipe.target);
		
	}
	
	@Test public void ToString(){
		CommonTask task = new CommonTask("name");
		task.name = "name";
		CommonPipe pipe = new CommonPipe(task, "name");
		String text = pipe.toString();
		assertEquals("toString",text,"name");
	}
	
	@Test public void getset(){
		CommonTask task = new CommonTask("name");
		CommonPipe pipe = new CommonPipe(task, "name");
		pipe.myId = 10;
		assertEquals(10, pipe.getID());
		
	}
}