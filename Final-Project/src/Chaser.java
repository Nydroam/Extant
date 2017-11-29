import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Chaser extends GameUnit{

	public Chaser(double radius) {
		color = Color.BLUE;
		shape = new Circle(radius);
		shape.setFill(color);
		highlight = new Circle(radius+5);
		highlight.setFill(Color.TRANSPARENT);
		highlight.setMouseTransparent(true);
		selected = true;
		toggleSelect();
		
	}
	
}
