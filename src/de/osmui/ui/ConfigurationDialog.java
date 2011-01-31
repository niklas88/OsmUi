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
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.osmui.i18n.I18N;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena Käfer
 * 
 *         will be tested in the system-test
 */
public class ConfigurationDialog extends JDialog {

	private static final long serialVersionUID = -1901364651992578489L;

	private JDialog dialog;

	public ConfigurationDialog() {
		this.dialog = this;
		setLayout(new BorderLayout());

		JPanel center = new JPanel();
		center.setLayout(new GridLayout(2, 0));

		JPanel osmuiPfad = new JPanel();
		osmuiPfad.setBorder(new EmptyBorder(15, 15, 15, 15));
		osmuiPfad.setLayout(new GridLayout());
		JLabel osmosisPfadLabel = new JLabel(
				I18N.getString("ConfigurationDialog.pathLabel"));
		final JTextField osmosisPfadTextField = new JTextField(
				MainFrame.getInstance().configurationManager.getEntry(
						"OsmosisPath",
						I18N.getString("ConfigurationDialog.osmosisStandardPath")));
		JButton osmosisPfadButton = new JButton(
				I18N.getString("ConfigurationDialog.browse"));
		osmosisPfadButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					osmosisPfadTextField.setText(chooser.getSelectedFile()
							.getAbsolutePath());

				}

			}
		});

		osmuiPfad.add(osmosisPfadLabel);
		osmuiPfad.add(osmosisPfadTextField);
		osmuiPfad.add(osmosisPfadButton);
		center.add(osmuiPfad);


		JPanel autoConf = new JPanel();
		autoConf.setLayout(new FlowLayout());
		JLabel autoConfLabel = new JLabel(I18N.getString("ConfigurationDialog.autoConf"));
		final JCheckBox autoConfCheckBox = new JCheckBox();
		autoConfCheckBox.setSelected(Boolean.valueOf(MainFrame.getInstance().configurationManager.getEntry("AutoConfCheckBox", "true")));
		autoConf.add(autoConfLabel);
		autoConf.add(autoConfCheckBox);
		center.add(autoConf);
		
		add(center, BorderLayout.CENTER);
		JPanel bottom = new JPanel();
		JButton save = new JButton(I18N.getString("ConfigurationDialog.save"));
		save.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().configurationManager.setEntry(
						"OsmosisPath", osmosisPfadTextField.getText());
				dialog.dispose();
				MainFrame.getInstance().configurationManager.setEntry("AutoConfCheckBox", String.valueOf(autoConfCheckBox.isSelected()));
			}
		});
		JButton cancel = new JButton(
				I18N.getString("ConfigurationDialog.cancel"));
		cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		bottom.setLayout(new FlowLayout());
		bottom.add(save);
		bottom.add(cancel);
		add(bottom, BorderLayout.SOUTH);
		pack();
	}
}
