package screen;
import javafx.scene.Scene;

public class ScreenManager {

	//Variables representing states of the game that it could be in
	public static final int MENU_STATE = 0;
	public static final int HELP_STATE = 1;
	public static final int SETTINGS_STATE = 2;
	public static final int PLAY_STATE = 3;
	
	//Scene to adjust based on state
	protected Scene scene;
	
	//Width and Height of Screens
	protected double width;
	protected double height;
	
	//Width and Height of Buttons
	protected double buttonWidth;
	protected double buttonHeight;
	
	public ScreenManager(double w, double h, Scene s) {
		scene = s;
		width = w;
		height = h;
		buttonWidth = width/10;
		buttonHeight = height/12;
	}
	
	//Sets a screen based on the given parameter
	public Screen setState(int s) {
		switch (s) {
			case MENU_STATE:
				setMenu();
				break;
			case HELP_STATE:
				setHelp();
				break;
			case SETTINGS_STATE:
				setSettings();
				break;
			case PLAY_STATE:
				setPlay();
				break;
		}
		return null;
	}
	
	public Screen setMenu() {
		Screen s = new Screen(scene);
		//Play Button
		s.addButton(width/2-buttonWidth/2,height/9*4,buttonWidth,buttonHeight,"Play", e-> {
			setState(PLAY_STATE);
		});
		
		//Help Button
		s.addButton(width/2-buttonWidth/2,height/9*5,buttonWidth,buttonHeight, "Help", e-> {
			setState(HELP_STATE);
		});
		
		//Settings Button
		s.addButton(width/2-buttonWidth/2,height/9*6,buttonWidth,buttonHeight, "Settings", e-> {
			setState(SETTINGS_STATE);
		});
		
		//Quit
		s.addButton(width/2-buttonWidth/2,height/9*7,buttonWidth,buttonHeight, "Quit", e-> {
			System.exit(0);
		});
		return s;
	}
	
	public Screen setHelp() {
		Screen s = new Screen(scene);
		//Menu Button
		s.addButton(width/6*5, height/2-buttonHeight/2, buttonWidth, buttonHeight, "Menu", e -> {
			setState(MENU_STATE);
		});
		return s;
		
		
	}
	
	public Screen setSettings() {
		Screen s = new Screen(scene);
		//Menu Button
		s.addButton(width/6*5, height/2-buttonHeight/2, buttonWidth, buttonHeight, "Menu", e -> {
			setState(MENU_STATE);
		});
		
		
		return s;
	}
	
	public Screen setPlay() {
		Screen s = new Screen(scene);
		s.addText(0, height/2, width, height/25, true, "Start Placing Units!\nPress Spacebar to Begin!");
		return s;
	}
}
