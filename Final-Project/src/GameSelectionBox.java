
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import selection.SelectionBox;

public class GameSelectionBox extends SelectionBox{

	UnitHandler unitHandler;
	public GameSelectionBox(Scene s, UnitHandler u) {
		super(s);
		unitHandler = u;
		selectionBox.setStroke(Settings.foregroundColor);
	}
	
	public void onMousePressedHandler(MouseEvent e) {
		super.onMousePressedHandler(e);
		if (e.getButton() == MouseButton.PRIMARY) {
			unitHandler.clearUnits(UnitHandler.SELECTED);
		}
	}
	
	public void onMouseDraggedHandler(MouseEvent e) {
		super.onMouseDraggedHandler(e);
		if (e.getButton() == MouseButton.PRIMARY) {
			selectionBox.setStroke(Settings.foregroundColor);
			unitHandler.getSet(UnitHandler.PLAYER).stream()
			.forEach( u-> {
				if(selectionBox.contains(new Point2D(u.getX(),u.getY()))) {
					if(!((PlayerUnit)u).isSelected()) {
						unitHandler.addUnit(UnitHandler.SELECTED, u);
					}
				} else {
					if(((PlayerUnit)u).isSelected()) {
						unitHandler.removeUnit(UnitHandler.SELECTED, u);
					}
				}
			});
		}
		
	}
	
}
