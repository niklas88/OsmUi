/**
 * 
 */
package de.osmui.model.pipelinemodel;

import java.util.List;
import java.util.Map;

import com.mxgraph.model.mxCell;

/**
 * This Class implements the Decorator design pattern to wrap any subclass of AbstractTask so that it can be used
 * in a JGraphx based JGPipelineModel and store which cell it belongs to and other JGraphx specific data
 * it overwrites equals and hashCode so that it can be found in the model with the equals method of the user object
 * 
 * @author Niklas Schnelle
 *
 *wird nicht getestet, da nur getter und setter
 */
public class JGTaskDecorator extends AbstractTask {

	private static final long serialVersionUID = -5676949819875895046L;

	protected AbstractTask decoratedTask;
	protected mxCell cell;
	
	public JGTaskDecorator(AbstractTask toDecorate){
		this.decoratedTask = toDecorate;
		//Now we also need to decorate all pipes, make it look really nice :-)
		List<AbstractPipe> pipes = this.decoratedTask.getOutputPipes();
		JGPipeDecorator jgpipe;
		for(int i=0; i<pipes.size(); ++i){
			jgpipe = new JGPipeDecorator(pipes.get(i));
			pipes.set(i, jgpipe);
		}
		//Needs to be set explicitly
		this.cell = null;
	}
	/**
	 * Sets the mxCell associated with this JGTaskDecorator
	 * 
	 * @param cell
	 */
	public void setCell(mxCell cell){
		this.cell = cell;
	}
	/**
	 * Gets the mxCell object associated with this JGTaskDecorator
	 * 
	 * @return the mxCell Object
	 */
	public mxCell getCell(){
		return cell;
	}
	
	/**
	 * Undecorates this task, that is it returns the original AbstracTask object allowing to access subclass methods
	 * that may exist. NOTE: The task might still contain decorated pipes
	 */
	public AbstractTask undecorate(){
		return decoratedTask;
	}
	
	/** The following methods must be forwarded to the encapsulated Task **/
	
	/*
	 * (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractTask#getDefaultParameter()
	 */
	@Override
	public AbstractParameter getDefaultParameter(){
		return decoratedTask.getDefaultParameter();
	}
	
	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractTask#getParameters()
	 */
	@Override
	public Map<String, AbstractParameter> getParameters() {
		return decoratedTask.getParameters();
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractTask#getInputPipes()
	 */
	@Override
	public List<AbstractPort> getInputPorts() {
		return decoratedTask.getInputPorts();
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractTask#getOutputPipes()
	 */
	@Override
	public List<AbstractPipe> getOutputPipes() {
		return decoratedTask.getOutputPipes();
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractTask#getModel()
	 */
	public AbstractPipelineModel getModel(){
		return decoratedTask.getModel();
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractTask#setModel(de.osmui.model.pipelinemodel.AbstractPipelineModel)
	 */
	public void setModel(AbstractPipelineModel model){
		decoratedTask.setModel(model);
	}
	

	/*
	 * (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractTask#getName()
	 */
	@Override
	public String getName(){
		return decoratedTask.getName();
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractTask#setName(java.lang.String)
	 */
	@Override
	public void setName(String s){
		decoratedTask.setName(s);
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractTask#setDefaultParameter(de.osmui.model.pipelinemodel.AbstractParameter)
	 */
	@Override
	public void setDefaultParameter(AbstractParameter p){
		decoratedTask.setDefaultParameter(p);
	}
	/*
	 * (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractTask#getCommandlineForm()
	 */
	@Override
	public String getCommandlineForm() {
		return decoratedTask.getCommandlineForm();
	}

	/**
	 * We override equals so that this decorator can be associated with references to the undecorated object easily
	 * @param the object to compare with
	 */
	//@Override
	public boolean equals(Object o){
		return (o != null)? o.hashCode()==decoratedTask.hashCode() : false;
	}
	
	/**
	 * We override hashCode so that this decorator can be associated with references to the undecorated object easily
	 * @param the object to compare with
	 */
	@Override
	public int hashCode(){
		return decoratedTask.hashCode();
	}


}
