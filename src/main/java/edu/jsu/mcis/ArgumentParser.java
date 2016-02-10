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
			throw new RuntimeException("usage: java VolumeCalculator length width height"+"\n"+"VolumeCalculator.java: error: unrecognized arguments: " + cla[cla.length - 1]);
		}
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
