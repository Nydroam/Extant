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
		unitHandler.getSet(UnitHandler.PLAYER).stream().forEach(un->{
			PlayerUnit u = (PlayerUnit)un;
			u.setMoveAnimation(
			new AnimationTimer() {
				int i = 0;
				public void handle(long now) {
					if(i==1) {
					if(u.getV()) {
						u.move(u.getX(), u.getY()+u.getM());
					}
					else {
						u.move(u.getX()+ u.getM(), u.getY());
					}
					if(u.getRadius()+u.getX()>scene.getWidth()||u.getX()-u.getRadius()<0||u.getY()+u.getRadius()>scene.getHeight()||u.getY()-u.getRadius()<0) {
						u.setDirection(u.getV(), -1*u.getM());
						if(u.getV()) {
							u.move(u.getX(), u.getY()+u.getM());
						}
						else {
							u.move(u.getX()+ u.getM(), u.getY());
						}
					}
					i = 0;
					}
					i++;
				}
			});
			u.startMoveAnimation();
		});
		scene.setOnKeyPressed( e -> {
			unitHandler.getSet(UnitHandler.SELECTED).stream().forEach(u->keyHandler(e,(PlayerUnit)u));
		});
	}
	
	public void keyHandler(KeyEvent e, PlayerUnit u) {
		
		if(u.isSelected()) {
			KeyCode key = e.getCode();
			if(key == KeyCode.W)
				u.setDirection(true, -1*Math.abs(u.getS()));
			if(key == KeyCode.A)
				u.setDirection(false, -1*Math.abs(u.getS()));
			if(key == KeyCode.S)
				u.setDirection(true, Math.abs(u.getS()));
			if(key == KeyCode.D)
				u.setDirection(false, Math.abs(u.getS()));
			if(key == KeyCode.SPACE)
				u.setDirection(false, 0);
		}
	}
}
