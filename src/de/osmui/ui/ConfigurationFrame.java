package de.osmui.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConfigurationFrame extends JDialog{

	public ConfigurationFrame() {
		this.setLayout(new BorderLayout());
		JLabel osmosisPfadLabel = new JLabel("Pfad zu Osmosis:");
		add(osmosisPfadLabel,BorderLayout.NORTH);
		JTextField osmosisPfadTextBox = new JTextField("Osmosis");
		JButton osmosisPfadButton = new JButton("Browse");
		JPanel center = new JPanel();
		center.setLayout(new GridBagLayout());
		center.add(osmosisPfadLabel);
		center.add(osmosisPfadTextBox);
		center.add(osmosisPfadButton);
		add(center, BorderLayout.CENTER);
		JPanel bottom = new JPanel();
		JButton ok = new JButton("OK");
		JButton cancel = new JButton("Cancel");
		bottom.setLayout(new GridLayout());
		bottom.add(ok);
		bottom.add(cancel);
		add(bottom,BorderLayout.SOUTH);
	}

}
