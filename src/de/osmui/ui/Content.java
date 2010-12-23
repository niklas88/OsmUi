package de.osmui.ui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class Content extends JPanel {

	public Content() {
		
		JTabbedPane tabBox = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
        JPanel taskTab = new JPanel();
		taskTab.add(new JList());
        tabBox.add("Tasks",taskTab);
		JPanel parameterTab = new JPanel();
		parameterTab.add(new JTable());
		tabBox.add("Parameter",parameterTab);
		

		
		CopyBox copyBox = new CopyBox();

		PipelineBox pipelineBox =new PipelineBox();
		

        // Creates the inner split pane that contains the library with the
        // palettes and the graph outline on the left side of the window
        JSplitPane right = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pipelineBox, copyBox);
        right.setDividerLocation(720);
        right.setResizeWeight(1);
        right.setDividerSize(6);
        right.setBorder(null);
		
		// Creates the outer split pane that contains the inner split pane and
        // the graph component on the right side of the window
        JSplitPane content = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabBox, right);
        content.setOneTouchExpandable(true);
        content.setDividerLocation(200);
        content.setDividerSize(6);
        content.setBorder(null);
                   

        this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);
		
	}

}
