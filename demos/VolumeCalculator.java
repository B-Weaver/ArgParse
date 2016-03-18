import edu.jsu.mcis.*;

public class VolumeCalculator{
	public static void main(String[] args) {
		ArgumentParser parser = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		parser.addPosArg("length","the length of the box", Argument.Type.INT, "1");
		parser.addPosArg("width", "the width of the box", Argument.Type.INT, "2");
		parser.addPosArg("height", "the height of the box", Argument.Type.INT, "3");
		try {
			parser.checkArgsThenParse(args);
			int l = Integer.parseInt(parser.getArg("length"));
			int w = Integer.parseInt(parser.getArg("width"));
			int h = Integer.parseInt(parser.getArg("height"));
			int v = l*w*h;
			System.out.println("The volume is: " + v);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
    }
}