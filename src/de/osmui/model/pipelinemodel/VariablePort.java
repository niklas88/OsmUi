/**
 * 
 */
package de.osmui.model.pipelinemodel;

/**
 * @author Niklas Schnelle
 *
 */
public class VariablePort extends CommonPort {
	

	private static final long serialVersionUID = 1526665480238004437L;

	protected IntParameter referencedParam;

	public VariablePort(AbstractTask owner, IntParameter param, String type) {
		super(owner, type);
		referencedParam = param;
	}
	
	/**
	 * Gets the referenced parameter that specifies the number of ports
	 * @return
	 */
	public IntParameter getReferencedParam(){
		return referencedParam;
	}
	
	/**
	 * Gets whether this pipe is variable
	 * 
	 * @return true
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
	public AbstractPort createPort(){
		
		// Clone this pipe, add it to our source and update the right parameter
		AbstractPort newPort = new VariablePort(getParent(), referencedParam, getType());
		getParent().getInputPorts().add(newPort);
		referencedParam.setValueInteger(referencedParam.getValueInteger()+1);
	
		return newPort;
	}
}
