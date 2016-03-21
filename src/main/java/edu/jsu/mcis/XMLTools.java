package edu.jsu.mcis;
import java.util.*;
import java.io.*;
import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
*
*/

public final class XMLTools{
	
	/**
	*
	*/
	
	public static void save(ArgumentParser parser, String fileName){
		try{
			File file = new File(fileName);
			file.createNewFile();
			PrintWriter writer = new PrintWriter(file);
			writer.print(parser.writeArgsAsXML());
			writer.close();
		}
		catch(Exception e){
			
		}
	}
	
	/**
	*
	*/

	public static ArgumentParser load(String file){
		String[] program = new String[2];
		XMLTools x = new XMLTools();
		program = x.programLoad(file);
		String programName = program[0];
		String programDescription = program[1];
		ArgumentParser p = new ArgumentParser(programName, programDescription);
		try{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(file));

			Element documentElement = doc.getDocumentElement();
			
			NodeList listOfXMLArgs = documentElement.getChildNodes();
			
			if(listOfXMLArgs != null && listOfXMLArgs.getLength() > 0){
				for(int i = 0; i < listOfXMLArgs.getLength(); i++){
					if(listOfXMLArgs.item(i).getNodeType() == Node.ELEMENT_NODE){
						Element el = (Element) listOfXMLArgs.item(i);
						String argName = "";
						String argShortName = "";
						String argDescription = "";
						String argType = "";
						String argValue = "";
						String argPosition = "";
						Argument.Type t;
						
						if(el.getNodeName().contains("named")){
							argName = el.getElementsByTagName("name").item(0).getTextContent();
							argDescription = el.getElementsByTagName("argdescription").item(0).getTextContent();
							argShortName = el.getElementsByTagName("shortname").item(0).getTextContent();
							argType = el.getElementsByTagName("type").item(0).getTextContent();
							argValue = el.getElementsByTagName("default").item(0).getTextContent();
							switch(argType.toLowerCase()){
								case "integer":
									t = Argument.Type.INT;
									break;
								case "boolean":
									t = Argument.Type.BOOLEAN;
									break;
								case "float":
									t = Argument.Type.FLOAT;
									break;
								default:
									t = Argument.Type.STRING;
									break;
								
							}
							p.addNamedArg(argName, argShortName, argDescription, t, argValue);
							
						}
						else if(el.getNodeName().contains("positional")){
							argName = el.getElementsByTagName("name").item(0).getTextContent();
							argDescription = el.getElementsByTagName("argdescription").item(0).getTextContent();
							argType = el.getElementsByTagName("type").item(0).getTextContent();
							argPosition = el.getElementsByTagName("position").item(0).getTextContent();
							switch(argType.toLowerCase()){
								case "integer":
									t = Argument.Type.INT;
									break;
								case "boolean":
									t = Argument.Type.BOOLEAN;
									break;
								case "float":
									t = Argument.Type.FLOAT;
									break;
								default:
									t = Argument.Type.STRING;
									break;
								
							}
							p.addPosArg(argName, argDescription, t, argPosition);
						}
						
					}
				}	
			}
		}
		catch(SAXException e){
			
		}
		catch(ParserConfigurationException e){
			
		}
		catch(IOException e){
			throw new XMLException(file);
		}
		return p;
	}
	
	/**
	*
	*/
	
	public String[] programLoad(String file){
		String[] prog = new String[2];
		try{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(file));

			Element documentElement = doc.getDocumentElement();
			
			NodeList listOfXMLArgs = documentElement.getChildNodes();
			
			if(listOfXMLArgs != null && listOfXMLArgs.getLength() > 0){
				for(int i = 0; i < listOfXMLArgs.getLength(); i++){
					if(listOfXMLArgs.item(i).getNodeType() == Node.ELEMENT_NODE){
						Element el = (Element) listOfXMLArgs.item(i);
						
						if(el.getNodeName().contains("program")){
							prog[0] = el.getElementsByTagName("programname").item(0).getTextContent();
							prog[1] = el.getElementsByTagName("description").item(0).getTextContent();
						}
					}
				}
			}
		}
		catch(SAXException e){
			
		}
		catch(ParserConfigurationException e){
			
		}
		catch(IOException e){
			throw new XMLException(file);
		}
		return prog;
	}
	
}