package de.osmui.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar {

	public Menu() {
		/*
		 * Menu "File"
		 */
		JMenu fileMenu = new JMenu("Datei");
		/*
		 * Menu items of the menu "File"
		 */
		
		/*
		 * Load
		 */
		JMenuItem load = new JMenuItem("Laden");
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("load");
			}
		});
		fileMenu.add(load);
		/*
		 * Save
		 */
		JMenuItem save = new JMenuItem("Speichern");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("save");
			}
		});
		fileMenu.add(save);
		/*
		 * SaveAs
		 */
		JMenuItem saveAs = new JMenuItem("Speichern unter ...");
		saveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("saveAs");
			}
		});
		fileMenu.add(saveAs);
		/*
		 * Separator
		 */
		fileMenu.addSeparator();
		/*
		 * ImportFile
		 */
		JMenuItem importFile = new JMenuItem("Importieren");
		importFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("importFile");
			}
		});
		fileMenu.add(importFile);
		/*
		 * ImportClipBoard
		 */
		JMenuItem importClipBoard = new JMenuItem("Importieren");
		importClipBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("importClipboard");
			}
		});
		fileMenu.add(importClipBoard);
		/*
		 * Export
		 */
		JMenuItem export = new JMenuItem("Exportieren");
		export.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("export");
			}
		});
		fileMenu.add(export);
		/*
		 * Separator
		 */
		fileMenu.addSeparator();
		/*
		 * Close
		 */
		JMenuItem close = new JMenuItem("OsmUi beenden");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		});
		fileMenu.add(close);
		
		this.add(fileMenu);
		/*
		 * Menu "Edit"
		 */
		JMenu editMenu = new JMenu("Bearbeiten");
		/*
		 * Menu items of the menu "Edit"
		 */
		
		/*
		 * Redo
		 */
		JMenuItem redo = new JMenuItem("Rückgängig");
		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("redo");
			}
		});
		editMenu.add(redo);
		/*
		 * Undo
		 */
		JMenuItem undo = new JMenuItem("wiederherstellen");
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("undo");
			}
		});
		editMenu.add(undo);
		/*
		 * Separator
		 */
		editMenu.addSeparator();
		/*
		 * Options
		 */
		JMenuItem options = new JMenuItem("Einstellungen");
		options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("options");
			}
		});
		editMenu.add(options);
		
		this.add(editMenu);
		/*
		 * Menu "Help"
		 */
		JMenu helpMenu = new JMenu("Hilfe");
		/*
		 * Menu items of the menu "Help"
		 */
		
		/*
		 * Help
		 */
		JMenuItem help = new JMenuItem("Hilfe");
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("help");
			}
		});
		helpMenu.add(help);
		/*
		 * Separator
		 */
		helpMenu.addSeparator();
		/*
		 * About
		 */
		JMenuItem about = new JMenuItem("Über OsmUi");
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("about");
			}
		});
		helpMenu.add(about);
		
		this.add(helpMenu);
	}

}
