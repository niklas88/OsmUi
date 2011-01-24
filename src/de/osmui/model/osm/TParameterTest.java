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

package de.osmui.model.osm;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 * 
 * @see TParameter
 * 
*/
public class TParameterTest {
	@Test public void getEnumVal(){
		List<TEnumValue> arr = new ArrayList<TEnumValue>();
		TParameter param = new TParameter();
		assertEquals("getEnumValue",param.getEnumValue(),arr);
	}
	
	@Test public void isRequir(){
		TParameter param = new TParameter();
		Boolean boole = null;
		boole = param.isRequired();
		assertEquals("required==null", boole, false);
		param.required = true;
		boole = param.isRequired();
		assertEquals("required==null", boole, true);
	}
	
	@Test public void setget(){
		TParameter param = new TParameter();
		param.setBooleanEncoding("value");
		param.setDefaultValue("value");
		param.setDescription("value");
		param.setFriendlyName("name");
		param.setName("name");
		param.setType("type");
		param.setRequired(true);
		param.setDefaultParameter(true);
		
		String enc = param.getBooleanEncoding();
		String defval = param.getDefaultValue();
		String desc = param.getDescription();
		String frname = param.getFriendlyName();
		String name = param.getName();
		String type = param.getType();
		Boolean requ = param.required;
		Boolean def = param.defaultParameter;
		
		assertEquals("value", enc);
		assertEquals("value", defval);
		assertEquals("value", desc);
		assertEquals("name", frname);
		assertEquals("name", name);
		assertEquals("type", type);
		assertEquals(true, requ);
		assertEquals(true, def);
	}
}
