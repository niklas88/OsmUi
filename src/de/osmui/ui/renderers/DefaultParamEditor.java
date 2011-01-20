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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import de.osmui.i18n.I18N;

import de.osmui.model.pipelinemodel.AbstractParameter;

/**
 * @author niklas
 * no tests, only getter and setter
 */
public class DefaultParamEditor  extends AbstractCellEditor
			implements TableCellEditor, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7189134812752397954L;

	private JTextField textField;
	
	AbstractParameter currParam;
	
	public DefaultParamEditor() {
		textField = new JTextField();
		textField.addActionListener(this);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		currParam = (AbstractParameter) value;
		textField.setText(currParam.getValue());
		return textField;

	}

	@Override
	public Object getCellEditorValue() {
		String formerValue = currParam.getValue();
		try {
			currParam.setValue(textField.getText());
		} catch (IllegalArgumentException e){
			JOptionPane.showMessageDialog(null,I18N.getString("ParamBox.ValWrong"));
			currParam.setValue(formerValue);
		}
		return  currParam;
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String formerValue = currParam.getValue();
		try {
			currParam.setValue(textField.getText());
			fireEditingStopped();
		} catch (IllegalArgumentException e){
			JOptionPane.showMessageDialog(null,I18N.getString("ParamBox.ValWrong"));
			currParam.setValue(formerValue);
		}
	}
}
