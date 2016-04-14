import edu.jsu.mcis.*;

public class VolumeCalculatorC{
	public static void main(String[] args) {
		String filename = "C:/Users/Barry/CS310/ArgParse/demos/Feature9Ex.xml";
		try{
			ArgumentParser parser = XMLTools.load(filename);
			parser.parseArgs(args);
			int l = Integer.parseInt(parser.getArg("length"));
			int w = Integer.parseInt(parser.getArg("width"));
			int h = Integer.parseInt(parser.getArg("height"));
			int v = l*w*h;
			System.out.println("The volume is: " + v);
			System.out.println("Digits: " +parser.getArg("digits"));
			System.out.println("Type: " +parser.getArg("type"));
			
			XMLTools.save(parser, "C:/Users/Barry/CS310/ArgParse/demos/Feature10.xml");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}