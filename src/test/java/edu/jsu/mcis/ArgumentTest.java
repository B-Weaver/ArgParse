package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentTest{
	
	@Test
	public void testCreateArgWithName(){
		
			Argument s = new Argument("length");
			
			assertEquals("length", s.getName());	
		
		
		
	}
	@Test
	public void testCreateArgWithNameAndValue(){
		
		Argument s = new Argument("length", "17");
		
		assertEquals("length", s.getName());
		assertEquals("17", s.getValue());
	}
}