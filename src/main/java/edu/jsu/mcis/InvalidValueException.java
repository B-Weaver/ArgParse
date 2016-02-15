package edu.jsu.mcis;
import java.util.*;

public class InvalidValueException extends RuntimeException{
	public InvalidValueException(String message){
		super(message);
	}
}