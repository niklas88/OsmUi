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
import de.osmui.util.TaskManager;
import de.osmui.util.exceptions.TaskNameUnknownException;

/**
 * @author Peter Vollmer
 * 
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
		MainFrame.getInstance().getPipeModel().layout();

	}

}
