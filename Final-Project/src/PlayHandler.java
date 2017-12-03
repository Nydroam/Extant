import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import screen.Screen;

public class PlayHandler {

	private Screen screen;
	private UnitHandler unitHandler;
	private GameScreenManager manager;
	private SpawnHandler spawn;
	public PlayHandler(Screen s, GameScreenManager m) {
		screen = s;
		manager = m;
		unitHandler = new UnitHandler(manager);
	}

	public void prepare() {
		
		/*screen.getScene().setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.SECONDARY)
				unitHandler.getSet(UnitHandler.SELECTED).stream().forEach(u -> u.move(e.getX(), e.getY()));
		});*/

		addUnits(PlayerUnit.TANKER);
		addUnits(PlayerUnit.SHOOTER);
		Eraser er = new Eraser(screen.getHeight()/20);
		screen.getScene().setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.E) {
				er.erase();
			}
		});
		addEraser(er);

		// setting player unit attacking and moving
		PlayerAnimationHandler h = new PlayerAnimationHandler(unitHandler, screen);
		h.prepareAnimations();
		// Setting up selection box
		GameSelectionBox selectionBox = new GameSelectionBox(screen.getScene(), unitHandler);
		screen.getPane().getChildren().add(selectionBox.getBox());
		selectionBox.setBoxEvents();
		play();
	}

	public void addUnits(int type) {
		int numUnits = 0;
		PlayerUnit u = null;
		switch (type) {
		case PlayerUnit.TANKER:
			numUnits = Settings.numTankers;
			for (int i = 0; i < numUnits; i++) {
				u = new Tanker(screen.getHeight() / 25);
				addUnit(u, i, numUnits, type);
			}
			break;
		case PlayerUnit.SHOOTER:
			numUnits = Settings.numShooters;
			for (int i = 0; i < numUnits; i++) {
				u = new Shooter(screen.getHeight() / 25);
				addUnit(u, i, numUnits, type);
			}
			break;
		}

	}
	public void addEraser(Eraser u) {
		u.move(screen.getWidth()/2, screen.getHeight()/2);
		screen.addNode(u.getShape());
		screen.addNode(u.getAttackLine());
		u.setAttackAnimation(unitHandler, screen.getPane());
		u.startAttackAnimation();
	}
	public void addUnit(PlayerUnit u, int i, int numUnits, int type) {

		u.move((i + 1) * screen.getWidth() / (numUnits + 1), screen.getHeight() / 3 * (type + 1));
		unitHandler.addUnit(UnitHandler.PLAYER, u);

		u.getShape().setOnMouseClicked(e -> unitClickHandler(e, u));

		screen.addNode(u.getShape());
		screen.addNode(u.getHighlight());
		if (u instanceof AttackUnit) {
			screen.addNode(u.getAttackRange());
			screen.addNode(u.getAttackLine());
		}

	}

	public void unitClickHandler(MouseEvent e, PlayerUnit u) {

		if (e.getButton() == MouseButton.PRIMARY) {
			unitHandler.clearUnits(UnitHandler.SELECTED);
			unitHandler.addUnit(UnitHandler.SELECTED, u);

		}
		
		
	}

	public void play() {
		// Removing Screen Instruction Text
		//screen.getPane().getChildren().remove(0);

		// removing preparation mechanic

		//score
		
		
		// starting spawn
		spawn = new SpawnHandler(unitHandler,screen);
		unitHandler.setSpawnHandler(spawn);
		spawn.setupTimer();
		spawn.startTimer();

	}
}
