package edu.jsu.mcis;
import java.util.*;

/**
*This class is responsible for storing named arguments and their values.
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
	Boolean requiredArg;
	protected List<String> possibleValues;
	
	/**
	*This constructor is called to create a new named argument. The user specifies the name, shortname, description, type, and the default value of these arguments.
	*Usage example: new NamedArg("digits", "d", "the number of digits", Argument.Type.FLOAT, "4");
	*@param n   the name of the argument
	*@param s   the shortname of the argument
	*@param d   the description of the argument
	*@param t   the type of object the argument is
	*@param dV  the default value of the argument
	*/
	
	public NamedArg(String n, String s, String d, Argument.Type t, String dV){
		super(n, d, t);
		shortName = s;
		defaultValue = dV;
		this.argValue = dV;
		possibleValues = new ArrayList<String>();
		requiredArg = false;
	}
	
	/**
	*This constructor is called to create a new named argument. The user specifies the name, shortname, description, type, and the default value of these arguments as well as
	*if the argument is required.
	*Usage example: new NamedArg("digits", "d", "the number of digits", Argument.Type.FLOAT, "4", true);
	*@param n   the name of the argument
	*@param s   the shortname of the argument
	*@param d   the description of the argument
	*@param t   the type of object the argument is
	*@param dV  the default value of the argument
	*@param rA  a boolean value stating if the argument is required. If true then the argument is required.
	*/
	
	public NamedArg(String n, String s, String d, Argument.Type t, String dV, Boolean rA){
		super(n, d, t);
		shortName = s;
		defaultValue = dV;
		this.argValue = dV;
		possibleValues = new ArrayList<String>();
		requiredArg = rA;
	}
	
	/**
	*This constructor is called to create a new named argument. The user specifies the name, shortname, description, type, the default value of these arguments, 
	*and the restricted values for these arguments.
	*Usage example: new NamedArg("digits", "d", "the number of digits", Argument.Type.FLOAT, "4");
	*@param n   the name of the argument
	*@param s   the shortname of the argument
	*@param d   the description of the argument
	*@param t   the type of object the argument is
	*@param dV  the default value of the argument
	@param rV   the list of restricted values
	*/
	
	public NamedArg(String n, String s, String d, Argument.Type t, String dV, List<String> rV){
		super(n, d, t);
		shortName = s;
		defaultValue = dV;
		this.argValue = dV;
		possibleValues = rV;
		requiredArg = false;
	}
	
	/**
	*This constructor is called to create a new named argument. The user specifies the name, shortname, description, type, the default value of these arguments, 
	*and the restricted values for these arguments.
	*Usage example: new NamedArg("digits", "d", "the number of digits", Argument.Type.FLOAT, "4");
	*@param n   the name of the argument
	*@param s   the shortname of the argument
	*@param d   the description of the argument
	*@param t   the type of object the argument is
	*@param dV  the default value of the argument
	*@param rA  a boolean value stating if the argument is requried. If true, then the argument is required.
	*@param rV   the list of restricted values
	*/
	
	public NamedArg(String n, String s, String d, Argument.Type t, String dV, Boolean rA, List<String> rV){
		super(n, d, t);
		shortName = s;
		defaultValue = dV;
		this.argValue = dV;
		possibleValues = rV;
		requiredArg = rA;
	}
	
	/**
	*This method can be used to return the shortname of a named argument. It's used by the checkArgsThenParse method is ArgumentParser to check for the shortname of a named argument.
	*@return   the shortname
	*/
	
	public String getShort(){
		return shortName;
	}
	
	/**
	*This method can be used to return the default value of a named argument.
	*@return   the default value of an argument
	*/
	
	public String getDefault(){
		return defaultValue;
	}
	
	/**
	*This method is used by the parseArgs method in ArgumentParser. It checks to see if an argument is a required argument.
	*@return    a boolean value stating if the named argument is required. If it returns true then the argument is required.
	*/
	
	public Boolean isArgRequired(){
		return requiredArg;
	}
	
	/**
	*This method is used by the writeArgsAsXML method in ArgumentParser, which is used by the save method in XMLTools, to write the named arguments to an XML file.
	*@return   a string formatting the named arguments and their values to an XML file.
	*/
	
	@Override
	public String stringToXML(){
		String toXML = "\t<named>\n\t\t<name>"+argName+"</name>\n\t\t<description>" + argDescription + "</description>\n\t\t<shortname>"+shortName+"</shortname>\n\t\t<type>"+getArgTypeAsString()+"</type>\n\t\t<default>"+defaultValue+"</default>";
		if(possibleValues.size() > 0){
			toXML = toXML + "\n\t\t<restricted>";	
			for(int i = 0; i < possibleValues.size(); i++){
				toXML = toXML + "\n\t\t\t<value>"+possibleValues.get(i)+"</value>";
			}
			toXML = toXML + "\n\t\t</restricted>";
		}
		if(requiredArg == true){
			toXML = toXML + "\n\t\t<required>true</required>";
		}
		toXML = toXML + "\n\t</named>\n";
		
		return toXML;
	}
	
	protected void setPossibleValue(List<String> str){
		possibleValues = str;
	}
}
