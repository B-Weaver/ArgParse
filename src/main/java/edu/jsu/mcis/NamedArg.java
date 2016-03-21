package edu.jsu.mcis;
import java.util.*;

/**
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*/

public class NamedArg extends Argument{
	String shortName;
	String defaultValue;
	
	/**
	*
	*/
	
	public NamedArg(String n, String s, String d, Argument.Type t, String dV){
		super(n, d, t);
		shortName = s;
		defaultValue = dV;
		this.argValue = dV;
	}
	
	/**
	*
	*/
	
	public String getShort(){
		return shortName;
	}
	
	/**
	*
	*/
	
	public String getDefault(){
		return defaultValue;
	}
	
	/**
	*
	*/
	
	@Override
	public String stringToXML(){
		String toXML = "\t<named>\n\t\t<name>"+argName+"</name>\n\t\t<argdescription>" + argDescription + "</argdescription>\n\t\t<shortname>"+shortName+"</shortname>\n\t\t<type>"+getArgTypeAsString()+"</type>\n\t\t<default>"+defaultValue+"</default>\n\t</named>\n";
		
		return toXML;
	}
}
