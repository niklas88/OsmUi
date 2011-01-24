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

package de.osmui.model.pipelinemodel.parameters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.osmui.model.osm.TParameter;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 * 
 * @see BooleanParameter
 * 
*/
public class BooleanParameterTest {
	@Test public void setgetValue(){
		TParameter desc = new TParameter();
		desc.setName("name");
		BooleanParameter param = new BooleanParameter(desc, "true");
		String s = "true";
		param.setValue(s);
		s = param.getValue();
		assertEquals("s==true", "yes", s);
		
		s = "false";
		param.setValue(s);
		s = param.getValue();
		assertEquals("s==false", "no", s);
		
		s = "yes";
		param.setValue(s);
		s = param.getValue();
		assertEquals("s==yes", s, "yes");
		
		s = "no";
		param.setValue(s);
		s = param.getValue();
		assertEquals("s==no", s, "no");
	}
	
	@Test public void isDefaultValue(){
		String s;
		TParameter desc = new TParameter();
		desc.setName("name");
		BooleanParameter param = new BooleanParameter(desc, "yes");
		s = "yes";
		param.description.setDefaultValue(s);
		assertEquals(param.getDefaultValue(), s);
		s = "no";
		param.description.setDefaultValue(s);
		assertEquals(param.getDefaultValue(), s);
		s = "true";
		param.description.setDefaultValue(s);
		assertEquals(param.getDefaultValue(), s);
		s = "false";
		param.description.setDefaultValue(s);
		assertEquals(param.getDefaultValue(), s);
	}
}