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
		secColor = Settings.secEraserColor;
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
		
		
		attackAnim = new AnimationTimer() {
			
			int i = 0;
			double charging=5000.0;
			double shooting=charging + 500.0;
			public void handle(long now) {
				shape.toBack();
				
				if(unitHandler.getSet(UnitHandler.PLAYER).isEmpty())
					this.stop();
				
				if(i<=charging) {//charge up state
					i++;
					shape.setFill(secColor);
					shape.setRotationAxis(new Point3D(0,0,1));
					shape.setRotate(i*100/charging*90-90);
					shape.setScaleY(1.0);
					shape.setScaleX(i/charging);
					shape.setStroke(Color.TRANSPARENT);
				}
				else if(!erase) {
					shape.setFill(color);
				}
				else if(i<=shooting&&erase) {
					i++;
					shape.setFill(color);
					shape.setRotationAxis(new Point3D(0,1,0));
					shape.setRotate(((i-charging)/(shooting-charging))*(-90+360*5));
					shape.setScaleX(1-(i-charging)/(shooting-charging));
					shape.setScaleY(1-(i-charging)/(shooting-charging));
					if(target!=null) {
					attackLine.setStroke(color);
					attackLine.setStartX(xPos);
					attackLine.setStartY(yPos);
					attackLine.setEndX(target.getX());
					attackLine.setEndY(target.getY());
					if(target.isAlive()) {
						target.decHP(15);
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
