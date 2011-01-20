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

/**
 * 
 */
package de.osmui.model.pipelinemodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;



/**
 * This is the Task class, it's object represent a single task in a pipeline with it's associated parameters
 * and pipes. It also allows navigating the pipeline graph, normally beginning at the source tasks;
 * 
 * @author Niklas Schnelle
 *
 *@see AbstractTaskTest
 */
public abstract class AbstractTask implements Serializable, Identifiable {

	private static final long serialVersionUID = 5857409627267578769L;
	
	protected static long idSeed = 0;

	protected long myId = 0;
	
	protected String name;
	protected AbstractPipelineModel model;
	protected AbstractParameter defaultParameter;
	
	
	/**
	 * Gets the model to which this task belongs, null if the task has not yet been added
	 * to a model or has been removed from a model.
	 * 
	 * @return model this task belongs to
	 */
	public AbstractPipelineModel getModel(){
		return model;
	}
	
	/**
	 * Implement getID from Identifiable
	 */
	public long getID(){
		if(myId == 0){
			return myId = ++idSeed;
		} else {
			return myId;
		}
	}

	
	/**
	 * Sets the model to which this task belongs
	 * 
	 * @param model
	 */
	void setModel(AbstractPipelineModel model){
		this.model = model;
	}
	

	/**
	 * Gets the name of this task e.g. 'read-xml'
	 * @return the name of this task
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * @param the new name
	 */
	void setName(String s){
		this.name=s;
	}
	/**
	 * Sets the default parameter for this task
	 * @param p
	 */
	public void setDefaultParameter(AbstractParameter p){
		defaultParameter = p;
	}
	
	/**
	 * Gets the default parameter for this task
	 * @return the default parameter
	 */
	public AbstractParameter getDefaultParameter(){
		return defaultParameter;
	}
	
	/**
	 * Gets the string representation of this task as used by osmosis
	 */
	public abstract String getCommandlineForm();
	
	/**
	 * Gets the list of Parameters associated with this task 
	 * @return the parameter list or null if no parameters exist
	 */
	public abstract Map<String, AbstractParameter> getParameters();
	
	/**
	 * Gets the list of both connected and unconnected inputPorts
	 * @return list of inputPorts null if this is a source
	 */
	public abstract List<AbstractPort> getInputPorts();
	
	/**
	 * Gets the list of both connected and unconnected outputPipes
	 * @return list of inputPipes null if this is a drain
	 */
	public abstract List<AbstractPipe> getOutputPipes();
	
	/**
	 * Gets whether this task can be connected to or can spawn a connection	 * 
	 * @return
	 */
	public boolean isConnectable(){
		
		// Check whether this task can spawn a connection that is be a source
		for(AbstractPipe pipe: getOutputPipes()){
			if(!pipe.isConnected() || pipe instanceof VariablePipe){
				return true;
			}
		}
		
		// Check if this task can be connected to that is be a target
		for(AbstractPort port: getInputPorts()){
			if(!port.isConnected() || port instanceof VariablePort){				
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public String toString(){
		return getName();
	}

}
