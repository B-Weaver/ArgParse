import edu.jsu.mcis.*;

public class ArgParserKeywords{
	private ArgumentParser parser = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
	private ArgumentParser parser2 = new ArgumentParser();
	private String output;
					
	public void StartVolumeCalculatorWithArguments(String[] args){
		parser.addArg("length");
		parser.addArg("width");
		parser.addArg("height");
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
	
	public void StartProgramWithArguments(String[] args){
		parser.addArg("length", "the length of the box (float)");
		parser.addArg("width", "the width of the box (float)");
		parser.addArg("height", "the height of the box (float)");
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
	
	public void StartAbsurdProgramWithArguments(String[] args){
		parser2.addArg("Pet");
		parser2.addArg("Number");
		parser2.addArg("Rainy");
		parser2.addArg("Bathrooms");
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