package de.osmui.util;

import java.awt.Toolkit;
import java.util.prefs.Preferences;

import javax.swing.JFrame;

import de.osmui.ui.Application;

public class Util {
	public static void saveConfiguration(Application osmUi){

		int mainFrameWidth = osmUi.getSize().width;
		int mainFrameHeigth = osmUi.getSize().height;
		int mainFrameXLocation = osmUi.getLocation().x;
		int mainFrameYLocation = osmUi.getLocation().y;
		
		Preferences root = Preferences.userRoot();
		Preferences userPrefs = root.node("OsmUi");
		userPrefs.putInt("MainFrameWidth", mainFrameWidth);
		userPrefs.putInt("MainFrameHeight", mainFrameHeigth);
		userPrefs.putInt("MainFrameXLocation", mainFrameXLocation);
		userPrefs.putInt("MainFrameYLocation", mainFrameYLocation);
		
		System.exit(0);
	}
	public static void loadConfiguration(Application osmUi) {

		
		Preferences root = Preferences.userRoot();
		Preferences userPrefs = root.node("OsmUi");
		
		int mainFrameWidth = userPrefs.getInt("FrameWidth", 800);
		int mainFrameHeight = userPrefs.getInt("FrameHeight", 600);
		
		int mainFrameXLocation = userPrefs.getInt("MainFrameXLocation", 100);
		int mainFrameYLocation = userPrefs.getInt("MainFrameYLocation", 100);
		
		osmUi.pack();
		osmUi.setSize(mainFrameWidth, mainFrameHeight);
		osmUi.setLocation(mainFrameXLocation, mainFrameYLocation);
		osmUi.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		
	}
}
