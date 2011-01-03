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
		referencedParam = param;
	}
	
	/**
	 * Gets the referenced parameter
	 * @return
	 */
	public IntParameter getReferencedParam(){
		return referencedParam;
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
	 * This method creates a pipe of the same type as this variable pipe 
	 * 
	 */
	@Override
	public AbstractPipe createPipe(){
		
		// Clone this pipe, and update the right parameter
		AbstractPipe newPipe = new VariablePipe(getSource(), referencedParam, getType());
		referencedParam.setValueInteger(referencedParam.getValueInteger()+1);
	
		return newPipe;
	}

	/**
	 * We override disconnect so that it removes this variable instance as long as
	 * there are more then the default value variable instances
	 */
	@Override
	public void disconnect(){
		super.disconnect();
		// As long as the referencedParam is not down to it's default we remove this pipe
		// as it's not longer needed
		if(!referencedParam.isDefaultParam()){
			getSource().getOutputPipes().remove(this);
		}
	}
}
