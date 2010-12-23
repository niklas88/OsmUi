package de.osmui.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.osmui.i18n.i18n;
import de.osmui.util.Util;

public class Menu extends JMenuBar {

	public Menu() {
		/*
		 * Menu "File"
		 */
		JMenu fileMenu = new JMenu(i18n.getString("Menu.File")); //$NON-NLS-1$
		/*
		 * Menu items of the menu "File"
		 */
		/*
		 * Load
		 */
		JMenuItem load = new JMenuItem(i18n.getString("Menu.load")); //$NON-NLS-1$
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("load"); //$NON-NLS-1$
			}
		});
		fileMenu.add(load);
		/*
		 * Save
		 */
		JMenuItem save = new JMenuItem(i18n.getString("Menu.save")); //$NON-NLS-1$
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("save"); //$NON-NLS-1$
			}
		});
		fileMenu.add(save);
		/*
		 * SaveAs
		 */
		JMenuItem saveAs = new JMenuItem(i18n.getString("Menu.saveAs")); //$NON-NLS-1$
		saveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("saveAs"); //$NON-NLS-1$
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
		JMenuItem importFile = new JMenuItem(i18n.getString("Menu.importFile")); //$NON-NLS-1$
		importFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("importFile"); //$NON-NLS-1$
			}
		});
		fileMenu.add(importFile);
		/*
		 * ImportClipBoard
		 */
		JMenuItem importClipBoard = new JMenuItem(i18n.getString("Menu.importClipBoard")); //$NON-NLS-1$
		importClipBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("importClipboard"); //$NON-NLS-1$
			}
		});
		fileMenu.add(importClipBoard);
		/*
		 * Export
		 */
		JMenuItem export = new JMenuItem(i18n.getString("Menu.export")); //$NON-NLS-1$
		export.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("export"); //$NON-NLS-1$
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
		JMenuItem close = new JMenuItem(i18n.getString("Menu.exit")); //$NON-NLS-1$
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Util.saveConfiguration();
			}
		});
		fileMenu.add(close);
		
		this.add(fileMenu);
		/*
		 * Menu "Edit"
		 */
		JMenu editMenu = new JMenu(i18n.getString("Menu.edit")); //$NON-NLS-1$
		/*
		 * Menu items of the menu "Edit"
		 */
		
		/*
		 * Redo
		 */
		JMenuItem redo = new JMenuItem(i18n.getString("Menu.undo")); //$NON-NLS-1$
		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("redo"); //$NON-NLS-1$
			}
		});
		editMenu.add(redo);
		/*
		 * Undo
		 */
		JMenuItem undo = new JMenuItem(i18n.getString("Menu.redo")); //$NON-NLS-1$
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(""); //$NON-NLS-1$
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
		JMenuItem options = new JMenuItem(i18n.getString("Menu.19")); //$NON-NLS-1$
		options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("options"); //$NON-NLS-1$
			}
		});
		editMenu.add(options);
		
		this.add(editMenu);
		/*
		 * Menu "Help"
		 */
		JMenu helpMenu = new JMenu(i18n.getString("Menu.help")); //$NON-NLS-1$
		/*
		 * Menu items of the menu "Help"
		 */
		
		/*
		 * Help
		 */
		JMenuItem help = new JMenuItem(i18n.getString("Menu.help")); //$NON-NLS-1$
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("help"); //$NON-NLS-1$
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
		JMenuItem about = new JMenuItem(i18n.getString("Menu.about")); //$NON-NLS-1$
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("about"); //$NON-NLS-1$
			}
		});
		helpMenu.add(about);
		
		this.add(helpMenu);
	}

}
