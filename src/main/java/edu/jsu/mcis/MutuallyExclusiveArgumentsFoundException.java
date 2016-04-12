package edu.jsu.mcis;
import java.util.*;

/**
*This exception is thrown if the user provides an more than one arguments that are mutually exclusive. For example, if there are two named arguments called shape and size, and
*both arguments are mutually exclusive, and if the user uses both arguments when they call parseArgs then the exception will be thrown stating that both arguments cannot be used at the
*same time.
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*/

public class MutuallyExclusiveArgumentsFoundException extends RuntimeException{
	/**
	*This exception is thrown if the user provides an more than one arguments that are mutually exclusive. For example, if there are two named arguments called shape and size, and
	*both arguments are mutually exclusive, and if the user uses both arguments when they call parseArgs then the exception will be thrown stating that both arguments cannot be used at the
	*same time.
	*@param message		message to be shown when exception thrown
	*/
	public MutuallyExclusiveArgumentsFoundException(String message){
		super(message);
	}
}