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

/**
 * This interface is used as a way for a class to signal, that it provides
 * a method to distinguish any one object of a class from any other.
 * That is during the course of execution
 * 
 * @author Niklas Schnelle, Peter Vollmer, Verena Käfer
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
