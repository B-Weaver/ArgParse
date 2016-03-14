package edu.jsu.mcis;
import java.util.*;

public class NamedArg extends Argument{
	String shortname;
	String defaultValue;
	
	public NamedArg(String n, String d, Type t, String s, String dv){
		argName = n;
		argDescription = d;
		type = t;
		shortname = s;
		defaultValue = dv;
		argValue = null;
	}
	
	@Override
	public String stringToXML(){
		String toXML = "	<named>\n" +"		<name>";
		toXML = toXML + argName + "</name>\n" +"		<shortname>";
		toXML = toXML + shortname +"</shortname>\n" +"		<type>";
		
		if(type == Type.FLOAT){
			toXML = toXML + "float</type>" + "		<position>";
		}
		else if(type == Type.INT){
			toXML = toXML + "integer</type>" + "		<position>";
		}
		else if(type == Type.BOOLEAN){
			toXML = toXML + "boolean</type>" + "		<position>";
		}
		else{
			toXML = toXML + "string</type>" + "		<position>";
		}
		
		toXML = toXML + position +"</position>\n" +"	</positional>\n";
		return toXML;
	}
}