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
 * 
 */
package de.osmui.model.pipelinemodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingConstants;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;

import de.osmui.i18n.I18N;
import de.osmui.model.exceptions.TasksNotCompatibleException;
import de.osmui.model.exceptions.TasksNotInModelException;
import de.osmui.util.ConfigurationManager;

/**
 * This class implements a pipeline model using an mxGraph as a backing store
 * 
 * @author Niklas Schnelle, Peter Vollmer, Verena Käfer
 * 
 * @see JGPipelineModelTest
 * 
 */
public class JGPipelineModel extends AbstractPipelineModel implements
		Serializable {

	private static final long serialVersionUID = -4609328085880199933L;
	private static final int VERTEX_WIDTH = 100;
	private static final int VERTEX_HEIGHT = 20;
	
	private class mxPipelineGraph extends mxGraph {

		// Overrides method to disallow editting
		@Override
		public boolean isCellEditable(Object cell) {
			return false;
		}

		@Override
		public boolean isCellConnectable(Object cell) {
			mxCell mxcell = (mxCell) cell;
			if (mxcell.isVertex()) {
				AbstractTask task = (AbstractTask) mxcell.getValue();
				return task.isConnectable();
			} else {
				return false;
			}
		}

		@Override
		public Object addCell(Object cell, Object parent, Integer index,
				Object source, Object target) {
			Object ret;
			ret = super.addCell(cell, parent, index, source, target);

			if (source != null && target != null && cell != null) {
				// Check the cell, which should be an edge to find out
				// whether the
				// tasks are already connected, then it has a pipe user
				// object
				mxCell mxcell = (mxCell) cell;
				if (mxcell.getValue() instanceof AbstractPipe) {
					// It's already set we are done here
					return ret;
				}
				mxCell mxsource = (mxCell) source;
				mxCell mxtarget = (mxCell) target;
				if (mxtarget.getValue() instanceof AbstractTask
						&& mxsource.getValue() instanceof AbstractTask) {
					AbstractTask sourceTask = (AbstractTask) mxsource
							.getValue();
					AbstractTask targetTask = (AbstractTask) mxtarget
							.getValue();

					try {
						AbstractPipe pipe = rawConnectTasks(sourceTask,
								targetTask);
						mxcell.setValue(pipe);
						pipeMap.put(Long.valueOf(pipe.getID()), mxcell);
					} catch (TasksNotInModelException e) {
						// shouldn't happen
					} catch (TasksNotCompatibleException e) {
						// Too bad,tried connection nonsense
						Object[] cells = { mxcell };
						removeCells(cells);
						return null;
					}

				}
			}
			return ret;
		}

		@Override
		public Object[] removeCells(Object[] cells, boolean includeEdges) {
			Object[] cellsRemoved = super.removeCells(cells, includeEdges);
			Object val;
			AbstractPipe pipe;
			for (Object cell : cellsRemoved) {
				val = ((mxCell) cell).getValue();
				if (val instanceof AbstractPipe) {
					pipe = (AbstractPipe) val;
					pipe.disconnect();

				} else if (val instanceof AbstractTask) {
					try {
						rawRemoveTask((AbstractTask) val);
					} catch (TasksNotInModelException e) {
						// Do nothing
					}
				}
			}
			return cellsRemoved;
		}
	}

	protected ArrayList<AbstractTask> tasks;

	protected Map<Long, mxCell> taskMap;
	protected Map<Long, mxCell> pipeMap;

	protected transient mxGraph graph;
	protected transient mxHierarchicalLayout lay;
	
	private int sourceTaskNum; 
	/**
	 * Constructs a new JGPipelineModel
	 */
	public JGPipelineModel() {
		tasks = new ArrayList<AbstractTask>();
		taskMap = new HashMap<Long, mxCell>();
		pipeMap = new HashMap<Long, mxCell>();
		graph = new mxPipelineGraph();
		sourceTaskNum = 0;
		lay = new mxHierarchicalLayout(graph, SwingConstants.NORTH);
		lay.setLayoutFromSinks(false);		
	}
	
	/** 
	 * Restores the transient fields after deserialisation
	 * @return
	 */
	private Object readResolve(){
		graph = new mxPipelineGraph();
		lay = new mxHierarchicalLayout(graph, SwingConstants.NORTH);
		// Add the old task cells
		graph.addCells(taskMap.values().toArray());
		// Add the old pipe cells
		graph.addCells(pipeMap.values().toArray());
		lay.setLayoutFromSinks(false);
		return this;
	}
	
	/**
	 * Puts all tasks from the given model into this model
	 * the sourceModel will get invalid
	 * @param sourceModel
	 */
	public void setAll(JGPipelineModel sourceModel){
		clean();
		tasks = sourceModel.tasks;
		taskMap = sourceModel.taskMap;
		pipeMap = sourceModel.pipeMap;
		// We need to set this model as the associated model 
		// for all tasks
		for(AbstractTask task : tasks){
			task.setModel(this);
		}
		graph.addCells(taskMap.values().toArray());
		graph.addCells(pipeMap.values().toArray());
		setChanged();
		notifyObservers(tasks.get(0));
	}
	
	/**
	 * Gets the associated graph
	 * @return
	 */
	public mxGraph getGraph() {
		return this.graph;
	}

	/**
	 * @see de.osmui.model.pipelinemodel.AbstractModel#getSourceTasks()
	 */
	@Override
	public List<AbstractTask> getSourceTasks() {
		return new ArrayList<AbstractTask>(tasks.subList(0, sourceTaskNum));
	}

	/**
	 * Adds the task to the model without wireing up jgraphx
	 * @param task
	 */
	private void rawAddTask(AbstractTask task){
		// Add to list of tasks, if it's a sourceTasks add to the beginning,
		// this speeds up getting sourceTasks
		if (task.getInputPorts().isEmpty()) {
			tasks.add(0, task);
			// Got a new source task
			sourceTaskNum++;
		} else {
			tasks.add(task);
		}

		task.setModel(this);
	}
	/**
	 * Adds a task to jgraphx and positions it
	 * @param task
	 */
	private void addTaskToJG(AbstractTask parentTask, AbstractTask task){
		double horizontalPos = 0.0;
		double verticalPos = 20.0;
		
		graph.getModel().beginUpdate();
		try {
			if(parentTask != null) {
				// NOTE: The child is not yet connected to the parent!		
				mxCell parentCell = getCellForTask(parentTask);
				mxCell currCell = null;
				double currX;
				horizontalPos = parentCell.getGeometry().getX();
				for(AbstractPipe outPipe : parentTask.getOutputPipes()){
					if(outPipe.isConnected()){
						currCell = (mxCell) getCellForPipe(outPipe).getTarget();
						currX = currCell.getGeometry().getX();
						currX -= (((double) VERTEX_WIDTH)+20.0)/2.0;
						currCell.getGeometry().setX(currX);
						horizontalPos += (((double) VERTEX_WIDTH)+20.0)/2.0;
					}
				}
				
				
				verticalPos += parentCell.getGeometry().getY()+VERTEX_HEIGHT+60.0;
			} else {
				// minus one to not count ourselves
				horizontalPos = (sourceTaskNum - 1)* (VERTEX_WIDTH+20.0) + 20.0;
			}
			// Add the task to the underling mxGraph model
			Object parent = graph.getDefaultParent();
			
			// Add a mxCell to our taskMap for this task
			taskMap.put(Long.valueOf(task.getID()), (mxCell) graph
					.insertVertex(parent, null, task, horizontalPos, verticalPos, VERTEX_WIDTH, VERTEX_HEIGHT));
			graph.refresh();
		} finally {
			graph.getModel().endUpdate();
		}
	}
	/**
	 * @see de.osmui.model.pipelinemodel.AbstractModel#addTask(de.osmui.model.
	 * pipelinemodel.AbstractTask)
	 */
	@Override
	public void addTask(AbstractTask task) {
		rawAddTask(task);
		addTaskToJG(null, task);
		setChanged();
		notifyObservers(task);
	}

	/**
	 * @see de.osmui.model.pipelinemodel.AbstractModel#addTask(de.osmui.model.
	 * pipelinemodel.AbstractTask, de.osmui.model.pipelinemodel.AbstractTask)
	 */
	@Override
	public void addTask(AbstractTask parent, AbstractTask child)
			throws TasksNotCompatibleException, TasksNotInModelException {

		if (parent.getModel() != this) {
			throw new TasksNotInModelException(I18N.getString("JGPipelineModel.parentNotInModel"));
		}

		// First add the child and then use our internal connect method to wire
		// things up
		rawAddTask(child);
		addTaskToJG(parent, child);
		connectTasks(parent, child);
		
		if(Boolean.valueOf(ConfigurationManager.getInstance().getEntry("AutoConfCheckBox", "true"))){
			layout(null);
		}
		setChanged();
		notifyObservers(child);

	}

	/**	 * 
	 * @see
	 * de.osmui.model.pipelinemodel.AbstractModel#removeTask(de.osmui.model.
	 * pipelinemodel.AbstractTask)
	 */
	@Override
	public boolean removeTask(AbstractTask task)
			throws TasksNotInModelException {
		if (task.getModel() != this) {
			throw new TasksNotInModelException(I18N.getString("JGPipelineModel.taskToRmNotInModel"));
		}

		Object[] cellArray = { getCellForTask(task) };
		// Our subclass of mxGraph handles disconnecting via rawRemoveTask
		boolean result = graph.removeCells(cellArray).length != 0;

		return result;
	}
	
	/**
	 * @see de.osmui.model.pipelinemodel.AbstractPipelineModel#clean()
	 */
	public void clean(){
		Collection<mxCell> cells = taskMap.values();
		Object[] cellArray = cells.toArray();
		// Our subclass of mxGraph handles disconnecting via rawRemoveTask
		graph.removeCells(cellArray);
		setChanged();
	}

	/**
	 * Helper method thar removes the Task from the model without affecting the
	 * under- lying mxGraph
	 * 
	 * @param taslk
	 * @return
	 * @throws TasksNotInModelException
	 */
	private boolean rawRemoveTask(AbstractTask task)
			throws TasksNotInModelException {
		if (task.getModel() != this) {
			throw new TasksNotInModelException(I18N.getString("JGPipelineModel.taskToRmNotInModel"));
		}
		// Disconnect all connected pipes (don't use iterator because then
		// we can't remove elements)
		int countOutputPipes = task.getOutputPipes().size();
		AbstractPipe out;
		for(int iter=0; iter < countOutputPipes; ++iter){
			out = task.getOutputPipes().get(iter);
			if (out.isConnected()) {
				out.disconnect();
			}
		}
		// Disconnect all connected ports
		int countInputPorts = task.getInputPorts().size();
		// Decrease sourceTaskNum if it was a source task
		if(countInputPorts == 0){
			sourceTaskNum--;
		}
		
		AbstractPort in;
		for(int iter=0; iter < countInputPorts; ++iter){
			in = task.getInputPorts().get(iter);
			if (in.isConnected()) {
				in.disconnect();
			}
		}

		task.setModel(null);
		boolean result = tasks.remove(task);
		setChanged();
		notifyObservers(task);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.osmui.model.pipelinemodel.AbstractModel#connectTasks(de.osmui.model
	 * .pipelinemodel.AbstractTask, de.osmui.model.pipelinemodel.AbstractTask)
	 */
	@Override
	public AbstractPipe connectTasks(AbstractTask parent, AbstractTask child)
			throws TasksNotCompatibleException, TasksNotInModelException {
		Object graphparent = graph.getDefaultParent();

		// Thanks to our subclass of mxGraph this will make the model
		// connection.
		// see overwritten addCell
		mxCell edge = (mxCell) graph.insertEdge(graphparent, null, null,
				getCellForTask(parent),
				getCellForTask(child));
		if(edge == null){
			throw new TasksNotCompatibleException(I18N.getString("JGPipelineModel.TasksNotComp"));
		}
		setChanged();
		notifyObservers(edge.getValue());
		return (AbstractPipe) edge.getValue();
	}

	/**
	 * This helper method connects tasks without adding their connection to the
	 * mxGraph
	 * 
	 * @param parent
	 * @param child
	 * @return
	 * @throws TasksNotCompatibleException
	 * @throws TasksNotInModelException
	 */
	private AbstractPipe rawConnectTasks(AbstractTask parent, AbstractTask child)
			throws TasksNotCompatibleException, TasksNotInModelException {
		return super.connectTasks(parent, child);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.osmui.model.pipelinemodel.AbstractPipelineModel#connectTasks(de.osmui
	 * .model.pipelinemodel.AbstractPipe,
	 * de.osmui.model.pipelinemodel.AbstractPort)
	 */
	public AbstractPipe connectTasks(AbstractPipe output, AbstractPort input)
			throws TasksNotCompatibleException, TasksNotInModelException {
		// Make the normal connection
		AbstractPipe pipe = super.connectTasks(output, input);
		// We need to get the corresponding tasks from our task list to make
		// sure we get the decorated version
		AbstractTask parent = output.getSource();
		AbstractTask child = input.getParent();

		// Setup the jgraphx madness
		Object graphparent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try {
			
			pipeMap.put(Long.valueOf(pipe.getID()), 
					(mxCell) graph.insertEdge(graphparent, null, pipe,
					getCellForTask(parent),
					getCellForTask(child)));
		} finally {
			graph.getModel().endUpdate();
		}
		setChanged();
		notifyObservers(pipe);
		return pipe;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.osmui.model.pipelinemodel.AbstractModel#disconnectTasks(de.osmui.model
	 * .pipelinemodel.AbstractTask, de.osmui.model.pipelinemodel.AbstractTask)
	 */
	@Override
	public AbstractPipe disconnectTasks(AbstractTask parent, AbstractTask child)
			throws TasksNotInModelException {
		AbstractPipe removedPipe = super.disconnectTasks(parent, child);
		Object[] cellArray = { getCellForPipe(removedPipe) };
		graph.removeCells(cellArray);

		return removedPipe;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.osmui.model.pipelinemodel.AbstractModel#isExecutable()
	 */
	@Override
	public boolean isExecutable() {
		// Check whether all pipes are connected
		for(AbstractTask task : tasks){
			// Check inputs
			for(AbstractPort port : task.getInputPorts()){
				if(!port.isConnected()){
					return false;
				}
			}
			
			// Check pipes
			for(AbstractPipe pipe : task.getOutputPipes()){
				if(!pipe.isConnected()){
					return false;
				}
			}
			
			
		}
		
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.osmui.model.pipelinemodel.AbstractModel#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return tasks.isEmpty();
	}

	public void layout(AbstractTask parent) {
		Object graphparent = (parent != null) ? pipeMap.get(Long.valueOf(parent
				.getID())) : graph.getDefaultParent();
		graph.getModel().beginUpdate();
		try {
			lay.execute(graphparent);
		} finally {
			graph.getModel().endUpdate();
		}
	}
	
	public mxCell getCellForTask(AbstractTask task){
		return taskMap.get(Long.valueOf(task.getID()));
	}
	
	public mxCell getCellForPipe(AbstractPipe pipe){
		return pipeMap.get(Long.valueOf(pipe.getID()));
	}

}
