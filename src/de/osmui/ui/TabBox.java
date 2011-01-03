package de.osmui.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import de.osmui.i18n.I18N;


public class TabBox extends JTabbedPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2984123985661193020L;
	
	private final TaskBox taskBox;
	
	public TabBox(TaskBox tb) {
		this.taskBox = tb;
		this.setTabPlacement(JTabbedPane.TOP);
		this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
	
		
		JPanel taskTab = new JPanel();
		taskTab.setLayout(new BorderLayout());

        JScrollPane taskScrollPane = new JScrollPane(taskBox);
        taskTab.add(taskScrollPane,BorderLayout.CENTER);
        JButton addButton = new JButton("hinzufügen");
        addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				taskBox.addSelectedToModel();
				
			}
		});
        taskTab.add(addButton,BorderLayout.SOUTH);
        
        
        this.add(I18N.getString("Content.tabBox"),taskTab); 
		
        
        JPanel parameterTab = new JPanel();
		parameterTab.add(new JTable());
		this.add(I18N.getString("Content.pipelineBox"),parameterTab); 	
	}
	
}
