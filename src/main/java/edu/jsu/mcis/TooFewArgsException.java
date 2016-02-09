package edu.jsu.mcis;
import java.util.*;

public class TooFewArgsException extends RuntimeException{
	public TooFewArgsException(String message){
		super(message);
	}
}