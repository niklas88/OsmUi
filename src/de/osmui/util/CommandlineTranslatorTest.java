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

package de.osmui.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.osmui.model.pipelinemodel.AbstractTask;
import de.osmui.model.pipelinemodel.CommonTask;
import de.osmui.model.pipelinemodel.JGPipelineModel;
import de.osmui.util.exceptions.ImportException;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 * 
 * @see CommandLineTranslator
 * 
*/
public class CommandlineTranslatorTest {
	
	@Test public void ImportLine(){
		JGPipelineModel model = new JGPipelineModel();
		CommandlineTranslator trans = CommandlineTranslator.getInstance();
		//trans.main(null);
		try {
			trans.importLine(
					model,
					"--rx full/planet-071128.osm.bz2 "
							+ "--tee outPipe.1=fooPipe "
							+ "--bp file=polygons/europe/germany/baden-wuerttemberg.poly \\"
							+ "--wx baden-wuerttemberg.osm.bz2 inPipe.0=fooPipe \\"
							+ "--bp file=polygons/europe/germany/bayern.poly "
							+ "--wx bayern.osm.bz2");
			
			List<AbstractTask> list = new ArrayList<AbstractTask>();
			list = model.getSourceTasks();
			List<AbstractTask> test = new ArrayList<AbstractTask>();
			AbstractTask task1 = new CommonTask("read-xml");
			test.add(task1);
			assertEquals(test.size(), list.size());
		} catch (ImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
