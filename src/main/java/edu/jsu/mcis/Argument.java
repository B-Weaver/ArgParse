package edu.jsu.mcis;
import java.util.*;

public class Argument{
	

	protected String argName;
	protected String argValue;
	protected String argDescription;
	public enum Type{ STRING, INT, FLOAT, BOOLEAN };
	protected Type type;
	
	
	public Argument(){
		this("", "", Type.STRING);
	}
	
	public Argument(String n){
		this(n, "", Type.STRING);
	}
	
	public Argument(String n, String d){
		this(n, d, Type.STRING);
	}
	
	public Argument(String n, String d, Type t){
		argName = n;
		argDescription = d;
		type = t;
		argValue = null;
	}
	
	public void setValue(String v){
		argValue = v;
	}
	
	public String getName(){
		return argName;
	}
	
	public void setDescription(String d){
		argDescription = d;
	}
	
	public String getDescription(){
		return argDescription;
	}
	
	public String getValue(){
		return argValue;
	}
	
	public String getNameAndDescription(){
		return argName + " " + argDescription;
	}
	
	public Type getArgType(){
		return type;
	}
	
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
	
	public void setTypeFromString(String t){
		switch(t.toLowerCase()){
			case "integer":
				type = Argument.Type.INT;
				break;
			case "boolean":
				type = Argument.Type.BOOLEAN;
				break;
			case "float":
				type = Argument.Type.FLOAT;
				break;
			default:
				type = Argument.Type.STRING;
				break;
			
		}
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Argument) {
			Argument a = (Argument)o;
			return argName.equals(a.getName());
		}
		else return false;
	}
	
	public String stringToXML(){
		String toXML = "\t<argument>\n\t\t<name>"+argName+"</name>\n\t\t<type>"+getArgTypeAsString()+"</type>\n\t</argument>\n";
		
		return toXML;
	}
	
}
