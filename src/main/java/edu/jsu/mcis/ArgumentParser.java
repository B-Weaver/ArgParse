package edu.jsu.mcis;
import java.util.*;

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
		args.add(new Argument(name));
	}
	
	public void addArg(String name, String description){
		args.add(new Argument(name, description));
	}
	
	public void addArg(String name, String description, String type){
		args.add(new Argument(name, description, type));
	}
	
	public void parse(String[] cla){
		if(cla.length > 0 && (cla[0].equals("-h") || cla[0].equals("--help"))){
				throw new GetHelpException(getHelp());
		}
		if(cla.length < args.size()){
			String message = "usage: java " + programName + getAllArgNames() +"\n" + programName + ".java: error: the following arguments are required:";
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
		try{
			return args.get(args.indexOf(a)).getValue();
		}
		catch(RuntimeException e){
			throw new ArgumentNotFoundException("The argument " + unit + " was not found");
		}
			
	}
	
	public String getAllArgNames(){
		String s = "";
		for(int i = 0; i < args.size(); i++){
			s = s + " " + args.get(i).getName();
		}
		
		return s;
	}
	
	public String getAllArgValues(){
		String s = "";
		for(int i = 0; i < args.size(); i++){
			s = s + " " + args.get(i).getValue() ;
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
	
	public void checkArgsThenParse(String[] arr){
		ArrayList<String> tempList = new ArrayList<String>(Arrays.asList(arr));
		for(int i = 0; i < tempList.size(); i++){
			System.out.println("************"+i+"************");
			if(tempList.get(i).contains("--")){
				String s = tempList.get(i).substring(2, tempList.get(i).length());
				
				Argument a = new Argument(s);
				if(s.equals("digits")){
					System.out.println(s);
					args.get(args.indexOf(a)).setValue(tempList.get(i+1));
					tempList.remove(i);
					tempList.remove(i+1);
					//i--;
				}
				
				else if(s.equals("type")){
					System.out.println(s);
					args.get(args.indexOf(a)).setValue(tempList.get(i+1));
					tempList.remove(i);
					tempList.remove(i+1);
					//i--;
				}
			}
		}
						System.out.println("\n\n");

		String[] tempArr = new String[tempList.size()];
		tempArr = tempList.toArray(tempArr);
		for(int t = 0; t < tempArr.length; t++){
			System.out.println(tempArr[t]);
		}
		parse(tempArr);
	}
	
}
