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
	protected IntParameter referencedParam;

	public VariablePipe(AbstractTask owner, IntParameter param, String type) {
		super(owner, type);
	}
	
	/**
	 * Variable pipes are always connectable
	 * @return true
	 */
	@Override
	public boolean isConnectable(){
		return true;
	}
	
	/**
	 * Our version of connect will, if this pipe is already connected create a copy of this pipe, connect it
	 * and if successful add it to our sources pipes
	 * 
	 * @param port the port to connect to
	 */
	@Override
	public boolean connect(AbstractPort port){
		boolean success = false;
		if(!this.isConnected()){
			success = connect(port);
		} else if(this.getType().equals(port.getType())) {
			// Clone this pipe, add it to our source and update the right parameter
			AbstractPipe newPipe = new VariablePipe(getSource(), referencedParam, getType());
			success = newPipe.connect(port);
			if(success){
				getSource().getOutputPipes().add(newPipe);
				referencedParam.setValueInteger(referencedParam.getValueInteger()+1);
			}
		}
		
		return success;
	}

}
