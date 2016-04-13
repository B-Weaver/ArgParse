package edu.jsu.mcis;
import java.util.*;

/**
*This exception is thrown when a user tries to give an argument of a different type of object than the argument takes. For instance, if an argument called length is of type INT
*and the user tries to pass in a boolean value for the argument, than the exception will be thrown with a message showing the illegal value.
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*/

public class InvalidValueException extends RuntimeException{
	/**
	*This constructor takes a string that is used to print out the exception message.
	*@param message		message to be shown when exception thrown
	*/
	public InvalidValueException(String message){
		super(message);
	}
}