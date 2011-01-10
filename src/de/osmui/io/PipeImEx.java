package de.osmui.io;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import de.osmui.model.pipelinemodel.AbstractPipelineModel;
import de.osmui.util.CommandlineTranslator;
import de.osmui.util.exceptions.ImportException;

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
			throw new ImportException("File not Found");// I18N.getMessage("File_Not_Found"));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
