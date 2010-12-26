/**
 * 
 */
package de.osmui.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import de.osmui.model.osm.OsmosisTaskDescription;
import de.osmui.model.osm.TParameter;
import de.osmui.model.osm.TTask;
import de.osmui.model.osm.TTaskGroup;
import de.osmui.model.osm.TPipe;

import de.osmui.model.pipelinemodel.*;
import de.osmui.util.exceptions.TaskNameUnknownException;

/**
 * This class provides factory methods for Task objects and access to their
 * descriptions. It implements the Singelton pattern, as well as being a Factory for tasks
 * 
 * @author Niklas Schnelle
 * 
 */
public class TaskManager {
	static protected TaskManager instance;

	protected HashMap<String, TTask> taskMap;
	protected OsmosisTaskDescription taskDescriptions;
	/**
	 * The protected constructor for the Singelton pattern
	 */
	protected TaskManager() {
		taskMap = new HashMap<String, TTask>();
		try {
			/*
			 * Get the JAXBContext
			 */
			JAXBContext jc;
			jc = JAXBContext.newInstance("de.osmui.model.osm");

			/* Read the xml file containing the different tasks */
			File xmlTasksFile = new File("osmosis-tasks.xml"); //
			Unmarshaller u = jc.createUnmarshaller();
			OsmosisTaskDescription taskDescriptions = (OsmosisTaskDescription) u
					.unmarshal(xmlTasksFile);

			/* Fill the map of taskDescriptions and output some Debug info */
			/*TODO: Find a way to store the groups and make it suitable for TaskBox */
			for (TTaskGroup group : taskDescriptions.getTaskGroup()) {
				System.out.println(group.getFriendlyName() + ":");
				for (TTask task : group.getTask()) {
					taskMap.put(task.getName(), task);
					System.out.println("---- " + task + " ----");
					System.out.println("   " + task.getName());
				}
			}

			/*
			 * Beispiel 2: Zulässige Parameter für den Task '--read-apidb'
			 * bestimmen
			 * 
			 * TTask taskReadApiDb = tasks.get("bounding-box"); if
			 * (taskReadApiDb != null) {
			 * System.out.println("\n\nParameter für Task " +
			 * taskReadApiDb.getName() + " (in Klammern: Standardwert)"); for
			 * (TParameter parameter : taskReadApiDb.getParameter()) {
			 * System.out.print("   " + parameter.getName() + ": " +
			 * parameter.getType()); System.out .println(" (" +
			 * parameter.getDefaultValue() + ")");
			 * 
			 * } }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Creates a new task Object when given the name of the task e.g. 'read-xml'
	 * using it's TTask description as kind of a building plan
	 * 
	 * @param taskName
	 * @return
	 */
	public AbstractTask createTask(String taskName)
			throws TaskNameUnknownException {
		AbstractTask newTask = new CommonTask();
		TTask taskDescription = getTaskDescription(taskName);
		if (taskDescription == null) {
			throw new TaskNameUnknownException();
		}

		Map<String, AbstractParameter> pMap = newTask.getParameters();
		// Generate the parameters of the task object
		for (TParameter paramDesc : taskDescription.getParameter()) {
			if (paramDesc.getType().equals("int")) {
				pMap.put(paramDesc.getName(), new IntParameter(paramDesc,
						paramDesc.getDefaultValue()));
			} else if (paramDesc.getType().equals("boolean")) {
				pMap.put(paramDesc.getName(), new BooleanParameter(paramDesc,
						paramDesc.getDefaultValue()));
			} else {
				pMap.put(paramDesc.getName(), new OtherParameter(paramDesc,
						paramDesc.getDefaultValue()));
			}
		}

		List<AbstractPort> portList = newTask.getInputPorts();
		// Generate list of inputPorts from described input_Pipes_
		for (TPipe pipeDesc : taskDescription.getInputPipe()) {
			AbstractPort newPort;
			if (pipeDesc.getCount().equals("single")) {
				newPort = new CommonPort(newTask, pipeDesc.getType());
			} else {
				newPort = new VariablePort(newTask,
						(IntParameter) pMap.get(pipeDesc.getSpecifiedBy()),
						pipeDesc.getType());
			}
			portList.add(newPort);
		}

		List<AbstractPipe> pipeList = newTask.getOutputPipes();
		// Generate list of outputPipes from described outputPipes
		for (TPipe pipeDesc : taskDescription.getInputPipe()) {
			AbstractPipe newPipe;
			if (pipeDesc.getCount().equals("single")) {
				newPipe = new CommonPipe(newTask, pipeDesc.getType());
			} else {
				newPipe = new VariablePipe(newTask,
						(IntParameter) pMap.get(pipeDesc.getSpecifiedBy()),
						pipeDesc.getType());
			}
			pipeList.add(newPipe);
		}
		
		return newTask;
	}

	/**
	 * Gets the description of the task given by it's name
	 * 
	 * @param taskName
	 * @return
	 */
	public TTask getTaskDescription(String taskName)
			throws TaskNameUnknownException {
		TTask result = taskMap.get(taskName);
		if (result == null) {
			throw new TaskNameUnknownException();
		}
		return result;
	}

	/**
	 * Gets the description of the given task object
	 * 
	 * @param task
	 * @return
	 */
	public TTask getTaskDescription(AbstractTask task)
			throws TaskNameUnknownException {
		return getTaskDescription(task.getName());
	}

	/**
	 * Gets the instance
	 * 
	 * @return
	 */
	public TaskManager getInstance() {
		if (instance == null) {
			instance = new TaskManager();
		}
		return instance;
	}

}
