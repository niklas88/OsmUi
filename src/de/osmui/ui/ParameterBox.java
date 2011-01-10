package de.osmui.ui;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import de.osmui.model.pipelinemodel.AbstractParameter;
import de.osmui.model.pipelinemodel.AbstractTask;
import de.osmui.ui.events.TaskSelectedEvent;
import de.osmui.ui.events.TaskSelectedEventListener;
import de.osmui.ui.models.ParameterBoxTableModel;
import de.osmui.ui.renderers.DefaultParamEditor;
import de.osmui.ui.renderers.ParamValueRenderer;

public class ParameterBox extends JTable implements TaskSelectedEventListener {

	
	private static final long serialVersionUID = 2965036681048549811L;

	private final ParameterBoxTableModel model;

	private AbstractTask selectedTask = null;

	public ParameterBox(ParameterBoxTableModel parameterBoxTableModel) {
		model = parameterBoxTableModel;
		this.setDefaultRenderer(AbstractParameter.class,  new ParamValueRenderer());
		this.setDefaultEditor(AbstractParameter.class, new DefaultParamEditor());
		this.setModel(parameterBoxTableModel);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		
		showActualParameters(null);
	}

	public void showActualParameters(AbstractTask task) {
		model.setTask(task);
	}

	@Override
	public void TaskSelected(TaskSelectedEvent e) {
		if(e != null){
			selectedTask = (AbstractTask) e.getSource();
			showActualParameters(selectedTask);
		}
	}

}
