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
 * @author Niklas Schnelle, Peter Vollmer, Verena Käfer
 * 
 * @see EnumParameter
 * 
 */
package de.osmui.model.pipelinemodel.parameters;

import static org.junit.Assert.*;

import org.junit.Test;

import de.osmui.model.osm.TEnumValue;
import de.osmui.model.osm.TParameter;


public class EnumParameterTest {
	@Test public void setGet(){
		TParameter desc = new TParameter();
		TEnumValue arg0 = new TEnumValue();
		arg0.setValue("value");
		desc.getEnumValue().add(arg0);
		EnumParameter param = new EnumParameter(desc , "value");
		param.setValue("value");
		param.getEnumerationValues().add(arg0);
		param.name = "name";
		
		assertEquals("value", param.getValue());
		
		assertEquals("", param.getDefaultValue());
		
		assertEquals("name=value", param.getCommandlineForm());
	}
}
