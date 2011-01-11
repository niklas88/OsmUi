package de.osmui.model.osm;

import static org.junit.Assert.*;

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
