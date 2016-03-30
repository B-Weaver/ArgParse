package edu.jsu.mcis;
import java.util.*;
import java.io.*;
import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
*@author Barry Weaver
*@author Gary Hastert
*@author Jonathon Beecham
*@author Matthew Arbuckle
*@author Oladiran Ojuolape
*@author Christopher Abercrombie
*This class allows for the user to create arguments and set their values by parsing through their inputs.
*/

public class ArgumentParser{
	protected List<Argument> args;
	private List<NamedArg> namedArgs;
	private List<PosArg> posArgs;
	protected String programName;
	protected String programPurpose;
	protected String datatype;
	
	/**
	*This is the default constructor. The user calls this if they do not specify a name and purpose for the program they are using.
	*/
	
	public ArgumentParser(){
		args = new ArrayList<Argument>();
		namedArgs = new ArrayList<NamedArg>();
		posArgs = new ArrayList<PosArg>();
		programName = "";
		programPurpose = "";
	}
	
	/**
	*This constructor creates a new instance of the ArgumentParser class when called.
	*@param n  specifies the name of the program, as provided by the user.
	*@param p  specifies the description of the program, as provided by the user.
	*/
	
	public ArgumentParser(String n, String p){
		args = new ArrayList<Argument>();
		namedArgs = new ArrayList<NamedArg>();
		posArgs = new ArrayList<PosArg>();
		programName = n;
		programPurpose = p;
	}
	
	/**
	*This method returns the name of the argument from the args list with at the specified index.
	*@param index  the index of the argument being returned.
	*@return       the argument at the index.
	*/
	
	public Argument getArg(int index) {
		return args.get(index);
	}
	
	/**
	*This method returns the size of the args list, which represents the number of total arguments provided by the user.
	*@return          the size of the args list.
	*/
	
	public int getNumArguments(){
		return args.size();
	}
	
	/**
	*This method adds the argument provided to the list by calling the main constructor by providing a blank string for a description and 
	*defaulting the argument to type string.
	*@param name   the name of the argument.
	*/
	
	public void addArg(String name){
		addArg(name, "", Argument.Type.STRING);
	}
	
	/**
	*This method adds the argument provided to the list by calling main constructor by providing the name and description, provided by the user, and
	*defaulting the type to string.
	*@param name   the name of the argument.
	*@param description   the description of said argument.
	*/
	
	public void addArg(String name, String description){
		addArg(name, description, Argument.Type.STRING);
	}
	
	/**
	*This method adds the argument provided to the list by calling main constructor by providing the name, description, and type of argument,
	*as provided by the user.
	*@param name   the name of the argument.
	*@param description   the description of said argument.
	*@param type   the type of argument.
	*/
	
	public void addArg(String name, String description, Argument.Type type){
		args.add(new Argument(name, description, type));
	}
	
	/**
	*This method adds a named argument by adding it to the args list and to the NamedArgs class.
	*@param name   the name of the argument.
	*@param shortName   the short name version of the argument, such as 't' instead of "type."
	*@param description   the description of said argument.
	*@param type   the type of argument.
	*@param defaultValue   the user specified default value of the named argument.
	*/
	
	public void addNamedArg(String name, String shortName, String description, Argument.Type type, String defaultValue){
		args.add(new NamedArg(name, shortName, description, type, defaultValue));
		namedArgs.add(new NamedArg(name, shortName, description, type, defaultValue));
	}
	
	/**
	*This method adds a positional argument by adding it to the args list and to the PosArgs class.
	*@param name   the name of the argument.
	*@param description   the description of said argument.
	*@param type   the type of argument.
	*@param position   the position in the args list the argument goes
	*/
	
	public void addPosArg(String name, String description, Argument.Type type, String position){
		args.add(new PosArg(name, description, type, position));
		posArgs.add(new PosArg(name, description, type, position));

	}
	
	/**
	*This method parses through the values provided by the user and assigns these values to the positional arguments.
	*@param cla   the values to be assigned to the positional arguments.
	*@exception TooFewArgsException   this exception is thrown when too few arguments are provided by the user.
	*@exception TooManyArgsException  this exception is thrown when too many arguments are provided by the user.
	*@exception InvalidValueException  this exception is thrown when the user provides a value of the wrong type for an argument.
	*/
	
