package de.osmui.ui;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import de.osmui.model.pipelinemodel.AbstractTask;
import de.osmui.model.pipelinemodel.JGPipelineModel;
import de.osmui.model.pipelinemodel.JGTaskDecorator;
import de.osmui.ui.events.TaskSelectedEvent;
import de.osmui.ui.events.TaskSelectedEventListener;

/**
 * 
 *  will be tested by system-tests
 *
 */

public class PipelineBox extends mxGraphComponent implements Observer{

	/**
	 * 
	 */
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
		
		
		this.setAutoExtend(true);
		this.setAntiAlias(true);
		this.setAutoScroll(true);
		this.setAutoscrolls(true);
		this.setFoldingEnabled(false);
		this.setDoubleBuffered(true);
		this.setImportEnabled(false);
		this.setExportEnabled(false);
		
		
		// Register Keyboard Actions
		this.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("DELETE"), "deleteCell");
		
		this.getActionMap().put("deleteCell", new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				final mxGraph graph = getGraph();	
				graph.removeCells();
				
			}
		});
		

	}
	
	@Override
	public void selectCellForEvent(Object cell, MouseEvent e){
		super.selectCellForEvent(cell, e);
		mxCell mxcell = (mxCell)  cell;

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
		for (TaskSelectedEventListener l : selectedListeners) {
			l.TaskSelected(e);
		}
	}
	
	/** This is from the Observer interface we react to model changes here
	 *  the model notifies it's observers with AbstractTask objects when they are added
	 */	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg1 instanceof AbstractTask){
			JGTaskDecorator task = (JGTaskDecorator) arg1;
			if(task.getModel()!= null && !task.equals(selectedTask)){
				this.graph.setSelectionCell(task.getCell());
				fireTaskSelected(new TaskSelectedEvent(task));
				selectedTask = task;
			} else {
				
				this.graph.setSelectionCell(null);
				fireTaskSelected(null);
				selectedTask = null;
			} 			
		} 
		
	}

}
