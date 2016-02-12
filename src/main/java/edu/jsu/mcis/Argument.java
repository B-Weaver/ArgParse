package edu.jsu.mcis;
import java.util.*;

public class Argument{
	
	private String argName;
	private String argValue;
	private String argDescription;
	
	public Argument(String n){
		argName = n;
		argDescription = "";
	}
	
	public Argument(String n, String d){
		argName = n;
		argDescription = d;	
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
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Argument) {
			Argument a = (Argument)o;
			return argName == a.getName();
		}
		else return false;
	}
	
}