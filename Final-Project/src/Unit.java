import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Unit {

	private Circle c;
	private Circle h;
	private double x;
	private double y;
	private int radius;
	private boolean selected;
	private Timeline moveAnim;
	private int hp;
	
	public Unit(double x, double y, int radius) {
		this.radius = radius;
		this.x = x;
		this.y = y;
		selected = false;
		c = new Circle();
		c.setStroke(Color.BLACK);
		c.setFill(Color.ALICEBLUE);
		c.setCenterX(x);
		c.setCenterY(y);
		c.setRadius(radius);
		h = new Circle();
		h.setMouseTransparent(true);
	}
	
	public Shape shape() {
		return c;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	public void move(double x, double y) {
		this.x = x;
		this.y = y;
		c.setCenterX(x);
		c.setCenterY(y);
		h.setCenterX(x);
		h.setCenterY(y);
	}
	
	public void setAnim(double x2, double y2) {
		if(moveAnim!=null)
			moveAnim.stop();
		double d = Driver.dist(x,y,x2,y2);
		double diffX = (x2-x)/d;
		double diffY = (y2-y)/d;
		moveAnim = new Timeline(new KeyFrame(Duration.millis(5), e->{
			move(x+diffX,y+diffY);
		}));
		moveAnim.setCycleCount((int)d);
		moveAnim.play();
	}
	
	public Shape select() {
		selected = true;
		h.setStroke(Color.GREEN);
		h.setFill(Color.TRANSPARENT);
		h.setCenterX(x);
		h.setCenterY(y);
		h.setRadius(radius+3);
		
		return h;
	}
	
	public Shape deselect() {
		selected = false;
		h.setStroke(Color.TRANSPARENT);
		return h;
	}
	
	public boolean selected() {
		return selected;
	}
}
