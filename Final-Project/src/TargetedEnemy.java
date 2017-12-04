import java.util.Comparator;

import javafx.scene.paint.Color;

public class TargetedEnemy extends Enemy{
	public TargetedEnemy(double r) {
		super(r);
		color = Settings.tankerColor;
		shape.setFill(color);
		attackRange.setRadius(radius*6);
		
	}
	public void retarget(UnitHandler unitHandler) {
		if(!unitHandler.getSet(UnitHandler.PLAYER).isEmpty()) {
		target = unitHandler.getSet(UnitHandler.PLAYER).stream().filter(u->u instanceof Tanker).
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
