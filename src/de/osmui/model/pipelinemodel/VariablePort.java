/**
 * 
 */
package de.osmui.model.pipelinemodel;

/**
 * @author niklas
 *
 */
public class VariablePort extends CommonPort {
	protected IntParameter referencedParam;

	public VariablePort(AbstractTask owner, IntParameter param, String type) {
		super(owner, type);
	}
	
	/**
	 * Variable ports are always connectable
	 * @return true
	 */
	@Override
	public boolean isConnectable(){
		return true;
	}
	
	/**
	 * Our version of connect will, if this port is already connected create a copy of this port, connect it
	 * and if successful add it to our owner's ports
	 * 
	 * @param port the port to connect to
	 */
	@Override
	public boolean connect(AbstractPipe pipe){
		boolean success = false;
		if(!this.isConnected()){
			success = connect(pipe);
		} else if(this.getType().equals(pipe.getType())) {
			// Clone this port, add it to our owner's ports and update the right parameter
			AbstractPort newPort = new VariablePort(getParent(), referencedParam, getType());
			success = newPort.connect(pipe);
			if(success){
				getParent().getInputPorts().add(newPort);
				referencedParam.setValueInteger(referencedParam.getValueInteger()+1);
			}
		}
		
		return success;
	}
}
