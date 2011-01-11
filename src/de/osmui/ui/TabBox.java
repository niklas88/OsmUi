package de.osmui.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import de.osmui.i18n.I18N;

/**
 * @author Peter Vollmer
 * 
 *  will be tested by system-tests
 */

public class TabBox extends JTabbedPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2984123985661193020L;
	
	private final TaskBox taskBox;
	private final ParameterBox paramBox;
	
	public TabBox(TaskBox tb, ParameterBox pb) {
		this.taskBox = tb;
		this.paramBox = pb;
		this.setTabPlacement(JTabbedPane.TOP);
		this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		JPanel taskTab = new JPanel();
		taskTab.setLayout(new BorderLayout());

        JScrollPane taskScrollPane = new JScrollPane(taskBox);
        JScrollPane paramScrollPane = new JScrollPane(paramBox);
        
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
		
       
		this.add(I18N.getString("Content.pipelineBox"),paramScrollPane); 	
	}
	
}
