/**
 * 
 */
package de.osmui.util;

import java.util.EventObject;

/**
 * @author Niklas Schnelle
 *
 */
public class ModelChangeEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new ModelChangeEvent
	 * 
	 * @param source
	 */
	public ModelChangeEvent(Object source) {
		super(source);
	}



}