	public void parse(String[] cla){
		if(cla.length < posArgs.size()){
			String message = "usage: java " + programName + getAllPosArgNames() +"\n" + programName + ".java: error: the following arguments are required:";
			for(int i = cla.length; i < args.size(); i++){
				if(i < args.size()-1){
					message = message + " " + args.get(i).getName() + ",";
				}
				else{
					message = message + " " + args.get(i).getName();
				}
			}
			throw new TooFewArgsException(message);
		}
		
		else if(cla.length > posArgs.size()) {
			String message = "usage: java " + programName + getAllPosArgNames() +"\n" + programName + ".java: error: unrecognized arguments:";
					for(int i = args.size(); i < cla.length; i++){
						if(i < cla.length-1){
							message = message + " " + cla[i] + ",";
						}
						else{
							message = message + " " + cla[i];
						}
					}
					throw new TooManyArgsException(message);
		}
		else{
			for(int i = 0; i < posArgs.size(); i++){
				Argument c = new Argument(posArgs.get(i).getName());
				if(posArgs.get(i).getArgType() == Argument.Type.FLOAT){
					try{
						args.get(args.indexOf(c)).setValue(cla[i]);
						float num = Float.parseFloat(args.get(i).getValue());
					}
					catch(RuntimeException e){
							throw new InvalidValueException(invalidValueMessage() + args.get(i).getName() + ": invalid float value: " + args.get(i).getValue());
					}
				}
					
				else if(posArgs.get(i).getArgType() == Argument.Type.INT){
					try{
						args.get(args.indexOf(c)).setValue(cla[i]);
						int num = Integer.parseInt(args.get(i).getValue());
					}
					catch(RuntimeException e){
						throw new InvalidValueException(invalidValueMessage() + args.get(i).getName() + ": invalid int value: " + args.get(i).getValue());
					}
				}
				else if(posArgs.get(i).getArgType() == Argument.Type.BOOLEAN){
					if(cla[i].equals("true") || cla[i].equals("false")){
						args.get(args.indexOf(c)).setValue(cla[i]);
					}
					else{
						throw new InvalidValueException(invalidValueMessage() + args.get(i).getName() + ": invalid boolean value: " + cla[i]);
					}
				}
				else{
					args.get(args.indexOf(c)).setValue(cla[i]);														
				}
			}
		}
	}
	
	/**
	*This method is called by the user and is used to return the value of an argument.
	*@param unit   the name of the argument to search for.
	*@return       the value of the argument.
	*@exception ArgumentNotFoundException  thrown when the argument doesn't exist.
	*/
	
	public String getArg(String unit){
		Argument a = new Argument(unit);
		try{
			return args.get(args.indexOf(a)).getValue();
		}
		catch(RuntimeException e){
			throw new ArgumentNotFoundException("The argument " + unit + " was not found");
		}		
	}
	
	/**
	*This method is called by various methods to return the names of all the positional arguments in the list.
	*@return  the names of all positional arguments.
	*/
	
	public String getAllPosArgNames(){
		String s = "";
		for(int i = 0; i < args.size(); i++){
			if(getArg(i).getArgType() != Argument.Type.STRING){
				s = s + " " + args.get(i).getName();
			}	
				
		}
		
		return s;
	}
	
	/**
	*This method is used to return the names of all the named arguments.
	*@return   the names of all named arguments.
	*/
	
	public String getAllNamedArgNames(){
		String s = "";
		for(int i = 0; i < namedArgs.size(); i++){
			s = s + " " + namedArgs.get(i).getName();
		}
		return s;
	}
	
	/**
	*This method is called when the user enters --help or -h. When called, a message with information on the program is displayed.
	*@return   the help message.
	*/
	
	public String getHelp(){
		String h = "";
		h = "usage: java " + programName + getAllPosArgNames();
		
		h = h + "\n" + programPurpose + "\npositional arguments:\n";
		
		for(int j = 0; j < args.size(); j++){
			if(getArg(j).getArgType() != Argument.Type.STRING){	
				if(j == 0){
					String nd = getArg(j).getNameAndDescription();
					h = h + nd;
				}
				else{
					String nd = "\n" + getArg(j).getNameAndDescription();
					h = h + nd;
				}
			}
		}
		return h;
	}
	
	/**
	*This method is called when the UnacceptedValueException is thrown. It generates the error message.
	*@param v   the unaccepted value.
	*@return   the error message.
	*/
	
