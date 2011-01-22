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

/**
 * 
 */
package de.osmui.ui.renderers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;

import de.osmui.model.pipelinemodel.BooleanParameter;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 *
 *
 *no tests, only getter and setter
 */
public class BooleanParamEditor extends AbstractCellEditor implements
		TableCellEditor, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7941388098517684947L;

	private JCheckBox checkBox;

	private BooleanParameter param;

	public BooleanParamEditor() {
		checkBox = new JCheckBox();
		checkBox.setHorizontalAlignment(SwingConstants.CENTER);
		param = null;
	}

	@Override
	public Object getCellEditorValue() {
		param.setValueBoolean(checkBox.isSelected());
		return param;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		param.setValueBoolean(checkBox.isSelected());
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		param = (BooleanParameter) value;
		checkBox.setSelected(param.getValueBoolean());
		return checkBox;
	}

}
