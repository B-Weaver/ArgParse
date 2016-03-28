package edu.jsu.mcis;
import java.util.*;

/**
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*This exception is thrown when the user types in either --help or -h. When they do, a help message will be printed showing the program's name and description as well as
*the name and description of all of the arguments in the program.
*/

public class GetHelpException extends RuntimeException{
	/**
	*This exception is thrown when the user types in either --help or -h. When they do, a help message will be printed showing the program's name and description as well as
	*the name and description of all of the arguments in the program.
	*@param message		message to be shown when exception thrown
	*/
	public GetHelpException(String message){
		super(message);
	}
}