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
		assertEquals("length", s.getName());
	}
	
	@Test
	public void testSetDescription(){
		Argument s = new Argument("length");
		s.setDescription("the length of the box");
		assertEquals("the length of the box", s.getDescription());
	}
	
	@Test
	public void testSetValue(){
		Argument s = new Argument("length");
		s.setValue("17");
		assertEquals("17", s.getValue());
	}
	
	@Test
	public void testGetNameAndDescription(){
		Argument s = new Argument("length");
		s.setDescription("the length of the box");
		assertEquals("length the length of the box", s.getNameAndDescription());
	}
	
	@Test
	public void testGetArgType(){
		Argument s = new Argument("length", "the length of the box", "FLOAT");
		assertEquals(Argument.Type.FLOAT, s.getArgType());
	}
}