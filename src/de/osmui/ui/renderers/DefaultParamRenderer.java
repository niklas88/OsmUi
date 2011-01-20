package de.osmui.ui.renderers;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import de.osmui.model.pipelinemodel.AbstractParameter;

/**
 * 
 * no tests, only getter and setter
 *
 */

public class DefaultParamRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 4215729606603179888L;

	public Component getTableCellRendererComponent(JTable table, Object obj,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
		
		AbstractParameter param = (AbstractParameter) obj;
		this.setText(param.getValue());
		this.setToolTipText(param.getDescription().getDescription());
		
		return this;
	}
}
