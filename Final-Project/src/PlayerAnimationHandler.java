import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PlayerAnimationHandler {
	UnitHandler unitHandler;
	Scene scene;

	public PlayerAnimationHandler(UnitHandler uh, Scene s) {
		unitHandler = uh;
		scene = s;
	}

	public void prepareAnimations() {
		unitHandler.getSet(UnitHandler.PLAYER).stream().forEach(un -> {
			PlayerUnit u = (PlayerUnit) un;
			u.setMoveAnimation(scene.getWidth(), scene.getHeight());
			u.startMoveAnimation();
			if (u instanceof AttackUnit) {
				AttackUnit atk = (AttackUnit) u;

				unitHandler.getSet(UnitHandler.ENEMY).stream().forEach(t -> u.setTarget(t));
				atk.setAttackAnimation();
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
				u.setDirection(true, -1 * Math.abs(u.getS()));
			if (key == KeyCode.A)
				u.setDirection(false, -1 * Math.abs(u.getS()));
			if (key == KeyCode.S)
				u.setDirection(true, Math.abs(u.getS()));
			if (key == KeyCode.D)
				u.setDirection(false, Math.abs(u.getS()));
			if (key == KeyCode.SPACE)
				u.setDirection(false, 0);
		}
	}
}
