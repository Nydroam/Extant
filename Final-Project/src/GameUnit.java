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
	protected boolean canAttack;
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
		canAttack = false;
		isAlive = true;
		hp = (int)maxHP;
	}

	// Accessors
	public boolean canAttack() {
		return canAttack;
	}
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
	
	public void decHP(int h) {
		hp -= h;
		if(hp<=0)
			isAlive = false;
		double frac = hp/maxHP;
		shape.setScaleX(frac);
		shape.setScaleY(frac);
		
	}
	
	public void incHP(int h) {
		if(isAlive) {
			if(hp+h>=maxHP)
				hp = (int)maxHP;
			else
				hp+=h;
			double frac = hp/maxHP;
			shape.setScaleX(frac);
			shape.setScaleY(frac);
		}
	}

	// Movement
	public void move(double x, double y) {
		super.move(x, y);
		if(canAttack) {
			attackRange.setLayoutX(x);
			attackRange.setLayoutY(y);
		}
	}
	
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
