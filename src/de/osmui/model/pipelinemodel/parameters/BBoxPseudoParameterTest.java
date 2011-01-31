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
 * @see BBoxPseudoParameter
 * 
 */
package de.osmui.model.pipelinemodel.parameters;

import static org.junit.Assert.*;
import org.junit.Test;

import de.osmui.model.osm.TParameter;
import de.osmui.model.pipelinemodel.AbstractTask;
import de.osmui.model.pipelinemodel.CommonTask;
import de.unistuttgart.iev.osm.bboxchooser.Bounds;


public class BBoxPseudoParameterTest {
	@Test public void setGet(){
		AbstractTask parent = new CommonTask("name");
		TParameter desc = new TParameter();
		BBoxPseudoParameter param = new BBoxPseudoParameter(desc , "value", parent , true);
		param.setValue("value");
		Bounds bbox = new Bounds(1.0, 2.0, 3.0, 4.0);
		param.setBoundingBox(bbox);
		
		assertEquals(null, param.getValue());
		assertEquals("2.0", param.left.getValue());
		assertEquals("4.0", param.right.getValue());
		assertEquals("3.0", param.top.getValue());
		assertEquals("1.0", param.bottom.getValue());
		
		assertEquals("", param.getCommandlineForm());
	}
}
