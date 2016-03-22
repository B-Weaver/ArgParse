package edu.jsu.mcis;
import java.util.*;

/**
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*This exception is thrown when an XML file cannot be found in the load method for the XMLTools class. If the user types in a file or filepath that does not exist, then the
*exception will be thrown with a message saying that the file cannot be found.
*/

public class XMLException extends RuntimeException{
	/**
	*This exception is thrown when an XML file cannot be found in the load method for the XMLTools class. If the user types in a file or filepath that does not exist, then the
	*exception will be thrown with a message saying that the file cannot be found.
	*/
	public XMLException(String message){
		super("XML File " + message + " Not Found.");
	}
}