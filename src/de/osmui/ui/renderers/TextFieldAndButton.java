/**
 * 
 */
package de.osmui.ui.renderers;


import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Niklas Schnelle
 *
 */
public class TextFieldAndButton extends JPanel {
	
	private static final long serialVersionUID = -739312461612898792L;
	private JTextField textField;
	private JButton button;
	
	/**
	 * 
	 */
	public TextFieldAndButton() {
		textField = new JTextField();
		button = new JButton();
		button.setBorderPainted(false);
		this.setLayout(new BorderLayout());
		this.add(textField, BorderLayout.CENTER);
		this.add(button, BorderLayout.EAST);
	}
	
	public JTextField getTextField() {
		return textField;
	}
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	public JButton getButton() {
		return button;
	}
	public void setButton(JButton button) {
		this.button = button;
	}





}
