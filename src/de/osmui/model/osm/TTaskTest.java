package de.osmui.model.osm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.osmui.i18n.I18N;

/**
* @see TTask
*/
public class TTaskTest {
	@Test public void getInputPip(){
		TTask task = new TTask();
		task.inputPipe = null;
		ArrayList<TPipe> list = new ArrayList<TPipe>();
		List<TPipe> arr = task.getInputPipe();
		assertEquals("getInputPipe", arr, list);
		
		TPipe e = null;
		list.add(e);
		arr.add(e);
		task.inputPipe = list;
		assertEquals("getInputPipe", arr, list);
	}
	
	@Test public void getOutputPip(){
		TTask task = new TTask();
		task.outputPipe = null;
		ArrayList<TPipe> list = new ArrayList<TPipe>();
		List<TPipe> arr = task.getInputPipe();
		assertEquals("getOutputPipe", arr, list);
		
		TPipe e = null;
		list.add(e);
		arr.add(e);
		task.outputPipe = list;
		assertEquals("getOutputPipe", arr, list);
	}
	
	@Test public void getParameter(){
		TTask task = new TTask();
		task.parameter = null;
		ArrayList<TParameter> list = new ArrayList<TParameter>();
		List<TParameter> arr = task.getParameter();
		assertEquals("getParameter", arr, list);
		
		TParameter e = null;
		list.add(e);
		arr.add(e);
		task.parameter = list;
		assertEquals("getInputPipe", arr, list);
	}
	
	@Test public void getsert(){
		TTask task = new TTask();
		task.setDescription("value");
		task.setFriendlyName("value");
		task.setHelpURI("value");
		task.setName("value");
		task.setShortName("value");
		
		String desc = task.getDescription();
		String frname = task.getFriendlyName();
		String help = task.getHelpURI();
		String name = task.getName();
		String shname = task.getShortName();
		
		assertEquals("value", desc);
		assertEquals("value", frname);
		assertEquals("value", help);
		assertEquals("value", name);
		assertEquals("value", shname);
	}
}