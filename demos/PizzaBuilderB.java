import edu.jsu.mcis.*;
import java.util.*;

public class PizzaBuilderB{
	public static void main(String[] args) {
		String filename = "C:/Users/Owner/Desktop/CS310/ArgParse/ArgParse/ArgParse/demos/Feature13Pizza.xml";
		try{
			ArgumentParser parser = XMLTools.load(filename);
			parser.parseArgs(args);
			float d = Float.parseFloat(parser.getArg("diameter"));
			float t = Float.parseFloat(parser.getArg("toppings"));
			float c = Float.parseFloat(parser.getArg("cuts"));
			float p = (d*.75f) + t + (c*.25f);
			System.out.println("The price is: $" + p);
			
			XMLTools.save(parser, "C:/Users/Owner/Desktop/CS310/ArgParse/ArgParse/ArgParse/demos/Feature13PizzaOut.xml");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}