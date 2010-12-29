package de.osmui.ui;

import javax.swing.JSplitPane;

public class Content extends JSplitPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5856610254528356675L;

	private static Content instance;

	// Prevents the creation of the object with other methods
	private Content() {
		
		// Creates the main split pane that contains the right split pane and
        // the tabBox component on the left side of this split.
		this.setOrientation(HORIZONTAL_SPLIT);
		this.setLeftComponent(TabBox.getInstance());
		this.setRightComponent(RightContent.getInstance());
        this.setOneTouchExpandable(true);
        this.setDividerSize(4);
        this.setBorder(null);
                   
		
	}
	

	// A access method on class level, which creates only once a instance a concrete object
	// of Content in a session of OsmUi and returns it.
	public static Content getInstance() {
		if (Content.instance == null) {
			Content.instance = new Content();
		}
		return Content.instance;
	}
}
