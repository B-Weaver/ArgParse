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
	
	public void parse(String[] cla){
		if(cla.length > args.size()) {
			throw new TooManyArgsException("usage: java VolumeCalculator length width height"+"\n"+"VolumeCalculator.java: error: unrecognized arguments: " + cla[cla.length - 1]);
		}
		else if(cla.length < args.size() && cla.length == 2) throw new RuntimeException("usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: the following arguments are required: height");
		else if(cla.length < args.size() && cla.length == 1) {
			if(cla[0].equals("-h")){
				String h = "";
				h = "usage: java VolumeCalculator ";
				for( int i = 0; i < args.size(); i++){
					String n = args.get(args.indexOf(i)).getName();
					h = h + n;
				}
				h = h + "\nCalculate the volume of a box.\npositional arguments:\n";
				for(int j = 0; j < args.size(); j++){
					String nd = args.get(args.indexOf(j)).getNameAndDescription();
					h = h + "\t" + nd +"\n";
				}
				throw new TooManyArgsException(h);
			}
			else 
				throw new RuntimeException("usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: the following arguments are required: width, height");
		}
		else if(cla.length < args.size() && cla.length == 0) throw new RuntimeException("usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: the following arguments are required: length, width, height");
		else{
			if(cla.length == args.size()){
				for(int i = 0; i < cla.length; i++){
					args.get(i).setValue(cla[i]);
				}
			}
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
	
	
	
}
