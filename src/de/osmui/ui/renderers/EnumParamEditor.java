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

package de.osmui.ui.renderers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.TableCellEditor;

import de.osmui.model.osm.TEnumValue;
import de.osmui.model.pipelinemodel.parameters.EnumParameter;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 */

public class EnumParamEditor extends AbstractCellEditor implements
		TableCellEditor, ActionListener {

	private static class EnumComboBoxRenderer extends BasicComboBoxRenderer {

		private static final long serialVersionUID = 2368155220295494506L;

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.plaf.basic.BasicComboBoxRenderer#getListCellRendererComponent
		 * (javax.swing.JList, java.lang.Object, int, boolean, boolean)
		 */
		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			TEnumValue enumValue = (TEnumValue) value;
			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
				list.setToolTipText(enumValue.getDescription());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			
			setText(enumValue.getValue());
			return this;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -578571469779942429L;

	private JComboBox comboBox;
	private DefaultComboBoxModel model;
	private EnumParameter param;

	public EnumParamEditor() {
		model = new DefaultComboBoxModel();
		comboBox = new JComboBox(model);
		comboBox.setRenderer(new EnumComboBoxRenderer());
		param = null;
	}

	@Override
	public Object getCellEditorValue() {
		String value = ((TEnumValue)model.getSelectedItem()).getValue();
		param.setValue(value);
		return param;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Do nothing
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		param = (EnumParameter) value;
		model.removeAllElements();
		for (TEnumValue val : param.getEnumerationValues()) {
			model.addElement(val);
		}
		return comboBox;
	}

}
