package de.osmui.model.pipelinemodel.parameters;

import static org.junit.Assert.*;

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
* @see IntParameter
* 
*/
import org.junit.Test;

import de.osmui.model.osm.TParameter;


public class IntParameterTest {
@Test public void setGet(){
	TParameter desc = new TParameter();
	IntParameter param = new IntParameter(desc , "9");
	param.setValue("9");
	param.setValueInteger(9);
	param.name = "name";
	param.reference();
	
	assertEquals("9", param.getValue());
	assertEquals(9, param.getValueInteger());
	assertEquals("name=9", param.getCommandlineForm());
	assertEquals(true, param.isReferenced());
}
}
