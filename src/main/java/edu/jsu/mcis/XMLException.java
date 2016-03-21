package edu.jsu.mcis;
import java.util.*;

/**
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*
*/

public class XMLException extends RuntimeException{
	public XMLException(String message){
		super("XML File " + message + " Not Found.");
	}
}