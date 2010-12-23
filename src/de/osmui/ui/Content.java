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
		

        // Creates the right split pane that contains the pipelineBox with the
        // pipeline's graph representation and the copyBox on the lower side of this split.
        JSplitPane right = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pipelineBox, copyBox);
        right.setDividerLocation(720);
        right.setResizeWeight(1);
        right.setDividerSize(6);
        right.setBorder(null);
		
		// Creates the main split pane that contains the right split pane and
        // the tabBox component on the left side of this split.
        JSplitPane content = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabBox, right);
        content.setOneTouchExpandable(true);
        content.setDividerLocation(200);
        content.setDividerSize(6);
        content.setBorder(null);
                   

        this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);
		
	}

}
