package edu.jsu.mcis;
import java.util.*;

/**
*This exception is thrown if the user fails to provide a user for a required named argument. If an, say for example an arg called type, and it is required. If when the user is passing
in values for parseArgs and they fail to provide a value for type, the exception will be thrown.
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*/

public class RequiredArgNotFoundException extends RuntimeException{
	/**
	*This exception is thrown if the user fails to provide a user for a required named argument. If an, say for example an arg called type, and it is required. If when the user is passing
	*in values for parseArgs and they fail to provide a value for type, the exception will be thrown.
	*@param message		message to be shown when exception thrown
	*/
	public RequiredArgNotFoundException(String message){
		super(message);
	}
}