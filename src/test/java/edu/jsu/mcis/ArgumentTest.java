package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

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
	public void testGetArgTypeFloat(){
		Argument s = new Argument("length", "the length of the box", Argument.Type.FLOAT);
		assertEquals(Argument.Type.FLOAT, s.getArgType());
	}
	
	@Test
	public void testGetArgTypeString(){
		Argument s = new Argument("length", "the length of the box");
		assertEquals(Argument.Type.STRING, s.getArgType());
	}
	
	@Test
	public void testGetArgTypeInt(){
		Argument s = new Argument("length", "the length of the box", Argument.Type.INT);
		assertEquals(Argument.Type.INT, s.getArgType());
	}
	
	@Test
	public void testGetArgTypeBoolean(){
		Argument s = new Argument("length", "the length of the box", Argument.Type.BOOLEAN);
		assertEquals(Argument.Type.BOOLEAN, s.getArgType());
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/*@Test
	public void testInvalidArgumentTypeException(){
		thrown.expect(InvalidArgumentTypeException.class);
		thrown.expectMessage("spoon is not a valid argument type.");
		Argument s = new Argument("length", "the length of the box", Argument.Type.SPOON);
		
	}*/
	
	@Test
	public void testGetArgFails(){
		Argument s = new Argument("length", "the length of the box", Argument.Type.FLOAT);
		assertEquals(false, s.equals("hat"));
	}
}