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

package de.osmui.ui;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import de.osmui.model.pipelinemodel.AbstractTask;
import de.osmui.model.pipelinemodel.JGPipelineModel;
import de.osmui.ui.events.TaskSelectedEvent;
import de.osmui.ui.events.TaskSelectedEventListener;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena Käfer
 * 
 * will be tested by system-tests
 * 
 */

public class PipelineBox extends mxGraphComponent implements Observer, MouseListener {

	private static final long serialVersionUID = -2865210986243818496L;

	private final ArrayList<TaskSelectedEventListener> selectedListeners;
	private AbstractTask selectedTask;

	public PipelineBox(mxGraph graph) {
		super(graph);
		this.selectedListeners = new ArrayList<TaskSelectedEventListener>();

		this.graph.setAllowDanglingEdges(false);
		this.graph.setAllowLoops(false);
		this.graph.setAutoSizeCells(true);
		this.graph.setCellsBendable(false);
		this.graph.setCellsMovable(true);
		this.graph.setCellsResizable(false);
		this.graph.setEdgeLabelsMovable(false);
		this.graph.setDropEnabled(false);
		
		this.getGraphControl().addMouseListener(this);

		this.setAutoExtend(true);
		this.setAntiAlias(true);
		this.setAutoScroll(true);
		this.setAutoscrolls(true);
		this.setFoldingEnabled(false);
		this.setDoubleBuffered(true);
		this.setImportEnabled(false);
		this.setExportEnabled(false);

		// Register Keyboard Actions
		this.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke("DELETE"), "deleteCell");

		this.getActionMap().put("deleteCell", new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				final mxGraph graph = getGraph();
				// Removes the selected Cells
				// Had a concurrent modification exception here doesn't
				// seem to run in the Event thread by default so make it run there
				SwingUtilities.invokeLater(new Runnable(){

					@Override
					public void run() {
						graph.removeCells();						
					}
					
				});

			}
		});

	}
	
		
	@Override
	public void selectCellForEvent(Object cell, MouseEvent e) {
		super.selectCellForEvent(cell, e);
		mxCell mxcell = (mxCell) cell;

		if (mxcell != null && mxcell.isVertex()) {
			fireTaskSelected(new TaskSelectedEvent(mxcell.getValue()));
		} else {
			fireTaskSelected(null);
		}
	}

	public void registerTaskSelectedListener(TaskSelectedEventListener l) {
		selectedListeners.add(l);
	}

	public void removeTaskSelectedListener(TaskSelectedEventListener l) {
		selectedListeners.remove(l);
	}

	public void fireTaskSelected(TaskSelectedEvent e) {
		for (TaskSelectedEventListener listener : selectedListeners) {
			listener.TaskSelected(e);
		}
	}

	/**
	 * This is from the Observer interface we react to model changes here the
	 * model notifies it's observers with AbstractTask objects when they are
	 * added
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

		if (arg1 instanceof AbstractTask) {
			AbstractTask task = (AbstractTask) arg1;
			// If the model is null the task was removed
			if (task.getModel() != null && !task.equals(selectedTask)) {
				this.graph.setSelectionCell(((JGPipelineModel) arg0)
						.getCellForTask(task));
				fireTaskSelected(new TaskSelectedEvent(task));
				selectedTask = task;
			} else if (task.getModel() == null){
				selectedTask = null;
				// Sadly sources for event's can't be null let the event be null
				fireTaskSelected(null);
			}
		}

	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(getCellAt(arg0.getX(), arg0.getY()) == null){
			fireTaskSelected(null);
		}		
	}
	
	// Need to specify the following methods but don't care 
	// for the events so do nothing

	@Override
	public void mouseEntered(MouseEvent arg0) {}


	@Override
	public void mouseExited(MouseEvent arg0) {}


	@Override
	public void mousePressed(MouseEvent arg0) {}


	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
