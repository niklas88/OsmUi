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

package de.osmui.ui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

import de.osmui.model.pipelinemodel.AbstractPipelineModel;
import de.osmui.model.pipelinemodel.AbstractTask;
import de.osmui.util.CommandlineTranslator;


/**
 * 
 * @author Niklas Schnelle, Peter Vollmer
 * 
 *         Provides CopyBox to have an easy way to construct a opportunity to
 *         show a Osmosis-command.
 * 
 * will be tested by system-tests
 */

public class CopyBox extends JScrollPane implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2196616949819474887L;

	private final CommandlineTranslator trans;

	private final AbstractPipelineModel model;

	private JTextField copyBoxTextField;

	/**
	 * Construct a CopyBox to show the parseable part of current pipeline model
	 * as Osmosis-command
	 * 
	 * @param pipelineModel
	 *            to read the dates out.
	 */
	public CopyBox(AbstractPipelineModel pipelineModel) {
		copyBoxTextField = new JTextField();
		copyBoxTextField.setEditable(false);
		trans = CommandlineTranslator.getInstance();
		model = pipelineModel;
		this.setViewportView(copyBoxTextField);
	}

	/**
	 * Updates the Content of the CopyBox.
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof AbstractTask) {
			copyBoxTextField.setText(trans.exportLine(model));
		}
	}
	/**
	 * Updates the copy box
	 */
	public void update(){
		copyBoxTextField.setText(trans.exportLine(model));
	}

}
