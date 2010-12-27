package de.osmui.ui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import de.osmui.util.ConfigurationManager;


public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4767348652713972190L;
	
	private static MainFrame instance;

	// Prevents the creation of the object with other methods
	private MainFrame() {
		Menu menu = new Menu();
		this.setJMenuBar(menu);
		Content content = new Content();
		setLayout(new BorderLayout());
		add(content, BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ConfigurationManager.saveConfiguration();
			}

		});
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
