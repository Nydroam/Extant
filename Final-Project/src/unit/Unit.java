package unit;

import javafx.scene.shape.Shape;

public class Unit {
	
	protected Shape shape;
	protected int hp;
	protected double xPos;
	protected double yPos;
	
	
	public Unit() {
	}
	
	public double getX() {
		return xPos;
	}
	public double getY() {
		return yPos;
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public int getHP() {
		return hp;
	}
	
	public void move(double x, double y) {
		xPos = x;
		yPos = y;
		shape.setLayoutX(x);
		shape.setLayoutY(y);
	}

}
