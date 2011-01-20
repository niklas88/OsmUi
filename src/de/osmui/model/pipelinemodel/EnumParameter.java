/**
 * 
 */
package de.osmui.model.pipelinemodel;


import java.util.List;

import de.osmui.model.osm.TEnumValue;
import de.osmui.model.osm.TParameter;
import de.osmui.i18n.*;

/**
 * @author Niklas Schnelle
 *
 */
public class EnumParameter extends AbstractParameter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4056315894183855778L;
	
	// The currently set value of this parameter
	protected String value;
	protected List<TEnumValue> enumeration;

	/**
	 * @param desc
	 * @param value
	 */
	public EnumParameter(TParameter desc, String value) {
		super(desc, value);
		enumeration = desc.getEnumValue();
		setValue(value);
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractParameter#getCommandlineForm()
	 */
	@Override
	public String getCommandlineForm() {
		return this.getName()+"="+this.getValue();
	}

	/**
	 * Returns a list of all possible values for this parameter
	 * @param tryFriendly
	 * @return
	 */
	public List<TEnumValue> getEnumerationValues(){
		return enumeration;
	}
	
	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractParameter#getValue()
	 */
	@Override
	public String getValue() {
		return value;
	}
	
	@Override
	public String getDefaultValue(){
		String ret = super.getDefaultValue();
		if(ret==null){
			return enumeration.get(0).getValue();
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see de.osmui.model.pipelinemodel.AbstractParameter#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String s) throws IllegalArgumentException {
		// Check whether s is null and set to a sensible value
		if(s == null){
			value = enumeration.get(0).getValue();
			return;
		}
		// Test whether the requested value is in the Enumeration
		for(TEnumValue val : enumeration){
			if(val.getValue().equals(s)){
				value = s;
				return;
			}
		}
		throw new IllegalArgumentException(I18N.getString("EnumParameter.ValueNotInEnumeration",s));
	}

}
