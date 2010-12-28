package de.osmui.ui;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;

public class TaskBox extends JTable {

	public TaskBox() {
		final DefaultListModel listModel= new DefaultListModel();
		JList taskBoxList = new JList(listModel);
		listModel.addElement("Test");
	}

}
