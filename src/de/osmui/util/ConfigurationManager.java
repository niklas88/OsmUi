package de.osmui.util;


import java.util.prefs.Preferences;
import javax.swing.JFrame;


import de.osmui.ui.MainFrame;

/**
 * @author Peter Vollmer
 * 
 */

public class ConfigurationManager {
	
	private static ConfigurationManager instance;
	
	protected Preferences userPrefs = Preferences.userRoot().node("OsmUi");
	
	private ConfigurationManager(){
		
	}
	
	public void saveConfiguration(){
		//MainFrame
		int mainFrameWidth = MainFrame.getInstance().getSize().width;
		int mainFrameHeight = MainFrame.getInstance().getSize().height;
		int mainFrameXLocation = MainFrame.getInstance().getLocation().x;
		int mainFrameYLocation = MainFrame.getInstance().getLocation().y;
		
		
		//Content
		int contentDividerLocation = MainFrame.getInstance().getContentDeviderLocation();
		//RightSplitPane
		int rightContentDividerLocation = MainFrame.getInstance().getRightContentDeviderLocation();
		
		
		userPrefs.putInt("MainFrameWidth", mainFrameWidth);
		userPrefs.putInt("MainFrameHeight", mainFrameHeight);
		userPrefs.putInt("MainFrameXLocation", mainFrameXLocation);
		userPrefs.putInt("MainFrameYLocation", mainFrameYLocation);
		//Content
		userPrefs.putInt("ContentDividerLocation", contentDividerLocation);
		//RightSplitPane
		userPrefs.putInt("RightContentDividerLocation", rightContentDividerLocation);
		
		System.exit(0);
	}
	public  void loadConfiguration() {

		
		Preferences root = Preferences.userRoot();
		Preferences userPrefs = root.node("OsmUi");
		
		int mainFrameWidth = userPrefs.getInt("MainFrameWidth", 800);
		int mainFrameHeight = userPrefs.getInt("MainFrameHeight", 600);
		
		int mainFrameXLocation = userPrefs.getInt("MainFrameXLocation", 100);
		int mainFrameYLocation = userPrefs.getInt("MainFrameYLocation", 100);
		
		
		//Content
		int contentDividerLocation = userPrefs.getInt("ContentDividerLocation", 220);
		//RightSplitPane
		int rightContentDividerLocation = userPrefs.getInt("RightContentDividerLocation", 620);
		
		MainFrame.getInstance().pack();
		MainFrame.getInstance().setSize(mainFrameWidth, mainFrameHeight);
		MainFrame.getInstance().setLocation(mainFrameXLocation, mainFrameYLocation);
		MainFrame.getInstance().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//Content
		MainFrame.getInstance().setContentDevider(contentDividerLocation);
		//RightSplitPane
		MainFrame.getInstance().setRightContentDeviderLocation(rightContentDividerLocation);
		
	}
	/**
	 * @return a instance of MainFrame
	 * 
	 */
	public static ConfigurationManager getInstance() {
		if (ConfigurationManager.instance == null) {
			ConfigurationManager.instance = new ConfigurationManager();
		}
		return ConfigurationManager.instance;
	}
}
