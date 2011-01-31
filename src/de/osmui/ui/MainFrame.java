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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import de.osmui.i18n.I18N;
import de.osmui.io.IO;
import de.osmui.io.IOFilter;
import de.osmui.io.exceptions.SaveException;
import de.osmui.model.pipelinemodel.JGPipelineModel;
import de.osmui.ui.models.ParameterBoxTableModel;
import de.osmui.ui.models.TaskBoxTableModel;
import de.osmui.util.ConfigurationManager;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena Käfer
 * 
 *         Provides MainFrame to have a easy way to construct a MainFrame with
 *         all UI Content
 * 
 *         will be tested by system-tests
 * 
 */

public class MainFrame extends JFrame implements Observer {

	private static final long serialVersionUID = -4767348652713972190L;

	private static MainFrame instance;

	private IOFilter ioFilter;



	private ConfigurationManager configurationManager;

	private TaskBox taskBox;

	private ParameterBox parameterBox;

	private PipelineBox pipeBox;

	private JGPipelineModel pipeModel;

	private CopyBox copyBox;
	
	private ContentSplitPane content;
	
	private ContentSplitPane rightContent;

	private boolean saved;

	private String savePath;

	/**
	 * Constructs the mainframe
	 */
	private MainFrame() {

		this.setTitle("OsmUi");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		configurationManager = ConfigurationManager.getInstance();
		configurationManager.loadConfiguration();
		this.setSize(configurationManager.getEntry("MainFrameWidth", 800),
				configurationManager.getEntry("MainFrameHeight", 600));
		this.setLocation(
				configurationManager.getEntry("MainFrameXLocation", 100),
				configurationManager.getEntry("MainFrameYLocation", 100));
		pipeModel = new JGPipelineModel();
		pipeModel.addObserver(this);
		pipeBox = new PipelineBox(pipeModel.getGraph());
		pipeModel.addObserver(pipeBox);

		TaskBoxTableModel taskBoxTableModel = new TaskBoxTableModel();
		taskBox = new TaskBox(taskBoxTableModel);

		copyBox = new CopyBox(pipeModel);
		pipeModel.addObserver(copyBox);

		ParameterBoxTableModel parameterBoxTableModel = new ParameterBoxTableModel();
		parameterBox = new ParameterBox(parameterBoxTableModel, copyBox);

		pipeBox.registerTaskSelectedListener(taskBox);
		pipeBox.registerTaskSelectedListener(parameterBox);

		rightContent = new ContentSplitPane(JSplitPane.VERTICAL_SPLIT, pipeBox,
				copyBox);
		rightContent.setDividerLocation(configurationManager.getEntry(
				"RightContentDividerLocation", 620));

		content = new ContentSplitPane(JSplitPane.HORIZONTAL_SPLIT, new TabBox(
				taskBox, parameterBox), rightContent);
		content.setDividerLocation(configurationManager.getEntry(
				"ContentDividerLocation", 220));

		Menu menu = new Menu();
		this.setJMenuBar(menu);

		ioFilter = new IOFilter();
		saved = true;
		savePath = "";

		setLayout(new BorderLayout());
		add(content, BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				shutdown();

			}

		});

	}

	public void shutdown() {
		if (!pipeModel.isEmpty() && !saved) {
			int selectionOption = JOptionPane.showConfirmDialog(this,
					I18N.getString("Menu.notSavedNew"),
					I18N.getString("Menu.notSavedNewTitle"),
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (selectionOption == JOptionPane.CANCEL_OPTION) {
				return;
			} else if (selectionOption == JOptionPane.YES_NO_OPTION) {
				if (save(savePath)) {
					savePath = "";
				} else {
					return;
				}
			}
		}
		configurationManager.setEntry("MainFrameWidth", this.getSize().width);
		configurationManager.setEntry("MainFrameHeight", this.getSize().height);
		configurationManager.setEntry("MainFrameXLocation",
				this.getLocation().x);
		configurationManager.setEntry("MainFrameYLocation",
				this.getLocation().y);
		configurationManager.setEntry("ContentDividerLocation",
				content.getDividerLocation());
		configurationManager.setEntry("RightContentDividerLocation",
				rightContent.getDividerLocation());
		configurationManager.saveConfiguration();
		System.exit(0);
	}

	protected boolean save(String systemSavePath) {
		String savePath = systemSavePath;
		String extension = "";
		if (savePath == "") {
			JFileChooser chooser = new JFileChooser();
			chooser.addChoosableFileFilter(ioFilter);
			chooser.setFileFilter(ioFilter);
			chooser.setAcceptAllFileFilterUsed(false);
			int returnVal = chooser.showSaveDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				if (chooser.getSelectedFile().exists()) {
					if (JOptionPane.showConfirmDialog(MainFrame.getInstance(),
							I18N.getString("Menu.overwriteWarnQuestion"),
							I18N.getString("Menu.overwriteWarnQuestionTitle"),
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION) {
						return false;
					}
				}
				savePath = chooser.getSelectedFile().getAbsolutePath();
				extension = chooser.getFileFilter().getDescription();
			} else {
				return false;
			}
		}
		try {
			IO.getInstance().save(MainFrame.getInstance().pipeModel, savePath,
					extension);
		} catch (SaveException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		MainFrame.getInstance().savePath = savePath;
		MainFrame.getInstance().setSaved(true);
		return true;
	}

	/**
	 * @return the pipeModel.
	 */
	public JGPipelineModel getPipeModel() {
		return pipeModel;
	}

	/**
	 * @return the taskBox
	 */
	public TaskBox getTaskBox() {
		return taskBox;
	}


	/**
	 * @return the parameterBox
	 */
	public ParameterBox getParameterBox() {
		return parameterBox;
	}

	/**
	 * @return the taskPipeBox
	 */
	public PipelineBox getPipeBox() {
		return pipeBox;
	}

	/**
	 * @return the copyBox
	 */
	public CopyBox getCopyBox() {
		return copyBox;
	}

	/**
	 * @return the pipeModel
	 */
	public boolean getSaved() {
		return saved;
	}

	/**
	 * @param saved
	 *            to set saved to
	 */
	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	/**
	 * @return the savePath of the Pipe null if pipeline was never saved.
	 */
	public String getSavePath() {
		return savePath;
	}

	/**
	 * Gets the ioFilter used by MainFrame
	 * 
	 * @return the ioFilter
	 */
	public IOFilter getIoFilter() {
		return ioFilter;
	}
	
	/**
	 * @param savePath
	 *            the savePath has to set.
	 */
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		saved = false;
	}

	// A access method on class level, which creates only once a instance a
	// concrete object
	// in a session of OsmUi and returns it.
	/**
	 * @return a instance of MainFrame
	 * 
	 */
	public static MainFrame getInstance() {
		if (MainFrame.instance == null) {
			MainFrame.instance = new MainFrame();
		}
		return MainFrame.instance;
	}

}
