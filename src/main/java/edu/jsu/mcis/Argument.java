package edu.jsu.mcis;
import java.util.*;

/**
*This class is where the arguments and there values are stored. Each argument's type, description, and value is stored here and can be used later.
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*/

public class Argument{
	
	/**
	*This class is where the arguments and there values are stored. Each argument's type, description, and value is stored here and can be used later.
	*/

	protected String argName;
	protected String argValue;
	protected String argDescription;
	
	/**
	*The enumeration Type is used to set the type of argument. An argument can be a string, an integer, a float, or a boolean.
	*When the user creates a new argument and wishes to specify the type they should type the following: Argument.Type.STRING/INT/FLOAT/BOOLEAN.
	*/
	
	public enum Type{ STRING, INT, FLOAT, BOOLEAN };
	protected Type type;
	
	
	/**
	*In this constructor the user specifies the name of the argument but no description. The type is defaulted to String as well.
	*This is done by typing something like this: new Argument("length")
	*@param n   the name of the argument.
	*/
	
	public Argument(String n){
		this(n, "", Type.STRING);
	}
	
	/**
	*In this constructor the user specifies the name and description of the argument. However, the type is defaulted to String.
	*This is done by typing something like this: new Argument("length", "the length of the shape").
	*@param n   the name of the argument.
	*@param d   the description of the argument.
	*/
	
	public Argument(String n, String d){
		this(n, d, Type.STRING);
	}
	
	/**
	*In this constructor the user specifies the name, description, and type of argument. The value of the argument is set to null until the user assigns a value
	*later on in the program. This is called by the addArg method in ArgumentParser.
	*Usage: new Argument("length", "the length of the shape", Argument.Type.INT).
	*@param n   the name of the argument.
	*@param d   the description of the argument.
	*@param t   the type of argument.
	*/
	
	public Argument(String n, String d, Type t){
		argName = n;
		argDescription = d;
		type = t;
		argValue = null;
	}
	
	/**
	*This method is called in the parse methods when the user provides a value for an argument.
	*@param v   the value to be assigned to an argument.
	*/
	
	public void setValue(String v){
		argValue = v;
	}
	
	/**
	*This method returns the name of an argument. Is used for things such as printing out exception messages.
	*@return    the name of an argument.
	*/
	
	public String getName(){
		return argName;
	}
	
	/**
	*This method sets the description of an argument. Typically this is never used by the user.
	*Usage example: Argument s = new Argument("length");
	*s.setDescription("the length");
	*@param d   the description of the argument.
	*/
	
	public void setDescription(String d){
		argDescription = d;
	}
	
	/**
	*This method is used to return the description of an argument. Can be used for things such as exception messages.
	*@return   the description of the argument.
	*/
	
	public String getDescription(){
		return argDescription;
	}
	
	/**
	*This method is used to return the value of an argument. This is called in the parser methods in ArgumentParser.
	*@return   the value of an argument.
	*/
	
	public String getValue(){
		return argValue;
	}
	
	/**
	*This method is used to return the name and description of various arguments. Typically this method is called to help generate exception messages.
	*@return   a string containting the name and description of an argument.
	*/
	
	public String getNameAndDescription(){
		return argName + " " + argDescription;
	}
	
	/**
	*This method returns the type of argument. This is mostly used for generating certain messages.
	*@return   the argument type.
	*/
	
	public Type getArgType(){
		return type;
	}
	
	/**
	*This method returns a the type an argument is as a string. This method is used by the stringToXML methods in PosArg and NamedArg, which is used for the save method
	*in the XMLTools class.
	*@return   a string showing the type of object an argument is.
	*/
	
	public String getArgTypeAsString(){
		if(type == Type.FLOAT)
			return "float";
		else if(type == Type.INT)
			return "integer";
		else if(type == Type.BOOLEAN)
			return "boolean";
		else
			return "string";
	}
	
	/**
	*This method checks to see if an object is an instance of the Argument class. It is rarely used but can be called by saying: if(Argument.equals("length")) for example.
	*@return   whether an object is an argument.
	*/
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Argument) {
			Argument a = (Argument)o;
			return argName.equals(a.getName());
		}
		else return false;
	}
	
	/**
	*This method returns part of the contents of an XML file to be written. This method is called in the save method of the XMLTools class.
	*@return   a string that has part of a XML file to be written.
	*/
	
	public String stringToXML(){
		String toXML = "\t<argument>\n\t\t<name>"+argName+"</name>\n\t\t<argdescription>" + argDescription + "</argdescription>\n\t\t<type>"+getArgTypeAsString()+"</type>\n\t</argument>\n";
		
		return toXML;
	}
	
}
