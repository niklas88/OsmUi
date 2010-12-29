package de.osmui.ui;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.osmui.ui.models.TaskBoxTableModel;
import de.osmui.util.TaskManager;

public class TaskBox extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4259689270781114248L;

	public TaskBox(TaskBoxTableModel taskBoxTableModel) {
		this.setModel(taskBoxTableModel);
	}

}
