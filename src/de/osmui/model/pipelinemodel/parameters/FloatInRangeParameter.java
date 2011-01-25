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
package de.osmui.model.pipelinemodel.parameters;

import de.osmui.i18n.I18N;
import de.osmui.model.osm.TParameter;

/**
 * This parameter defines a range of floating point numbers, primarily for boundig-box
 * e.g. for left -180.0 to 180.0
 * 
 * @author Niklas Schnelle
 * 
 * @see FloatInRangeParameterTest
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
	
	/**
	 * Returns it's value as native double
	 * @return
	 */
	public double getValueDouble(){
		return value;
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractParameter#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String s) throws IllegalArgumentException {
		double val = Double.valueOf(s);
		if(val < lowerBound || val > upperBound){
			throw new IllegalArgumentException(I18N.getString("FloatInRangeParameter.valueNotInRange",lowerBound,upperBound));
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
