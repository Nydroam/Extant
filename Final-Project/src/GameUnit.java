import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import unit.Unit;

public class GameUnit extends Unit {

	// Visuals of Unit
	
	protected Color color;
	protected double radius;

	protected int speed;
	protected AnimationTimer moveAnim;
	protected AnimationTimer attackAnim;
	protected boolean isAlive;
	// Attack
	protected GameUnit target;
	protected Line attackLine;
	protected Circle attackRange;
	protected double maxHP;

	// setup
	public void setup() {
		shape.setFill(color);
		shape.setStroke(Color.BLACK);

		isAlive = true;
		hp = (int)maxHP;
		if(this instanceof AttackUnit)
			attackLine.setStroke(Color.TRANSPARENT);
	}

	// Accessors
	
	public Circle getAttackRange() {
		return attackRange;
	}
	public int getS() {
		return speed;
	}
	public Line getAttackLine() {
		return attackLine;
	}
	public double getRadius() {
		return radius;
	}
	public GameUnit getTarget() {
		return target;
	}
	public boolean isAlive() {
		return isAlive;
	}
	// Mutators
	public void setTarget(GameUnit u) {
		target = u;
	}
	
	public void decHP(double h) {
		hp -= h;
		if(hp<=0)
			isAlive = false;
		rescale();
		
	}
	
	public void rescale() {
		double frac = hp/maxHP/5*4+0.2;
		shape.setScaleX(frac);
		shape.setScaleY(frac);
	}
	
	public void incHP(double h) {
		if(isAlive) {
			if(hp+h>=maxHP)
				hp = (int)maxHP;
			else
				hp+=h;
			rescale();
		}
	}

	// Movement
	
	
	//Animation

	public void startMoveAnimation() {
		moveAnim.start();
	}
	public void stopMoveAnimation() {
		moveAnim.stop();
	}
	
	public void startAttackAnimation() {
		attackAnim.start();
	}
	public void stopAttackAnimation() {
		attackAnim.stop();
	}

}
