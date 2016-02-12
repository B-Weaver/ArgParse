package edu.jsu.mcis;
import java.util.*;

public class ArgumentParser{
	private List<Argument> args;
	
	public ArgumentParser(){
		args = new ArrayList<Argument>();
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
				throw new TooFewArgsException(getHelp());
			}
			
			else if(cla.length > args.size()) {
				throw new TooManyArgsException("usage: java VolumeCalculator length width height"+"\n"+"VolumeCalculator.java: error: unrecognized arguments: " + cla[cla.length - 1]);
			}
			else if(cla.length == args.size()){
				for(int i = 0; i < cla.length; i++){
					args.get(i).setValue(cla[i]);
				}
			}
			else if(cla.length < args.size() && cla.length == 2){
				throw new RuntimeException("usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: the following arguments are required: height");
			}
			else if(cla.length < args.size() && cla.length == 1) {
				throw new RuntimeException("usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: the following arguments are required: width, height");
			}
		}
		
		else if(cla.length == 0){
			throw new RuntimeException("usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: the following arguments are required: length, width, height");
		}
	}
	
	public String getArg(String unit){
		Argument a = new Argument(unit);
		if(args.contains(a)){
			return args.get(args.indexOf(a)).getValue();
		}
		else 
			return "";
	}
	
	public String getHelp(){
		String h = "";
		h = "usage: java VolumeCalculator ";
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
		h = h + "\nCalculate the volume of a box.\npositional arguments:\n";
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
	
}
