package de.osmui.ui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import de.osmui.model.pipelinemodel.JGPipelineModel;
import de.osmui.ui.models.ParameterBoxTableModel;
import de.osmui.ui.models.TaskBoxTableModel;
import de.osmui.util.ConfigurationManager;

/**
 * @author Peter Vollmer
 * 
 *         Provides MainFrame to have a easy way to construct a MainFrame with
 *         all UI Content
 * 
 *         wird durch Systemtest abgedeckt
 */

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4767348652713972190L;

	private static MainFrame instance;

	protected TaskBoxTableModel taskBoxTableModel;

	protected TaskBox taskBox;

	protected ParameterBoxTableModel parameterBoxTableModel;

	protected ParameterBox parameterBox;

	protected ContentSplitPane rightContent;

	protected ContentSplitPane content;

	protected PipelineBox pipeBox;

	protected JGPipelineModel pipeModel;

	protected CopyBox copyBox;

	/**
	 * Constructs the mainframe
	 */
	private MainFrame() {
		pipeModel = new JGPipelineModel();
		pipeBox = new PipelineBox(pipeModel.getGraph());
		pipeModel.addObserver(pipeBox);

		taskBoxTableModel = new TaskBoxTableModel();
		taskBox = new TaskBox(taskBoxTableModel);

		copyBox = new CopyBox(pipeModel);
		pipeModel.addObserver(copyBox);

		parameterBoxTableModel = new ParameterBoxTableModel();
		parameterBox = new ParameterBox(parameterBoxTableModel);

		pipeBox.registerTaskSelectedListener(taskBox);
		pipeBox.registerTaskSelectedListener(parameterBox);

		rightContent = new ContentSplitPane(JSplitPane.VERTICAL_SPLIT, pipeBox,
				copyBox);

		content = new ContentSplitPane(JSplitPane.HORIZONTAL_SPLIT, new TabBox(
				taskBox, parameterBox), rightContent);

		Menu menu = new Menu();
		this.setJMenuBar(menu);

		setLayout(new BorderLayout());
		add(content, BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ConfigurationManager.saveConfiguration();
			}

		});

	}

	/**
	 * @return the ContentDeviderLocation
	 */
	public int getContentDeviderLocation() {
		return content.getDividerLocation();
	}

	/**
	 * @param ContentDividerLocation
	 *            the ContentDividerLocation to set
	 */
	public void setContentDevider(int contentDividerLocation) {
		content.setDividerLocation(contentDividerLocation);
	}

	/**
	 * @return the RightContentDeviderLocation
	 */
	public int getRightContentDeviderLocation() {
		return rightContent.getDividerLocation();
	}

	/**
	 * @param rightContentDividerLocation
	 *            the rightContentDividerLocation to set
	 */
	public void setRightContentDeviderLocation(int rightContentDividerLocation) {
		rightContent.setDividerLocation(rightContentDividerLocation);
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
	 * @return the taskBoxTableModel
	 */
	public TaskBoxTableModel getTaskBoxTableModel() {
		return taskBoxTableModel;
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
