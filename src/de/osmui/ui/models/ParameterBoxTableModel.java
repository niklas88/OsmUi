package de.osmui.ui.models;

import javax.swing.table.AbstractTableModel;

import de.osmui.model.pipelinemodel.AbstractTask;

public class ParameterBoxTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6247700341831278770L;
	AbstractTask data;
	
	public void setTask(AbstractTask task){
		data = task;
		fireTableStructureChanged();
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public int getRowCount() {
		return 1;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return data;
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
