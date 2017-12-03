import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Eraser extends GameUnit implements AttackUnit {
	private boolean erase;
	public Eraser(double r) {
		radius = r;
		color = Settings.eraserColor;
		shape = new Circle(radius);
		attackLine = new Line();
		attackLine.setStrokeWidth(10);
		setup();
	}
	
	public void erase() {
		erase = true;
	}
	
	public boolean getErase() {
		return erase;
	}
	
	public void setAttackAnimation(UnitHandler unitHandler, Pane pane) {
		
		shape.setRotationAxis(new Point3D(0,1,0));
		attackAnim = new AnimationTimer() {
			
			int i = 0;
			double charging=100.0;
			double shooting=charging + 500.0;
			public void handle(long now) {
				shape.toBack();
				if(unitHandler.getSet(UnitHandler.PLAYER).isEmpty())
					this.stop();
				
				if(i<=charging) {//charge up state
					i++;
					shape.setFill(Settings.secEraserColor);
					shape.setRotate(i/charging*90-90);
					shape.setStroke(Color.TRANSPARENT);
				}
				else if(!erase) {
					shape.setFill(Settings.eraserColor);
				}
				else if(i<=shooting&&erase) {
					i++;
					shape.setFill(Settings.eraserColor);
					shape.setRotate(((i-charging)/(shooting-charging))*(-90+360*5));
					if(target!=null) {
					attackLine.setStroke(color);
					attackLine.setStartX(xPos);
					attackLine.setStartY(yPos);
					attackLine.setEndX(target.getX());
					attackLine.setEndY(target.getY());
					if(target.isAlive()) {
						target.decHP(50);
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
				else if(erase){
					erase = false;
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
