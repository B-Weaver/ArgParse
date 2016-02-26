package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class ArgsParserTest {
	@Test
	public void testNewInstanceHasNoArguments() {
		ArgumentParser p = new ArgumentParser();
		assertEquals(0, p.getNumArguments());
	}
	
	@Test
	public void testArgumentIsAddedCorrectly() {
		ArgumentParser p = new ArgumentParser();
		p.addArg("length");
		assertEquals(1, p.getNumArguments());
	}
	
	@Test
	public void testArgumentValueIsParsedCorrectly() {
		String[] s = {"17"};
		ArgumentParser p = new ArgumentParser();
		p.addArg("length", "the length of the box", "float");
		p.parse(s);
		assertEquals("17", p.getArg("length"));
	}
	
	@Test
	public void testCalculateVolume(){
		String[] s = {"7", "5", "2"};
		ArgumentParser p = new ArgumentParser();
		p.addArg("length", "the length of the box", "float");
		p.addArg("width", "the width of the box", "float");
		p.addArg("height", "the height of the box", "float");
		p.parse(s);
		String len = p.getArg("length");
		String wid = p.getArg("width");
		String hgt = p.getArg("height");
		float length = Float.parseFloat(len);
		float width = Float.parseFloat(wid);
		float height = Float.parseFloat(hgt);
		assertEquals(70.0, length * width * height, 0.00001);
	}
	
	@Test
	public void testsizeEquals(){
		String[] s = {"7", "5", "2"};
		ArgumentParser p = new ArgumentParser();
		p.addArg("length", "the length of the box", "float");
		p.addArg("width", "the width of the box", "float");
		p.addArg("height", "the height of the box", "float");
		p.parse(s);
		String len = p.getArg("length");
		String wid = p.getArg("width");
		String hgt = p.getArg("height");
		float length = Float.parseFloat(len);
		float width = Float.parseFloat(wid);
		float height = Float.parseFloat(hgt);
		assertTrue(s.length == p.getNumArguments());
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testTooManyArguments(){
		String[] s = {"7", "5", "2", "4"};
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addArg("length", "the length of the box", "float");
		p.addArg("width", "the width of the box", "float");
		p.addArg("height", "the height of the box", "float");
		thrown.expect(TooManyArgsException.class);
		thrown.expectMessage("usage: java "+ p.programName + " length width height"+"\n"+"VolumeCalculator.java: error: unrecognized arguments: " + s[3]);
		p.parse(s);		
	}

	@Test
	public void testTooFewArgumentsForZeroArguments(){
		String[] s = new String[0];
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addArg("length", "the length of the box", "float");
		p.addArg("width", "the width of the box", "float");
		p.addArg("height", "the height of the box", "float");
		thrown.expect(TooFewArgsException.class);
		thrown.expectMessage("usage: java "+ p.programName + " length width height\nVolumeCalculator.java: error: the following arguments are required: length, width, height");
		p.parse(s);
	}
	
	@Test
	public void testTooFewArgumentsForOneArgument(){
		String[] s = {"7"};
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addArg("length", "the length of the box", "float");
		p.addArg("width", "the width of the box", "float");
		p.addArg("height", "the height of the box", "float");
		thrown.expect(TooFewArgsException.class);
		thrown.expectMessage("usage: java "+ p.programName + " length width height\nVolumeCalculator.java: error: the following arguments are required: width, height");
		p.parse(s);
	}
	
	@Test
	public void testTooFewArgumentsForTwoArguments(){
		String[] s = {"7", "5"};
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addArg("length", "the length of the box");
		p.addArg("width", "the width of the box");
		p.addArg("height", "the height of the box");
		thrown.expect(TooFewArgsException.class);
		thrown.expectMessage("usage: java "+ p.programName + " length width height\nVolumeCalculator.java: error: the following arguments are required: height");
		p.parse(s);
	}
	
	@Test
	public void testHelpMessage(){
		String [] s = {"-h"};
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addArg("length", "the length of the box (float)", "float");
		p.addArg("width", "the width of the box (float)", "string");
		p.addArg("height", "the height of the box (float)", "int");
		thrown.expect(GetHelpException.class);
		thrown.expectMessage("usage: java "+ p.programName + " length width height\n" + p.programPurpose + "\npositional arguments:\nlength the length of the box (float)\nwidth the width of the box (float)\nheight the height of the box (float)");
		p.parse(s);
	}
	
	@Test
	public void testInvalidValueMessageFloat(){
		String[] s = {"7", "something", "2"};
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addArg("length", "the length of the box", "STRING");
		p.addArg("width", "the width of the box", "FLOAT");
		p.addArg("height", "the height of the box", "int");
		thrown.expect(InvalidValueException.class);
		thrown.expectMessage("usage: java " + p.programName + p.getAllArgNames() + "\n" + p.programName +".java: error: argument width: invalid float value: something");
		p.parse(s);
	}
	
	@Test
	public void testInvalidValueMessageInt(){
		String[] s = {"7", "something", "2"};
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addArg("length", "the length of the box", "INT");
		p.addArg("width", "the width of the box", "int");
		p.addArg("height", "the height of the box", "float");
		thrown.expect(InvalidValueException.class);
		thrown.expectMessage("usage: java " + p.programName + p.getAllArgNames() + "\n" + p.programName +".java: error: argument width: invalid int value: something");
		p.parse(s);
	}
	
	@Test
	public void testInvalidValueMessageBoolean(){
		String[] s = {"true", "false", "2"};
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addArg("length", "the length of the box", "boolean");
		p.addArg("width", "the width of the box", "boolean");
		p.addArg("height", "the height of the box", "BOOLEAN");
		thrown.expect(InvalidValueException.class);
		thrown.expectMessage("usage: java " + p.programName + p.getAllArgNames() + "\n" + p.programName +".java: error: argument height: invalid boolean value: 2");
		p.parse(s);
	}
	
	
	@Test
	public void testArgumentNotFoundException(){
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addArg("length", "the length of the box", "boolean");
		p.addArg("width", "the width of the box", "boolean");
		p.addArg("height", "the height of the box", "boolean");
		thrown.expect(ArgumentNotFoundException.class);
		thrown.expectMessage("The argument depth was not found");
		p.getArg("depth");
	}
	
	@Test
	public void testCanUseNamedArguments(){
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of an ellipsoid.");
		String[] s = {"7", "5", "2", "--type", "ellipsoid", "--digits", "1"};
		p.addArg("length", "the length of the box", "float");
		p.addArg("width", "the width of the box", "float");
		p.addArg("height", "the height of the box", "float");
		p.addArg("type");
		p.addArg("digits");
		p.checkArgsThenParse(s);
		
		System.out.println(p.getAllArgNames());
		System.out.println(p.getAllArgValues());
		
		assertEquals("ellipsoid", p.getArg("type"));
	}
}