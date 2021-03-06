import javafx.animation.AnimationTimer;

public class EnemyUnit extends GameUnit{
	public void setStronger() {
		maxHP*=1.25;
		hp = maxHP;
		speed += 1;
		
		color = Settings.eraserColor;
		secColor = Settings.secEraserColor;
		shape.setFill(color);
		if(attackLine!=null)
			attackLine.setStroke(color);
	}
	public void setMoveAnimation(double width, double height) {
		
		moveAnim = new AnimationTimer() {
			public void handle(long now) {
				
				if(target!=null) {
					
					double xDist = target.getX()-xPos;
					
					double yDist = target.getY()-yPos;
					double dist = Math.sqrt(Math.pow(xDist,2)+Math.pow(yDist, 2));
					xDist = speed*xDist/dist;
					yDist = speed*yDist/dist;
					//System.out.println("BEFORE:" + xPos);
					//System.out.println("DIFF:" + xDist);
					if(dist>getAttackRange().getRadius()/3*2)
						move(shape.getLayoutX()+xDist,shape.getLayoutY()+yDist);
					//System.out.println("AFTER:" + xPos);
				}
				
				
				
			}
		};
	}
}
