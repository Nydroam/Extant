import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Shooter extends PlayerUnit {
	public Shooter(double r) {
		radius = r;
		color = Color.RED;
		shape = new Circle(radius);
		highlight = new Circle(radius+5);
		attackLine = new Line();
		
		attackRange = new Circle(radius*6);
		attackRange.setMouseTransparent(true);
		attackRange.setFill(Color.TRANSPARENT);
		attackRange.setStroke(Color.BLACK);
		speed = 3;
		setup();
	}
}
