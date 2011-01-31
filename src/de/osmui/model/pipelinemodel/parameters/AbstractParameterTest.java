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
 */

package de.osmui.model.pipelinemodel.parameters;

import static org.junit.Assert.*;

import org.junit.Test;

import de.osmui.model.osm.TParameter;


public class AbstractParameterTest {
	@Test public void getDescription(){
		TParameter desc = new TParameter();
		AbstractParameter param = new BooleanParameter(desc , "true");
		param.description = desc;
		assertEquals(desc, param.getDescription());
	}
}
