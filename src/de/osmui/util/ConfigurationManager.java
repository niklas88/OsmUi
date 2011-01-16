package de.osmui.util;

import java.util.HashMap;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.JFrame;

import de.osmui.ui.MainFrame;

/**
 * @author Peter Vollmer
 * 
 */

public class ConfigurationManager {

	private static ConfigurationManager instance;

	private Preferences userPrefs;

	private Map<String, String> configEntries;

	private ConfigurationManager() {

		configEntries = new HashMap<String, String>();

	}

	public int getEntry(String key,int standardValue){
		if (configEntries.get(key) == null){ 
			return standardValue;
		}
		return Integer.parseInt(configEntries.get(key));
		
	}
	
	public String getEntry (String key, String standardValue){
		if (configEntries.get(key) == null){ 
			return standardValue;
		}
		return configEntries.get(key);
	}
	
	public void setEntry (String key, int value){
		configEntries.put(key, Integer.toString(value));
	}
	
	public void setEntry (String key, String value){
		configEntries.put(key, value);
	}

	public void saveConfiguration() {
		userPrefs = Preferences.userRoot().node("OsmUi");
		for (String currentEntry : configEntries.keySet()){
			userPrefs.put(currentEntry, configEntries.get(currentEntry));
		}
	}

	public void loadConfiguration() {
		userPrefs = Preferences.userRoot().node("OsmUi");
		try {
			for (String currentKey : userPrefs.keys()) {
				configEntries.put(currentKey, userPrefs.get(currentKey, currentKey));
			}
		} catch (BackingStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
