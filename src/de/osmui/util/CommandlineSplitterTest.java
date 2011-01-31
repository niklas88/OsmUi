package de.osmui.util;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.text.ParseException;

import org.junit.Test;


public class CommandlineSplitterTest {
	@Test public void hasNext(){
		char[] qC = {'\''};
		char eC = '\\';
		CommandlineSplitter splitter = new CommandlineSplitter("hello\\ world 'test'", qC , eC);
		assertEquals(true, splitter.hasNext());
	}
	@Test public void next() throws ParseException{
		char[] qC = {'a', 'b', 'c'};
		char eC = 't';
		CommandlineSplitter splitter = new CommandlineSplitter("hello\\ world 'test'", qC , eC);
		splitter.next();
	}
}
