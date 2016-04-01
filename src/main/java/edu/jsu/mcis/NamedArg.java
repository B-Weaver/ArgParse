package edu.jsu.mcis;
import java.util.*;

/**
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*This class is responsible for storing named arguments and their values.
*/ 

public class NamedArg extends Argument{
	String shortName;
	String defaultValue;
	protected List<String> possVals;
	
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
		possVals = new ArrayList<String>();
	}
	public NamedArg(String n, String s, String d, Argument.Type t, String dV, ArrayList<String> rV){
		super(n, d, t);
		shortName = s;
		defaultValue = dV;
		this.argValue = dV;
		possVals = rV;
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
	*This method is used by the writeArgsAsXML method in ArgumentParser, which is used by the save method in XMLTools, to write the named arguments to an XML file.
	*@return   a string formatting the named arguments and their values to an XML file.
	*/
	
	@Override
	public String stringToXML(){
		String toXML = "\t<named>\n\t\t<name>"+argName+"</name>\n\t\t<argdescription>" + argDescription + "</argdescription>\n\t\t<shortname>"+shortName+"</shortname>\n\t\t<type>"+getArgTypeAsString()+"</type>\n\t\t<default>"+defaultValue+"</default>";
		//if(possVals.size() > 0){
		for(int i = 0; i < possVals.size(); i++){
			toXML = toXML + "\n\t\t<restricted>"+possVals.get(i)+"</restricted>";
		}
		toXML = toXML + "\n\t</named>\n";
		
		//}
		
		return toXML;
	}
	
	protected void setPossVal(ArrayList<String> str){
		possVals = str;
	}
}
