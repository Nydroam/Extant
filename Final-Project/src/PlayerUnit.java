import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class PlayerUnit extends GameUnit {
	public static final int CHASER = 0;
	public static final int SHOOTER = 1;

	protected Shape highlight;
	
	// Player Movement Controls
	protected boolean selected;
	protected boolean vertical;
	protected int magnitude;
	
	public void setup() {
		super.setup();
		highlight.setFill(Color.TRANSPARENT);
		highlight.setMouseTransparent(true);
		selected = true;
		toggleSelect();
	}
	
	//accessors
	public boolean isSelected() {
		return selected;
	}
	
	public boolean getV() {
		return vertical;
	}

	public int getM() {
		return magnitude;
	}
	public Shape getHighlight() {
		return highlight;
	}
	public void toggleSelect() {
		if (selected) {
			selected = false;
			highlight.setStroke(Color.TRANSPARENT);
		} else {
			selected = true;
			highlight.setStroke(color);
		}
	}
	
	public void setDirection(boolean v, int m) {
		vertical = v;
		magnitude = m;
	}
}
