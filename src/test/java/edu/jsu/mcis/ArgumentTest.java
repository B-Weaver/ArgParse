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
	public void testCreateArgWithNameAndDescription(){
		
		Argument s = new Argument("length", "the length of the box");
		
		assertEquals("length the length of the box", s.getNameAndDescription());
	}
}