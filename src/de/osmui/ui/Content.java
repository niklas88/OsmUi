package de.osmui.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;


import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;

import de.osmui.i18n.I18N;
import de.osmui.ui.models.TaskBoxTableModel;

public class Content extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5856610254528356675L;

	public Content() {
		


		
		JTabbedPane tabBox = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		JPanel taskTab = new JPanel();
		
//		final DefaultListModel listModel= new DefaultListModel();
//		JList taskBoxList = new JList(listModel);
		TaskBoxTableModel taskBoxTableModel = new TaskBoxTableModel();
		JTable taskBoxTable = new JTable(taskBoxTableModel);
		try {
			taskBoxTableModel.addTasks(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JScrollPane taskScrollPane = new JScrollPane(taskBoxTable);
        taskScrollPane.setPreferredSize(new Dimension(180, 600));
        taskTab.add(taskScrollPane);
        tabBox.add(I18N.getString("Content.tabBox"),taskTab); //$NON-NLS-1$
		
        
        JPanel parameterTab = new JPanel();
		parameterTab.add(new JTable());
		tabBox.add(I18N.getString("Content.pipelineBox"),parameterTab); //$NON-NLS-1$
		
		JScrollPane testete = new JScrollPane(tabBox);

		
		
		CopyBox copyBox = new CopyBox();
		 
		PipelineBox pipelineBox =new PipelineBox();
		JScrollPane pipelineBoxScrollPane = new JScrollPane(pipelineBox);

        // Creates the right split pane that contains the pipelineBox with the
        // pipeline's graph representation and the copyBox on the lower side of this split.
        JSplitPane right = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pipelineBoxScrollPane, copyBox);
        right.setDividerLocation(720);
        right.setResizeWeight(1);
        right.setDividerSize(6);
        right.setBorder(null);
		
		// Creates the main split pane that contains the right split pane and
        // the tabBox component on the left side of this split.
        JSplitPane content = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, testete, right);
        content.setOneTouchExpandable(true);
        content.setDividerLocation(200);
        content.setDividerSize(6);
        content.setBorder(null);
                   

        this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);
		
	}

}
