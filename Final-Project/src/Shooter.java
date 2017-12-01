import java.util.Comparator;

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
		attackLine.setStrokeWidth(5);
		
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
	
	public void setAttackAnimation(UnitHandler unitHandler) {
		attackAnim = new AnimationTimer() {
			public void handle(long now) {
				if(target!=null&&attackRange.contains(target.getX()-xPos,target.getY()-yPos)) {
					attackLine.toFront();
					attackLine.setStroke(Color.MEDIUMVIOLETRED);
					attackLine.setStartX(xPos);
					attackLine.setStartY(yPos);
					attackLine.setEndX(target.getX());
					attackLine.setEndY(target.getY());
					retarget(unitHandler);
				}
				else {
					retarget(unitHandler);
					attackLine.setStroke(Color.TRANSPARENT);
				}
			}
		};
	}
	
	public void retarget(UnitHandler unitHandler) {
		target = unitHandler.getSet(UnitHandler.ENEMY).stream().
				min( new Comparator<GameUnit>() {
			public int compare(GameUnit a, GameUnit b) {
				double dist1 = Math.sqrt(Math.pow(a.getX()-xPos,2)+Math.pow(a.getY()-yPos, 2));
				double dist2 = Math.sqrt(Math.pow(b.getX()-xPos,2)+Math.pow(b.getY()-yPos, 2));
				if(dist1>dist2)
					return 1;
				else
					return -1;
			}
		}).get();
	}
}
