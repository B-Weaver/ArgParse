package edu.jsu.mcis;
import java.util.*;

public class PosArg extends Argument{
	int position;
	
	public PosArg(String n, String d, Type t, int pos){
		argName = n;
		argDescription = d;
		type = t;
		position = pos;
		argValue = null;
	}
	
	@Override
	public String stringToXML(){
		String toXML = "	<positional>\n" +"		<name>";
		toXML = toXML + argName + "</name>\n" +"		<type>";
		
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