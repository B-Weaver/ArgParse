package edu.jsu.mcis;
import java.util.*;

/**
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*This exception is thrown if the user provides an illegal value for a named argument. For example, if the user provides a value for a hypothetical argument called type
*that is not an accepted value for that argument, the exception will be thrown.
*/

public class UnacceptedValueException extends RuntimeException{
	/**
	*This exception is thrown if the user provides an illegal value for a named argument. For example, if the user provides a value for a hypothetical argument called type
	*that is not an accepted value for that argument, the exception will be thrown.
	*@param message		message to be shown when exception thrown
	*/
	public UnacceptedValueException(String message){
		super(message);
	}
}