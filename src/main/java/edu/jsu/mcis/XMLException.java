package edu.jsu.mcis;
import java.util.*;

/**
*This exception is thrown when an XML file cannot be found in the load method for the XMLTools class. If the user types in a file or filepath that does not exist, then the
*exception will be thrown with a message saying that the file cannot be found.
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*/

public class XMLException extends RuntimeException{
	/**
	*This constructor takes a string that is used to print out the exception message.
	*@param message		message to be shown when exception thrown
	*/
	public XMLException(String message){
		super(message + " is not an XML File.");
	}
}