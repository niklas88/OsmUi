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
 *wird nicht getestet, da nur getter und setter
 */
public class JGPipeDecorator extends AbstractPipe{
	

	private static final long serialVersionUID = -9172610493196783362L;

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
	
	/**
	 * We need to decorate the created pipe
	 */
	@Override
	public AbstractPipe createPipe(){
		return new JGPipeDecorator(decoratedPipe.createPipe());
	}
	/*
	 * The following operations will be handed over to the decorated pipe
	 */
	
	@Override
	public boolean isConnected(){
		return decoratedPipe.isConnected();
	}

	@Override
	public String getType() {
		return decoratedPipe.getType();
	}
	
	@Override
	public boolean isVariable(){
		return decoratedPipe.isVariable();
	}
	
	@Override
	public IntParameter getReferencedParam(){
		return decoratedPipe.getReferencedParam();
	}

	
	@Override
	public boolean connect(AbstractPort target){
		return decoratedPipe.connect(target);
	}
	
	@Override
	public void disconnect(){
		decoratedPipe.disconnect();
	}
	
	@Override
	public boolean isNamed(){
		return decoratedPipe.isNamed();
	}
	
	@Override
	public String getName(){
		return decoratedPipe.getName();
	}
	
	@Override
	public void setName(String s){
		decoratedPipe.setName(s);
	}
	
	@Override
	public AbstractTask getSource(){
		return decoratedPipe.getSource();
	}
	
	@Override
	public AbstractPort getTarget(){
		return decoratedPipe.getTarget();
	}

}
