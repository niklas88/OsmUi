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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import de.osmui.i18n.I18N;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 * 
 *         will be tested by system-tests
 */

public class TabBox extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2984123985661193020L;

	private final TaskBox taskBox;
	private final ParameterBox paramBox;


	
	public TabBox(TaskBox tb, ParameterBox pb) {
		this.taskBox = tb;
		this.paramBox = pb;
		this.setTabPlacement(JTabbedPane.TOP);
		this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		JPanel taskTab = new JPanel();
		taskTab.setLayout(new BorderLayout());

		JScrollPane taskScrollPane = new JScrollPane(taskBox);
		JScrollPane paramScrollPane = new JScrollPane(paramBox);

		taskTab.add(taskScrollPane, BorderLayout.CENTER);

		JButton addButton = new JButton(I18N.getString("TabBox.add"));
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (taskBox.getSelectedRow() == -1) {

				}else{
					taskBox.addSelectedToModel();
				}

			}
		});
		taskTab.add(addButton, BorderLayout.SOUTH);

		this.add(I18N.getString("Content.tabBox"), taskTab);

		this.add(I18N.getString("Content.pipelineBox"), paramScrollPane);
	}
}
