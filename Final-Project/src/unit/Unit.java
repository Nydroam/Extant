package unit;

import javafx.scene.shape.Shape;

public class Unit {
	
	protected Shape shape;
	protected int hp;
	protected double xPos;
	protected double yPos;
	
	public Unit() {
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public int getHP() {
		return hp;
	}
	
	public void decHP() {
		hp--;
	}
	public void incHP() {
		hp++;
	}
	
	public void move(double x, double y) {
		shape.setLayoutX(x);
		shape.setLayoutY(y);
	}
	
	public void attack(Unit other) {
		other.decHP();
	}
}
