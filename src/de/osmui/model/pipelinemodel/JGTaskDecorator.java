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
 */
public class JGTaskDecorator extends AbstractTask {
	protected AbstractTask decoratedTask;
	protected mxCell cell;
	
	public JGTaskDecorator(AbstractTask toDecorate){
		this.decoratedTask = toDecorate;
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
	public List<AbstractPipe> getInputPipes() {
		return decoratedTask.getInputPipes();
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractTask#getOutputPipes()
	 */
	@Override
	public List<AbstractPipe> getOutputPipes() {
		return decoratedTask.getOutputPipes();
	}
	/**
	 * We override equals so that this decorator can be associated with references to the undecorated object easily
	 * @param the object to compare with
	 */
	@Override
	public boolean equals(Object o){
		return decoratedTask.equals(o);
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
