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
 *         will be tested by system-tests
 */

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4767348652713972190L;

	private static MainFrame instance;

	protected ConfigurationManager configurationManager;

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
		pipeBox = new PipelineBox(pipeModel.getGraph());
		pipeModel.addObserver(pipeBox);

		taskBoxTableModel = new TaskBoxTableModel();
		taskBox = new TaskBox(taskBoxTableModel);

		copyBox = new CopyBox(pipeModel);
		pipeModel.addObserver(copyBox);

		parameterBoxTableModel = new ParameterBoxTableModel();
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

		setLayout(new BorderLayout());
		add(content, BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
				writePrefs();				
				configurationManager.saveConfiguration();
				System.exit(0);
			}

		});

	}
	
	public void writePrefs(){
		configurationManager.setEntry("MainFrameWidth", this.getSize().width);
		configurationManager.setEntry("MainFrameHeight", this.getSize().height);
		configurationManager.setEntry("MainFrameXLocation", this.getLocation().x);
		configurationManager.setEntry("MainFrameYLocation", this.getLocation().y);
		configurationManager.setEntry("ContentDividerLocation", content.getDividerLocation());
		configurationManager.setEntry("RightContentDividerLocation", rightContent.getDividerLocation());
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

	/**
	 * @return the taskBoxTableModel
	 */
	public ConfigurationManager getConfigurationManager() {
		return configurationManager;
	}
	
	public ParameterBox getParameterBox() {
		return parameterBox;
	}

	public PipelineBox getPipeBox() {
		return pipeBox;
	}

	public CopyBox getCopyBox() {
		return copyBox;
	}

	public void setPipeModel(JGPipelineModel pipeModel) {
		this.pipeModel = pipeModel;
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
