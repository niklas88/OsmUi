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

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import de.osmui.model.pipelinemodel.BooleanParameter;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 *
 */
public class BooleanParamRenderer implements TableCellRenderer {

	protected TableCellRenderer delegate;
	
	public BooleanParamRenderer(TableCellRenderer defaultRenderer){
		delegate = defaultRenderer;
		
	}
	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
			BooleanParameter param = (BooleanParameter) value;
			Component c = delegate.getTableCellRendererComponent(table, param.getValueBoolean(), isSelected, hasFocus, row, column);
			if(c instanceof JCheckBox){
				JCheckBox cb = (JCheckBox) c;
				cb.setHorizontalAlignment(SwingConstants.CENTER);
			}
				
		
		return c;
	}

}
