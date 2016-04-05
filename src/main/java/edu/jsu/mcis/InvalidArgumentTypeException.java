package edu.jsu.mcis;
import java.util.*;

/**
*This exception is thrown when the user gives an argument an illegal type. Arguments should only be of types int, float, boolean, or string. If the user tries to
*make an argument of any other type then the exception will be thrown.
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*/

public class InvalidArgumentTypeException extends RuntimeException{
	/**
	*This exception is thrown when the user gives an argument an illegal type. Arguments should only be of types int, float, boolean, or string. If the user tries to
	*make an argument of any other type then the exception will be thrown.
	*@param message		message to be shown when exception thrown
	*/
	public InvalidArgumentTypeException(String message){
		super(message);
	}
}