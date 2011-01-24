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

package de.osmui.model.pipelinemodel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.osmui.model.osm.TParameter;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 * 
 * @see VariablePipe
 * 
*/
public class VariablePipeTest {
	@Test public void createPipe(){
		AbstractTask task = new CommonTask("name");
		TParameter Tparam = new TParameter();
		IntParameter param = new IntParameter(Tparam, "9");
		VariablePipe pipe = new VariablePipe(task, param, "type");
		pipe.createPipe();
		AbstractPipe test= new CommonPipe(task, "type");
		assertEquals(pipe.type, test.getType());
	}
	
	@Test public void disconnect(){
		AbstractTask task = new CommonTask("name");
		TParameter Tparam = new TParameter();
		IntParameter param = new IntParameter(Tparam, "9");
		VariablePipe pipe = new VariablePipe(task, param, "type");
		pipe.referencedParam = param;
		pipe.disconnect();
		assertEquals(false, pipe.isConnected());
	}
}
