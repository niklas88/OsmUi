/**
 * 
 */
package de.osmui.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

import de.osmui.model.exceptions.TasksNotCompatibleException;
import de.osmui.model.exceptions.TasksNotInModelException;
import de.osmui.model.pipelinemodel.AbstractParameter;
import de.osmui.model.pipelinemodel.AbstractPipelineModel;
import de.osmui.model.pipelinemodel.AbstractPipe;
import de.osmui.model.pipelinemodel.AbstractPort;
import de.osmui.model.pipelinemodel.AbstractTask;
import de.osmui.model.pipelinemodel.IntParameter;
import de.osmui.model.pipelinemodel.JGPipelineModel;
import de.osmui.util.exceptions.ImportException;
import de.osmui.util.exceptions.TaskNameUnknownException;

/**
 * This class is responsible for importing an Osmosis command line into an
 * AbstractPipelineModel and exporting an Osmosis command line from a given
 * AbstractPipelineModel. It is implemented using the Singelton pattern.
 * 
 * @author Niklas Schnelle
 * 
 */
public class CommandlineTranslator {
	protected static CommandlineTranslator instance;

	private void finishTask(AbstractPipelineModel model, AbstractTask currTask,
			Stack<AbstractPipe> pipeStack, Map<String, AbstractPipe> pipeMap)
			throws ImportException {
		System.out.println(currTask.getCommandlineForm());
		// Wire up input ports not connected through explicit naming using the
		// stack
		for (AbstractPort port : currTask.getInputPorts()) {
			if (!port.isConnected()) {
				try {
					model.connectTasks(pipeStack.pop(), port);
				} catch (TasksNotCompatibleException e) {
					throw new ImportException(
							"Tried to connect incompatible tasks at:"
									+ currTask.getCommandlineForm());
				} catch (TasksNotInModelException e) {
					// Failure of program logic task should be in the model
					e.printStackTrace();
				}
			}
		}
		// Push all unnamed pipes onto the stack and add named pipes to the map
		for (AbstractPipe pipe : currTask.getOutputPipes()) {
			if (!pipe.isNamed()) {
				pipeStack.push(pipe);
			} else {
				pipeMap.put(pipe.getName(), pipe);
			}
		}
		System.out.println("Task done");

	}

	private AbstractTask createTaskFromToken(TaskManager tm, String currToken)
			throws ImportException {
		// Create a new task object
		String taskName = tm.unshortenTaskname(currToken.substring(2));
		try {
			return tm.createTask(taskName);
		} catch (TaskNameUnknownException e) {
			throw new ImportException("Taskname " + taskName
					+ "doesn't match anything");
		}
	}

	private void handleParam(AbstractTask currTask, AbstractParameter param,
			String paramValue) {
		param.setValue(paramValue);
		// Check if the parameter specifies a variable
		// pipe/port count and create pipes/ports
		// accordingly
		if (param instanceof IntParameter) {
			IntParameter intParam = (IntParameter) param;
			int defaultCount = Integer.parseInt(intParam.getDefaultValue());

			for (AbstractPort port : currTask.getInputPorts()) {

				if (port.isVariable() &&  port.getReferencedParam().equals(intParam)) {
					for (int i = defaultCount; i < intParam.getValueInteger(); ++i) {
						port.createPort();
					}
					// We are done
					return;

				}
			}

			for (AbstractPipe pipe : currTask.getOutputPipes()) {

				if (pipe.isVariable() && pipe.getReferencedParam().equals(intParam)) {
					for (int i = defaultCount; i < intParam.getValueInteger(); ++i) {
						pipe.createPipe();
					}
					// We are done
					return;

				}
			}
		}
	}

	private void handleParamOrPipe(AbstractPipelineModel model,
			AbstractTask currTask, Map<String, AbstractPipe> pipeMap,
			String currToken) throws ImportException {
		AbstractParameter param;
		AbstractPipe pipe;
		String paramName;
		String paramValue;
		int pipeNum = 0;

		int eqIndex = currToken.indexOf("=");
		paramName = (eqIndex >= 0) ? currToken.substring(0, eqIndex) : null;
		paramValue = (eqIndex >= 0) ? currToken.substring(eqIndex + 1)
				: currToken;
		if (paramName == null) {
			// This is the value of the default parameter
			param = currTask.getDefaultParameter();
			handleParam(currTask, param, paramValue);
		} else if (paramName.startsWith("inPipe.")) {
			// We need to connect a named pipe with the correct port
			// 7th position is the one after the .
			pipeNum = Integer.parseInt(paramName.substring(7));
			pipe = pipeMap.remove(paramValue);
			if (pipe == null) {
				throw new ImportException("Can't connect to unknown pipe: "
						+ paramValue);
			}
			try {
				model.connectTasks(pipe, currTask.getInputPorts().get(pipeNum));
			} catch (TasksNotCompatibleException e) {
				throw new ImportException(
						"Tried to connect incompatible tasks at: " + currTask);
			} catch (TasksNotInModelException e) {
				// Failure in program logic task should be added
				e.printStackTrace();
			}

		} else if (paramName.startsWith("outPipe.")) {
			// We got a new named pipe
			// 6th position is the one after the .
			pipeNum = Integer.parseInt(paramName.substring(8));
			currTask.getOutputPipes().get(pipeNum).setName(paramValue);
		} else {
			// This is a named parameter
			param = currTask.getParameters().get(paramName);
			handleParam(currTask, param, paramValue);
		}
	}

	public void importLine(AbstractPipelineModel model, String line)
			throws ImportException {
		StringTokenizer st = new StringTokenizer(line, " \t\n\r\f\\");

		// Stack for unnamed pipes
		Stack<AbstractPipe> pipeStack = new Stack<AbstractPipe>();
		// HashMap for named pipes so we can connect them later on
		HashMap<String, AbstractPipe> pipeMap = new HashMap<String, AbstractPipe>();

		AbstractTask currTask = null;
		TaskManager tm = TaskManager.getInstance();
		String currToken;

		while (st.hasMoreTokens()) {
			currToken = st.nextToken();
			// System.out.println(currToken);
			if (currToken.startsWith("--")) {
				// Ok we got a new task:
				// If currTask != null this wasn't the first task and the last
				// is done
				if (currTask != null) {
					finishTask(model, currTask, pipeStack, pipeMap);
				}

				currTask = createTaskFromToken(tm, currToken);
				model.addTask(currTask);
			} else {
				// Not a task must be parameter or pipe, the currTask must be
				// non null or else something's wrong
				if (currTask == null) {
					throw new ImportException("Parameters before first task");
				}
				handleParamOrPipe(model, currTask, pipeMap, currToken);
			}

		}

	}

	public static CommandlineTranslator getInstance() {
		if (instance == null) {
			instance = new CommandlineTranslator();
		}
		return instance;
	}

	// Little test method ;-)
	public static void main(String[] args) {
		JGPipelineModel model = new JGPipelineModel();
		CommandlineTranslator trans = CommandlineTranslator.getInstance();
		try {
			trans.importLine(
					model,
					"--rx full/planet-071128.osm.bz2 "
							+ "--tee 2 "
							+ "--bp file=polygons/europe/germany/baden-wuerttemberg.poly \\"
							+ "--wxc baden-wuerttemberg.osm.bz2 \\"
							+ "--bp file=polygons/europe/germany/bayern.poly "
							+ "--wx bayern.osm.bz2");
		} catch (ImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
