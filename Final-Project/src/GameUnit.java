import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import unit.Unit;

public class GameUnit extends Unit{
	protected Shape highlight;
	protected Color color;
	protected boolean selected;
	protected boolean vertical;
	protected int magnitude;
	protected double length;
	
	public GameUnit() {
		setDirection(true,0);
	}
	public Shape getHighlight() {
		return highlight;
	}
	
	public void setDirection(boolean v, int m) {
		vertical = v;
		magnitude = m;
	}
	
	public double getLength() {
		return length;
	}
	
	public boolean getV() {
		return vertical;
	};
	public int getM() {
		return magnitude;
	};
	
	public void move(double x, double y) {
		super.move(x, y);
		highlight.setLayoutX(x);
		highlight.setLayoutY(y);
	}
	
	public boolean isSelected() {
		return selected;
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
