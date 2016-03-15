package edu.jsu.mcis;
import java.util.*;

public class PosArg extends Argument{
	int position;
	
	public PosArg(String n, String d, Argument.Type t, String p){
		super(n, d, t);
		position = Integer.parseInt(p);
	}
	
	@Override
	public String stringToXML(){
		String toXML = "\t<positional>\n\t\t<name>"+argName+"</name>\n\t\t<type>"+getArgTypeAsString()+"</type>\n\t\t<position>"+position+"</position>\n\t</positional>\n";
		return toXML;
	}
}
/*
<arguments>
    <positional>
        <name>length</name>
        <type>float</type>
        <position>1</position>
    </positional>
    <positional>
        <name>width</name>
        <type>float</type>
        <position>2</position>
    </positional>
    <positional>
        <name>height</name>
        <type>float</type>
        <position>3</position>
    </positional>
    <named>
        <name>type</name>
        <shortname>t</shortname>
        <type>string</type>
        <default>box</default>
    </named>
    <named>
        <name>digits</name>
        <shortname>d</shortname>
        <type>integer</type>
        <default>4</default>
    </named>
</arguments>
*/