package de.osmui.ui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CopyBox extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2196616949819474887L;

	public CopyBox() {
		JTextField copyBox = new JTextField("copybox");
		copyBox.setEditable(false);
        this.setLayout(new BorderLayout());
		this.add(copyBox, BorderLayout.CENTER);
	}

}
