package de.osmui.ui;

import javax.swing.JSplitPane;

public class RightContent extends JSplitPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3474064077403281682L;
	
	private static RightContent instance;
	
	// Prevents the creation of the object with other methods
	private RightContent() {
        // Creates the right split pane that contains the pipelineBox with the
        // pipeline's graph representation and the copyBox on the lower side of this split.
		this.setOrientation(VERTICAL_SPLIT);
		this.setLeftComponent(new PipelineBox());
		this.setRightComponent(new CopyBox());
        this.setResizeWeight(1);
        this.setDividerSize(4);
        this.setBorder(null);
	}
	
	// A access method on class level, which creates only once a instance a concrete object
	// of Content in a session of OsmUi and returns it.
	public static RightContent getInstance() {
		if (RightContent.instance == null) {
			RightContent.instance = new RightContent();
		}
		return RightContent.instance;
	}
}
