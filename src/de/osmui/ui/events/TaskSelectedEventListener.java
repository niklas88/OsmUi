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
package de.osmui.ui.events;

/**
 * This interface specifies the methods needed to register for TaskSelectedEvents
 * 
 * @author Niklas Schnelle, Peter Vollmer, Verena Käfer
 *
 */
public interface TaskSelectedEventListener {
	/**
	 * This method is called when a new task was selected or no task was selected, in this case e==null
	 * @param e
	 */
	public void TaskSelected(TaskSelectedEvent e);
}
