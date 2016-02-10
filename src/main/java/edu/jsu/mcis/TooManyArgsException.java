package edu.jsu.mcis;
import java.util.*;

public class TooManyArgsException extends RuntimeException{
	public TooManyArgsException(String message){
		super(message);
	}
}