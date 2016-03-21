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
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*This class is used to load information from an XML file and to write various arguments and their values to said XML file.
*/

public final class XMLTools{
	
	/**
	*This method is used to save arguments and their values to a specified XML file. It calls ArgumentParser's writeArgsAsXML method to get the values.
	*@param parser   an instance of ArgumentParser passed by the user that contains that passes the values of the arguments to this method.
	*@param fileName the name of the file, passed by the user, that the method will write to.
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
	*This method is used to load the names of arguments, the types of arguments they are, and their values. It then gives these values to the instance of ArgumentParser calling it.
	*Usage example: ArgumentParser p = XMLTools.load(fileName);
	*@param file   the name of the file to be read from
	*@return    an instance of ArgumentParser containing the values read from the file.
	*@exception XMLException   this exception is thrown when something goes wrong reading from the file, or the file just can't be found.
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
	*This method is called by the load method to provide the method the name and description of the file to be given to the instance of ArgumentParser the load method creates.
	*@param file   the name of the file to be read from.
	*@return   an String array containing the name and description of the file being read from.
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