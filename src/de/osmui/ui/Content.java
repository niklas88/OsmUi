package de.osmui.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


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
import de.osmui.util.ConfigurationManager;

public class Content extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5856610254528356675L;

	private static Content instance;

	// Prevents the creation of the object with other methods
	private Content() {
		


		
		JTabbedPane tabBox = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		JPanel taskTab = new JPanel();
		
//		final DefaultListModel listModel= new DefaultListModel();
//		JList taskBoxList = new JList(listModel);
		TaskBoxTableModel taskBoxTableModel = new TaskBoxTableModel();
		JTable taskBoxTable = new JTable(taskBoxTableModel);
		try {
			taskBoxTableModel.showCompatibleTasks("read-xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JScrollPane taskScrollPane = new JScrollPane(taskBoxTable);
        taskScrollPane.setPreferredSize(new Dimension(20, 600));
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
        content.setDividerLocation(220);
        content.setDividerSize(6);
        content.setBorder(null);
                   

        this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);
		
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
