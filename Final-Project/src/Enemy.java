import java.util.Comparator;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Enemy extends EnemyUnit implements AttackUnit{
	
	public Enemy(double r) {
		radius = r;
		color = Settings.enemyColor;
		shape = new Rectangle(r*2,r*2);
		speed = 4;
		shape.setFill(color);
		attackRange = new Circle(radius*6);
		attackLine = new Line();
		attackLine.setStrokeWidth(10);
		maxHP = 50;
		setup();
	}
	public void setup() {
		super.setup();
		attackRange.setMouseTransparent(true);
		attackRange.setFill(Color.TRANSPARENT);
		///attackRange.setStroke(Color.BLACK);
	}
	public void decHP(int h) {
		super.decHP(h);
	}
	public void move(double x, double y) {
		xPos = x + ((Rectangle)shape).getWidth()/2.0;
		yPos = y + ((Rectangle)shape).getHeight()/2.0;
		shape.setLayoutX(x);
		shape.setLayoutY(y);
		attackRange.setLayoutX(xPos);
		attackRange.setLayoutY(yPos);
	}
	
	public void setAttackAnimation(UnitHandler unitHandler, Pane pane) {
		attackAnim = new AnimationTimer() {
			int i = 0;
			public void handle(long now) {
				i++;
				if(target!=null&&attackRange.contains(target.getX()-xPos,target.getY()-yPos)) {
					shape.setRotate(i*3);
					attackLine.toBack();
					attackLine.setStroke(color);
					attackLine.setStartX(xPos);
					attackLine.setStartY(yPos);
					attackLine.setEndX(target.getX());
					attackLine.setEndY(target.getY());
					if(target.isAlive()) {
						target.decHP(2);
					if(!target.isAlive()) {
						unitHandler.removeUnit(UnitHandler.PLAYER, target);
						pane.getChildren().removeAll(target.getShape(),target.getAttackLine(),target.getAttackRange(),((PlayerUnit)target).getHighlight());
						target.stopMoveAnimation();
						if(target instanceof AttackUnit)
							target.stopAttackAnimation();
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
		if(!unitHandler.getSet(UnitHandler.PLAYER).isEmpty()) {
		target = unitHandler.getSet(UnitHandler.PLAYER).stream().
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
			this.stopMoveAnimation();
			this.stopAttackAnimation();
			
		}
		
	}
	

}
