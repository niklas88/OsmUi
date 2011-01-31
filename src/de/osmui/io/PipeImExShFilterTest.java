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
 * @see PipeImExShFilter
*/
package de.osmui.io;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;


public class PipeImExShFilterTest {
	
	@Test public void accept(){
		File file = new File("name.sh");
		PipeImExShFilter filter = new PipeImExShFilter();
		boolean boole = filter.accept(file);
		assertEquals(true, boole);
		
		file = new File("name.bat");
		boole = filter.accept(file);
		assertEquals(false, boole);
		
		file = new File("name");
		boole = filter.accept(file);
		assertEquals(false, boole);
	}
	
	@Test public void getDescription(){
		PipeImExShFilter filter = new PipeImExShFilter();
		String test = filter.getDescription();
		assertEquals(".sh", test);
	}
}
