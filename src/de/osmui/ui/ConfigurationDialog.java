package de.osmui.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.osmui.i18n.I18N;

public class ConfigurationDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1901364651992578489L;

	private JDialog dialog;

	public ConfigurationDialog() {
		this.dialog = this;
		setLayout(new BorderLayout());

		JPanel center = new JPanel();
		center.setBorder(new EmptyBorder(15, 15, 15, 15));
		center.setLayout(new GridLayout());
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

		center.add(osmosisPfadLabel);
		center.add(osmosisPfadTextField);
		center.add(osmosisPfadButton);
		add(center, BorderLayout.CENTER);

		JPanel bottom = new JPanel();
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().configurationManager.setEntry(
						"OsmosisPath", osmosisPfadTextField.getText());
				dialog.dispose();
			}
		});
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		bottom.setLayout(new FlowLayout());
		bottom.add(ok);
		bottom.add(cancel);
		add(bottom, BorderLayout.SOUTH);
		pack();
	}

}
