/**
 * 
 */
package de.osmui.model.pipelinemodel;

import de.osmui.model.osm.TParameter;

/**
 * This parameter defines a range of floating point numbers, primarily for boundig-box
 * e.g. for left -180.0 to 180.0
 * 
 * @author Niklas Schnelle
 *
 */
public class FloatInRangeParameter extends AbstractParameter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1954522899689855574L;
	
	protected double lowerBound;
	protected double upperBound;
	protected double value;

	/**
	 * Creates a new FloatInRangeParameter
	 * 
	 * @param desc
	 * @param value
	 */
	public FloatInRangeParameter(TParameter desc, String value) {
		super(desc, value);
		lowerBound = Double.NEGATIVE_INFINITY;
		upperBound = Double.POSITIVE_INFINITY;
		setValue(value);
	}
	
	public FloatInRangeParameter(TParameter desc, String value, double lowerBound, double upperBound){
		super(desc, value);
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		setValue(value);
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractParameter#getCommandlineForm()
	 */
	@Override
	public String getCommandlineForm() {
		return this.getName()+"="+this.getValue();
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractParameter#getValue()
	 */
	@Override
	public String getValue() {
		return Double.toString(value);
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractParameter#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String s) throws IllegalArgumentException {
		double val = Double.valueOf(s);
		if(val < lowerBound || val > upperBound){
			throw new IllegalArgumentException("Value not in range ("+lowerBound+","+upperBound+")");
		}
		value = val;
	}
	
	
	public void setValue(double val){
		if(val < lowerBound || val > upperBound){
			//TODO
			throw new IllegalArgumentException("Value not in range ("+lowerBound+","+upperBound+")");
		}
		value = val;
	}

}