	public String unacceptedValueMessage(String v){
		String m = "usage: java " + programName + getAllPosArgNames() + " " + getAllNamedArgNames();
		m = m + "\n" + programName + ".java: unacceptedValue: " + v;
		
		return m;
	}
	
	/**
	*This method is called when the user enters an invalid value for an argument.
	*@return   the error message for the InvalidValueException.
	*/
	
	public String invalidValueMessage(){
		String h = "";
		h = "usage: java " + programName + getAllPosArgNames();
		
		h = h + "\n" +programName + ".java: error: argument ";
		return h;
	}
	
	/**
	*This method is to be called by the user. It should be called by the user instead of parse. This method calls checkArgsThenParse. It is mainly
	*here for something the user can call easier instead of checkArgsThenParse because it has a shorter name.
	*@param arr   an array with values for arguments provided by the user.
	*/
	
	public void parseArgs(String[] arr) {
		checkArgsThenParse(arr);
	}
	
	/**
	*This method checks for the named arguments, assigns their default values, and removes the arguments from the array of arguments. 
	*It then calls parse for the positional arguments left to be assigned values.
	*@param arr   an array with values for arguments provided by the user.
	*@exception GetHelpException  an exception called when the user wants the help message. Thrown when --help or -h is provided by the user.
	*@exception ArgumentNotFoundException  an exception thrown when the user provides a long-form or a short-name argument that doesn't exist in the list of arguments.
	*/
	
	public void checkArgsThenParse(String[] arr){
		ArrayList<String> tempList = new ArrayList<String>(Arrays.asList(arr));
		
		if(tempList.contains("--help")){
			tempList.remove(tempList.indexOf("--help"));
			throw new GetHelpException(getHelp());
			
		}
		else if(tempList.contains("-h")){
			tempList.remove(tempList.indexOf("-h"));
			throw new GetHelpException(getHelp());
		}
		else{
			for(int i = 0; i < tempList.size(); i++){
				if(tempList.get(i).contains("--")){
					String s = tempList.get(i).substring(2, tempList.get(i).length());
					String v = tempList.get(i+1);
					getArg(s);
					Argument a = new Argument(s);
					if(args.contains(a)){
						args.get(args.indexOf(a)).setValue(v);
						tempList.remove(tempList.get(i));
						tempList.remove(tempList.get(i));
						i--;
					}
					else{
						throw new ArgumentNotFoundException("The argument " + s + " was not found");
					}
				}
				
				else if(tempList.get(i).contains("-")){
					String s = tempList.get(i).substring(1, tempList.get(i).length());
					String v = tempList.get(i+1);
					boolean shortFound = false;
					
					for(NamedArg n : namedArgs){
						if(n.getShort().equals(s)){
							if(n.possVals.size() > 0){
								if(n.possVals.contains(v)){
									Argument b = new Argument(n.getName());
									args.get(args.indexOf(b)).setValue(v);
									tempList.remove(tempList.get(i));
									tempList.remove(tempList.get(i));
									i--;
									shortFound = true;
									break;
								}
								
								else
									throw new UnacceptedValueException(unacceptedValueMessage(v));
							}
							
							else{
								Argument b = new Argument(n.getName());
								args.get(args.indexOf(b)).setValue(v);
								tempList.remove(tempList.get(i));
								tempList.remove(tempList.get(i));
								i--;
								shortFound = true;
								break;
							}
							
						}
					}
					if(!shortFound){
						throw new ArgumentNotFoundException("No argument found with short name " + s);
					}
				}
			}
			
			String[] tempArr = new String[tempList.size()];
			tempArr = tempList.toArray(tempArr);
			parse(tempArr);
		}
		
	}
	
	public void addNamedArgPossValues(String argName, ArrayList<String> list){
		Argument a = new Argument(argName);
		NamedArg n = (NamedArg) args.get(args.indexOf(a));
		namedArgs.get(namedArgs.indexOf(n)).setPossVal(list);
	}
	
	/**
	*This method is used in the save method of XMLTools. This method converts the arguments and their values into a format to be used in an XML file.
	*@return   a string containing the arguments and their info in a format suitable for an XML file.
	*/
	
	public String writeArgsAsXML(){
		String s = "<ArgParser>\n\t<program>\n\t\t<programname>" + programName + "</programname>\n\t\t<description>" + programPurpose +"</description>\n\t</program>\n";
		for(Argument a : args){
			s += a.stringToXML();
		}
		s += "</ArgParser>";
		return s;
	}
}
