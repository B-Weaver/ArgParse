package edu.jsu.mcis;
import java.util.*;

/**
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*This exception is thrown if the user provides too many values for the positional arguments. For example, if the program has three positional arguments and the user provides
*four or more values for those arguments, then the exception will be thrown with a message showing the extra value(s).
*/

public class TooManyArgsException extends RuntimeException{
	/**
	*This exception is thrown if the user provides too many values for the positional arguments. For example, if the program has three positional arguments and the user provides
	*four or more values for those arguments, then the exception will be thrown with a message showing the extra value(s).
	*/
	public TooManyArgsException(String message){
		super(message);
	}
}