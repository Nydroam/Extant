import java.util.HashSet;
import java.util.stream.Collectors;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class Tracker extends EnemyUnit implements AttackUnit {
	public Tracker(double r) {
		radius = r;
		color = Settings.trackerColor;
		shape = new Polygon();
		((Polygon)shape).getPoints().addAll(new Double[] {
				0.0,-radius,
				-radius, radius,
				radius, radius
		}
		);
		shape.setFill(color);
		speed = 3;
		maxHP = 200;
		attackRange = new Circle(radius/2);
		attackLine = new Line();
		attackLine.setStrokeWidth(1);
		setup();
	}
	public void setup() {
		super.setup();
		attackRange.setMouseTransparent(true);
		attackRange.setFill(Color.TRANSPARENT);
		
	}
	public void move(double x, double y) {
		super.move(x, y);
		attackRange.setLayoutX(xPos);
		attackRange.setLayoutY(yPos);
	}
	public void setAttackAnimation(UnitHandler unitHandler, Pane pane) {
		attackAnim = new AnimationTimer() {
			int i = 0;
			public void handle(long now) {
				i++;
				if(target!=null&&target.isAlive()) {
					attackLine.toFront();
					attackLine.setStroke(Color.BLACK);
					attackLine.setStartX(xPos);
					attackLine.setStartY(yPos);
					attackLine.setEndX(target.getX());
					attackLine.setEndY(target.getY());
					if(attackRange.contains(target.getX()-xPos,target.getY()-yPos)) {
				
						shape.setRotate(i*3);
						if(target.isAlive()) {
							target.decHP(5);
						if(!target.isAlive()) {
							unitHandler.removeUnit(UnitHandler.PLAYER, target);
							pane.getChildren().removeAll(target.getShape(),target.getAttackLine(),target.getAttackRange(),((PlayerUnit)target).getHighlight());
							target.stopMoveAnimation();
							if(target instanceof AttackUnit)
								target.stopAttackAnimation();
							attackLine.setStroke(Color.TRANSPARENT);
							retarget(unitHandler);
						}}
					}
				}
				else {
					attackLine.setStroke(Color.TRANSPARENT);
					retarget(unitHandler);
				}
			}
		};
	}
	
	public void retarget(UnitHandler unitHandler) {
		HashSet<GameUnit> enemies = unitHandler.getSet(UnitHandler.PLAYER);
		if(!enemies.isEmpty()) {
			target = enemies.iterator().next();
			}else {
				target = null;
			}
	}
}
