package de.osmui.ui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import de.osmui.util.Util;

public class MainFrame extends JFrame {

	private static MainFrame instance;

	// Verhindere die Erzeugung des Objektes über andere Methoden
	private MainFrame() {
		System.out.println("1");
		Menu menu = new Menu();
		this.setJMenuBar(menu);
		System.out.println("2");
		Content content = new Content();
		System.out.println("3");
		setLayout(new BorderLayout());
		/*
		 * add(getToolbar(), BorderLayout.NORTH);
		 */
		add(content, BorderLayout.CENTER);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Util.saveConfiguration();
			}

		});
	}

	// Eine Zugriffsmethode auf Klassenebene, welches dir '''einmal''' ein
	// konkretes
	// Objekt erzeugt und dieses zurückliefert.
	public static MainFrame getInstance() {
		if (MainFrame.instance == null) {
			MainFrame.instance = new MainFrame();
		}
		return MainFrame.instance;
	}

}
