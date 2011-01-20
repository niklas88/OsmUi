/**
 * 
 */
package de.osmui.ui.renderers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;

import de.osmui.model.pipelinemodel.BooleanParameter;

/**
 * @author Niklas Schnelle
 * 
 */
public class BooleanParamEditor extends AbstractCellEditor implements
		TableCellEditor, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7941388098517684947L;

	private JCheckBox checkBox;

	private BooleanParameter param;

	public BooleanParamEditor() {
		checkBox = new JCheckBox();
		checkBox.setHorizontalAlignment(SwingConstants.CENTER);
		param = null;
	}

	@Override
	public Object getCellEditorValue() {
		param.setValueBoolean(checkBox.isSelected());
		return param;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		param.setValueBoolean(checkBox.isSelected());
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		param = (BooleanParameter) value;
		checkBox.setSelected(param.getValueBoolean());
		return checkBox;
	}

}
