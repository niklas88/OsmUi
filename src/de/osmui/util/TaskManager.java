/**
 * 
 */
package de.osmui.util;

import java.util.ArrayList;
import java.util.Collection;
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
 * descriptions. It implements the Singelton pattern, as well as being a Factory
 * for tasks
 * 
 * @author Niklas Schnelle
 * 
 * @see TaskManagerTest
 */
public class TaskManager {
	static protected TaskManager instance;

	protected HashMap<String, TTask> taskMap;
	protected HashMap<String, String> shortNameTable;
	protected OsmosisTaskDescription taskDescriptions;

	/**
	 * The protected constructor for the Singelton pattern
	 */
	protected TaskManager() {
		taskMap = new HashMap<String, TTask>();
		shortNameTable = new HashMap<String, String>();
		try {
			/*
			 * Get the JAXBContext
			 */
			JAXBContext jc;
			jc = JAXBContext.newInstance("de.osmui.model.osm");

			/* Read the xml file containing the different tasks */

			// File xmlTasksFile = new File("osmosis-tasks.xml"); //
			Unmarshaller u = jc.createUnmarshaller();
			OsmosisTaskDescription taskDescriptions = (OsmosisTaskDescription) u
					.unmarshal(TaskManager.class
							.getResourceAsStream("osmosis-tasks.xml"));
			// .unmarshal(xmlTasksFile);

			/* Fill the map of taskDescriptions and output some Debug info */
			/*
			 * TODO: Find a way to store the groups and make it suitable for
			 * TaskBox
			 */
			for (TTaskGroup group : taskDescriptions.getTaskGroup()) {
				System.out.println(group.getFriendlyName() + ":");
				for (TTask task : group.getTask()) {
					taskMap.put(task.getName(), task);
					if (task.getShortName() != null) {
						shortNameTable.put(task.getShortName(), task.getName());
					}
					// System.out.println("---- " + task + " ----");
					System.out.println(task.getShortName() + " <=> "
							+ task.getName());
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
	 * Gets the long version of a taskname e.g. 'read-xml' for 'rx', if a long
	 * name can't be found the name is returned unaltered.
	 * 
	 * @param name
	 * @return the possibly unshortened name
	 */
	public String unshortenTaskname(String name) {
		String longName = shortNameTable.get(name);
		return (longName != null) ? longName : name;
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

		TTask taskDescription = getTaskDescription(taskName);
		if (taskDescription == null) {
			throw new TaskNameUnknownException();
		}
		AbstractTask newTask = new CommonTask(taskName);

		Map<String, AbstractParameter> pMap = newTask.getParameters();
		AbstractParameter newParameter;
		// Generate the parameters of the task object
		for (TParameter paramDesc : taskDescription.getParameter()) {

			if (paramDesc.getType().equals("int")) {
				newParameter = new IntParameter(paramDesc,
						paramDesc.getDefaultValue());
			} else if (paramDesc.getType().equals("boolean")) {
				newParameter = new BooleanParameter(paramDesc,
						paramDesc.getDefaultValue());
			} else if (paramDesc.getType().equals("enum")) {
				newParameter = new EnumParameter(paramDesc,
						paramDesc.getDefaultValue());
			} else if (paramDesc.getType().equals("bbox")) {
				if(newTask.getName().equals("dataset-bounding-box")){
					// Needs a short form BoundingBox
					newParameter = new BBoxPseudoParam(paramDesc, "", newTask, false);
				} else {
					// Needs a long form BoundingBox
					newParameter = new BBoxPseudoParam(paramDesc, "", newTask, true);
				}
			} else {
				newParameter = new OtherParameter(paramDesc,
						paramDesc.getDefaultValue());
			}
			if (newParameter.isDefaultParam()) {
				newTask.setDefaultParameter(newParameter);
			}
			pMap.put(paramDesc.getName(), newParameter);
		}

		List<AbstractPort> portList = newTask.getInputPorts();
		AbstractPort newPort;
		// Generate list of inputPorts from described input_Pipes_
		for (TPipe pipeDesc : taskDescription.getInputPipe()) {

			if (pipeDesc.getCount().equals("single")) {
				newPort = new CommonPort(newTask, pipeDesc.getType());
				portList.add(newPort);
			} else {
				// Create as many VariablePorts as the default value (which is
				// set) of SpecifiedBy tells us
				IntParameter specifiedBy = (IntParameter) pMap.get(pipeDesc
						.getSpecifiedBy());
				for (int i = 0; i < specifiedBy.getValueInteger(); ++i) {
					newPort = new VariablePort(newTask, specifiedBy,
							pipeDesc.getType());
					portList.add(newPort);
				}
			}

		}

		List<AbstractPipe> pipeList = newTask.getOutputPipes();
		AbstractPipe newPipe;
		// Generate list of outputPipes from described outputPipes
		for (TPipe pipeDesc : taskDescription.getOutputPipe()) {

			if (pipeDesc.getCount().equals("single")) {
				newPipe = new CommonPipe(newTask, pipeDesc.getType());
				pipeList.add(newPipe);
			} else {
				// Create as many VariablePipes as the default value (which is
				// set) of SpecifiedBy tells us
				IntParameter specifiedBy = (IntParameter) pMap.get(pipeDesc
						.getSpecifiedBy());
				for (int i = 0; i < specifiedBy.getValueInteger(); ++i) {
					newPipe = new VariablePipe(newTask, specifiedBy,
							pipeDesc.getType());
					pipeList.add(newPipe);
				}
			}

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
	 * Searches the task descriptions for compatible tasks and returns them
	 * 
	 * @param taskName
	 * @return compatibleTasks
	 */
	public ArrayList<TTask> getCompatibleTasks(String taskName) {
		ArrayList<TTask> compatibleTasks = new ArrayList<TTask>();
		Collection<TTask> taskDescriptions = taskMap.values();
		TTask taskDescSearched = taskMap.get(taskName);

		if (taskDescSearched != null
				&& !taskDescSearched.getOutputPipe().isEmpty()) {
			for (TTask taskDesc : taskDescriptions) {
				if (!taskDesc.getInputPipe().isEmpty()) {
					PipeLoop: for (TPipe outPipe : taskDescSearched
							.getOutputPipe()) {
						for (TPipe inPipe : taskDesc.getInputPipe()) {
							if (outPipe.getType().equals(inPipe.getType())) {
								compatibleTasks.add(taskDesc);
								// We are done with this taskDesc
								break PipeLoop;
							}
						}
					}

				}

			}
		} else {
			for (TTask taskDesc : taskDescriptions) {
				if (taskDesc.getInputPipe().isEmpty()) {
					compatibleTasks.add(taskDesc);
				}
			}
		}

		return compatibleTasks;
	}

	/**
	 * Gets the instance
	 * 
	 * @return
	 */
	public static TaskManager getInstance() {
		if (instance == null) {
			instance = new TaskManager();
		}
		return instance;
	}

}
