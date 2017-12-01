import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import screen.Screen;

public class PlayerAnimationHandler {
	UnitHandler unitHandler;
	Scene scene;
	Pane pane;

	public PlayerAnimationHandler(UnitHandler uh, Screen s) {
		unitHandler = uh;
		scene = s.getScene();
		pane = s.getPane();
	}

	public void prepareAnimations() {
		unitHandler.getSet(UnitHandler.PLAYER).stream().forEach(un -> {
			PlayerUnit u = (PlayerUnit) un;
			u.setMoveAnimation(scene.getWidth(), scene.getHeight());
			u.startMoveAnimation();
			if (u instanceof AttackUnit) {
				AttackUnit atk = (AttackUnit) u;
				atk.setAttackAnimation(unitHandler, pane);
				u.startAttackAnimation();
			}
		});
		scene.setOnKeyPressed(e -> {
			unitHandler.getSet(UnitHandler.SELECTED).stream().forEach(u -> keyHandler(e, (PlayerUnit) u));
		});
	}

	public void keyHandler(KeyEvent e, PlayerUnit u) {

		if (u.isSelected()) {
			KeyCode key = e.getCode();
			if (key == KeyCode.W)
				u.setDirection(true, -1 * u.getS());
			if (key == KeyCode.A)
				u.setDirection(false, -1 * u.getS());
			if (key == KeyCode.S)
				u.setDirection(true,u.getS());
			if (key == KeyCode.D)
				u.setDirection(false, u.getS());
			if (key == KeyCode.SPACE)
				u.setDirection(false, 0);
		}
	}
}
