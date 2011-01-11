package de.osmui.ui;

import javax.swing.SwingUtilities;

import de.osmui.util.ConfigurationManager;

/**
 * @author Peter Vollmer
 *
 * Application main class that is used to initialize OsmUi
 * and to start up the UI.
 * 
 * wird durch Systemtest abgedeckt
 */

public class Application {
	

	/**
	 * Starts OsmUi
	 * @param args No runtime arguments are evaluated
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame osmUi = MainFrame.getInstance();
				ConfigurationManager.loadConfiguration();
				osmUi.setVisible(true);
			}

		});
	}

}
