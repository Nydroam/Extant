import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import screen.Screen;

public class PlayHandler {

	private Screen screen;
	private int numChasers;
	private int numShooters;
	private UnitHandler unitHandler;
	public PlayHandler(Screen s, int nc, int ns) {
		screen = s;
		numChasers = nc;
		numShooters = ns;
		unitHandler = new UnitHandler();
		
	}
	
	public void prepare() {
		screen.getScene().setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.SPACE)
				play();
		});
		screen.getScene().setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.SECONDARY)
				unitHandler.getSet(UnitHandler.SELECTED).stream().forEach(u->u.move(e.getX(),e.getY()));
		});
		
		//add chasers
		for(int i = 0; i < numChasers; i++) {
			GameUnit u = new Chaser(screen.getHeight()/25);
			addUnit(u,i,1);
		}
		//add shooters
		for(int i = 0; i < numShooters; i++) {
			GameUnit u = new Shooter(screen.getHeight()/25);
			addUnit(u,i,2);
		}
	}
	
	public void addUnit(GameUnit u, int i, int j) {
		u.move((i+1)*screen.getWidth()/(numChasers+1),screen.getHeight()/3*j);
		unitHandler.addUnit(UnitHandler.PLAYER,u);
		
		u.getShape().setOnMouseClicked( e -> unitClickHandler(e,u));
		
		screen.addNode(u.getShape());
		screen.addNode(u.getHighlight());
	}
	public void unitClickHandler(MouseEvent e, GameUnit u) {
		
		if( e.getButton() == MouseButton.PRIMARY) {
			unitHandler.clearUnits(UnitHandler.SELECTED);
			unitHandler.addUnit(UnitHandler.SELECTED, u);
			
		}
	}
	
	public void play() {
		//Removing Screen Instruction Text
		screen.getPane().getChildren().remove(0);
		
		//Setting up selection box
		GameSelectionBox selectionBox = new GameSelectionBox(screen.getScene(), unitHandler);
		screen.getPane().getChildren().add(selectionBox.getBox());
		selectionBox.setBoxEvents();
		
		//removing preparation mechanic
		screen.getScene().setOnMouseClicked(null);
		
		MovementHandler m= new MovementHandler(unitHandler, screen.getScene());
		m.prepareAnimations();
	}
}
