package de.osmui.ui.models;


import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.osmui.model.osm.TTask;

public class TaskBoxTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2453623808903546286L;

	List<TTask> data;
	
	public void setTasks(List<TTask> tasks){
		data = tasks;
		fireTableStructureChanged();
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public int getRowCount() {
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

}
