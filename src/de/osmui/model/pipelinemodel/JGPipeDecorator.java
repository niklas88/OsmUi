/**
 * 
 */
package de.osmui.model.pipelinemodel;

import com.mxgraph.model.mxCell;

/**
 * This class implements the decorator pattern to make pipe objects useable as edges in the jgraphx based JGPipelineModel
 * 
 * @author Niklas Schnelle
 *
 */
public class JGPipeDecorator extends AbstractPipe {
	
	protected AbstractPipe decoratedPipe;
	//The mxCell for the edge belonging to this pipe, only valid if the pipe is connected.
	protected mxCell cell;
	
	/**
	 * Constructs a JGPipeDecorator from an existing Pipe object
	 * 
	 * @param source
	 */
	public JGPipeDecorator(AbstractPipe toDecorate) {
		this.decoratedPipe = toDecorate;
		// Must be set outside because only there can the geometry be arranged
		this.cell = null;
		
	}
	
	/**
	 * Gets the mxCell object associated with this pipe, should be an edge
	 * 
	 * @return associated cell
	 */
	public mxCell getCell(){
		return cell;
	}
	/**
	 * Sets the mxCell object associated with this pipe object, should be an edge
	 * 
	 * @param cell
	 */
	public void setCell(mxCell cell){
		this.cell = cell;
	}
	
	/**
	 * Undecorates this pipe so that any existing subclass methods might be reached again
	 * 
	 * @return the undecorated pipe
	 */
	public AbstractPipe undecorate(){
		return decoratedPipe;
	}

	@Override
	public String getType() {
		return decoratedPipe.getType();
	}

}
