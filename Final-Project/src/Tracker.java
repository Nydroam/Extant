import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public class Tracker extends EnemyUnit implements AttackUnit {
	Rotate rotate;
	public Tracker(double r) {
		radius = r;
		color = Settings.trackerColor;
		shape = new Polygon();
		((Polygon)shape).getPoints().addAll(new Double[] {
				0.0,-radius,
				-radius*Math.sqrt(3)/2, radius/2,
				radius*Math.sqrt(3)/2, radius/2
		}
		);
		shape.setFill(color);
		speed = 4;
		maxHP = 250;
		attackRange = new Circle(radius/2);
		attackLine = new Line();
		attackLine.setStrokeWidth(1);
		setup();
	}
	public void setup() {
		super.setup();
		attackRange.setMouseTransparent(true);
		attackRange.setFill(Color.TRANSPARENT);
		rotate = new Rotate();
		
		shape.getTransforms().add(rotate);
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
					
					attackLine.setStroke(Settings.foregroundColor);
					attackLine.setStartX(shape.getLayoutX());
					attackLine.setStartY(shape.getLayoutY());
					attackLine.setEndX(target.getX());
					attackLine.setEndY(target.getY());
					rotate.setAngle(i);
					if(attackRange.contains(target.getX()-xPos,target.getY()-yPos)) {
						shape.setFill(target.getShape().getFill());
						i+=3;
						if(target.isAlive()) {
							target.decHP(5);
						}
						if(!target.isAlive()) {
							unitHandler.removeUnit(UnitHandler.PLAYER, target);
							pane.getChildren().removeAll(target.getShape(),target.getAttackLine(),target.getAttackRange(),((PlayerUnit)target).getHighlight());
							target.stopMoveAnimation();
							if(target instanceof AttackUnit)
								target.stopAttackAnimation();
							attackLine.setStroke(Color.TRANSPARENT);
							
						}
						retarget(unitHandler);
					}else {
						shape.setFill(color);
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
		HashSet<GameUnit> enemies = (HashSet<GameUnit>)unitHandler.getSet(UnitHandler.PLAYER);
		if(!enemies.isEmpty()) {
			int i = 0;
			int rand = (int)(Math.random()*enemies.size());
			
			for(GameUnit u:enemies) {	
				if(i==rand)
					target = u;
				i++;
			}
			attackLine.toBack();
			}else {
				target = null;
			}
		
	}
}
