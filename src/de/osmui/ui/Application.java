package de.osmui.ui;

import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.osmui.util.ConfigurationManager;

/**
 * @author Peter Vollmer
 * 
 *         Application main class that is used to initialize OsmUi and to start
 *         up the UI.
 * 
 *         will be tested by system-tests
 */

public class Application {

	/**
	 * Starts OsmUi
	 * 
	 * @param args
	 *            No runtime arguments are evaluated
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame osmUi = MainFrame.getInstance();
				osmUi.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				osmUi.setVisible(true);
			}

		});
	}

}
