package de.osmui.ui.models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import de.osmui.i18n.I18N;
import de.osmui.model.pipelinemodel.AbstractParameter;
import de.osmui.model.pipelinemodel.AbstractTask;

public class ParameterBoxTableModel extends AbstractTableModel {

	/**
	 * 
	 *  will be tested by system-tests
	 */
	private static final long serialVersionUID = 6247700341831278770L;

	private ArrayList<AbstractParameter> paramList;

	public void setTask(AbstractTask task) {
		if (task != null) {
			paramList = new ArrayList<AbstractParameter>(task.getParameters()
					.values());
			fireTableStructureChanged();
		}
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return (paramList != null)? paramList.size():0;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return (column == 0) ? paramList.get(row).getName() : paramList.get(row);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return true;//(col == 1)? true : false;
	}

	@Override
	public String getColumnName(int col){
		return (col == 0)?I18N.getString("ParameterBoxTableModel.name"):
			I18N.getString("ParameterBoxTableModel.value");
	}
	
	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return (c > 0) ? getValueAt(0, c).getClass() : String.class;
	}
}
