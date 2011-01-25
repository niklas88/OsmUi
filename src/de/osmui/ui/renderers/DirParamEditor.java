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
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import de.osmui.model.pipelinemodel.parameters.DirParameter;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena Käfer
 * 
 */

public class DirParamEditor extends AbstractCellEditor implements
		TableCellEditor, ActionListener {

	private static final long serialVersionUID = 7200685439949374656L;

	private TextFieldAndButton textFieldButton;
	private DirParameter param;
	
	public DirParamEditor() {
		textFieldButton = new TextFieldAndButton();
		textFieldButton.getButton().setText("...");
		textFieldButton.getButton().addActionListener(this);
		param = null;
	}

	@Override
	public Object getCellEditorValue() {
		String value = textFieldButton.getTextField().getText();
		param.setValue(value);
		return param;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == textFieldButton.getButton()) {
			JFileChooser chooser = new JFileChooser();
			chooser.addChoosableFileFilter(new DirFileFilter());
			chooser.setFileFilter(new DirFileFilter());
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				textFieldButton.getTextField().setText(
						chooser.getSelectedFile().getAbsolutePath());
			}
		}

	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		param = (DirParameter) value;
		textFieldButton.getTextField().setText(param.getValue());
		return textFieldButton;
	}

}
