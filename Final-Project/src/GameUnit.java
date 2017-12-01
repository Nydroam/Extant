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
	
	// Attack
	protected GameUnit target;
	protected Line attackLine;
	protected Circle attackRange;

	// setup
	public void setup() {
		shape.setFill(color);
		canAttack = false;
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

	// Mutators
	public void setTarget(GameUnit u) {
		target = u;
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
