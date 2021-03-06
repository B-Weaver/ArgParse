package edu.jsu.mcis;
import java.util.*;

/**
*This exception is thrown if the user provides too few values for the positional arguments. For example, if the program has four positional arguments and the user provides
*three or fewer values for those arguments, then the exception will be thrown with a message showing the argument that is missing a value.
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*/

public class TooFewArgsException extends RuntimeException{
	/**
	*This constructor takes a string that is used to print out the exception message.
	*@param message		message to be shown when exception thrown
	*/
	public TooFewArgsException(String message){
		super(message);
	}
}