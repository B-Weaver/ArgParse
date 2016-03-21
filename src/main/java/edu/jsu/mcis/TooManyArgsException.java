package edu.jsu.mcis;
import java.util.*;

/**
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*
*/

public class TooManyArgsException extends RuntimeException{
	public TooManyArgsException(String message){
		super(message);
	}
}