import edu.jsu.mcis.*;
import java.util.*;

public class VolumeCalculatorD{
	public static void main(String[] args) {
		String filename = "C:/Users/Owner/Desktop/CS310/ArgParse/ArgParse/ArgParse/demos/Feature12Ex.xml";
		try{
			ArgumentParser parser = XMLTools.load(filename);
			parser.parseArgs(args);
			int l = Integer.parseInt(parser.getArgValue("length"));
			int w = Integer.parseInt(parser.getArgValue("width"));
			int h = Integer.parseInt(parser.getArgValue("height"));
			int v = l*w*h;
			System.out.println("The volume is: " + v);
			System.out.println("Digits: " +parser.getArgValue("digits"));
			System.out.println("Type: " +parser.getArgValue("type"));
			
			XMLTools.save(parser, "C:/Users/Owner/Desktop/CS310/ArgParse/ArgParse/ArgParse/demos/Feature12Out.xml");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}