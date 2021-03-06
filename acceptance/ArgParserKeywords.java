import edu.jsu.mcis.*;

public class ArgParserKeywords{
	private ArgumentParser parser;
	private ArgumentParser parser2 = new ArgumentParser();
	private String output;
					
	public void StartVolumeCalculatorWithArguments(String[] args){
		parser = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		parser.addPosArg("length", "the length of the box (float)", Argument.Type.FLOAT, "1");
		parser.addPosArg("width", "the width of the box (float)", Argument.Type.FLOAT, "2");
		parser.addPosArg("height", "the height of the box (float)", Argument.Type.FLOAT, "3");
		try {
			parser.parseArgs(args);
			int l = Integer.parseInt(parser.getArgValue("length"));
			int w = Integer.parseInt(parser.getArgValue("width"));
			int h = Integer.parseInt(parser.getArgValue("height"));
			int v = l*w*h;
			output = v + "";
		}
		catch(Exception e) {
			output = e.getMessage();
		}
	}
	
	public void StartVolumeCalcuatorWithArguments(String[] args){
		parser = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		parser.addPosArg("length", "the length of the box (float)", Argument.Type.FLOAT, "1");
		parser.addPosArg("width", "the width of the box (float)", Argument.Type.FLOAT, "2");
		parser.addPosArg("height", "the height of the box (float)", Argument.Type.FLOAT, "3");
		parser.addNamedArg("type", "t", "type of shape", Argument.Type.STRING, "box");
		parser.addNamedArg("digits", "d", "digits of type", Argument.Type.STRING, "4");
		
		try {
			parser.parseArgs(args);
		}
		catch(Exception e) {
			output = e.getMessage();
		}
		
	}
	
	public void StartProgramWithArguments(String[] args){
		parser = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		parser.addPosArg("length", "the length of the box (float)", Argument.Type.FLOAT, "1");
		parser.addPosArg("width", "the width of the box (float)", Argument.Type.FLOAT, "2");
		parser.addPosArg("height", "the height of the box (float)", Argument.Type.FLOAT, "3");
		parser.addNamedArg("type", "t", "type of shape", Argument.Type.STRING, "box");
		parser.addNamedArg("digits", "d", "digits of type", Argument.Type.STRING, "4");
		try {
			parser.parseArgs(args);
			int l = Integer.parseInt(parser.getArgValue("length"));
			int w = Integer.parseInt(parser.getArgValue("width"));
			int h = Integer.parseInt(parser.getArgValue("height"));
			int v = l*w*h;
			output = v + "";
		}
		catch(Exception e) {
			output = e.getMessage();
		}
	}
	
	public String getLength(){
		return parser.getArgValue("length");
	}
	
	public String getWidth(){
		return parser.getArgValue("width");
	}
	
	public String getHeight(){
		return parser.getArgValue("height");
	}
	
	public String getProgramOutput(){
		return output;
	}
	
	public String getDigits(){
		return parser.getArgValue("digits");
	}
	
	public String getType(){
		return parser.getArgValue("type");
	}
	
	public void StartAbsurdProgramWithArguments(String[] args){
		parser2.addPosArg("Pet", "Animal", Argument.Type.STRING, "1");
		parser2.addPosArg("Number", "random", Argument.Type.INT, "2");
		parser2.addPosArg("Rainy", "weahter", Argument.Type.BOOLEAN, "3");
		parser2.addPosArg("Bathrooms", "number of bathrooms", Argument.Type.FLOAT, "4");
		parser2.parseArgs(args);
	}
	
	public String getPet(){
		return parser2.getArgValue("Pet");
	}
	
	public String getNumber(){
		return parser2.getArgValue("Number");
	}
	
	public String getRainy(){
		return parser2.getArgValue("Rainy");
	}
	
	public String getBathrooms(){
		return parser2.getArgValue("Bathrooms");
	}
}