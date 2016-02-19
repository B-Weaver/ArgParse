package edu.jsu.mcis;
import java.util.*;

public class InvalidArgumentTypeException extends RuntimeException{
	public InvalidArgumentTypeException(String message){
		super(message);
	}
}