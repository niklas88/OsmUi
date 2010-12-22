package de.osmui.model.pipelinemodel;

/**
 * This class represents a dataset pipe, that is a connection between to tasks carrying an dataset stream
 * 
 * @author Niklas Schnelle
 *
 */
public class DatasetPipe extends AbstractPipe {

	@Override
	public String getType() {
		return "dataset";
	}

}
