import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class PlayerUnit extends GameUnit {
	public static final int TANKER = 0;
	public static final int SHOOTER = 1;
	public static final int ERASER = 2;

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
			if(attackRange!=null)
				attackRange.setStroke(Color.TRANSPARENT);
				
		} else {
			selected = true;
			highlight.setStroke(color);
			if(attackRange!=null)
				attackRange.setStroke(Color.BLACK);
		}
	}
	
	public void setDirection(boolean v, int m) {
		vertical = v;
		magnitude = m;
	}
	
	public void rescale() {
		super.rescale();
		((Circle)highlight).setRadius(radius*(hp/maxHP/10*9+0.1)+5);
	}
	public void setMoveAnimation(double width, double height) {
		PlayerUnit t = this;
		moveAnim = new AnimationTimer() {
			int i = 0;
			public void handle(long now) {
				i++;
				//healing
				if(t instanceof Tanker)
					t.incHP(1);
				
				
				if(vertical) {
					move(xPos, yPos+magnitude);
				}
				else {
					move(xPos+magnitude, yPos);
				}
				double frac = hp/maxHP;
				double r = radius*frac;
				if(xPos+r>width||xPos-r<0||yPos+r>height||yPos-r<0) {
					setDirection(vertical,-1*magnitude);
					if(vertical) {
						move(xPos, yPos+magnitude);
					}
					else {
						move(xPos+magnitude, yPos);
					}
				}
				
				
			}
		};
	}
	
}
