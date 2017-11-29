package selection;

import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SelectionBox {

	protected Rectangle selectionBox;
	protected Scene scene;
	protected double boxX;
	protected double boxY;

	public SelectionBox(Scene s) {
		selectionBox = new Rectangle();
		selectionBox.setFill(Color.TRANSPARENT);
		selectionBox.setMouseTransparent(true);
		scene = s;
	}

	public Rectangle getBox() {
		return selectionBox;
	}

	public void setBoxEvents() {
		scene.setOnMousePressed(e -> onMousePressedHandler(e));
		scene.setOnMouseDragged(e -> onMouseDraggedHandler(e));
		scene.setOnMouseReleased(e -> onMouseReleasedHandler(e));
	}

	public void onMousePressedHandler(MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			selectionBox.setStroke(Color.BLACK);
			boxX = e.getX();
			boxY = e.getY();
			selectionBox.setX(boxX);
			selectionBox.setY(boxY);
			selectionBox.setWidth(0);
			selectionBox.setHeight(0);
		}
	}

	public void onMouseDraggedHandler(MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			
			selectionBox.setStroke(Color.BLACK);
			if (e.getX() - boxX < 0) {
				selectionBox.setWidth(boxX - e.getX());
				selectionBox.setX(e.getX());
			} else {
				selectionBox.setWidth(e.getX() - selectionBox.getX());
			}
			if (e.getY() - boxY < 0) {
				selectionBox.setHeight(boxY - e.getY());
				selectionBox.setY(e.getY());
			} else {
				selectionBox.setHeight(e.getY() - selectionBox.getY());
			}
		}
	}

	public void onMouseReleasedHandler(MouseEvent e) {
		if(e.getButton() == MouseButton.PRIMARY)
			selectionBox.setStroke(Color.TRANSPARENT);
	}

}
