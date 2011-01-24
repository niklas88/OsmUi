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
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Locale;

import javax.help.HelpSet;
import javax.help.JHelp;
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
import javax.swing.KeyStroke;

import de.osmui.i18n.I18N;
import de.osmui.io.IO;
import de.osmui.io.PipeImEx;
import de.osmui.io.PipeImExBatFilter;
import de.osmui.io.PipeImExShFilter;
import de.osmui.io.exceptions.ExportException;
import de.osmui.io.exceptions.LoadException;
import de.osmui.model.pipelinemodel.JGPipelineModel;
import de.osmui.util.exceptions.ImportException;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 * 
 *         Provides Menu to have an easy way to construct the whole Menu of
 *         Osmui.
 * 
 *         will be tested by system-tests
 * 
 */

public class Menu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6521196530844239528L;

	PipeImExShFilter pipeImExShFilter = new PipeImExShFilter();
	PipeImExBatFilter pipeImExBatFilter = new PipeImExBatFilter();

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
		 * New
		 */
		JMenuItem newPipe = new JMenuItem(I18N.getString("Menu.newPipe")); //$NON-NLS-1$
		newPipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!MainFrame.getInstance().getPipeModel().isEmpty()
						&& !MainFrame.getInstance().getSaved()) {
					int selectionOption = JOptionPane.showConfirmDialog(
							MainFrame.getInstance(),
							I18N.getString("Menu.notSavedNew"),
							I18N.getString("Menu.notSavedNewTitle"),
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (selectionOption == JOptionPane.CANCEL_OPTION) {
						return;
					} else if (selectionOption == JOptionPane.YES_NO_OPTION) {
						if (MainFrame.getInstance().save(
								MainFrame.getInstance().getSavePath())) {
							MainFrame.getInstance().savePath = "";
						} else {
							return;
						}
					}
				}
				MainFrame.getInstance().pipeModel.clean();
				MainFrame.getInstance().setSaved(true);
			}
		});
		fileMenu.add(newPipe);

		/*
		 * Load
		 */
		JMenuItem load = new JMenuItem(I18N.getString("Menu.load")); //$NON-NLS-1$
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!MainFrame.getInstance().getPipeModel().isEmpty()
						&& !MainFrame.getInstance().getSaved()) {
					int selectionOption = JOptionPane.showConfirmDialog(
							MainFrame.getInstance(),
							I18N.getString("Menu.notSavedNew"),
							I18N.getString("Menu.notSavedNewTitle"),
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (selectionOption == JOptionPane.CANCEL_OPTION) {
						return;
					} else if (selectionOption == JOptionPane.YES_NO_OPTION) {
						if (!MainFrame.getInstance().save(
								MainFrame.getInstance().getSavePath())) {
							return;
						}
					}
				}
				JFileChooser chooser = new JFileChooser();
				chooser.addChoosableFileFilter(MainFrame.getInstance().ioFilter);
				chooser.setFileFilter(MainFrame.getInstance().ioFilter);
				chooser.setAcceptAllFileFilterUsed(false);
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						JGPipelineModel loaded = IO.getInstance().load(
								chooser.getSelectedFile().getAbsolutePath());
						MainFrame.getInstance().pipeModel.setAll(loaded);
					} catch (LoadException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}

			}
		});
		fileMenu.add(load);
		/*
		 * Save
		 */
		JMenuItem save = new JMenuItem(I18N.getString("Menu.save")); //$NON-NLS-1$
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().save(
						MainFrame.getInstance().getSavePath());

			}
		});
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		fileMenu.add(save);
		/*
		 * SaveAs
		 */
		JMenuItem saveAs = new JMenuItem(I18N.getString("Menu.saveAs")); //$NON-NLS-1$
		saveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().save("");
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

				if (!MainFrame.getInstance().getPipeModel().isEmpty()) {
					if (JOptionPane.showConfirmDialog(MainFrame.getInstance(),
							I18N.getString("Menu.importAddToPipeline"),
							I18N.getString("Menu.importAddToPipelineTitle"),
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION) {
						if (!MainFrame.getInstance().getSaved()) {
							int selectionOption = JOptionPane.showConfirmDialog(
									MainFrame.getInstance(),
									I18N.getString("Menu.notSavedNew"),
									I18N.getString("Menu.notSavedNewTitle"),
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE);
							if (selectionOption == JOptionPane.CANCEL_OPTION) {
								return;
							} else if (selectionOption == JOptionPane.YES_OPTION) {
								if (MainFrame.getInstance().save(
										MainFrame.getInstance().getSavePath())) {
									MainFrame.getInstance().savePath = "";
								} else {
									return;
								}
							}
							MainFrame.getInstance().getPipeModel().clean();
						}
					}
				}
				JFileChooser chooser = new JFileChooser();
				chooser.addChoosableFileFilter(pipeImExBatFilter);
				chooser.addChoosableFileFilter(pipeImExShFilter);
				if (System.getProperty("os.name").contains("Windows")) {
					chooser.setFileFilter(pipeImExBatFilter);
				} else {
					chooser.setFileFilter(pipeImExShFilter);
				}
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						PipeImEx.getInstance().importOutOfFile(
								MainFrame.getInstance().pipeModel,
								chooser.getSelectedFile().getAbsolutePath());
					} catch (ImportException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					MainFrame.getInstance().pipeModel.layout(null);
					MainFrame.getInstance().saved = false;
				}

			}
		});
		fileMenu.add(importFile);
		/*
		 * ImportClipBoard
		 */
		JMenuItem importClipBoard = new JMenuItem(
				I18N.getString("Menu.importClipBoard")); //$NON-NLS-1$
		importClipBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!MainFrame.getInstance().getPipeModel().isEmpty()) {
					if (JOptionPane.showConfirmDialog(MainFrame.getInstance(),
							I18N.getString("Menu.importAddToPipeline"),
							I18N.getString("Menu.importAddToPipelineTitle"),
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION) {
						if (MainFrame.getInstance().getSaved()) {
							int selectionOption = JOptionPane.showConfirmDialog(
									MainFrame.getInstance(),
									I18N.getString("Menu.notSavedNew"),
									I18N.getString("Menu.notSavedNewTitle"),
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE);
							if (selectionOption == JOptionPane.CANCEL_OPTION) {
								return;
							} else if (selectionOption == JOptionPane.YES_NO_OPTION) {
								if (MainFrame.getInstance().save(
										MainFrame.getInstance().getSavePath())) {
									MainFrame.getInstance().savePath = "";
								} else {
									return;
								}
							}
						}
						MainFrame.getInstance().getPipeModel().clean();
					}
				}
				try {
					PipeImEx.getInstance().importClipBoard(
							MainFrame.getInstance().pipeModel,
							Toolkit.getDefaultToolkit().getSystemClipboard());
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (ImportException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				MainFrame.getInstance().pipeModel.layout(null);
				MainFrame.getInstance().saved = false;
			}
		});
		fileMenu.add(importClipBoard);
		/*
		 * Export
		 */
		JMenuItem export = new JMenuItem(I18N.getString("Menu.export")); //$NON-NLS-1$
		export.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!MainFrame.getInstance().pipeModel.isExecutable()) {

					if (JOptionPane.showConfirmDialog(MainFrame.getInstance(),
							I18N.getString("Menu.exportWarnQuestion"),
							I18N.getString("Menu.exportWarnQuestionTitle"),
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.WARNING_MESSAGE) == JOptionPane.CANCEL_OPTION) {
						return;
					}
				}
				JFileChooser chooser = new JFileChooser();
				chooser.addChoosableFileFilter(pipeImExShFilter);
				chooser.addChoosableFileFilter(pipeImExBatFilter);
				if (System.getProperty("os.name").contains("Windows")) {
					chooser.setFileFilter(pipeImExBatFilter);
				} else {
					chooser.setFileFilter(pipeImExShFilter);
				}
				chooser.setAcceptAllFileFilterUsed(false);
				int returnVal = chooser.showSaveDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					if (chooser.getSelectedFile().exists()) {
						if (JOptionPane.showConfirmDialog(MainFrame
								.getInstance(), I18N
								.getString("Menu.overwriteWarnQuestion"), I18N
								.getString("Menu.overwriteWarnQuestionTitle"),
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION) {
							return;
						}

					}
					try {
						PipeImEx.getInstance().export(
								MainFrame.getInstance().pipeModel,
								chooser.getSelectedFile().getAbsolutePath(),
								chooser.getFileFilter().getDescription());
					} catch (ExportException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
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
				MainFrame.getInstance().shutdown();
			}
		});
		close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
				ActionEvent.ALT_MASK));
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
		JMenuItem redo = new JMenuItem(I18N.getString("Menu.redo")); //$NON-NLS-1$
		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("redo"); //$NON-NLS-1$
			}
		});
		editMenu.add(redo);
		/*
		 * Undo
		 */
		JMenuItem undo = new JMenuItem(I18N.getString("Menu.undo")); //$NON-NLS-1$
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("undo"); //$NON-NLS-1$
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
		JMenuItem preferences = new JMenuItem(
				I18N.getString("Menu.preferences")); //$NON-NLS-1$
		preferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigurationDialog configurationDialog = new ConfigurationDialog();
				configurationDialog.setVisible(true);
			}
		});
		editMenu.add(preferences);

		this.add(editMenu);
		/*
		 * Menu "Layout"
		 */
		JMenu layoutMenu = new JMenu(I18N.getString("Menu.layout"));
		/*
		 * Menu items of the menu "Layout"
		 */

		/*
		 * automatic Layout
		 */
		JMenuItem layoutAutomatic = new JMenuItem(
				I18N.getString("Menu.layoutAutomatic")); //$NON-NLS-1$
		layoutAutomatic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().pipeModel.layout(null);
			}
		});
		layoutMenu.add(layoutAutomatic);

		this.add(layoutMenu);
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
					if (locale.getLanguage() == "de") {
						URL url = HelpSet.findHelpSet(cl, "jhelpset.hs");
						helpViewer = new JHelp(new HelpSet(cl, url));
						helpViewer.setCurrentID("Simple.Introduction");
					} else {
						URL url = HelpSet.findHelpSet(cl, "en.hs");
						helpViewer = new JHelp(new HelpSet(cl, url));
						helpViewer.setCurrentID("Simple.Introduction");
					}
				} catch (Exception f) {
					// System.err.println("API Help Set not found");
				}
				JFrame frame = new JFrame();
				frame.setTitle(I18N.getString("Menu.help"));
				frame.setSize(800, 600);
				frame.getContentPane().add(helpViewer);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
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

				Icon icon = new ImageIcon(getClass().getResource(
						"Logo_Osmui.png"));
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
