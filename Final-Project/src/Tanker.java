import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Tanker extends PlayerUnit{

	public Tanker(double r) {
		radius = r;
		color = Color.BLUE;
		shape = new Circle(radius);
		highlight = new Circle(radius+5);
		speed = 4;
		maxHP = 2000;
		setup();
		
	}
	
	
	
	
	
}