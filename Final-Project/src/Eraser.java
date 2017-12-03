import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Eraser extends PlayerUnit implements AttackUnit {
	public Eraser(double r) {
		radius = r;
		color = Settings.eraserColor;
		shape = new Circle(radius);
		highlight = new Circle(radius+5);
		attackLine = new Line();
		attackLine.setStrokeWidth(20);
		maxHP = 500;
		speed = 2;
		setup();
	}
	
	public void setAttackAnimation(UnitHandler unitHandler, Pane pane) {
		
		shape.setRotationAxis(new Point3D(1,0,0));
		attackAnim = new AnimationTimer() {
			int i = 0;
			double charging=5000.0;
			double shooting=charging + 500.0;
			public void handle(long now) {
				i++;
				if(i<=charging) {//charge up state
					
					shape.setRotate(i/charging*80-80);
				}
				else if(i<=shooting) {
					
					shape.setRotate(((i-charging)/(shooting-charging))*(-80+360*3));
					if(target!=null) {
					attackLine.setStroke(color);
					attackLine.setStartX(xPos);
					attackLine.setStartY(yPos);
					attackLine.setEndX(target.getX());
					attackLine.setEndY(target.getY());
					if(target.isAlive()) {
						target.decHP(5);
					}
					if(!target.isAlive()) {
						unitHandler.removeUnit(UnitHandler.ENEMY, target);
						pane.getChildren().removeAll(target.getShape(),target.getAttackLine(),target.getAttackRange());
						target.stopAttackAnimation();
						target.stopMoveAnimation();
						attackLine.setStroke(Color.TRANSPARENT);
					}
					retarget(unitHandler);
					}
					else {
						attackLine.setStroke(Color.TRANSPARENT);
						retarget(unitHandler);
					}
				}
				else {
					attackLine.setStroke(Color.TRANSPARENT);
					i = 0;
				}
			}
		};
	}
	
	public void retarget(UnitHandler unitHandler) {
		HashSet<GameUnit> enemies = unitHandler.getSet(UnitHandler.ENEMY);
		if(!enemies.isEmpty()) {
			target = enemies.iterator().next();
			}else {
				target = null;
			}
	}
}
