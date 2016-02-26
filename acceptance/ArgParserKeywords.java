import edu.jsu.mcis.*;

public class ArgParserKeywords{
	private ArgumentParser parser;
	private ArgumentParser parser2 = new ArgumentParser();
	private String output;
					
	public void StartVolumeCalculatorWithArguments(String[] args){
		parser = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		parser.addArg("length", "the length of the box (float)", "float");
		parser.addArg("width", "the width of the box (float)", "float");
		parser.addArg("height", "the height of the box (float)", "float");
		try {
			parser.checkArgsThenParse(args);
			int l = Integer.parseInt(parser.getArg("length"));
			int w = Integer.parseInt(parser.getArg("width"));
			int h = Integer.parseInt(parser.getArg("height"));
			int v = l*w*h;
			output = v + "";
		}
		catch(Exception e) {
			output = e.getMessage();
		}
	}
	
	public void StartVolumeCalcuatorWithArguments(String[] args){
		parser = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		parser.addArg("length", "the length of the box (float)", "float");
		parser.addArg("width", "the width of the box (float)", "float");
		parser.addArg("height", "the height of the box (float)", "float");
		parser.addArg("type");
		parser.addArg("digits");
		try {
			parser.checkArgsThenParse(args);
			int l = Integer.parseInt(parser.getArg("length"));
			int w = Integer.parseInt(parser.getArg("width"));
			int h = Integer.parseInt(parser.getArg("height"));
			int v = l*w*h;
			output = v + "";
		}
		catch(Exception e) {
			output = e.getMessage();
		}
	}
	
	public void StartProgramWithArguments(String[] args){
		parser = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		parser.addArg("length", "the length of the box (float)", "float");
		parser.addArg("width", "the width of the box (float)", "float");
		parser.addArg("height", "the height of the box (float)", "float");
		try {
			parser.parse(args);
			int l = Integer.parseInt(parser.getArg("length"));
			int w = Integer.parseInt(parser.getArg("width"));
			int h = Integer.parseInt(parser.getArg("height"));
			int v = l*w*h;
			output = v + "";
		}
		catch(Exception e) {
			output = e.getMessage();
		}
	}
	
	public String getLength(){
		return parser.getArg("length");
	}
	
	public String getWidth(){
		return parser.getArg("width");
	}
	
	public String getHeight(){
		return parser.getArg("height");
	}
	
	public String getProgramOutput(){
		return output;
	}
	
	public String getDigits(){
		return parser.getArg("digits");
	}
	
	public String getType(){
		return parser.getArg("type");
	}
	
	public void StartAbsurdProgramWithArguments(String[] args){
		parser2.addArg("Pet", "Animal", "string");
		parser2.addArg("Number", "random", "int");
		parser2.addArg("Rainy", "weahter", "boolean");
		parser2.addArg("Bathrooms", "number of bathrooms", "float");
		parser2.parse(args);
	}
	
	public String getPet(){
		return parser2.getArg("Pet");
	}
	
	public String getNumber(){
		return parser2.getArg("Number");
	}
	
	public String getRainy(){
		return parser2.getArg("Rainy");
	}
	
	public String getBathrooms(){
		return parser2.getArg("Bathrooms");
	}
}