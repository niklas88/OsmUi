/**
 * 
 */
package de.osmui.model.pipelinemodel;

/**
 * This class implements a variable pipe, that is a pipe that when already connected clones itself
 * and sets up the connection with the clone, it also needs to adjust the task parameter indicating
 * it's variability number
 * 
 * @author Niklas Schnelle
 *
 */
public class VariablePipe extends CommonPipe {

	private static final long serialVersionUID = 8630873926692554364L;

	protected IntParameter referencedParam;

	public VariablePipe(AbstractTask owner, IntParameter param, String type) {
		super(owner, type);
	}
	/**
	 * Gets that this is a variable pipe
	 * 
	 */
	@Override
	public boolean isVariable(){
		return true;
	}
	
	/**
	 * This method creates a pipe of the same type as this variable pipe and adds it to
	 * it's source task
	 */
	@Override
	public AbstractPipe createPipe(){
		
		// Clone this pipe, add it to our source and update the right parameter
		AbstractPipe newPipe = new VariablePipe(getSource(), referencedParam, getType());
		getSource().getOutputPipes().add(newPipe);
		referencedParam.setValueInteger(referencedParam.getValueInteger()+1);
	
		return newPipe;
	}

}
