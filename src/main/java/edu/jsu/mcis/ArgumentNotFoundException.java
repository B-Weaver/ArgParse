package edu.jsu.mcis;
import java.util.*;

public class ArgumentNotFoundException extends RuntimeException{
	public ArgumentNotFoundException(String message){
		super(message);
	}
}