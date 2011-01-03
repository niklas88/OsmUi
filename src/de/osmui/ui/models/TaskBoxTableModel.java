package de.osmui.ui.models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import de.osmui.model.osm.TTask;
import de.osmui.util.TaskManager;

public class TaskBoxTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2453623808903546286L;
	
	ArrayList<TTask> data;
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
		TTask value = data.get(row + 1);
		return value;
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	@Override
    public Class<? extends Object> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
	
	public void showCompatibleTasks(String task) throws Exception {
		data = TaskManager.getInstance().getCompatibleTasks(task);
		fireTableStructureChanged();
	}
}
