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
import java.text.ParseException;

import de.osmui.i18n.I18N;
import de.osmui.io.exceptions.ExportException;
import de.osmui.model.pipelinemodel.AbstractPipelineModel;
import de.osmui.ui.Application;
import de.osmui.ui.MainFrame;
import de.osmui.util.CommandlineTranslator;
import de.osmui.util.ConfigurationManager;
import de.osmui.util.exceptions.ImportException;

/**
 * @author Niklas Schnelle, Peter Vollmer, Verena Käfer
 * 
 * will be tested in the systemtest
 * 
 * Provides the practicality to export to a file and import
 * Osmosis commands to given pipeline Model 
 **/
public class PipeImEx {

	private static PipeImEx instance;

	// Prevents the creation of the object with other methods
	private PipeImEx() {
	}

	/**
	 * @param pipelineModel
	 * @param fileName
	 * @throws ImportException
	 */
	public void importOutOfFile(AbstractPipelineModel pipelineModel,
			String fileName) throws ImportException, ParseException {

		BufferedReader reader = null;
		char escapeChar = (fileName.endsWith(".bat"))? '^' : '\\';
		
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
						// Add the newline back, removed by readLine
						// This helps the translator deal with escapes
						toParse.append('\n');
					}
				} else {
					toParse.append(row);
					// Add the newline back, removed by readLine
					// This helps the translator deal with escapes
					toParse.append('\n');
				}
			}

			if (toParse != null) {
				CommandlineTranslator trans = CommandlineTranslator
						.getInstance();
				trans.importLine(pipelineModel, toParse.toString(), escapeChar);
			} else {
				throw new ImportException(
						I18N.getString("PipeImEx.noImportablePipeFoundFile"));
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
			Clipboard clipBoardToParse) throws ImportException, ParseException {
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
				// Remove \r from DOS line endings
				char currChar;
				for(int pos=0; pos < toParse.length(); ++pos){
					currChar = toParse.charAt(pos);
					if(currChar == '\r'){
						toParse.deleteCharAt(pos);
					}
				}
				CommandlineTranslator trans = CommandlineTranslator
						.getInstance();
				trans.importLine(pipelineModel, toParse.toString(), '\\');
			} else {
				throw new ImportException(
						I18N.getString("PipeImEx.noImportablePipeFoundClipboard"));
			}
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw new ImportException(e.getLocalizedMessage());
		}

	}

	/**
	 * @param pipelineModel
	 * @param fileName
	 * @param extension
	 * @throws ExportException
	 */
	public void export(AbstractPipelineModel pipelineModel, String filename,
			String extension) throws ExportException {
				
		if (!filename.endsWith(extension)) {
			filename = filename +extension;
		}
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filename), Charset.forName("UTF-8")));
			StringBuilder commandToExport = new StringBuilder();
			CommandlineTranslator trans = CommandlineTranslator.getInstance();
			if (extension == ".bat") {
				writer.write(I18N.getString("IO.sh", "rem", "\r\n", Application.version));
				commandToExport
						.append(ConfigurationManager
								.getInstance()
								.getEntry(
										"OsmosisPath",
										I18N.getString("ConfigurationDialog.osmosisStandardPath")));
				commandToExport.append(" ");
				commandToExport.append(trans.exportLine(pipelineModel,"^\r\n"));
				writer.write(commandToExport.toString());

			} else {
				writer.write("#!/bin/sh\n");
				writer.write(I18N.getString("IO.sh", "#", "\n", Application.version));
				commandToExport
						.append(ConfigurationManager.getInstance()
								.getEntry(
										"OsmosisPath",
										I18N.getString("ConfigurationDialog.osmosisStandardPath")));
				commandToExport.append(" ");
				commandToExport.append(trans.exportLine(pipelineModel, "\\\n"));
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
