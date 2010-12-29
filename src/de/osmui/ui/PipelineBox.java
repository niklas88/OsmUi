package de.osmui.ui;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

public class PipelineBox extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2865210986243818496L;

	public PipelineBox() {
		JLabel pipelineBox = new JLabel("pipelineBox");
        this.setLayout(new ScrollPaneLayout());
		this.setViewportView(pipelineBox);
	}

}
