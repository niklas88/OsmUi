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

import de.osmui.model.exceptions.TasksNotCompatibleException;
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
					"--read-xml-change outPipe.0=AUTO7to5 " +
					"--tee-change outputCount=3 inPipe.0=" +
					"AUTO7to5 outPipe.0=AUTO8to6 outPipe.1=" +
					"AUTO10to7 outPipe.2=AUTO11to8 --tee-" +
					"change outputCount=3 inPipe.0=AUTO11to8 " +
					"outPipe.2=AUTO13to11 --sort-change inPipe" +
					".0=AUTO10to7 outPipe.0=AUTO12to12 --sort-" +
					"change inPipe.0=AUTO8to6 outPipe.0=AUTO9to" +
					"10 --append-change sourceCount=3 inPipe.0=" +
					"AUTO9to10 inPipe.1=AUTO12to12 inPipe.2=AUTO13to11 ");
			
			String testen = trans.exportLine(model, "");
			assertEquals(1, model.getSourceTasks().size());
			
		} catch (ImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(expected=NumberFormatException.class)
	public void finishTask() {
		JGPipelineModel model = new JGPipelineModel();
		CommandlineTranslator trans = CommandlineTranslator.getInstance();
		//trans.main(null);
		try {
			trans.importLine(
					model,
					"--read-xml-change outPipe.0=AUTO7to5 " +
					"--tee-change outputCount=3 inPipe.0=" +
					"AUTO7to5 outPipe.0=AUTO8to6 outPipe.1=" +
					"AUTO10to7 outPipe.2=AUTO11to8 --tee-" +
					"change o8 " +
					"outPipe.2=AUTO13to11 --sort-change inPipe" +
					".0=AUTO10to7 outPipe.0=AUTO12to12 --sort-" +
					"change inPipe.0=AUTO8to6 outPipe.0=AUTO9to" +
					"10 --append-change sourceCount=3 inPipe.0=" +
					"AUTO9to10 inPipe.1=AUTO12to12 inPipe.2=AUTO13to11 ");
			
		} catch (ImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
