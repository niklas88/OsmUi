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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import de.osmui.model.exceptions.TasksNotCompatibleException;
import de.osmui.model.exceptions.TasksNotInModelException;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 * 
 * @see JGPipelineModel
 * 
*/
public class JGPipelineModelTest {	
	@Test public void getSourceTasks(){
		JGPipelineModel model = new JGPipelineModel();
		ArrayList<AbstractTask> sourceTasks = new ArrayList<AbstractTask>();
		AbstractTask source1 = new CommonTask("name");
		sourceTasks.add(source1);
		model.addTask(source1);
		List<AbstractTask> test = model.getSourceTasks();
		assertEquals(sourceTasks.size(), test.size());
	}
	
	@Test public void addTask() throws TasksNotCompatibleException, TasksNotInModelException{
		CommonTask parent = new CommonTask("name");
		CommonPipe pipe = new CommonPipe(parent, "type");
		parent.getOutputPipes().add(pipe);
		
		CommonTask child = new CommonTask("name");
		CommonPort port = new CommonPort(child, "type");
		child.getInputPorts().add(port);
		
		CommonTask parent1 = new CommonTask("name");
		CommonPipe pipe1 = new CommonPipe(parent, "type");
		parent1.getOutputPipes().add(pipe1);
		
		CommonTask child1 = new CommonTask("name");
		CommonPort port1 = new CommonPort(child1, "type");
		child1.getInputPorts().add(port1);
		
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(parent);
		model.addTask(parent, child);
		
		JGPipelineModel test = new JGPipelineModel();
		test.addTask(parent1);
		test.addTask(parent1, child1);
		assertEquals(model.tasks.size(), test.tasks.size());
	}

	@Test public void removeTask() throws TasksNotInModelException, TasksNotCompatibleException{
		CommonTask parent = new CommonTask("name");
		CommonPipe pipe = new CommonPipe(parent, "type");
		parent.getOutputPipes().add(pipe);
		
		CommonTask child = new CommonTask("name");
		CommonPort port = new CommonPort(child, "type");
		child.getInputPorts().add(port);
		
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(parent);
		model.addTask(parent, child);
		assertEquals(true, model.removeTask(parent));
	}
	
	/**
	 * @see JGPipelineModel#connectTasks(AbstractTask, AbstractTask)
	 * 
	 * @throws TasksNotCompatibleException
	 * @throws TasksNotInModelException
	 */
	@Test public void connectTasks() throws TasksNotCompatibleException, TasksNotInModelException{
		CommonTask parent = new CommonTask("name");
		CommonPipe pipe = new CommonPipe(parent, "type");
		parent.getOutputPipes().add(pipe);
		
		CommonTask child = new CommonTask("name");
		CommonPort port = new CommonPort(child, "type");
		child.getInputPorts().add(port);
		
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(parent);
		model.addTask(parent, child);
		ArrayList<AbstractTask> list = new ArrayList<AbstractTask>();
		list.add(parent);
		list.add(child);
		assertEquals(2, model.tasks.size());
	}
	
	/**
	 * @see JGPipelineModel#connectTasks(AbstractPipe, AbstractPort)
	 * 
	 * @throws TasksNotCompatibleException
	 * @throws TasksNotInModelException
	 */
	@Test public void connectTasks1() throws TasksNotCompatibleException, TasksNotInModelException{
		AbstractTask task = new CommonTask("name");
		AbstractTask task1 = new CommonTask("name");
		AbstractPipe output = new CommonPipe(task, "type");
		AbstractPort input = new CommonPort(task1, "type");
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(task);
		model.addTask(task1);
		model.connectTasks(output, input);
		ArrayList<AbstractTask> list = model.tasks;
		ArrayList<AbstractTask> test = new ArrayList<AbstractTask>();
		AbstractTask e = new CommonTask("name");
		test.add(e );
		AbstractTask f = new CommonTask("name");
		test.add(f);
		assertEquals(test.size(), list.size());
	}
	
	@Test public void disconnectTasks() throws TasksNotCompatibleException, TasksNotInModelException{
		CommonTask parent = new CommonTask("name");
		CommonPipe pipe = new CommonPipe(parent, "type");
		parent.getOutputPipes().add(pipe);
		
		CommonTask child = new CommonTask("name");
		CommonPort port = new CommonPort(child, "type");
		child.getInputPorts().add(port);
		
		JGPipelineModel model = new JGPipelineModel();
		model.addTask(parent);
		model.addTask(parent, child);
		model.disconnectTasks(parent, child);
		
		ArrayList<AbstractTask> list = model.tasks;
		ArrayList<AbstractTask> test = new ArrayList<AbstractTask>();
		AbstractTask e = new CommonTask("name");
		test.add(e );
		AbstractTask f = new CommonTask("name");
		test.add(f);
		assertEquals(test.size(), list.size());
	}
}
