package de.osmui.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import de.osmui.i18n.I18N;
import de.osmui.ui.models.TaskBoxTableModel;

public class TabBox extends JTabbedPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2984123985661193020L;
	
	private static TabBox instance;
	
	// Prevents the creation of the object with other methods
	private TabBox() {

		this.setTabPlacement(JTabbedPane.TOP);
		this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		TaskBoxTableModel taskBoxTableModel = new TaskBoxTableModel();
		try {
			taskBoxTableModel.showCompatibleTasks("read-xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JTable taskBoxTable = new JTable(taskBoxTableModel);
		JPanel taskTab = new JPanel();
		taskTab.setLayout(new BorderLayout());

        JScrollPane taskScrollPane = new JScrollPane(taskBoxTable);
        taskTab.add(taskScrollPane,BorderLayout.CENTER);
        taskTab.add(new JButton("hinzufügen"),BorderLayout.SOUTH);
        
        this.add(I18N.getString("Content.tabBox"),taskTab); 
		
        
        JPanel parameterTab = new JPanel();
		parameterTab.add(new JTable());
		this.add(I18N.getString("Content.pipelineBox"),parameterTab); 	
	}
	
	
	// A access method on class level, which creates only once a instance a concrete object
	// of Content in a session of OsmUi and returns it.
	public static TabBox getInstance() {
		if (TabBox.instance == null) {
			TabBox.instance = new TabBox();
		}
		return TabBox.instance;
	}
}
