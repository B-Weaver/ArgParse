package edu.jsu.mcis;
import java.util.*;

public class ArgumentParser{
	private List<Argument> args;
	protected String programName;
	protected String programPurpose;
	
	public ArgumentParser(){
		args = new ArrayList<Argument>();
		programName = "";
		programPurpose = "";
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
	
	public void parse(String[] cla){
		if(cla.length > 0){
			if(cla[0].equals("-h")){
				throw new GetHelpException(getHelp());
			}
			
			else if(cla.length > args.size()) {
				throw new TooManyArgsException("usage: java " + programName + getAllArgNames() +"\n" + programName + ".java: error: unrecognized arguments: " + cla[cla.length - 1]);
			}
			else if(cla.length == args.size()){
				for(int i = 0; i < cla.length; i++){
					args.get(i).setValue(cla[i]);
				}
			}
			else if(cla.length < args.size() && cla.length == 2){
				String message = "usage: java " + programName + getAllArgNames() +"\n" + programName + ".java: error: the following arguments are required: ";
				for(int i = 2; i < args.size(); i++){
					message = message + args.get(i).getName();
				}
				throw new TooFewArgsException(message);
			}
			else if(cla.length < args.size() && cla.length == 1) {
				String[] newArgs = new String[3];
				String message = "usage: java " + programName + getAllArgNames() +"\n" + programName + ".java: error: the following arguments are required: ";
				for(int i = 1; i < args.size(); i++){
					newArgs[i] = args.get(i).getName();
					message = message + newArgs[i];
					if(i < 2){
						message = message + ", ";
					}
				}
				throw new TooFewArgsException(message);
			}
		}
		
		else if(cla.length == 0){
			String[] newArgs = new String[3];
			String message = "usage: java " + programName + getAllArgNames() +"\n" + programName + ".java: error: the following arguments are required: ";
			for(int i = 0; i < args.size(); i++){
				newArgs[i] = args.get(i).getName();
				message = message + newArgs[i];
				if(i < 2){
					message = message + ", ";
				}
			}
			throw new TooFewArgsException(message);
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
		h = "usage: java " + programName + " ";
		for( int i = 0; i < args.size(); i++){
			if(i < args.size()-1){
				String n = args.get(i).getName();
				h = h + n + " ";
			}
			else{
				String n = args.get(i).getName();
				h = h + n;
			}
		}
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
	
	public String invalidValue(){
		String h = "";
		h = "usage: java " + programName + " ";
		for( int i = 0; i < args.size(); i++){
			if(i < args.size()-1){
				String n = args.get(i).getName();
				h = h + n + " ";
			}
			else{
				String n = args.get(i).getName();
				h = h + n;
			}
		}
		h = h + "\n" +programName + ".java: error: invalid ";
		return h;
	}
	
}
