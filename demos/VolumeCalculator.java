import edu.jsu.mcis.*;

public class VolumeCalculator{
	public static void main(String[] args) {
		ArgumentParser parser = new ArgumentParser("VolumeCalculator", "Calculate the volume of a box.");
		parser.addArg("length","the length of the box", Argument.Type.INT);
		parser.addArg("width", "the width of the box", Argument.Type.INT);
		parser.addArg("height", "the height of the box", Argument.Type.INT);
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