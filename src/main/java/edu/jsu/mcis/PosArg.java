package edu.jsu.mcis;
import java.util.*;

/**
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*
*/

public class PosArg extends Argument{
	int position;
	
	/**
	*
	*/
	
	public PosArg(String n, String d, Argument.Type t, String p){
		super(n, d, t);
		position = Integer.parseInt(p);
	}
	
	/**
	*
	*/
	
	@Override
	public String stringToXML(){
		String toXML = "\t<positional>\n\t\t<name>"+argName+"</name>\n\t\t<argdescription>" + argDescription +"</argdescription>\n\t\t<type>"+getArgTypeAsString()+"</type>\n\t\t<position>"+position+"</position>\n\t</positional>\n";
		return toXML;
	}
}