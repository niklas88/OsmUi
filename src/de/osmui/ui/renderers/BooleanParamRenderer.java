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
 * @author Niklas Schnelle
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
