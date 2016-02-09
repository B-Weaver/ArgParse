package edu.jsu.mcis;
import java.util.*;

public class Argument{
	
	private String argName;
	private String argValue;
	
	public Argument(String n){
		
		argName = n;
		
	}
	public Argument(String n, String v){
		
		argName = n;
		argValue = v;	
		
		
	}
	
	public void setValue(String v){
		
		argValue = v;
		
		
	}
	public String getName(){
		
		return argName;
		
		
	}
	public String getValue(){
		
		
		return argValue;
		
	}
}