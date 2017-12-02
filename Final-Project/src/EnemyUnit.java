import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;

public class EnemyUnit extends GameUnit{
	public void setMoveAnimation(double width, double height) {
		moveAnim = new AnimationTimer() {
			int i = 0;
			public void handle(long now) {
				i++;
				shape.setRotationAxis(new Point3D(0,0,1));
				shape.setRotate(i*3);
				if(target!=null) {
					
					double xDist = target.getX()-xPos;
					
					double yDist = target.getY()-yPos;
					double dist = Math.sqrt(Math.pow(xDist,2)+Math.pow(yDist, 2));
					xDist = speed*xDist/dist;
					yDist = speed*yDist/dist;
					//System.out.println("BEFORE:" + xPos);
					//System.out.println("DIFF:" + xDist);
					if(dist>getAttackRange().getRadius())
						move(shape.getLayoutX()+xDist,shape.getLayoutY()+yDist);
					//System.out.println("AFTER:" + xPos);
				}
				
				
				
			}
		};
	}
}
