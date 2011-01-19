/**
 * 
 */
package de.osmui.ui.renderers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import de.osmui.i18n.I18N;
import de.osmui.model.pipelinemodel.BBoxPseudoParameter;

/**
 * @author Niklas Schnelle
 *
 */
public class BBoxCellEditor  extends AbstractCellEditor implements
TableCellEditor, TableCellRenderer,ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3647091148956495004L;
	
	protected JButton button;
	protected BBoxPseudoParameter param;
	
	public BBoxCellEditor(){
		button = new JButton(I18N.getString("ParamBox.BBoxEdit"));
		button.addActionListener(this);
		param = null;
	}

	@Override
	public Object getCellEditorValue() {
		return param;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO
		System.out.println("Clicked BBoxEdit for "+param);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		param = (BBoxPseudoParameter) value;
		return button;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		return button;
	}
}


