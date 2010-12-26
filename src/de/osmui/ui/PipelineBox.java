package de.osmui.ui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PipelineBox extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2865210986243818496L;

	public PipelineBox() {
		JLabel pipelineBox = new JLabel("pipelineBox");
        this.setLayout(new BorderLayout());
		this.add(pipelineBox, BorderLayout.CENTER);
	}

}
