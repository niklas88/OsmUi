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
 * @see ObjectFactory
 * 
*/

package de.osmui.model.osm;

import static org.junit.Assert.assertEquals;

import javax.xml.bind.JAXBElement;

import org.junit.Test;


public class ObjectFactoryTest {
	@Test public void createParameter(){
		TParameter value = null;
		ObjectFactory ob = new ObjectFactory();
		JAXBElement<TParameter> test = ob.createParameter(value);
		assertEquals("createParameter",test.getValue(),null);
	}
	
	@Test public void createTaskGroup(){
		TTaskGroup value = null;
		ObjectFactory ob = new ObjectFactory();
		JAXBElement<TTaskGroup> test = ob.createTaskGroup(value);
		assertEquals("createTaskGroup",test.getValue(),null);
	}
	
	@Test public void createTask(){
		TTask value = null;
		ObjectFactory ob = new ObjectFactory();
		JAXBElement<TTask> test = ob.createTask(value);
		assertEquals("createTask",test.getValue(),null);
	}
	
	@Test public void createInputPipe(){
		TPipe value = null;
		ObjectFactory ob = new ObjectFactory();
		JAXBElement<TPipe> test = ob.createInputPipe(value);
		assertEquals("createInputPipe",test.getValue(),null);
	}
	
	@Test public void createOutputPipe(){
		TPipe value = null;
		ObjectFactory ob = new ObjectFactory();
		JAXBElement<TPipe> test = ob.createOutputPipe(value);
		assertEquals("createOutputPipe",test.getValue(),null);
	}
	
	@Test public void create(){
		ObjectFactory ob = new ObjectFactory();
		ob.createTParameter();
		ob.createTTaskGroup();
		ob.createTPipe();
		ob.createTEnumValue();
		ob.createOsmosisTaskDescription();
		ob.createTTask();
	}
}
