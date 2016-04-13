package edu.jsu.mcis;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.io.*;

public class ArgsParserTest {
	@Test
	public void testNewInstanceHasNoArguments() {
		ArgumentParser p = new ArgumentParser();
		assertEquals(0, p.getNumArguments());
	}
	
	@Test
	public void testArgumentIsAddedCorrectly() {
		ArgumentParser p = new ArgumentParser();
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		assertEquals(1, p.getNumArguments());
	}
	
	@Test
	public void testArgumentValueIsParsedCorrectly() {
		String[] s = {"17"};
		ArgumentParser p = new ArgumentParser();
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.parse(s);
		assertEquals("17", p.getArgValue("length"));
	}
	
	@Test
	public void testCalculateVolume(){
		String[] s = {"7", "5", "2"};
		ArgumentParser p = new ArgumentParser();
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		p.parse(s);
		String len = p.getArgValue("length");
		String wid = p.getArgValue("width");
		String hgt = p.getArgValue("height");
		float length = Float.parseFloat(len);
		float width = Float.parseFloat(wid);
		float height = Float.parseFloat(hgt);
		assertEquals(70.0, length * width * height, 0.00001);
	}
	
	@Test
	public void testsizeEquals(){
		String[] s = {"7", "5", "2"};
		ArgumentParser p = new ArgumentParser();
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		p.parse(s);
		String len = p.getArgValue("length");
		String wid = p.getArgValue("width");
		String hgt = p.getArgValue("height");
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
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		thrown.expect(TooManyArgsException.class);
		thrown.expectMessage("usage: java "+ p.programName + " length width height"+"\n"+"VolumeCalculator.java: error: unrecognized arguments: " + s[3]);
		p.parse(s);		
	}
	
	@Test
	public void testTooManyArguments3ExtraArguments(){
		String[] s = {"7", "5", "2", "4", "55", "16"};
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		thrown.expect(TooManyArgsException.class);
		thrown.expectMessage("usage: java "+ p.programName + " length width height"+"\n"+"VolumeCalculator.java: error: unrecognized arguments: " + s[3]);
		p.parse(s);		
	}

	@Test
	public void testTooFewArgumentsForZeroArguments(){
		String[] s = new String[0];
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		thrown.expect(TooFewArgsException.class);
		thrown.expectMessage("usage: java "+ p.programName + " length width height\nVolumeCalculator.java: error: the following arguments are required: length, width, height");
		p.parse(s);
	}
	
	@Test
	public void testTooFewArgumentsForOneArgument(){
		String[] s = {"7"};
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		thrown.expect(TooFewArgsException.class);
		thrown.expectMessage("usage: java "+ p.programName + " length width height\nVolumeCalculator.java: error: the following arguments are required: width, height");
		p.parse(s);
	}
	
	@Test
	public void testTooFewArgumentsForTwoArguments(){
		String[] s = {"7", "5"};
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		thrown.expect(TooFewArgsException.class);
		thrown.expectMessage("usage: java "+ p.programName + " length width height\nVolumeCalculator.java: error: the following arguments are required: height");
		p.parse(s);
	}
	
	@Test
	public void testHelpMessage(){
		String [] s = {"-h"};
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addPosArg("length", "the length of the box (float)", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box (float)", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box (float)", Argument.Type.FLOAT, "3");
		thrown.expect(GetHelpException.class);
		thrown.expectMessage("usage: java "+ p.programName + " length width height\n" + p.programPurpose + "\npositional arguments:\nlength the length of the box (float)\nwidth the width of the box (float)\nheight the height of the box (float)");
		p.parseArgs(s);
	}
	
	@Test
	public void testNamedHelpMessage(){
		String[] s = {"7", "--help", "2", "5", "8"};
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addPosArg("length", "the length of the box (float)", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box (float)", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box (float)", Argument.Type.FLOAT, "3");
		p.addArg("type");
		p.addArg("digits");
		
		thrown.expect(GetHelpException.class);
		thrown.expectMessage("usage: java "+ p.programName + " length width height\n" + p.programPurpose + "\npositional arguments:\nlength the length of the box (float)\nwidth the width of the box (float)\nheight the height of the box (float)");
		p.parseArgs(s);
	}
	
	@Test
	public void testInvalidValueMessageFloat(){
		String[] s = {"7", "something", "2"};
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		thrown.expect(InvalidValueException.class);
		thrown.expectMessage("usage: java " + p.programName + p.getAllPosArgNames() + "\n" + p.programName +".java: error: argument width: invalid float value: something");
		p.parse(s);
	}
	
