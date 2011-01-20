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

package de.osmui.util;

import java.util.HashMap;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

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
