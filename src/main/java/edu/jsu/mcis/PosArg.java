package edu.jsu.mcis;
import java.util.*;

/**
*This class is responsible for storing positional arguments and their values.
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*/

public class PosArg extends Argument{
	
	int position;
	
	/**
	*This constructor is called to create new positional argument. The user specifies the name, description, type, and position of these arguments.
	*Usage example: new PosArg("length", "the length of a shape", Argument.Type.INT, "1");
	*@param n   the name of the argument
	*@param d   the description of the argument
	*@param t   the type of object the argument is
	*@param p   the position of the argument
	*/
	
	public PosArg(String n, String d, Argument.Type t, String p){
		super(n, d, t);
		position = Integer.parseInt(p);
	}
	
	/**
	*This method is used by the writeArgsAsXML method in ArgumentParser, which is called by the save method in XMLTools, to write the positional arguments to an XML file.
	*@return   a string formatting the positional arguments and their values to be written to an XML file.
	*/
	
	@Override
	public String stringToXML(){
		String toXML = "\t<positional>\n\t\t<name>"+argName+"</name>\n\t\t<argdescription>" + argDescription +"</argdescription>\n\t\t<type>"+getArgTypeAsString()+"</type>\n\t\t<position>"+position+"</position>\n\t</positional>\n";
		return toXML;
	}
}