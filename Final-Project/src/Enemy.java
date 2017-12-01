import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemy extends EnemyUnit{
	
	public Enemy(double r) {
		radius = r;
		color = Color.GRAY;
		shape = new Rectangle(r*2,r*2);
		speed = 4;
		shape.setFill(color);
	}
	
	public void move(double x, double y) {
		xPos = x + radius;
		yPos = y + radius;
		shape.setLayoutX(x);
		shape.setLayoutY(y);
		//attackRange.setLayoutX(x);
		//attackRange.setLayoutY(y);
	}
	
	
	

}
