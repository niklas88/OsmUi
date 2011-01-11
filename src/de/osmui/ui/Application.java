package de.osmui.ui;

import javax.swing.SwingUtilities;

import de.osmui.util.ConfigurationManager;

/**
 * @author Peter Vollmer
 * 
 * wird durch Systemtest abgedeckt
 */

public class Application {
	

	/**
	 * @param args
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
