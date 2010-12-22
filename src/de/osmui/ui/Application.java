package de.osmui.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;


public class Application extends JFrame {

	public Application() {
		Menu menu = new Menu();
		this.setJMenuBar(menu);
		
		JTabbedPane tabBox = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		JPanel taskTab = new JPanel();
		taskTab.add(new JLabel("Testen"));
		JPanel parameterTab = new JPanel();
		tabBox.add("Tasks",taskTab);
		tabBox.add("Parameter",parameterTab);
		FlowLayout mainLayout = new FlowLayout();
		mainLayout.addLayoutComponent("Test", tabBox);
		mainLayout.addLayoutComponent("klo", new JButton("test"));
		this.setLayout(mainLayout);
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
