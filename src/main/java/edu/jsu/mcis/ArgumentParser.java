package edu.jsu.mcis;
import java.util.*;

public class ArgumentParser{
	private List<Argument> args;
	protected String programName;
	protected String programPurpose;
	protected String datatype;
	private boolean hasTwoDashes;
	
	public ArgumentParser(){
		args = new ArrayList<Argument>();
		programName = "";
		programPurpose = "";
		hasTwoDashes = false;
	}
	
	public ArgumentParser(String n, String p){
		args = new ArrayList<Argument>();
		programName = n;
		programPurpose = p;
	}
	
	public int getNumArguments(){
		return args.size();
	}
	
	public void addArg(String name)
	{
		args.add(new Argument(name));
	}
	
	public void addArg(String name, String description){
		args.add(new Argument(name, description));
	}
	
	public void addArg(String name, String description, String type){
		args.add(new Argument(name, description, type));
	}
	
	public void parse(String[] cla){
		String[] tempArray = cla;
		List<String> tempList = new ArrayList<String>();
		if(tempArray.length < args.size()){
			if(tempArray.length > 0 && tempArray[0].equals("-h")){
				throw new GetHelpException(getHelp());
			}
			else{
				String message = "usage: java " + programName + getAllArgNames() +"\n" + programName + ".java: error: the following arguments are required:";
				for(int i = tempArray.length; i < args.size(); i++){
					if(i < args.size()-1){
						message = message + " " + args.get(i).getName() + ",";
					}
					else{
						message = message + " " + args.get(i).getName();
					}
				}
				throw new TooFewArgsException(message);
			}
		}
		
		else if(cla.length > args.size()) {
			String[] newTempArray = new String[tempArray.length - 1];
			for(int i = 0; i < cla.length; i++){
				if(checkForDashes(cla[i])){
					args.add(new Argument(tempArray[i].substring(2, cla[i].length() - 1)));
					tempArray[i] = tempArray[i + 1];
					parse(newTempArray);
				}
			}
			String message = "usage: java " + programName + getAllArgNames() +"\n" + programName + ".java: error: unrecognized arguments:";
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
			for(int i = 0; i < cla.length; i++){
				if(args.get(i).getArgType() == Argument.Type.FLOAT){
					try{
						args.get(i).setValue(cla[i]);
						float num = Float.parseFloat(args.get(i).getValue());
					}
					catch(RuntimeException e){
							throw new InvalidValueException(invalidValueMessage() + args.get(i).getName() + ": invalid float value: " + args.get(i).getValue());
					}
				}
					
				else if(args.get(i).getArgType() == Argument.Type.INT){
					try{
						args.get(i).setValue(cla[i]);
						int num = Integer.parseInt(args.get(i).getValue());
					}
					catch(RuntimeException e){
						throw new InvalidValueException(invalidValueMessage() + args.get(i).getName() + ": invalid int value: " + args.get(i).getValue());
					}
				}
				else if(args.get(i).getArgType() == Argument.Type.BOOLEAN){
					if(cla[i].equals("true") || cla[i].equals("false")){
						args.get(i).setValue(cla[i]);
					}
					else{
						throw new InvalidValueException(invalidValueMessage() + args.get(i).getName() + ": invalid boolean value: " + cla[i]);
					}
				}
				else{
					args.get(i).setValue(cla[i]);					
										
				}
			}
		}
	}
	
	public String getArg(String unit){
		Argument a = new Argument(unit);
		if(!args.contains(a)){
			throw new ArgumentNotFoundException("The argument " + unit + " was not found");
			
			
		}
		else {
			return args.get(args.indexOf(a)).getValue();
		}
			
	}
	
	public String getAllArgNames(){
		String s = "";
		for(int i = 0; i < args.size(); i++){
			s = s + " " + args.get(i).getName() ;
		}
		
		return s;
	}
	
	public String getHelp(){
		String h = "";
		h = "usage: java " + programName + getAllArgNames();
		
		h = h + "\n" + programPurpose + "\npositional arguments:\n";
		
		for(int j = 0; j < args.size(); j++){
			if(j < args.size()-1){
				String nd = args.get(j).getNameAndDescription();
				h = h + nd +"\n";
			}
			else{
				String nd = args.get(j).getNameAndDescription();
				h = h + nd;
			}
		}
		return h;
	}
	
	public String invalidValueMessage(){
		String h = "";
		h = "usage: java " + programName + getAllArgNames();
		
		h = h + "\n" +programName + ".java: error: argument ";
		return h;
	}
	
	public boolean checkForDashes(String s){
		if(s.substring(0).equals("-") && s.substring(1).equals("-")){
			hasTwoDashes = true;
		}
		else hasTwoDashes = false;
		return hasTwoDashes;
	}
	
}
