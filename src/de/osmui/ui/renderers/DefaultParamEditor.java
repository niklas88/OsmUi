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
	
	private AbstractParameter currParam;
	
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