	@Test
	public void testInvalidValueMessageInt(){
		String[] s = {"7", "something", "2"};
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addPosArg("length", "the length of the box", Argument.Type.INT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.INT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		thrown.expect(InvalidValueException.class);
		thrown.expectMessage("usage: java " + p.programName + p.getAllPosArgNames() + "\n" + p.programName +".java: error: argument width: invalid int value: something");
		p.parse(s);
	}
	
	@Test
	public void testInvalidValueMessageBoolean(){
		String[] s = {"true", "false", "2"};
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addPosArg("length", "the length of the box", Argument.Type.BOOLEAN, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.BOOLEAN, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.BOOLEAN, "3");
		thrown.expect(InvalidValueException.class);
		thrown.expectMessage("usage: java " + p.programName + p.getAllPosArgNames() + "\n" + p.programName +".java: error: argument height: invalid boolean value: 2");
		p.parse(s);
	}
	
	
	@Test
	public void testArgumentNotFoundException(){
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		thrown.expect(ArgumentNotFoundException.class);
		thrown.expectMessage("The argument depth was not found");
		p.getArgValue("depth");
	}
	
	@Test
	public void testArgumentNotFoundExceptionOnNamed(){
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		String[] s = {"7", "5", "2", "-t", "ellipsoid", "--hope", "1"};
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		p.addNamedArg("type", "t", "type of shape", Argument.Type.STRING, "box");
		p.addNamedArg("digits", "d", "digits of type", Argument.Type.STRING, "4");
		thrown.expect(ArgumentNotFoundException.class);
		thrown.expectMessage("The argument hope was not found");
		p.parseArgs(s);
	}
	
	@Test
	public void testArgumentNotFoundExceptionOnShortname(){
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		String[] s = {"7", "5", "2", "-t", "ellipsoid", "-p", "1"};
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		p.addNamedArg("type", "t", "type of shape", Argument.Type.STRING, "box");
		p.addNamedArg("digits", "d", "digits of type", Argument.Type.STRING, "4");
		thrown.expect(ArgumentNotFoundException.class);
		thrown.expectMessage("No argument found with short name p");
		p.parseArgs(s);
	}
	
	@Test
	public void testNamedArgumentNotFoundException(){
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		
		thrown.expect(ArgumentNotFoundException.class);
		thrown.expectMessage("The argument --myarg was not found");
		p.getArgValue("--myarg");
	}
	
	@Test
	public void testCanUseNamedArguments(){
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of an ellipsoid.");
		String[] s = {"7", "5", "2", "--type", "ellipsoid", "--digits", "1"};
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		p.addNamedArg("type", "t", "type of shape", Argument.Type.STRING, "box");
		p.addNamedArg("digits", "d", "digits of type", Argument.Type.STRING, "4");
		p.parseArgs(s);
		
		assertEquals("1", p.getArgValue("digits"));
	}
	
	@Test
	public void testCanUseShortNamedArguments(){
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of an ellipsoid.");
		String[] s = {"7", "5", "2", "-t", "ellipsoid", "-d", "1"};
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		p.addNamedArg("type", "t", "type of shape", Argument.Type.STRING, "box");
		p.addNamedArg("digits", "d", "digits of type", Argument.Type.STRING, "4");
		p.parseArgs(s);
		
		assertEquals("ellipsoid", p.getArgValue("type"));
	}
	
	@Test
	public void testOneNamedArguments(){
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of an ellipsoid.");
		String[] s = {"7", "5", "2", "--type", "ellipsoid"};
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		p.addNamedArg("type", "t", "type of shape", Argument.Type.STRING, "box");
		p.addNamedArg("digits", "d", "digits of type", Argument.Type.STRING, "4");
		p.parseArgs(s);
		
		assertEquals("4", p.getArgValue("digits"));
	}
	
	@Test
	public void testOneDigitNamedArguments(){
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of an ellipsoid.");
		String[] s = {"7", "5", "2", "--digits", "8"};
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		p.addNamedArg("type", "t", "type of shape", Argument.Type.STRING, "box");
		p.addNamedArg("digits", "d", "digits of type", Argument.Type.STRING, "4");
		p.parseArgs(s);
		
		assertEquals("8", p.getArgValue("digits"));
	}
	
	@Test
	public void testOneDigitShortNamedArguments(){
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of an ellipsoid.");
		String[] s = {"7", "5", "2", "-d", "8"};
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		p.addNamedArg("type", "t", "type of shape", Argument.Type.STRING, "box");
		p.addNamedArg("digits", "d", "digits of type", Argument.Type.STRING, "4");
		p.parseArgs(s);
		
		assertEquals("8", p.getArgValue("digits"));
	}
	
	@Test
	public void testOneTypeNamedArguments(){
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of an ellipsoid.");
		String[] s = {"7", "5", "2", "--type", "triangle"};
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		p.addNamedArg("type", "t", "type of shape", Argument.Type.STRING, "box");
		p.addNamedArg("digits", "d", "digits of type", Argument.Type.STRING, "4");
		p.parseArgs(s);
		
		assertEquals("triangle", p.getArgValue("type"));
	}
	
	@Test
	public void testOneTypeShortNamedArguments(){
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of an ellipsoid.");
		String[] s = {"7", "5", "2", "--type", " square", "-t", "triangle"};
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		p.addNamedArg("type", "t", "type of shape", Argument.Type.STRING, "box");
		p.addNamedArg("digits", "d", "digits of type", Argument.Type.STRING, "4");
		p.parseArgs(s);
		
		assertEquals("triangle", p.getArgValue("type"));
	}
	
	@Test
	public void testDefaultsNamedArguments(){
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of an ellipsoid.");
		String[] s = {"7", "5", "2"};
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		p.addNamedArg("type", "t", "type of shape", Argument.Type.STRING, "box");
		p.addNamedArg("digits", "d", "digits of type", Argument.Type.STRING, "4");
		p.parseArgs(s);
		
		assertEquals("box", p.getArgValue("type"));
	}
	
	@Test
	public void testParseXMLFile(){
		String[] s = {"7", "5", "2", "-t", "square", "--digits", "6"};
		String filename = "src/test/java/edu/jsu/mcis/Feature9Ex.xml";
		ArgumentParser p = XMLTools.load(filename);
		try {
			p.parseArgs(s);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("7", p.getArgValue("length"));
		assertEquals("5", p.getArgValue("width"));
		assertEquals("2", p.getArgValue("height"));
		assertEquals("square", p.getArgValue("type"));
		assertEquals("6", p.getArgValue("digits"));
	}
	
	@Test
	public void testParseXMLFileAndGetDescription(){
		String[] s = {"7", "5", "2", "-t", "square", "--digits", "6"};
		String filename = "src/test/java/edu/jsu/mcis/Feature9Ex.xml";
		ArgumentParser p = XMLTools.load(filename);
		try {
			p.parseArgs(s);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("length the length of the shape", p.getArg(0).getNameAndDescription());
	}
	
	@Test
	public void testInvalidArgumentType(){
		String[] s = {"7", "5", "2", "-t", "square", "--digits", "6"};
		String filename = "demos/Feature9Ex2.xml";
		thrown.expect(InvalidArgumentTypeException.class);
		thrown.expectMessage("goat is not a valid argument type");
		ArgumentParser p = XMLTools.load(filename);
		
		p.parseArgs(s);
	}
	
	@Test
	public void testParseXMLFileNoNamedArgs(){
		String[] s = {"7", "5", "2"};
		String filename = "src/test/java/edu/jsu/mcis/Feature9Ex.xml";
		ArgumentParser p = XMLTools.load(filename);
		p.parseArgs(s);
		assertEquals("7", p.getArgValue("length"));
		assertEquals("5", p.getArgValue("width"));
		assertEquals("2", p.getArgValue("height"));
		assertEquals("box", p.getArgValue("type"));
		assertEquals("4", p.getArgValue("digits"));
	}
	@Test
	public void testParseXMLFileSpecifyType(){
		String[] s = {"7", "5", "2","--type", "ellipsoid"};
		String filename = "src/test/java/edu/jsu/mcis/Feature9Ex.xml";
		ArgumentParser p = XMLTools.load(filename);
		p.parseArgs(s);
		
		assertEquals("7", p.getArgValue("length"));
		assertEquals("5", p.getArgValue("width"));
		assertEquals("2", p.getArgValue("height"));
		assertEquals("ellipsoid", p.getArgValue("type"));
		assertEquals("4", p.getArgValue("digits"));
	}
	
	@Test
	public void testParseXMLFileSpecifyTypeBooleanValue(){
		String[] s = {"7", "true", "2","-t", "ellipsoid"};
		String filename = "src/test/java/edu/jsu/mcis/Feature9ExB.xml";
		String saveFile = "src/test/java/edu/jsu/mcis/Feature10Ex.xml";
		ArgumentParser p = XMLTools.load(filename);
		p.parseArgs(s);
		XMLTools.save(p, saveFile);
		assertEquals("7", p.getArgValue("length"));
		assertEquals("true", p.getArgValue("width"));
		assertEquals("2", p.getArgValue("height"));
		assertEquals("ellipsoid", p.getArgValue("type"));
		assertEquals("4", p.getArgValue("digits"));
	}
	
	@Test
	public void testParseXMLFileSpecifyAllTypes(){
		String[] s = {"7", "5", "2","-t", "true"};
		String filename = "src/test/java/edu/jsu/mcis/Feature9ExC.xml";
		String saveFile = "src/test/java/edu/jsu/mcis/Feature10ExB.xml";
		ArgumentParser p = XMLTools.load(filename);
		p.parseArgs(s);
		XMLTools.save(p, saveFile);
		assertEquals("7", p.getArgValue("length"));
		assertEquals("5", p.getArgValue("width"));
		assertEquals("2", p.getArgValue("height"));
		assertEquals("true", p.getArgValue("type"));
		assertEquals("4", p.getArgValue("digits"));
	}
	
	@Test
	public void testParseXMLFileFileNotAXMLFile(){
		String[] s = {"7", "true", "2","-t", "ellipsoid"};
		String filename = "src/test/java/edu/jsu/mcis/Cat.bod";
		
		thrown.expect(XMLException.class);
		thrown.expectMessage(filename + " is not an XML File.");
		ArgumentParser p = XMLTools.load(filename);
	}
	
	@Test
	public void testParseXMLFileFileNotFound(){
		String[] s = {"7", "true", "2","-t", "ellipsoid"};
		String filename = "src/test/java/edu/jsu/mcis/bup.xml";
		
		thrown.expect(XMLException.class);
		ArgumentParser p = XMLTools.load(filename);
	}
	
	@Test
	public void testNamedArgumentsPossibleValues(){
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of an ellipsoid.");
		List<String> list = new ArrayList<String>();
		list.add("box");
		list.add("ellipsoid");
		list.add("pyramid");
		String[] s = {"7", "5", "2", "--type", "ellipsoid", "-t", "pyramid"};
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		p.addNamedArg("type", "t", "type of shape", Argument.Type.STRING, "box", list);
		p.addNamedArg("digits", "d", "digits of type", Argument.Type.STRING, "4");
		p.parseArgs(s);
		assertEquals("pyramid", p.getArgValue("type"));
	}
	
	
	@Test
	public void testUnacceptedValueException(){
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of an ellipsoid.");
		List<String> list = new ArrayList<String>();
		list.add("box");
		list.add("ellipsoid");
		list.add("pyramid");
		String[] s = {"7", "5", "2", "--type", "ellipsoid", "-t", "boat"};
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		p.addNamedArg("type", "t", "type of shape", Argument.Type.STRING, "box", list);
		p.addNamedArg("digits", "d", "digits of type", Argument.Type.STRING, "4");
		thrown.expect(UnacceptedValueException.class);
		thrown.expectMessage("usage: java VolumeCalculator length width height type digits\nVolumeCalculator.java: unaccepted value: boat");
		p.parseArgs(s);
		
	}
	
	@Test
	public void testNamedArgumentsPossibleValuesXML(){
		String[] s = {"7", "5", "2", "-t", "ellipsoid", "-d", "4"};
		String filename = "src/test/java/edu/jsu/mcis/Feature12Ex.xml";
		String outfile = "src/test/java/edu/jsu/mcis/Feature12OutEx.xml";
		ArgumentParser p = XMLTools.load(filename);
		p.parseArgs(s);
		XMLTools.save(p, outfile);
		assertEquals("ellipsoid", p.getArgValue("type"));
		assertEquals("4", p.getArgValue("digits"));
	}
	
	@Test
	public void testUnacceptedValueExceptionXMLShortNamedTest(){
		String[] s = {"7", "5", "2", "-t", "sphere", "--digits", "4"};
		String filename = "src/test/java/edu/jsu/mcis/Feature12Ex.xml";
		String outfile = "src/test/java/edu/jsu/mcis/Feature12OutEx2.xml";
		ArgumentParser p = XMLTools.load(filename);
		thrown.expect(UnacceptedValueException.class);
		thrown.expectMessage("usage: java VolumeCalculatorD length width height type digits\nVolumeCalculatorD.java: unaccepted value: sphere");
		p.parseArgs(s);
		XMLTools.save(p, outfile);
	}
	
	@Test
	public void testUnacceptedValueExceptionXMLLongNamedTest(){
		String[] s = {"7", "5", "2", "-t", "box", "--digits", "15"};
		String filename = "src/test/java/edu/jsu/mcis/Feature12Ex.xml";
		String outfile = "src/test/java/edu/jsu/mcis/Feature12OutEx2.xml";
		ArgumentParser p = XMLTools.load(filename);
		thrown.expect(UnacceptedValueException.class);
		thrown.expectMessage("usage: java VolumeCalculatorD length width height type digits\nVolumeCalculatorD.java: unaccepted value: 15");
		p.parseArgs(s);
		XMLTools.save(p, outfile);
	}
	
	@Test
	public void testArgNotFoundXML(){
		String[] s = {"7", "5", "2", "--vapor", "box", "-d", "15"};
		String filename = "src/test/java/edu/jsu/mcis/Feature12Ex.xml";
		String outfile = "src/test/java/edu/jsu/mcis/Feature12OutEx3.xml";
		ArgumentParser p = XMLTools.load(filename);
		thrown.expect(ArgumentNotFoundException.class);
		thrown.expectMessage("The argument vapor was not found");
		p.parseArgs(s);
		XMLTools.save(p, outfile);
	}
	
	@Test
	public void testArgNotFoundShortXML(){
		String[] s = {"7", "5", "2", "-v", "box", "-d", "15"};
		String filename = "src/test/java/edu/jsu/mcis/Feature12Ex.xml";
		String outfile = "src/test/java/edu/jsu/mcis/Feature12OutEx4.xml";
		ArgumentParser p = XMLTools.load(filename);
		thrown.expect(ArgumentNotFoundException.class);
		thrown.expectMessage("No argument found with short name v");
		p.parseArgs(s);
		XMLTools.save(p, outfile);
	}
	
	@Test
	public void testNamedArgumentsRequiredArguments(){
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of an ellipsoid.");
		List<String> list = new ArrayList<String>();
		list.add("box");
		list.add("ellipsoid");
		list.add("pyramid");
		String[] s = {"7", "5", "2", "--type", "ellipsoid", "-d", "4"};
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		p.addNamedArg("type", "t", "type of shape", Argument.Type.STRING, "box", true, list);
		p.addNamedArg("digits", "d", "digits of type", Argument.Type.STRING, "4", false);
		p.parseArgs(s);
		assertEquals("ellipsoid", p.getArgValue("type"));
	}
	
	@Test
	public void testNamedArgumentsRequiredArgumentsException(){
		ArgumentParser p = new ArgumentParser("VolumeCalculator", "Calculate the volume of an ellipsoid.");
		List<String> list = new ArrayList<String>();
		list.add("box");
		list.add("ellipsoid");
		list.add("pyramid");
		String[] s = {"7", "5", "2", "-d", "4"};
		p.addPosArg("length", "the length of the box", Argument.Type.FLOAT, "1");
		p.addPosArg("width", "the width of the box", Argument.Type.FLOAT, "2");
		p.addPosArg("height", "the height of the box", Argument.Type.FLOAT, "3");
		p.addNamedArg("type", "t", "type of shape", Argument.Type.STRING, "box", true, list);
		p.addNamedArg("digits", "d", "digits of type", Argument.Type.STRING, "4", false);
		thrown.expect(RequiredArgNotFoundException.class);
		p.parseArgs(s);
		assertEquals("ellipsoid", p.getArgValue("type"));
	}
	
	@Test
	public void testNamedArgumentsRequiredArgumentXML(){
		String[] s = {"7", "5", "2", "-t", "ellipsoid", "-d", "4"};
		String filename = "src/test/java/edu/jsu/mcis/Feature13Ex.xml";
		String outfile = "src/test/java/edu/jsu/mcis/Feature13OutEx.xml";
		ArgumentParser p = XMLTools.load(filename);
		p.parseArgs(s);
		XMLTools.save(p, outfile);
		assertEquals("ellipsoid", p.getArgValue("type"));
		assertEquals("4", p.getArgValue("digits"));
	}
	
	@Test
	public void testNamedArgumentsRequiredArgumentExceptionXML(){
		String[] s = {"7", "5", "2", "-d", "4"};
		String filename = "src/test/java/edu/jsu/mcis/Feature13Ex.xml";
		String outfile = "src/test/java/edu/jsu/mcis/Feature13OutEx.xml";
		ArgumentParser p = XMLTools.load(filename);
		thrown.expect(RequiredArgNotFoundException.class);
		p.parseArgs(s);
		XMLTools.save(p, outfile);
		assertEquals("ellipsoid", p.getArgValue("type"));
		assertEquals("4", p.getArgValue("digits"));
	}
}










