package de.osmui.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ConfigurationFrame extends JFrame{

	public ConfigurationFrame() {
		this.setLayout(new BorderLayout());
		this.setLocation(x, y)
		JLabel osmosisPfadLabel = new JLabel("sss");
		JTextField osmosisPfadTextBox = new JTextField("ddd");
		JButton osmosisPfadButton = new JButton("fff");
		this.add(osmosisPfadLabel);
		this.add(osmosisPfadTextBox);
		this.add(osmosisPfadButton);
	}

}
