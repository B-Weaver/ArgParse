import edu.jsu.mcis.*;
import java.util.*;

public class VolumeCalculatorD{
	public static void main(String[] args) {
		String filename = "C:/Users/Owner/Desktop/CS310/ArgParse/ArgParse/ArgParse/demos/Feature12Ex.xml";
		ArrayList<String> list = new ArrayList<String>();
		list.add("box");
		list.add("ellipsoid");
		list.add("pyramid");
		try{
			ArgumentParser parser = XMLTools.load(filename);
			parser.addNamedArgPossValues("type", list);
			parser.parseArgs(args);
			int l = Integer.parseInt(parser.getArg("length"));
			int w = Integer.parseInt(parser.getArg("width"));
			int h = Integer.parseInt(parser.getArg("height"));
			int v = l*w*h;
			System.out.println("The volume is: " + v);
			System.out.println("Digits: " +parser.getArg("digits"));
			System.out.println("Type: " +parser.getArg("type"));
			
			XMLTools.save(parser, "C:/Users/Owner/Desktop/CS310/ArgParse/ArgParse/ArgParse/demos/Feature12Out.xml");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}