package de.osmui.ui;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;

import de.osmui.util.TaskManager;

public class TaskBox extends JTable {

	public TaskBox() {
		TaskManager taskManager = TaskManager.getInstance(); 
		final DefaultListModel listModel= new DefaultListModel();
		JList taskBoxList = new JList(listModel);
		listModel.addElement("Test");
	}

}
