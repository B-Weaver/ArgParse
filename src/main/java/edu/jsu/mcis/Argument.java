package edu.jsu.mcis;
import java.util.*;

public class Argument{
	
	private String argName;
	private String argValue;
	private String argDescription;
	public enum Type{ STRING, INT, FLOAT, BOOLEAN };
	private Type type;
	
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
	
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Argument) {
			Argument a = (Argument)o;
			return argName.equals(a.getName());
		}
		else return false;
	}
	
}