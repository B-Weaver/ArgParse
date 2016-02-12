import edu.jsu.mcis.*;

public class BoxVolumeCalculator{
	public static void main(String[] args) {
		ArgumentParser parser = new ArgumentParser();
		parser.addArg("length","the length of the box");
		parser.addArg("width", "the width of the box");
		parser.addArg("height", "the height of the box");
		try {
			parser.parse(args);
			int l = Integer.parseInt(parser.getArg("length"));
			int w = Integer.parseInt(parser.getArg("width"));
			int h = Integer.parseInt(parser.getArg("height"));
			int v = l*w*h;
			System.out.println("The volume is: " + v);
		}
		catch(Exception e) {
			System.out.println(e.toString().substring(34));
		}
    }
}