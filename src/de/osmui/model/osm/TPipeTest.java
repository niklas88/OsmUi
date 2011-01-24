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
 * @author verenaNiklas Schnelle, Peter Vollmer, Verena käfer
 * 
 * @see TPipe
 * 
*/

package de.osmui.model.osm;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class TPipeTest {
	@Test public void setget(){
		TPipe pipe = new TPipe();
		pipe.setCount("value");
		pipe.setDescription("value");
		pipe.setSpecifiedBy("value");
		pipe.setType("type");
		BigInteger value = new BigInteger("10");
		pipe.setIndex(value);
		
		String count = pipe.getCount();
		String desc = pipe.getDescription();
		String spez = pipe.specifiedBy;
		String type = pipe.getType();
		BigInteger big = pipe.getIndex();
		
		assertEquals("value", count);
		assertEquals("value", desc);
		assertEquals("value", spez);
		assertEquals("type", type);
		assertEquals("10", big.toString());
	}
}
