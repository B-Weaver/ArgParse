package edu.jsu.mcis;
import java.util.*;
import java.io.*;
import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ArgumentParser{
	private List<Argument> args;
	protected String programName;
	protected String programPurpose;
	protected String datatype;
	
	
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
	
	public Argument getArg(int index) {
		return args.get(index);
	}
	
	public int getNumArguments(){
		return args.size();
	}
	
	public void addArg(String name)
	{
		addArg(name, "", Argument.Type.STRING);
	}
	
	public void addArg(String name, String description){
		addArg(name, description, Argument.Type.STRING);
	}
	
	
	
	public void addArg(String name, String description, Argument.Type type){
		args.add(new Argument(name, description, type));
	}
	
	public void parse(String[] cla){
		
		Argument type = new Argument("type");
		Argument digits = new Argument("digits");
		
		if(cla.length < args.size() && (!args.contains(type) || !args.contains(digits))){
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
		
		else if(cla.length > args.size()) {
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
			for(int i = 0; i < args.size(); i++){
				if(args.get(i).getName().equals("type") || args.get(i).getName().equals("digits")){	
					if(args.get(i).getName().equals("type") && args.get(i).getValue() == null){
						args.get(i).setValue("box");
					}
					
					else if(args.get(i).getName().equals("digits") && args.get(i).getValue() == null){
						args.get(i).setValue("4");
					}
				}
				
				else if(args.get(i).getArgType() == Argument.Type.FLOAT){
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
					if(s.equals("digits")){
						args.get(args.indexOf(a)).setValue(v);
						tempList.remove(tempList.get(i));
						tempList.remove(tempList.get(i));
						i--;
					}
					
					else if(s.equals("type")){
						args.get(args.indexOf(a)).setValue(v);
						tempList.remove(tempList.get(i));
						tempList.remove(tempList.get(i));
						i--;
					}
					
				}
				
				else if(tempList.get(i).contains("-")){
					String s = "";
					if(tempList.get(i).equals("-d")){
						s = "digits";
					}
					else if(tempList.get(i).equals("-t")){
						s = "type";
					}
					String v = tempList.get(i+1);
					
					Argument a = new Argument(s);
					if(s.equals("digits")){
						args.get(args.indexOf(a)).setValue(v);
						tempList.remove(tempList.get(i));
						tempList.remove(tempList.get(i));
						i--;
					}
					
					else if(s.equals("type")){
						args.get(args.indexOf(a)).setValue(v);
						tempList.remove(tempList.get(i));
						tempList.remove(tempList.get(i));
						i--;
					}
					
				}
			}
			
			String[] tempArr = new String[tempList.size()];
			tempArr = tempList.toArray(tempArr);
			System.out.println("index 1 : "+tempArr[0]);
			parse(tempArr);
		}
		
	}
	
	public void parseXMLFile(String xml){
		try{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(xml));
			
			doc.getDocumentElement().normalize();
			
			
		}
		catch(Throwable t){
			t.printStackTrace();
		}
	}
	
}
