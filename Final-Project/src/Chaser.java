import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Chaser extends PlayerUnit{

	public Chaser(double r) {
		radius = r;
		color = Color.BLUE;
		shape = new Circle(radius);
		highlight = new Circle(radius+5);
		
		speed = 4;
		setup();
		
	}
	
	
	
}
