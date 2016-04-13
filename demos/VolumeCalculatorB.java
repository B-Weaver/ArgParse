import edu.jsu.mcis.*;

public class VolumeCalculatorB{
	public static void main(String[] args) {
		ArgumentParser parser = new ArgumentParser("VolumeCalculatorB", "Calculate the volume of whatever.");
		parser.addPosArg("length","the length of the box (int)", Argument.Type.INT, "1");
		parser.addPosArg("width", "the width of the box (int)", Argument.Type.INT, "2");
		parser.addPosArg("height", "the height of the box (int)", Argument.Type.INT, "3");
		parser.addNamedArg("type", "t", "type of shape", Argument.Type.STRING, "box");
		parser.addNamedArg("digits", "d", "digits of type", Argument.Type.STRING, "4");
		try {
			parser.parseArgs(args);
			int l = Integer.parseInt(parser.getArgValue("length"));
			int w = Integer.parseInt(parser.getArgValue("width"));
			int h = Integer.parseInt(parser.getArgValue("height"));
			int v = l*w*h;
			System.out.println("The volume is: " + v);
			System.out.println("Digits: " +parser.getArgValue("digits"));
			System.out.println("Type: " +parser.getArgValue("type"));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
    }
}