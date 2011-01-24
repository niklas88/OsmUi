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
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import de.osmui.i18n.I18N;
import de.osmui.model.pipelinemodel.BBoxPseudoParameter;
import de.unistuttgart.iev.osm.bboxchooser.BBoxChooserDialog;
import de.unistuttgart.iev.osm.bboxchooser.Bounds;
import de.unistuttgart.iev.osm.bboxchooser.DialogResponse;

/**
 * @author Niklas Schnelle
 *
 */
public class BBoxCellEditor  extends AbstractCellEditor implements
TableCellEditor, TableCellRenderer,ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3647091148956495004L;
	
	protected JButton button;
	protected BBoxPseudoParameter param;
	
	public BBoxCellEditor(){
		button = new JButton(I18N.getString("BBoxPseudoParamEditor.BBoxEdit"));
		button.addActionListener(this);
		param = null;
	}

	@Override
	public Object getCellEditorValue() {
		return param;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		BBoxChooserDialog dialog = new BBoxChooserDialog();
		dialog.setBoundingBox(param.getBoundingBox());
		dialog.setVisible(true);
		if (dialog.getResponse() == DialogResponse.OK) {
			Bounds boundingBox = dialog.getBoundingBox();
			if (boundingBox != null && param != null) {
				param.setBoundingBox(boundingBox);
			}
		}
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		param = (BBoxPseudoParameter) value;
		return button;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		return button;
	}
}


