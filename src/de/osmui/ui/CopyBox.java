package de.osmui.ui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class CopyBox extends JPanel{

	public CopyBox() {
		JTextField copyBox = new JTextField("copybox");
		copyBox.setEditable(false);
        this.setLayout(new BorderLayout());
		this.add(copyBox, BorderLayout.CENTER);
	}

}
