package edu.jsu.mcis;
import java.util.*;

/**
*This exception is thrown if the user provides too many values for the positional arguments. For example, if the program has three positional arguments and the user provides
*four or more values for those arguments, then the exception will be thrown with a message showing the extra value(s).
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*/

public class TooManyArgsException extends RuntimeException{
	/**
	*This exception is thrown if the user provides too many values for the positional arguments. For example, if the program has three positional arguments and the user provides
	*four or more values for those arguments, then the exception will be thrown with a message showing the extra value(s).
	*@param message		message to be shown when exception thrown
	*/
	public TooManyArgsException(String message){
		super(message);
	}
}