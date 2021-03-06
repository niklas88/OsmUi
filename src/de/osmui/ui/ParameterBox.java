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

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import de.osmui.model.pipelinemodel.AbstractTask;
import de.osmui.model.pipelinemodel.parameters.AbstractParameter;
import de.osmui.model.pipelinemodel.parameters.BBoxPseudoParameter;
import de.osmui.model.pipelinemodel.parameters.BooleanParameter;
import de.osmui.model.pipelinemodel.parameters.DirParameter;
import de.osmui.model.pipelinemodel.parameters.EnumParameter;
import de.osmui.model.pipelinemodel.parameters.FileParameter;
import de.osmui.ui.events.TaskSelectedEvent;
import de.osmui.ui.events.TaskSelectedEventListener;
import de.osmui.ui.models.ParameterBoxTableModel;
import de.osmui.ui.renderers.BBoxCellEditor;
import de.osmui.ui.renderers.BooleanParamEditor;
import de.osmui.ui.renderers.BooleanParamRenderer;
import de.osmui.ui.renderers.DefaultParamEditor;
import de.osmui.ui.renderers.DefaultParamRenderer;
import de.osmui.ui.renderers.DirParamEditor;
import de.osmui.ui.renderers.EnumParamEditor;
import de.osmui.ui.renderers.FileParamEditor;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena Käfer
 *
 * Provides ParameterBox to have an easy way to construct the parameter
 * box of OsmUi
 * 
 * will be tested by system-tests
 * 
 */

public class ParameterBox extends JTable implements TaskSelectedEventListener {

	private static final long serialVersionUID = 2965036681048549811L;

	private final ParameterBoxTableModel model;

	private AbstractTask selectedTask = null;
	
	private CopyBox copyBox;



	
	public ParameterBox(ParameterBoxTableModel parameterBoxTableModel, CopyBox cb) {
		copyBox = cb;
		model = parameterBoxTableModel;
		this.setAutoCreateRowSorter(true);
		DefaultParamRenderer defaultParamRenderer = new DefaultParamRenderer();
		
		this.setDefaultRenderer(AbstractParameter.class, defaultParamRenderer);
		this.setDefaultEditor(AbstractParameter.class, new DefaultParamEditor());
		
		this.setDefaultRenderer(BooleanParameter.class, new BooleanParamRenderer(getDefaultRenderer(Boolean.class)));
		this.setDefaultEditor(BooleanParameter.class, new BooleanParamEditor());
		
		this.setDefaultRenderer(EnumParameter.class,defaultParamRenderer);
		this.setDefaultEditor(EnumParameter.class, new EnumParamEditor());
		
		this.setDefaultRenderer(FileParameter.class,defaultParamRenderer);
		this.setDefaultEditor(FileParameter.class, new FileParamEditor());
		
		this.setDefaultRenderer(DirParameter.class, defaultParamRenderer);
		this.setDefaultEditor(DirParameter.class, new DirParamEditor());
		
		this.setDefaultRenderer(BBoxPseudoParameter.class, new BBoxCellEditor());
		this.setDefaultEditor(BBoxPseudoParameter.class, new BBoxCellEditor());
		
		this.setModel(parameterBoxTableModel);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		

		showActualParameters(null);
	}

	public void showActualParameters(AbstractTask task) {
		model.setTask(task);
	}

	@Override
	public void TaskSelected(TaskSelectedEvent e) {
		if (e.getTask() != null) {
			selectedTask = (AbstractTask) e.getTask();
			showActualParameters(selectedTask);
			this.getRowSorter().toggleSortOrder(1);
		}
	}
	
	@Override
	 public TableCellRenderer getCellRenderer(int row, int column) {
		  Object value = getValueAt(row,column);
		  if (value !=null) {
		    return getDefaultRenderer(value.getClass());
		  }
		  return super.getCellRenderer(row,column);
	 }
	
	@Override
	public TableCellEditor getCellEditor(int row, int column){
	  Object value = getValueAt(row,column);
	  if (value !=null) {
	    return getDefaultEditor(value.getClass());
	  }
	  return super.getCellEditor(row,column);
	}
	
	@Override
	public void	tableChanged(TableModelEvent e) {
		super.tableChanged(e);
		//  Update the copy box
		if(copyBox != null){
			copyBox.update();
		}
	}

}
