package de.osmui.ui;

import javax.swing.SwingUtilities;

import de.osmui.util.Util;

public class Application {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame osmUi = MainFrame.getInstance();
				Util.loadConfiguration();
				osmUi.setVisible(true);
			}

		});
	}

}
