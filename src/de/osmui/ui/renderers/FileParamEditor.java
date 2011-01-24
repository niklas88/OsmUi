/**
 * 
 */
package de.osmui.ui.renderers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import de.osmui.model.pipelinemodel.FileParameter;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena Käfer
 *
 */
public class FileParamEditor extends AbstractCellEditor implements
		TableCellEditor, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7892064790758688299L;

	private TextFieldAndButton textFieldButton;
	private FileParameter param;

	public FileParamEditor() {
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
			int returnVal = chooser.showSaveDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				textFieldButton.getTextField().setText(
						chooser.getSelectedFile().getAbsolutePath());
			}
		}

	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		param = (FileParameter) value;
		textFieldButton.getTextField().setText(param.getValue());
		return textFieldButton;
	}

}
