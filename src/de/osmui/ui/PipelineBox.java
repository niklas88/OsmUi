package de.osmui.ui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PipelineBox extends JPanel {

	public PipelineBox() {
		JLabel pipelineBox = new JLabel("pipelineBox");
        this.setLayout(new BorderLayout());
		this.add(pipelineBox, BorderLayout.CENTER);
	}

}
