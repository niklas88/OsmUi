/**
 * 
 */
package de.osmui.util;

import java.util.StringTokenizer;

import de.osmui.model.pipelinemodel.AbstractParameter;
import de.osmui.model.pipelinemodel.AbstractPipelineModel;
import de.osmui.model.pipelinemodel.AbstractTask;
import de.osmui.util.exceptions.ImportException;
import de.osmui.util.exceptions.TaskNameUnknownException;

/**
 * This class is responsible for importing an Osmosis command line into an AbstractPipelineModel
 * and exporting an Osmosis command line from a given AbstractPipelineModel. It is implemented using the Singelton
 * pattern.
 * 
 * @author Niklas Schnelle
 *
 */
public class CommandlineTranslator {
	protected static CommandlineTranslator instance;
	
	public void importLine(AbstractPipelineModel model, String line) throws ImportException {
		StringTokenizer st = new StringTokenizer(line, " \t\n\r\f\\");
		
		AbstractTask currTask = null;
		AbstractParameter currParam = null;
		TaskManager tm = TaskManager.getInstance();
		String currToken;
		String taskName;
		String paramName;
		String paramValue;
		while(st.hasMoreTokens()){
			currToken = st.nextToken();
			//System.out.println(currToken);
			if(currToken.startsWith("--")){
				//Ok we got a new task:
				// If currTask != null this wasn't the first task and the last is done
				if(currTask != null){
					System.out.println("Task done");
					System.out.println(currTask.getCommandlineForm());
					//TODO wire the pipes up
				}
				taskName = tm.unshortenTaskname(currToken.substring(2));
				try {
					currTask = tm.createTask(taskName);
				} catch (TaskNameUnknownException e) {
					throw new ImportException("Taskname "+taskName+"doesn't match anything");
				}
				
			} else {
				//Not a task must be parameter or pipe, the currTask must be non null or else something's wrong
				if(currTask == null){
					throw new ImportException("Parameters before first task");
				}
				//System.out.println(currToken);
				int eqIndex = currToken.indexOf("=");
				paramName = (eqIndex >= 0)? currToken.substring(0, eqIndex): null;		
				paramValue = (eqIndex >= 0)? currToken.substring(eqIndex + 1): currToken;
				if(paramName == null){
					currParam = currTask.getDefaultParameter();
				} else {
					currParam = currTask.getParameters().get(paramName);
				}
				currParam.setValue(paramValue);
				
			}
			
		}
		
	}
	
	public static CommandlineTranslator getInstance(){
		if(instance == null){
			instance = new CommandlineTranslator();
		}
		return instance;
	}
	
	//Little test method ;-)
	public static void main(String[] args){
		CommandlineTranslator trans = CommandlineTranslator.getInstance();
		try {
			trans.importLine(null, "--rx full/planet-071128.osm.bz2 "
									+"--tee 2 "
									+"--bp file=polygons/europe/germany/baden-wuerttemberg.poly \\"
									+"--wx baden-wuerttemberg.osm.bz2 \\"
									+"--bp file=polygons/europe/germany/bayern.poly "
									+"--wx bayern.osm.bz2");
		} catch (ImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
