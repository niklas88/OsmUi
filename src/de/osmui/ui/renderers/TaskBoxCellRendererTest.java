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
 * @author Niklas Schnelle, Peter Vollmer, Veren Käfer
 * 
 * @see TaskBoxCellRenderer
 * 
 */
package de.osmui.ui.renderers;

import static org.junit.Assert.*;

import java.awt.Component;

import javax.swing.JTable;

import org.junit.Test;

import de.osmui.model.osm.TTask;


public class TaskBoxCellRendererTest {
	@Test public void get(){
		TaskBoxCellRenderer renderer = new TaskBoxCellRenderer();
		int column = 1;
		JTable table = new JTable();
		boolean isSelected = true;
		TTask task = new TTask();
		task.setDescription("desc");
		Object obj = task;
		int row = 1;
		boolean hasFocus = true;
		Component comp = renderer.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
		assertEquals("Table.cellRenderer", comp.getName());
	}
}
