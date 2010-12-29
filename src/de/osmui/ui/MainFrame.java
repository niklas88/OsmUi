package de.osmui.ui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import de.osmui.util.ConfigurationManager;


public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4767348652713972190L;
	
	private static MainFrame instance;
	
    // Creates the right split pane that contains the pipelineBox with the
    // pipeline's graph representation and the copyBox on the lower side of this split.
	static ContentSplitPane rightContent = new ContentSplitPane(JSplitPane.VERTICAL_SPLIT, new PipelineBox(), new CopyBox());
	// Creates the main split pane that contains the right split pane and
    // the tabBox component on the left side of this split.
	static ContentSplitPane content = new ContentSplitPane(JSplitPane.HORIZONTAL_SPLIT,TabBox.getInstance(),rightContent);

	// Prevents the creation of the object with other methods
	private MainFrame() {
		Menu menu = new Menu();
		this.setJMenuBar(menu);
		

		setLayout(new BorderLayout());
		add(content, BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ConfigurationManager.saveConfiguration();
			}

		});
		

	}
	
	public static ContentSplitPane getContent(){
		return content;
	}
	
	
	public static ContentSplitPane getRightContent(){
		return rightContent;
	}
	
	// A accessmethod on class level, which creates only once a instance a concrete object
	// in a session of OsmUi and returns it.
	public static MainFrame getInstance() {
		if (MainFrame.instance == null) {
			MainFrame.instance = new MainFrame();
		}
		return MainFrame.instance;
	}

}
