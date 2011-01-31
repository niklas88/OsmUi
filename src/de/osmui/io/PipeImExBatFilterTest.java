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
 * @see PipeImExBatFilter
*/
package de.osmui.io;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;



public class PipeImExBatFilterTest {
	@Test public void accept(){
		File file = new File("name.bat");
		PipeImExBatFilter filter = new PipeImExBatFilter();
		boolean boole = filter.accept(file);
		assertEquals(true, boole);
		
		file = new File("name.sh");
		boole = filter.accept(file);
		assertEquals(false, boole);
		
		file = new File("name");
		boole = filter.accept(file);
		assertEquals(false, boole);
	}
	
	@Test public void getDescription(){
		PipeImExBatFilter filter = new PipeImExBatFilter();
		String test = filter.getDescription();
		assertEquals(".bat", test);
	}
}
