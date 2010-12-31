package de.osmui.ui.models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import de.osmui.util.TaskManager;

public class TaskBoxTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2453623808903546286L;
	
	ArrayList<String> data;
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return (data == null) ? 0 : data.size() - 1;
	}

	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		String value = data.get(row + 1);
		return value;
	}
	
	public void showCompatibleTasks(String task) throws Exception {
		data = TaskManager.getInstance().getCompatibleTasks(task);
		fireTableStructureChanged();
		}
}
