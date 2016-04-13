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
*This class is used to load information from an XML file and to write various arguments and their values to said XML file.
*
*This class is used to load information from an XML file and to write various arguments and their values to said XML file. Typically, a user would make an instance of ArgumentParser
*equal to the load function of XMLTools. Likewise, the user can also save arguments and their values in an XML file. Both ways are done like this:
*<blockquote><pre>
*{@code
*public class VolumeCalculatorD{
*public static void main(String[] args) {
*	String filename = "C:/Users/Owner/Desktop/CS310/ArgParse/ArgParse/ArgParse/demos/Feature12Ex.xml";
*	try{
*		ArgumentParser parser = XMLTools.load(filename);
*		parser.parseArgs(args);
*		int l = Integer.parseInt(parser.getArgValue("length"));
*		int w = Integer.parseInt(parser.getArgValue("width"));
*		int h = Integer.parseInt(parser.getArgValue("height"));
*		int v = l*w*h;
*		System.out.println("The volume is: " + v);
*		System.out.println("Digits: " +parser.getArgValue("digits"));
*		System.out.println("Type: " +parser.getArgValue("type"));
*		
*		XMLTools.save(parser, "C:/Users/Owner/Desktop/CS310/ArgParse/ArgParse/ArgParse/demos/Feature12Out.xml");
*	}
*	catch(Exception e) {
*		System.out.println(e.getMessage());
*	}
* }
* }
*}
*</pre></blockquote>
*
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
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
		ArgumentParser p = new ArgumentParser();
		if(!file.substring(file.length()-4).equals(".xml")){
			throw new XMLException(file);
		}
		String[] program = new String[2];
		
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
							program[0] = el.getElementsByTagName("name").item(0).getTextContent();
							program[1] = el.getElementsByTagName("description").item(0).getTextContent();
						}
					}
				}
			}
		
			Boolean hasRequired = false;
			
			p = new ArgumentParser(program[0], program[1]);
			
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
						List<String> restrictedVals = new ArrayList<String>();
						Argument.Type t;
						int count = 0;
						Boolean requiredArg = false;
						
						if(el.getNodeName().contains("named")){
							argName = el.getElementsByTagName("name").item(0).getTextContent();
							NodeList nl = el.getElementsByTagName("description");
							if(nl.getLength() > 0){
								argDescription = nl.item(0).getTextContent();
							}
							argShortName = el.getElementsByTagName("shortname").item(0).getTextContent();
							argType = el.getElementsByTagName("type").item(0).getTextContent();
							argValue = el.getElementsByTagName("default").item(0).getTextContent();
							NodeList rL = el.getElementsByTagName("required");
							if(rL.getLength() > 0){
								if(el.getElementsByTagName("required").item(0).getTextContent().equals("true"));
								requiredArg = true;
								hasRequired = true;
							}
							NodeList restrictedValueNodeList = el.getChildNodes();
							if(restrictedValueNodeList != null && restrictedValueNodeList.getLength() > 0){
								for(int j = 0; j < restrictedValueNodeList.getLength(); j++){
									if(restrictedValueNodeList.item(j).getNodeType() == Node.ELEMENT_NODE){
										Element values = (Element) restrictedValueNodeList.item(j);
										NodeList vals = values.getChildNodes();
										if(values.getNodeName().contains("restricted")){
											if(vals !=null && vals.getLength() > 0){
												for(int k = 0; k < vals.getLength(); k++){
													if(vals.item(k).getNodeType() == Node.ELEMENT_NODE){
														restrictedVals.add(values.getElementsByTagName("value").item(count).getTextContent());
														count++;
													}
												} 
											}
										}
									}
								}
							}
							argType = argType.toLowerCase();
							
							if(!argType.equals("integer") && !argType.equals("boolean") && !argType.equals("string") && !argType.equals("float")){
								throw new InvalidArgumentTypeException(argType + " is not a valid argument type");
							}
							switch(argType){
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
							
							p.addNamedArg(argName, argShortName, argDescription, t, argValue, requiredArg, restrictedVals);
						}
						else if(el.getNodeName().contains("positional")){
							argName = el.getElementsByTagName("name").item(0).getTextContent();
							NodeList nl = el.getElementsByTagName("description");
							if(nl.getLength() > 0){
								argDescription = nl.item(0).getTextContent();
							}
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
}