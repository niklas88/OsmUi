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

package de.osmui.io;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import de.osmui.i18n.I18N;
import de.osmui.io.exceptions.ExportException;
import de.osmui.model.pipelinemodel.AbstractPipelineModel;
import de.osmui.ui.MainFrame;
import de.osmui.util.CommandlineTranslator;
import de.osmui.util.exceptions.ImportException;

/**
 *@author Niklas Schnelle, Peter Vollmer, Verena käfer
 * 
 * will be tested in the systemtest
*/
public class PipeImEx {

	private static PipeImEx instance;

	// Prevents the creation of the object with other methods
	private PipeImEx() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pipelineModel
	 * @param fileName
	 * @throws ImportException
	 */
	public void importOutOfFile(AbstractPipelineModel pipelineModel,
			String fileName) throws ImportException {

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileName), Charset.forName("UTF-8")));
			String row;
			StringBuilder toParse = null;
			while (reader.ready()) {
				row = reader.readLine();
				int indexOfBegin;
				if (toParse == null) {
					if ((indexOfBegin = row.indexOf("--")) != -1) {
						toParse = new StringBuilder(row.substring(indexOfBegin));
					}
				} else {
					toParse.append(row);
				}
			}

			if (toParse != null) {
				CommandlineTranslator trans = CommandlineTranslator
						.getInstance();
				trans.importLine(pipelineModel, toParse.toString());
			} else {
				throw new ImportException(
						"Es wurde keine importierbare Pipeline gefunden! Bitte die Datei überprüfen oder eine andere Datei");
			}
		} catch (FileNotFoundException e) {
			throw new ImportException(I18N.getString("PipeImEx.fileNotFound"));

		} catch (IOException e) {
			throw new ImportException(e.getLocalizedMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {

					throw new ImportException(e.getLocalizedMessage());
				}
			}
		}
	}

	/**
	 * @param pipelineModel
	 * @param clipBoardToParse
	 * @throws ImportException
	 */
	public void importClipBoard(AbstractPipelineModel pipelineModel,
			Clipboard clipBoardToParse) throws ImportException {
		try {
			StringBuilder toParse = null;
			Transferable transferData = clipBoardToParse.getContents(null);
			for (DataFlavor dataFlavor : transferData.getTransferDataFlavors()) {
				Object content;

				content = transferData.getTransferData(dataFlavor);
				int indexOfBegin;
				if (content instanceof String) {
					if (toParse == null) {
						if ((indexOfBegin = content.toString().indexOf("--")) != -1) {
							toParse = new StringBuilder(content.toString()
									.substring(indexOfBegin));
						}
					} else {
						toParse.append(content);
					}

					break;
				}

			}
			if (toParse != null) {
				CommandlineTranslator trans = CommandlineTranslator
						.getInstance();
				trans.importLine(pipelineModel, toParse.toString());
			} else {
				throw new ImportException(
						"Es wurde keine importierbare Pipeline im ClipBoard gefunden!");
			}
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			throw new ImportException(e.getLocalizedMessage());
		}

	}

	/**
	 * @param pipelineModel
	 * @param fileName
	 * @throws ImportException
	 */
	public void export(AbstractPipelineModel pipelineModel, String fileName,
			String extension) throws ExportException {
		System.out.println(extension);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileName+extension), Charset.forName("UTF-8")));
			StringBuilder commandToExport = new StringBuilder();
			CommandlineTranslator trans = CommandlineTranslator.getInstance();
			if (extension == ".bat") {
				commandToExport
						.append(MainFrame
								.getInstance()
								.getConfigurationManager()
								.getEntry(
										"OsmosisPath",
										I18N.getString("ConfigurationDialog.osmosisStandardPath")));
				commandToExport.append(" ");
				commandToExport.append(trans.exportLine(pipelineModel));
				writer.write(commandToExport.toString());

			} else {
				writer.write("#!/bin/sh\n");
				writer.write("# "+
						"Hier kommen noch schöne internationale Kommentare hin\n");
				commandToExport
						.append(MainFrame
								.getInstance()
								.getConfigurationManager()
								.getEntry(
										"OsmosisPath",
										I18N.getString("ConfigurationDialog.osmosisStandardPath")));
				commandToExport.append(" ");
				commandToExport.append(trans.exportLine(pipelineModel));
				writer.write(commandToExport.toString());
			}
		} catch (FileNotFoundException e) {
			throw new ExportException(I18N.getString("PipeImEx.fileNotFound"));
		} catch (IOException e) {
			throw new ExportException(e.getLocalizedMessage());
		}finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				throw new ExportException(e.getLocalizedMessage());
			}
		}

	}

	/*	*//**
	 * Stores the to-do entries in the file with the given name.
	 * 
	 * @param fileName
	 *            of the file to write the to-do entries to.
	 * @param entries
	 *            to save to a file.
	 * @throws ApplicationException
	 *             if storing fails
	 */
	/*
	 * protected static void store(String fileName, Vector<TodoEntry> entries)
	 * throws ApplicationException { BufferedWriter writer = null; try { if
	 * (entries.size() != 0) { writer = new BufferedWriter(new
	 * OutputStreamWriter(new FileOutputStream(fileName),
	 * Charset.forName("UTF-8"))); StringBuilder strBldr = new StringBuilder();
	 * for (TodoEntry entry : entries) { strBldr.delete(0, strBldr.length());
	 * strBldr.append(Locale.getDefault().getLanguage()); strBldr.append('\t');
	 * strBldr.append(entry.getDueDate()); strBldr.append('\t');
	 * strBldr.append(entry.getDescription()); strBldr.append('\t');
	 * strBldr.append(entry.getSeverity()); writer.write(strBldr.toString());
	 * writer.newLine(); } } else { throw new ApplicationException(I18N
	 * .getMessage("No_Entries_To_Save")); } } catch (IOException e) { throw new
	 * ApplicationException(e.getLocalizedMessage());
	 * 
	 * } finally { try { if (writer != null) { writer.close(); } } catch
	 * (IOException e) { throw new
	 * ApplicationException(e.getLocalizedMessage()); } } }
	 */

	// A access method on class level, which creates only once a instance a
	// concrete object
	// in a session of OsmUi and returns it.
	public static PipeImEx getInstance() {
		if (PipeImEx.instance == null) {
			PipeImEx.instance = new PipeImEx();
		}
		return PipeImEx.instance;
	}
}
