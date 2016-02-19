package edu.jsu.mcis;
import java.util.*;

public class Argument{
	
	private String argName;
	private String argValue;
	private String argDescription;
	public enum Type{ STRING, INT, FLOAT, BOOLEAN };
	private Type type;
	
	public Argument(String n){
		argName = n;
		argDescription = "";
		type = Type.STRING;
	}
	
	public Argument(String n, String d){
		argName = n;
		argDescription = d;	
		type = Type.STRING;
	}
	
	public Argument(String n, String d, String t){
		argName = n;
		argDescription = d;
		if(t.equals("STRING") || t.equals("string")) type = Type.STRING;
		else if(t.equals("boolean") || t.equals("BOOLEAN")) type = Type.BOOLEAN;
		else if(t.equals("int") || t.equals("INT")) type = Type.INT;
		else if(t.equals("float") || t.equals("FLOAT")) type = Type.FLOAT;
		else{
			throw new InvalidArgumentTypeException(t + " is not a valid argument type.");
		}
		
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
	
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Argument) {
			Argument a = (Argument)o;
			return argName == a.getName();
		}
		else return false;
	}
	
}