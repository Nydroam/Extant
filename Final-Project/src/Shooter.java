import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Shooter extends PlayerUnit implements AttackUnit{
	public Shooter(double r) {
		radius = r;
		color = Color.RED;
		shape = new Circle(radius);
		highlight = new Circle(radius+5);
		attackRange = new Circle(radius*6);
		attackLine = new Line();
		
		
		speed = 3;
		setup();
	}
	public void setup() {
		super.setup();
		canAttack = true;
		attackRange.setMouseTransparent(true);
		attackRange.setFill(Color.TRANSPARENT);
		attackRange.setStroke(Color.BLACK);
	}
	
	public void setAttackAnimation() {
		attackAnim = new AnimationTimer() {
			public void handle(long now) {
				if(target!=null&&attackRange.contains(target.getX()-xPos,target.getY()-yPos)) {
					attackLine.toFront();
					attackLine.setStroke(Color.BLACK);
					attackLine.setStartX(xPos);
					attackLine.setStartY(yPos);
					attackLine.setEndX(target.getX());
					attackLine.setEndY(target.getY());
				}
				else {
					attackLine.setStroke(Color.TRANSPARENT);
				}
			}
		};
	}
}
