import edu.jsu.mcis.*;

public class VolumeCalculatorB{
	public static void main(String[] args) {
		ArgumentParser parser = new ArgumentParser("VolumeCalculatorB", "Calculate the volume of whatever.");
		parser.addArg("length","the length of the box (int)", Argument.Type.INT);
		parser.addArg("width", "the width of the box (int)", Argument.Type.INT);
		parser.addArg("height", "the height of the box (int)", Argument.Type.INT);
		parser.addArg("type");
		parser.addArg("digits");
		try {
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