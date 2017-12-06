import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Sticker extends Enemy {
	public Sticker(double r) {
		super(r);
	}
	public void retarget(UnitHandler unitHandler) {
		HashSet<GameUnit> enemies = (HashSet<GameUnit>) unitHandler.getSet(UnitHandler.PLAYER);
		if (!enemies.isEmpty()) {
			int i = 0;
			int rand = (int) (Math.random() * enemies.size());

			for (GameUnit u : enemies) {
				if (i == rand)
					target = u;
				i++;
			}
			shape.setFill(target.getShape().getFill());
		} else {
			target = null;
		}

	}public void setAttackAnimation(UnitHandler unitHandler, Pane pane) {
		attackAnim = new AnimationTimer() {
			int i = 0;
			public void handle(long now) {
				
				if(target!=null&&target.isAlive()) {
					
					
					if(attackRange.contains(target.getX()-xPos,target.getY()-yPos)) {
						i++;
						attackLine.toBack();
						attackLine.setStroke(color);
						attackLine.setStartX(xPos);
						attackLine.setStartY(yPos);
						attackLine.setEndX(target.getX());
						attackLine.setEndY(target.getY());
						shape.setRotate(i*5);
					
						if(target.isAlive()) {
							target.decHP(2);
						}
						if(!target.isAlive()) {
							unitHandler.removeUnit(UnitHandler.PLAYER, target);
							pane.getChildren().removeAll(target.getShape(),target.getAttackLine(),target.getAttackRange(),((PlayerUnit)target).getHighlight());
							target.stopMoveAnimation();
							if(target instanceof AttackUnit)
								target.stopAttackAnimation();
							attackLine.setStroke(Color.TRANSPARENT);
							retarget(unitHandler);
						}
						
					}else {
						attackLine.setStroke(Color.TRANSPARENT);
					}
				}
				else {
					attackLine.setStroke(Color.TRANSPARENT);
					retarget(unitHandler);
				}
			}
		};
	}
}
