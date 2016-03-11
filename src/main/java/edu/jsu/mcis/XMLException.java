package edu.jsu.mcis;
import java.util.*;

public class XMLException extends RuntimeException{
	public XMLException(){
		super("XML File Not Found.");
	}
}