package de.osmui.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Application extends JFrame {
	

	public Application() {
		Menu menu = new Menu();
		this.setJMenuBar(menu);
		
		Content content = new Content();
		
		setLayout(new BorderLayout());
        /*
         * add(getToolbar(), BorderLayout.NORTH);
         */
        add(content, BorderLayout.CENTER);
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Application osmUi = new Application();
				osmUi.pack();
				osmUi.setSize(1024, 768);
				osmUi.setLocation(300, 200);
				osmUi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				osmUi.setVisible(true);
			}

		});
	}

}
