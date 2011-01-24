package de.osmui.ui.renderers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import de.osmui.model.pipelinemodel.parameters.DirParameter;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena Käfer
 * 
 */

public class DirParamEditor extends AbstractCellEditor implements
		TableCellEditor, ActionListener {

	/**
 * 
 */
	private static final long serialVersionUID = -7892064790758688299L;

	private TextFieldAndButton textFieldButton;
	private DirParameter param;
	
	public DirParamEditor() {
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
			chooser.addChoosableFileFilter(new DirFileFilter());
			chooser.setFileFilter(new DirFileFilter());
			chooser.setAcceptAllFileFilterUsed(false);
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
		param = (DirParameter) value;
		textFieldButton.getTextField().setText(param.getValue());
		return textFieldButton;
	}

}
