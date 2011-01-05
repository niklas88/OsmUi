package de.osmui.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import de.osmui.ui.events.TaskSelectedEvent;
import de.osmui.ui.events.TaskSelectedEventListener;

public class PipelineBox extends mxGraphComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2865210986243818496L;

	private final ArrayList<TaskSelectedEventListener> selectedListeners;

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

		this.getGraphControl().addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				mxCell cell = (mxCell) getCellAt(e.getX(), e.getY());

				if (cell != null && cell.isVertex()) {
					fireTaskSelected(new TaskSelectedEvent(cell.getValue()));
				} else {
					fireTaskSelected(null);
				}
			}
		});

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

}
