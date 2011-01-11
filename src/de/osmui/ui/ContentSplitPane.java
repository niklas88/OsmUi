package de.osmui.ui;


import javax.swing.JComponent;

import javax.swing.JSplitPane;

/**
 * @author Peter Vollmer
 * 
 */

public class ContentSplitPane extends JSplitPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5856610254528356675L;


	public ContentSplitPane(int orientation, JComponent rightComponent, JComponent leftComponent ) {
		this.setOrientation(orientation);
		this.setLeftComponent(rightComponent);
		this.setRightComponent(leftComponent);
		this.setOneTouchExpandable(true);
        this.setDividerSize(4);
        this.setBorder(null);
                   
		
	}
	

}
