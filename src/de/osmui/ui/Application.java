package de.osmui.ui;

import javax.swing.SwingUtilities;

public class Application {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame osmUi = MainFrame.getInstance();
				osmUi.setVisible(true);
			}

		});
	}

}
