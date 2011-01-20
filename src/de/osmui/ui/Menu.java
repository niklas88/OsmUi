/*OsmUi is a user interface for Osmosis
    Copyright (C) 2011  Verena Käfer, Peter Vollmer, Niklas Schnelle

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or 
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package de.osmui.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Enumeration;
import java.util.Locale;

import javax.help.HelpSet;
import javax.help.JHelp;
import javax.help.JHelpIndexNavigator;
import javax.help.JHelpNavigator;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.osmui.util.exceptions.ImportException;
import de.osmui.i18n.I18N;
import de.osmui.io.PipeImEx;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 *
 * Provides Menu to have an easy way to construct the whole Menu of Osmui.
 *
 * will be tested by system-tests
 * 
 */

public class Menu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6521196530844239528L;

	/**
	 * Constructs the menu with all its entries of Osmui.
	 */
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
				
			    JFileChooser chooser = new JFileChooser();
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	try {
						PipeImEx.getInstance().importOutOfFile(MainFrame.getInstance().pipeModel,chooser.getSelectedFile().getAbsolutePath());
					} catch (ImportException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					MainFrame.getInstance().pipeModel.layout(null);
			    }

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
				try {
					PipeImEx.getInstance().importClipBoard(MainFrame.getInstance().pipeModel, Toolkit.getDefaultToolkit().getSystemClipboard());
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ImportException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainFrame.getInstance().pipeModel.layout(null);
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
				MainFrame.getInstance().writePrefs();
				MainFrame.getInstance().configurationManager.saveConfiguration();
				System.exit(0);
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
				JHelp helpViewer = null;
				try {
				      ClassLoader cl = Menu.class.getClassLoader();
				      Locale locale = Locale.getDefault();
				      if (locale.getLanguage() == "de"){
				    	  URL url = HelpSet.findHelpSet(cl, "jhelpset.hs");
					      helpViewer = new JHelp(new HelpSet(cl, url));
					      helpViewer.setCurrentID("Simple.Introduction");
				      }
				      else{
				    	  URL url = HelpSet.findHelpSet(cl, "en.hs");
					      helpViewer = new JHelp(new HelpSet(cl, url));
					      helpViewer.setCurrentID("Simple.Introduction");
				      }
				} catch (Exception f) {
				      System.err.println("API Help Set not found");
				}  
				JFrame frame = new JFrame();
				frame.setTitle(I18N.getString("Menu.help"));
				frame.setSize(800,600);
				frame.getContentPane().add(helpViewer);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
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
				JDialog frame = new JDialog();
				frame.setLayout(new BorderLayout());
				frame.setTitle(I18N.getString("Menu.about"));
				
				Icon icon = new ImageIcon(getClass().getResource("Logo_Osmui1.png"));
				JLabel bild = new JLabel(icon);
				frame.add(bild, BorderLayout.NORTH);
				
				JLabel test = new JLabel(I18N.getString("Menu.about_text"));
				frame.add(test, BorderLayout.CENTER);
				frame.setVisible(true);
				frame.pack();

			}
		});
		helpMenu.add(about);
		
		this.add(helpMenu);
	}

}
