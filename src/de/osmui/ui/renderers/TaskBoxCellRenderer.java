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

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import de.osmui.model.osm.TTask;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena Käfer
 * 
 * @see TaskBoxCellRendererTest
 */
public class TaskBoxCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 4215729606606179888L;
	
	private String lineWrapDescription(String description){
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		int ch;
		int sinceWrap = 0;
		for(int pos = 0; pos < description.length(); ++pos){
			ch = description.codePointAt(pos);
			if(sinceWrap >= 80 && Character.isWhitespace(ch)){
				sb.append("<br>");
				sinceWrap = 0;
			} else {
				sinceWrap++;
			}
			sb.appendCodePoint(ch);
		}
		sb.append("</html>");
		return sb.toString();
	}

	public Component getTableCellRendererComponent(JTable table, Object obj,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
		
		TTask task = (TTask) obj;
		this.setText(task.getName());
		this.setToolTipText(lineWrapDescription(task.getDescription()));
		
		return this;
	}
}
