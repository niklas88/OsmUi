package de.osmui.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.osmui.model.pipelinemodel.AbstractTask;
import de.osmui.model.pipelinemodel.CommonTask;
import de.osmui.model.pipelinemodel.JGPipelineModel;
import de.osmui.util.exceptions.ImportException;

/**
 * 
 * @author verena
 * 
 * @see CommandLineTranslator
 * 
*/
public class CommandlineTranslatorTest {
	
	@Test public void ImportLine(){
		JGPipelineModel model = new JGPipelineModel();
		CommandlineTranslator trans = CommandlineTranslator.getInstance();
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
	
	@Test public void getInstance(){
		CommandlineTranslator trans = null;
		CommandlineTranslator test = new CommandlineTranslator();
		trans.getInstance();
		assertEquals(trans.getInstance(), test.getInstance());
	}
}
