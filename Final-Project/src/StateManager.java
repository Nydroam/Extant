import javafx.scene.Scene;

public class StateManager {

	//Variables representing states of the game that it could be in
	public static final int MENU_STATE = 0;
	public static final int HELP_STATE = 1;
	public static final int SETTINGS_STATE = 2;
	//public final int PREP_STATE = 3;
	public static final int PLAY_STATE = 3;
	
	//The current state the game is in
	private int currState;
	
	private Scene scene;
	
	//Width and Height of Screens
	private double width;
	private double height;
	
	public StateManager(double w, double h, Scene s) {
		scene = s;
		width = w;
		height = h;
	}
	
	//Sets a screen based on the given parameter
	public Screen setState(int s) {
		switch (s) {
			case MENU_STATE:
				setMenu();
				break;
			case HELP_STATE:
				return setHelp();
			case SETTINGS_STATE:
				return setSettings();
			case PLAY_STATE:
				return setPlay();
		}
		return null;
	}
	
	public void setMenu() {
		Screen s = new Screen(scene);
		//Play Button
		double w = width/10;
		double h = height/7;
		s.addButton(width/2-w/2,height/5*2-h/2,w,h,"Play", e-> {
			setState(PLAY_STATE);
		});
		
		//Help Button
		s.addButton(width/2-w/2, height/5*3-h/2, w, h, "Help", e-> {
			setState(HELP_STATE);
		});
		
		//Settings Button
		s.addButton(width/2-w/2, height/5*4-h/2, w, h, "Settings", e-> {
			setState(SETTINGS_STATE);
		});
	}
	
	public Screen setHelp() {
		Screen s = new Screen(scene);
		double w = width/10;
		double h = height/7;
		//Menu Button
		s.addButton(width/6*5-w/2, height/2-h/2, w, h, "Menu", e -> {
			setState(MENU_STATE);
		});
		return s;
	}
	
	public Screen setSettings() {
		Screen s = new Screen(scene);
		double w = width/10;
		double h = height/7;
		//Menu Button
		s.addButton(width/6*5-w/2, height/2-h/2, w, h, "Menu", e -> {
			setState(MENU_STATE);
		});
		return s;
	}
	
	public Screen setPlay() {
		Screen s = new Screen(scene);
		return s;
	}
}
