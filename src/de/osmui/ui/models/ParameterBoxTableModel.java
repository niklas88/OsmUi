/*OsmUi is a user interface for Osmosis
    Copyright (C) 2011  Verena Käfer, Peter Vollmer, Niklas Schnelle

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or 
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package de.osmui.ui.models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import de.osmui.i18n.I18N;
import de.osmui.model.pipelinemodel.AbstractParameter;
import de.osmui.model.pipelinemodel.AbstractTask;

public class ParameterBoxTableModel extends AbstractTableModel {

	/**
	 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
	 * 
	 * no tests, only getter and setter
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
		return (paramList != null) ? paramList.size() : 0;
	}

	@Override
	public Object getValueAt(int row, int column) {
		if (row < 0 || row > getRowCount()) {
			return null;
		}
		return (column == 0) ? paramList.get(row).getName() : paramList
				.get(row);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return (col == 1) ? true : false;
	}

	@Override
	public String getColumnName(int col){
		return (col == 0)?I18N.getString("ParameterBoxTableModel.name"):
			I18N.getString("ParameterBoxTableModel.value");
	}

	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return (c > 0 && paramList != null && !paramList.isEmpty()) ? AbstractTask.class
				: String.class;
	}
	
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		super.setValueAt(value, rowIndex, columnIndex);
		fireTableDataChanged();
	}
}
