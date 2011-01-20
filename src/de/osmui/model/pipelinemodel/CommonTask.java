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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a general implementation of an AbstractTask
 * 
 * @author Niklas Schnelle
 *
 *@see CommonTaskTest
 */
public class CommonTask extends AbstractTask {


	private static final long serialVersionUID = -5302045303266284628L;
	
	protected HashMap<String, AbstractParameter> parameters;
	protected ArrayList<AbstractPort> inputPorts;
	protected ArrayList<AbstractPipe> outputPipes;

	public CommonTask(String s) {
		name = s;
		parameters = new HashMap<String, AbstractParameter>();
		inputPorts = new ArrayList<AbstractPort>();
		outputPipes = new ArrayList<AbstractPipe>();
	}
	/*
	 * (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractTask#getCommandlineForm()
	 */
	@Override
	public String getCommandlineForm() {
		StringBuilder sb = new StringBuilder("--");
		sb.append(getName());
		// Add the parameters, leaving out the ones that have default value
		Collection<AbstractParameter> pList = getParameters().values();
		for(AbstractParameter param : pList){
			if(!param.isDefaultValue()){
				sb.append(" ");
				sb.append(param.getCommandlineForm());
			}
		}
		sb.append(" ");
		
		// Incoming pipes first
		AbstractPipe incoming;
		int num = 0;
		for(AbstractPort port : this.getInputPorts()){
			if(port.isConnected()){
				incoming = port.getIncoming();
				sb.append("inPipe.");
				sb.append(num);
				sb.append("=");
				if(incoming.isNamed()){
					sb.append(incoming.getName());
				} else {
					sb.append("AUTO");
					sb.append(incoming.getID());
					sb.append("to");
					sb.append(port.getID());
				}
				sb.append(" ");
			}
			num++;
		}
		
		
		// Write the outgoing pipes
		AbstractPort downPort;
		num = 0;
		for(AbstractPipe pipe : this.getOutputPipes()){
			if(pipe.isConnected()){
				downPort = pipe.getTarget();
				
				// Write the pipe
				sb.append("outPipe.");
				sb.append(num);
				sb.append("=");
				if(pipe.isNamed()){
					sb.append(pipe.getName());
				} else {
					sb.append("AUTO");
					sb.append(pipe.getID());
					sb.append("to");
					sb.append(downPort.getID());
				}
				sb.append(" ");
			}
			num++;
		}
		return sb.toString();
	}
	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractTask#getParameters()
	 */
	@Override
	public Map<String, AbstractParameter> getParameters() {
		return parameters;
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractTask#getInputPorts()
	 */
	@Override
	public List<AbstractPort> getInputPorts() {		
		return inputPorts;
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractTask#getOutputPipes()
	 */
	@Override
	public List<AbstractPipe> getOutputPipes() {
		return outputPipes;
	}

}
