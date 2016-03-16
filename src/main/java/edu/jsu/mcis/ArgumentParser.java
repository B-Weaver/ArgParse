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

public class ArgumentParser{
	protected List<Argument> args;
	private List<NamedArg> namedArgs;
	private List<PosArg> posArgs;
	protected String programName;
	protected String programPurpose;
	protected String datatype;
	
	
	public ArgumentParser(){
		args = new ArrayList<Argument>();
		namedArgs = new ArrayList<NamedArg>();
		posArgs = new ArrayList<PosArg>();
		programName = "";
		programPurpose = "";
	}
	
	public ArgumentParser(String n, String p){
		args = new ArrayList<Argument>();
		namedArgs = new ArrayList<NamedArg>();
		posArgs = new ArrayList<PosArg>();
		programName = n;
		programPurpose = p;
	}
	
	public Argument getArg(int index) {
		return args.get(index);
	}
	
	public int getNumArguments(){
		return args.size();
	}
	
	public void addArg(String name){
		addArg(name, "", Argument.Type.STRING);
	}
	
	public void addArg(String name, String description){
		addArg(name, description, Argument.Type.STRING);
	}
	
	public void addArg(String name, String description, Argument.Type type){
		args.add(new Argument(name, description, type));
	}
	
	public void addNamedArg(String name, String shortName, String description, Argument.Type type, String defaultValue){
		args.add(new NamedArg(name, shortName, description, type, defaultValue));
		namedArgs.add(new NamedArg(name, shortName, description, type, defaultValue));
	}
	
	public void addPosArg(String name, String description, Argument.Type type, String position){
		args.add(new PosArg(name, description, type, position));
		posArgs.add(new PosArg(name, description, type, position));

	}
	
	public void parse(String[] cla){
		
		Argument type = new Argument("type");
		Argument digits = new Argument("digits");
		
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
	
	public String getArg(String unit){
		Argument a = new Argument(unit);
		try{
			return args.get(args.indexOf(a)).getValue();
		}
		catch(RuntimeException e){
			throw new ArgumentNotFoundException("The argument " + unit + " was not found");
		}
			
	}
	
	public String getAllPosArgNames(){
		String s = "";
		for(int i = 0; i < args.size(); i++){
			if(getArg(i).getArgType() != Argument.Type.STRING){
				s = s + " " + args.get(i).getName();
			}	
				
		}
		
		return s;
	}
	
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
	
	public String invalidValueMessage(){
		String h = "";
		h = "usage: java " + programName + getAllPosArgNames();
		
		h = h + "\n" +programName + ".java: error: argument ";
		return h;
	}
	
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
				}
				
				else if(tempList.get(i).contains("-")){
					String s = tempList.get(i).substring(1, tempList.get(i).length());
					String v = tempList.get(i+1);
					
					for(NamedArg n : namedArgs){
						if(n.getShort().equals(s)){
							Argument b = new Argument(n.getName());
							args.get(args.indexOf(b)).setValue(v);
							tempList.remove(tempList.get(i));
							tempList.remove(tempList.get(i));
							i--;
						}
					}
				}
			}
			
			String[] tempArr = new String[tempList.size()];
			tempArr = tempList.toArray(tempArr);
			parse(tempArr);
		}
		
	}
	
	public String writeArgsAsXML(){
		String s = "<arguments>\n";
		for(Argument a : args){
			s += a.stringToXML();
		}
		s += "</arguments>";
		return s;
	}
}
