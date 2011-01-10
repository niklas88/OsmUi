package de.osmui.ui.models;

import java.util.Map;

import javax.swing.table.AbstractTableModel;

/**
 * @author Peter Vollmer
 * 
 */

import de.osmui.model.osm.TTask;
import de.osmui.model.pipelinemodel.AbstractParameter;
import de.osmui.model.pipelinemodel.AbstractTask;

public class ParameterBoxTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6247700341831278770L;
	
	Map<String, AbstractParameter> data;
	
	public void setTask(AbstractTask task){
		data = task.getParameters();
		fireTableStructureChanged();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return (data == null) ? 0 : data.size() - 1;
	}

	@Override
	public Object getValueAt(int row, int column) {
		value = data.values().
		get(row + 1);
		return data;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		if (row == 2){
			return true;	
		}else {
			return false;
		}
		
	}

	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
