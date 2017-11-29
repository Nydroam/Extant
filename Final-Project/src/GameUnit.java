import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import unit.Unit;

public class GameUnit extends Unit{
	protected Shape highlight;
	protected Color color;
	boolean selected;
	
	public Shape getHighlight() {
		return highlight;
	}
	
	public void move(double x, double y) {
		super.move(x, y);
		highlight.setLayoutX(x);
		highlight.setLayoutY(y);
	}
	
	public void toggleSelect() {
		if(selected) {
			selected = false;
			highlight.setStroke(Color.TRANSPARENT);
		}
		else {
			selected = true;
			highlight.setStroke(color);
		}
	}
}
