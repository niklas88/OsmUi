package de.osmui.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


import de.osmui.util.Util;
import de.osmui.i18n.I18N;
public class Menu extends JMenuBar {

	public Menu() {
		/*
		 * Menu "File"
		 */
		JMenu fileMenu = new JMenu(I18N.getString("Menu.file")); //$NON-NLS-1$
		/*
		 * Menu items of the menu "File"
		 */
		/*
		 * Load
		 */
		JMenuItem load = new JMenuItem(I18N.getString("Menu.load")); //$NON-NLS-1$
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("load"); //$NON-NLS-1$
			}
		});
		fileMenu.add(load);
		/*
		 * Save
		 */
		JMenuItem save = new JMenuItem(I18N.getString("Menu.save")); //$NON-NLS-1$
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("save"); //$NON-NLS-1$
			}
		});
		fileMenu.add(save);
		/*
		 * SaveAs
		 */
		JMenuItem saveAs = new JMenuItem(I18N.getString("Menu.saveAs")); //$NON-NLS-1$
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
		JMenuItem importFile = new JMenuItem(I18N.getString("Menu.importFile")); //$NON-NLS-1$
		importFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("importFile"); //$NON-NLS-1$
			}
		});
		fileMenu.add(importFile);
		/*
		 * ImportClipBoard
		 */
		JMenuItem importClipBoard = new JMenuItem(I18N.getString("Menu.importClipBoard")); //$NON-NLS-1$
		importClipBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("importClipboard"); //$NON-NLS-1$
			}
		});
		fileMenu.add(importClipBoard);
		/*
		 * Export
		 */
		JMenuItem export = new JMenuItem(I18N.getString("Menu.export")); //$NON-NLS-1$
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
		JMenuItem close = new JMenuItem(I18N.getString("Menu.exit")); //$NON-NLS-1$
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
		JMenu editMenu = new JMenu(I18N.getString("Menu.edit")); //$NON-NLS-1$
		/*
		 * Menu items of the menu "Edit"
		 */
		
		/*
		 * Redo
		 */
		JMenuItem redo = new JMenuItem(I18N.getString("Menu.undo")); //$NON-NLS-1$
		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("redo"); //$NON-NLS-1$
			}
		});
		editMenu.add(redo);
		/*
		 * Undo
		 */
		JMenuItem undo = new JMenuItem(I18N.getString("Menu.redo")); //$NON-NLS-1$
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
		JMenuItem preferences = new JMenuItem(I18N.getString("Menu.preferences")); //$NON-NLS-1$
		preferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("preferences"); //$NON-NLS-1$
			}
		});
		editMenu.add(preferences);
		
		this.add(editMenu);
		/*
		 * Menu "Help"
		 */
		JMenu helpMenu = new JMenu(I18N.getString("Menu.help")); //$NON-NLS-1$
		/*
		 * Menu items of the menu "Help"
		 */
		
		/*
		 * Help
		 */
		JMenuItem help = new JMenuItem(I18N.getString("Menu.help")); //$NON-NLS-1$
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
		JMenuItem about = new JMenuItem(I18N.getString("Menu.about")); //$NON-NLS-1$
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("about"); //$NON-NLS-1$
			}
		});
		helpMenu.add(about);
		
		this.add(helpMenu);
	}

}
