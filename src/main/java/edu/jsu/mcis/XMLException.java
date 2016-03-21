package edu.jsu.mcis;
import java.util.*;

/**
*
*/

public class XMLException extends RuntimeException{
	public XMLException(String message){
		super("XML File " + message + " Not Found.");
	}
}