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
 * @author Niklas Schnelle, Peter Vollmer, Verena käfer
 * 
 */
package de.osmui.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import de.osmui.i18n.I18N;
import de.osmui.io.exceptions.ExportException;
import de.osmui.io.exceptions.LoadException;
import de.osmui.io.exceptions.SaveException;
import de.osmui.model.pipelinemodel.AbstractPipelineModel;
import de.osmui.model.pipelinemodel.JGPipelineModel;

public class IO {
	
	private static IO instance;

	// Prevents the creation of the object with other methods
	private IO() {
	}
	
	public JGPipelineModel load (String filename) throws LoadException{
		try {
			System.out.println("Load: "+filename);
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
			JGPipelineModel model = (JGPipelineModel) in.readObject();
			return model;
		} catch (IOException e){
			throw new LoadException(I18N.getString("IO.loadFormat"));
		} catch (ClassNotFoundException e) {
			throw new LoadException(I18N.getString("IO.loadLoad"));
		}
	}
	
	public void save (AbstractPipelineModel pipelineModel, String filename,String extension ) throws SaveException{
		if (!filename.endsWith(extension)) {
			filename += extension;
		}
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
			out.writeObject(pipelineModel);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new SaveException(I18N.getString("IO.saveFormat"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new SaveException(I18N.getString("IO.saveSave"));
		}
		
	}
	
	// A access method on class level, which creates only once a instance a
	// concrete object
	// in a session of OsmUi and returns it.
	public static IO getInstance() {
		if (IO.instance == null) {
			IO.instance = new IO();
		}
		return IO.instance;
	}
}
