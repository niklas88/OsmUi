/**
 * 
 */
package de.osmui.model.pipelinemodel;

import de.osmui.model.osm.TParameter;

/**
 * This class is used to represent parameters with boolean values
 * 
 * @author Niklas Schnelle
 *
 */
public class BooleanParameter extends AbstractParameter {
	private boolean value;
	
	public BooleanParameter(TParameter desc, String value){
		super(desc, value);
	}

	/* (non-Javadoc)
	 * @see de.osumi.model.pipelinemodel.AbstractParameter#getCommandlineForm()
	 */
	@Override
	public String getCommandlineForm() {
		return this.getName()+'='+this.getValue();
	}

	/* (non-Javadoc)
	 * @see de.osumi.model.pipelinemodel.AbstractParameter#getValue()
	 */
	@Override
	public String getValue() {
		String booleanEncoding = description.getBooleanEncoding();
		
		if(booleanEncoding.equals("truefalse")){
			return (value)?"yes":"no";
		} else {
			return (value)?"true":"false";
		}
	}
	/* (non-Javadoc)
	 * @see de.osumi.model.pipelinemodel.AbstractParameter#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String s) throws IllegalArgumentException {
		String booleanEncoding = description.getBooleanEncoding();
		
		if(booleanEncoding.equals("truefalse")){
			if(s.equals("true")) {
				value=true;
			} else if (s.equals("false")){
				value=false;
			} else {
				throw new IllegalArgumentException();
			}
		} else {
			//yesno is default
			if(s.equals("yes")) {
				value=true;
			} else if (s.equals("no")){
				value=false;
			} else {
				throw new IllegalArgumentException();
			}
		} 
	}
	
	/**
	 * Sets the value to the specified boolean
	 * @param value the value to set
	 */
	public void setValue(boolean value) {
		this.value = value;
	}

	/**
	 * Returns the value as native boolean
	 * @return the value
	 */
	public boolean getValueBoolean() {
		return value;
	}



}
