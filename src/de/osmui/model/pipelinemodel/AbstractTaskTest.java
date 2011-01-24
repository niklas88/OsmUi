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
 * @see AbstractPort
 * 
*/
public class AbstractTaskTest {
	@Test public void isConnectable(){
		CommonTask task = new CommonTask("name");	
		CommonPipe pipe1 = new CommonPipe(task, "name");	
		CommonPipe pipe2 = new CommonPipe(task, "name");	
		task.outputPipes.add(pipe1);
		task.outputPipes.add(pipe2);
		assertEquals("isConnectable", true, task.isConnectable());
		
		CommonPort port1 = new CommonPort(task, "name");	
		CommonPort port2 = new CommonPort(task, "name");	
		task.inputPorts.add(port1);
		task.inputPorts.add(port2);
		assertEquals("isConnectable", true, task.isConnectable());
		
		
	}
}
