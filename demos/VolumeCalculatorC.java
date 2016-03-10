import edu.jsu.mcis.*;

public class VolumeCalculatorC{
	public static void main(String[] args) {
		ArgumentParser parser = new ArgumentParser("VolumeCalculatorC", "Calculate the volume of any shape.");
		String filename = "C:/Users/Barry/CS310/ArgParse/src/test/java/edu/jsu/mcis/Feature9Ex.xml";
		try{
			parser.parseXMLFile(filename);
			parser.checkArgsThenParse(args);
			int l = Integer.parseInt(parser.getArg("length"));
			int w = Integer.parseInt(parser.getArg("width"));
			int h = Integer.parseInt(parser.getArg("height"));
			int v = l*w*h;
			System.out.println("The volume is: " + v);
			System.out.println("Digits: " +parser.getArg("digits"));
			System.out.println("Type: " +parser.getArg("type"));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}