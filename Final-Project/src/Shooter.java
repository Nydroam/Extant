import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Shooter extends GameUnit {
	public Shooter(double radius) {
		color = Color.RED;
		shape = new Circle(radius);
		shape.setFill(color);
		highlight = new Circle(radius+5);
		highlight.setFill(Color.TRANSPARENT);
		highlight.setMouseTransparent(true);
		selected = true;
		toggleSelect();
	}
}
