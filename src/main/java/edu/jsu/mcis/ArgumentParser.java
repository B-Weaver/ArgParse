package edu.jsu.mcis;
import java.util.*;

public class ArgumentParser{
	private List<String> argNames;
	private List<String> argValues;
	
	public ArgumentParser(){
		argNames = new ArrayList<String>();
		argValues = new ArrayList<String>();
	}
	
	public int getNumArguments(){
		return argNames.size();
	}
	
	public void addArg(String name)
	{
		argNames.add(name);
	}
	
	public void parse(String[] cla){
		if(cla.length > argNames.size()) {
			throw new TooManyArgsException("usage: java VolumeCalculator length width height\nVolumeCalculator.java: error: unrecognized arguments: " + cla[cla.length - 1]);
		}
		else if(cla.length < argNames.size() && cla.length == 2) throw new TooFewArgsException("Failed to supply the following arguments: height");
		else if(cla.length < argNames.size() && cla.length == 1) throw new TooFewArgsException("Failed to supply the following arguments: width, height");
		else if(cla.length < argNames.size() && cla.length == 0) throw new TooFewArgsException("Failed to supply the following arguments: length, width, height");
		else{
			for(int i = 0; i < cla.length; i++)
			{
				argValues.add(cla[i]);
			}
		}
	}
	
	public String getArg(String unit){
		String value = "";
		int position;
		if(argNames.contains(unit))
		{
			position = argNames.indexOf(unit);
			value = argValues.get(position);
		}
		return value;
	}
	
}
