package de.osmui.ui;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import de.osmui.i18n.I18N;

public class Content extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5856610254528356675L;

	public Content() {
		
		JTabbedPane tabBox = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
        JPanel taskTab = new JPanel();
		taskTab.add(new JList());
        tabBox.add(I18N.getString("Content.tabBox"),taskTab); //$NON-NLS-1$
		JPanel parameterTab = new JPanel();
		parameterTab.add(new JTable());
		tabBox.add(I18N.getString("Content.pipelineBox"),parameterTab); //$NON-NLS-1$
		

		
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
