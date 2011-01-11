/**
 * 
 */
package de.osmui.ui.renderers;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import de.osmui.model.osm.TTask;

/**
 * @author Niklas Schnelle
 * 
 * wird nicht getestet, da nur getter und setter
 */
public class TaskBoxCellRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4215729606606179888L;

	public Component getTableCellRendererComponent(JTable table, Object obj,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
		
		TTask task = (TTask) obj;
		//this.setText((task.getFriendlyName()!= null)?task.getFriendlyName():task.getName());
		this.setText(task.getName());
		this.setToolTipText(task.getDescription());
		
		return this;
	}
}
