/**
 * 
 */
package de.osmui.ui.events;

/**
 * This interface specifies the methods needed to register for TaskSelectedEvents
 * 
 * @author Niklas Schnelle
 *
 */
public interface TaskSelectedEventListener {
	/**
	 * This method is called when a new task was selected or no task was selected, in this case e==null
	 * @param e
	 */
	public void TaskSelected(TaskSelectedEvent e);
}
