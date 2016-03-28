package edu.jsu.mcis;
import java.util.*;

/**
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*This exception is thrown when the user gives an argument an illegal type. Arguments should only be of types int, float, boolean, or string. If the user tries to
*make an argument of any other type then the exception will be thrown.
*/

public class InvalidArgumentTypeException extends Exception{
	/**
	*This exception is thrown when the user gives an argument an illegal type. Arguments should only be of types int, float, boolean, or string. If the user tries to
	*make an argument of any other type then the exception will be thrown.
	*@param message		message to be shown when exception thrown
	*/
	public InvalidArgumentTypeException(String message){
		super(message);
	}
}