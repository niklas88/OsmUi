/**
 * 
 */
package de.osmui.model.pipelinemodel;

/**
 * This interface is used as a way for a class to signal, that it provides
 * a method to distinguish any one object of a class from any other.
 * That is during the course of execution
 * 
 * @author Niklas Schnelle
 *
 */
public interface Identifiable {
	/**
	 * Returns the unique ID of an implementing object, it only has to be unique
	 * to for all objects of the implementing class and it's subclasses.
	 * @return
	 */
	public long getID();
}
