package edu.jsu.mcis;
import java.util.*;

/**
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*This exception is thrown when a user tries to give an argument that does not exist. For instance, if a program has three arguments called length, width, and height
*and the user tries to query for an argument called allArgs, then the exception will be thrown with a message showing the illegal argument.
*/

public class ArgumentNotFoundException extends RuntimeException{
	/**
	*This exception is thrown when a user tries to give an argument that does not exist. For instance, if a program has three arguments called length, width, and height
	*and the user tries to query for an argument called allArgs, then the exception will be thrown with a message showing the illegal argument.
	*/
	public ArgumentNotFoundException(String message){
		super(message);
	}
}