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
		
		addUnits(PlayerUnit.CHASER);
		addUnits(PlayerUnit.SHOOTER);
	}
	
	public void addUnits(int type) {
		int numUnits = 0;
		PlayerUnit u = null;
		switch(type){
			case PlayerUnit.CHASER:
				numUnits = numChasers;
				for(int i = 0; i < numUnits; i++) {
					u = new Chaser(screen.getHeight()/25);
					addUnit(u,i,numUnits,type);
				}
				break;
			case PlayerUnit.SHOOTER:
				numUnits = numShooters;
				for(int i = 0; i < numUnits; i++) {
				u = new Shooter(screen.getHeight()/25);
				addUnit(u,i,numUnits,type);
				}
				break;
		}
		
	}
	
	public void addUnit(PlayerUnit u,int i, int numUnits, int type) {
		
			u.move((i+1)*screen.getWidth()/(numUnits+1),screen.getHeight()/3*(type+1));
			unitHandler.addUnit(UnitHandler.PLAYER,u);
			
			u.getShape().setOnMouseClicked( e -> unitClickHandler(e,u));
			
			screen.addNode(u.getShape());
			screen.addNode(u.getHighlight());
			if(u.canAttack()) {
				screen.addNode(u.getAttackRange());
				screen.addNode(u.getAttackLine());
			}
		
	}
	public void unitClickHandler(MouseEvent e, PlayerUnit u) {
		
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
		
		//starting spawn
		for(int i = 0; i < 50;i++) {
		Enemy e = new Enemy(screen.getHeight()/50);
		e.move(Math.random()*screen.getWidth()*3/4,Math.random()*screen.getHeight()*3/4);
		unitHandler.addUnit(UnitHandler.ENEMY, e);
		screen.addNode(e.getShape());
		}
		//setting player unit attacking and moving
		PlayerAnimationHandler h = new PlayerAnimationHandler(unitHandler, screen.getScene());
		h.prepareAnimations();
	}
}
