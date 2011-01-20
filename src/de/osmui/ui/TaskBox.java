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

package de.osmui.ui;

import javax.swing.JTable;

import javax.swing.ListSelectionModel;

import de.osmui.model.exceptions.TasksNotCompatibleException;
import de.osmui.model.exceptions.TasksNotInModelException;
import de.osmui.model.osm.TTask;
import de.osmui.model.pipelinemodel.AbstractTask;
import de.osmui.ui.events.TaskSelectedEvent;
import de.osmui.ui.events.TaskSelectedEventListener;
import de.osmui.ui.models.TaskBoxTableModel;
import de.osmui.ui.renderers.TaskBoxCellRenderer;
import de.osmui.util.TaskManager;
import de.osmui.util.exceptions.TaskNameUnknownException;

/**
 * @author Peter Vollmer
 * 
 *  will be tested by system-tests
 */

public class TaskBox extends JTable implements TaskSelectedEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4259689270781114248L;

	private final TaskBoxTableModel model;

	private AbstractTask selectedTask = null;

	public TaskBox(TaskBoxTableModel taskBoxTableModel) {
		this.setModel(taskBoxTableModel);
		this.setDefaultRenderer(TTask.class,  new TaskBoxCellRenderer());
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = taskBoxTableModel;
		showCompatibleTasks(null);

	}

	public void showCompatibleTasks(AbstractTask task) {
		if(task != null && task.isConnectable()){
			model.setTasks(TaskManager.getInstance().getCompatibleTasks(task.getName()));
		} else {
			model.setTasks(TaskManager.getInstance().getCompatibleTasks(""));
		}
	}

	@Override
	public void TaskSelected(TaskSelectedEvent e) {	

		if (e != null) {
			selectedTask = (AbstractTask) e.getSource();
			showCompatibleTasks(selectedTask);
		} else {
			showCompatibleTasks(null);
			selectedTask = null;
		}

	}

	public void addSelectedToModel() {
		TTask taskDesc = (TTask) model.getValueAt(this.getSelectedRow(), 0);
		AbstractTask newTask;
		try {
			newTask = TaskManager.getInstance().createTask(taskDesc.getName());
		} catch (TaskNameUnknownException e) {
			// Do nothing
			return;
		}
		if (selectedTask != null && selectedTask.isConnectable()) {
			try {
				MainFrame.getInstance().getPipeModel()
						.addTask(selectedTask, newTask);
			} catch (TasksNotCompatibleException e) {
				System.err.println(e);
			} catch (TasksNotInModelException e) {
				// Shouldn't happen
				e.printStackTrace();
			}
		} else {
			MainFrame.getInstance().getPipeModel().addTask(newTask);
		}

	}

}
