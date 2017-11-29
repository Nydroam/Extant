import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MovementHandler {
	UnitHandler unitHandler;
	Scene scene;
	
	public MovementHandler(UnitHandler uh, Scene s) {
		unitHandler = uh;
		scene = s;
	}
	
	public void prepareAnimations() {
		unitHandler.getSet(UnitHandler.PLAYER).stream().forEach(u->{
			new AnimationTimer() {
				public void handle(long now) {
					if(u.getV()) {
						u.move(u.getX(), u.getY()+u.getM());
					}
					else {
						u.move(u.getX()+ u.getM(), u.getY());
					}
					if(u.getLength()+u.getX()>scene.getWidth()||u.getX()-u.getLength()<0||u.getY()+u.getLength()>scene.getHeight()||u.getY()-u.getLength()<0) {
						u.setDirection(u.getV(), -1*u.getM());
						if(u.getV()) {
							u.move(u.getX(), u.getY()+u.getM());
						}
						else {
							u.move(u.getX()+ u.getM(), u.getY());
						}
					}
				}
			}.start();
		});
		scene.setOnKeyPressed( e -> {
			unitHandler.getSet(UnitHandler.SELECTED).stream().forEach(u->keyHandler(e,u));
		});
	}
	
	public void keyHandler(KeyEvent e, GameUnit u) {
		
		if(u.isSelected()) {
			KeyCode key = e.getCode();
			if(key == KeyCode.W)
				u.setDirection(true, -3);
			if(key == KeyCode.A)
				u.setDirection(false, -3);
			if(key == KeyCode.S)
				u.setDirection(true, 3);
			if(key == KeyCode.D)
				u.setDirection(false, 3);
			if(key == KeyCode.SPACE)
				u.setDirection(false, 0);
		}
	}
}
