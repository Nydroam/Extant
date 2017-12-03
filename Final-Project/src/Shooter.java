import java.util.Comparator;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Shooter extends PlayerUnit implements AttackUnit{
	public Shooter(double r) {
		radius = r;
		color = Settings.shooterColor;
		shape = new Circle(radius);
		highlight = new Circle(radius+5);
		attackRange = new Circle(radius*6);
		attackLine = new Line();
		attackLine.setStrokeWidth(5);
		maxHP = 500;
		speed = 3;
		setup();
	}
	public void move(double x, double y) {
		super.move(x, y);
		attackRange.setLayoutX(x);
		attackRange.setLayoutY(y);
	}
	public void setup() {
		super.setup();
		attackRange.setMouseTransparent(true);
		attackRange.setFill(Color.TRANSPARENT);
		//attackRange.setStroke(Color.BLACK);
	}
	
	public void setAttackAnimation(UnitHandler unitHandler, Pane pane) {
		attackAnim = new AnimationTimer() {
			
			public void handle(long now) {
		
				if(target!=null&&attackRange.contains(target.getX()-xPos,target.getY()-yPos)) {
					attackLine.toBack();
					attackLine.setStroke(color);
					attackLine.setStartX(xPos);
					attackLine.setStartY(yPos);
					attackLine.setEndX(target.getX());
					attackLine.setEndY(target.getY());
					if(target.isAlive()) {
						target.decHP(1);
					if(!target.isAlive()) {
						unitHandler.removeUnit(UnitHandler.ENEMY, target);
						pane.getChildren().removeAll(target.getShape(),target.getAttackLine(),target.getAttackRange());
						target.stopAttackAnimation();
						target.stopMoveAnimation();
						attackLine.setStroke(Color.TRANSPARENT);
					}}
						retarget(unitHandler);
				}
				else {
					attackLine.setStroke(Color.TRANSPARENT);
					
						retarget(unitHandler);
					
				}
			}
		};
	}
	
	public void retarget(UnitHandler unitHandler) {
		if(!unitHandler.getSet(UnitHandler.ENEMY).isEmpty()) {
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
		}else {
			target = null;
		}
		
	}
}
