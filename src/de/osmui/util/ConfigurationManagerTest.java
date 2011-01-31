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

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 * 
 * @see ConfigurationManager
 */
package de.osmui.util;

import static org.junit.Assert.*;

import org.junit.Test;


public class ConfigurationManagerTest {
	@Test public void saveLoad(){
		ConfigurationManager manager = ConfigurationManager.getInstance();
		manager.saveConfiguration();
		manager.loadConfiguration();
	}
	
	@Test public void getSet(){
		ConfigurationManager manager = ConfigurationManager.getInstance();
		manager.setEntry("key", 1);
		manager.setEntry("key", "1");
		
		String test = manager.getEntry("key", "1");
		int test1 = manager.getEntry("key", 1);
		String test2 = manager.getEntry("key1", "1");
		int test3 = manager.getEntry("key1", 1);
		
		assertEquals("1", test);
		assertEquals(1, test1);
		assertEquals("1", test2);
		assertEquals(1, test3);
	}
}
