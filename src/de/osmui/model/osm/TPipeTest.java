package de.osmui.model.osm;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

/**
 * 
 * @author verena
 * 
 * @see TPipe
 * 
*/
public class TPipeTest {
	@Test public void setget(){
		TPipe pipe = new TPipe();
		pipe.setCount("value");
		pipe.setDescription("value");
		pipe.setSpecifiedBy("value");
		pipe.setType("type");
		BigInteger value = new BigInteger("10");
		pipe.setIndex(value);
		
		String count = pipe.getCount();
		String desc = pipe.getDescription();
		String spez = pipe.specifiedBy;
		String type = pipe.getType();
		BigInteger big = pipe.getIndex();
		
		assertEquals("value", count);
		assertEquals("value", desc);
		assertEquals("value", spez);
		assertEquals("type", type);
		assertEquals("10", big.toString());
	}
}
