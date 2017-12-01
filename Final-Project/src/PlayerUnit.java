import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
	
	public void move(double x, double y) {
		super.move(x, y);
		highlight.setLayoutX(x);
		highlight.setLayoutY(y);
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
	
	public void decHP(int h) {
		super.decHP(h);
		double frac = hp/maxHP;
		highlight.setScaleX(frac);
		highlight.setScaleY(frac);
	}
	public void setMoveAnimation(double width, double height) {
		moveAnim = new AnimationTimer() {
			int i = 0;
			public void handle(long now) {
				if(i==1) {
				if(vertical) {
					move(xPos, yPos+magnitude);
				}
				else {
					move(xPos+magnitude, yPos);
				}
				double r = ((Circle)shape).getRadius();
				if(xPos+r>width||xPos-r<0||yPos+r>=height||yPos-r<=0) {
					setDirection(vertical,-1*magnitude);
					if(vertical) {
						move(xPos, yPos+magnitude);
					}
					else {
						move(xPos+magnitude, yPos);
					}
				}
				i = 0;
				}
				i++;
			}
		};
	}
	
}
