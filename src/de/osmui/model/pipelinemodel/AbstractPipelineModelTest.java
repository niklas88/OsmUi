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

import de.osmui.model.exceptions.TasksNotCompatibleException;
import de.osmui.model.exceptions.TasksNotInModelException;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 * 
 * @see AbstractPiplineModel
 * 
*/
public class AbstractPipelineModelTest {
	/**
	 * @see AbstractPipelineModel#connectTasks(AbstractTask, AbstractTask)
	 * 
	 * @throws TasksNotCompatibleException
	 * @throws TasksNotInModelException
	 */
	@Test public void connectTaska() throws TasksNotCompatibleException, TasksNotInModelException{
		AbstractTask parent = new CommonTask("name");
		AbstractPipe output = new CommonPipe (parent, "name");
		output.source = parent;
		parent.getOutputPipes().add(output);
		
		AbstractTask child = new CommonTask("name");
		AbstractPort input = new CommonPort(child, "name");
		input.parent = child;
		child.getInputPorts().add(input);
		
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(parent);
		model.addTask(child);
		AbstractPipe test = model.connectTasks(parent, child);
		assertEquals(test.name, output.name);
	}
	
	/**
	 * @see AbstractPipelineModel#connectTasks(AbstractTask, AbstractTask)
	 * 
	 * @throws TasksNotCompatibleException
	 * @throws TasksNotInModelException
	 */
	@Test public void connectTaska2() throws TasksNotCompatibleException, TasksNotInModelException{
		AbstractTask parent = new CommonTask("name");
		AbstractPipe output = new CommonPipe (parent, "name");
		output.source = parent;
		parent.getOutputPipes().add(output);
		
		AbstractTask child = new CommonTask("name");
		AbstractPort input = new CommonPort(child, "name");
		input.parent = child;
		child.getInputPorts().add(input);
		
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(parent);
		model.addTask(child);
		AbstractPipe test = model.connectTasks(output, input);
		assertEquals(test.name, output.name);
	}
	
	/**
	 * @see AbstractPipelineModel#connectTasks(AbstractPipe, AbstractPort)
	 * 
	 * @throws TasksNotCompatibleException
	 * @throws TasksNotInModelException
	 */
	@SuppressWarnings("unused")
	@Test public void connectb() throws TasksNotCompatibleException, TasksNotInModelException{
		AbstractTask task = new CommonTask("name");
		AbstractTask child = new CommonTask("name");
		AbstractPipe output = new CommonPipe(task, "type");
		/*pi.source = child;
		AbstractPort input = new CommonPort(parent, "type");
		input.parent = parent;
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(dec);
		model.addTask(deco);
		model.connectTasks(pi, input);*/
		
	}
	
	/**
	 * @see AbstractPipelineModel#connectTasks(AbstractPipe, AbstractPort)
	 * 
	 * @throws TasksNotCompatibleException
	 * @throws TasksNotInModelException
	 */
	@SuppressWarnings("unused")
	@Test public void disconnectTaskb1() throws TasksNotInModelException, TasksNotCompatibleException{
		
		CommonTask parent = new CommonTask("name");
		AbstractPipe pipe = new CommonPipe (parent, "name");
		pipe.source = parent;
		CommonTask child = new CommonTask ("name");
		AbstractPort port = new CommonPort(child, "name");
		port.parent = child;
		JGPipelineModel model = new JGPipelineModel();
		//model.addTask(dec);
		//model.addTask(deco);
		pipe.target = port;
		//assertEquals("name", model.disconnectTasks(dec, deco).name);
	}
	
	/**
	 * @see AbstractPipelineModel#connectTasks(AbstractPipe, AbstractPort)
	 * 
	 * @throws TasksNotCompatibleException
	 * @throws TasksNotInModelException
	 */
	@Test (expected=TasksNotInModelException.class) 
		public void disconnectTaskb2() throws TasksNotInModelException{
		AbstractTask child = new CommonTask("name");
		AbstractPipe out = new CommonPipe(child, "type");
		AbstractTask parent = new CommonTask("name");
		out.source = parent;
		AbstractPort port = new CommonPort(child, "name");
		out.target = port;
		AbstractPipelineModel model = new JGPipelineModel();
		model.addTask(parent);
		model.addTask(child);
		AbstractPipelineModel model2 = new JGPipelineModel();
		model2.addTask(parent);
		model2.addTask(child);
		model.disconnectTasks(parent, child);
		assertEquals(model, model2);
	}
}
