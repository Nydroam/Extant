import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import screen.Screen;
import screen.ScreenManager;
public class GameScreenManager extends ScreenManager {

	
	public GameScreenManager(double w, double h, Scene s) {
		super(w, h, s);
	}
	public void colorButtons(Screen s) {
		s.getPane().setBackground(new Background(new BackgroundFill(Settings.backgroundColor, null, null)));
		s.getPane().getChildren().stream().filter(n->n instanceof Button).forEach(n->{
			((Button)n).setTextFill(Settings.backgroundColor);
			((Button)n).setBackground(new Background(new BackgroundFill(Settings.foregroundColor, null, null)));
		});
	}
	public Screen setMenu() {
		Screen s = super.setMenu();
		colorButtons(s);
		return s;
	}
	public Screen setHelp() {
		Screen s = super.setHelp();
		// Help Text
		colorButtons(s);
		//s.getPane().setBackground(new Background(new BackgroundFill(Settings.backgroundColor, null, null)));
		String text = "This is the game Blahdiblah.\n\n";
		text += "Click on a circle to select it. Click and drag to select more than one at once.\n\n";
		text += "Use WASD to move selected Units.\n\n";
		text += "Press ESC while ingame to end the session early.\n\n";
		text += "You can change the number of units you have and difficulty in settings. \n\n";
		s.addText(width / 8, height / 6, width / 5 * 3, height / 25, false, text, Settings.foregroundColor);
		return s;
	}

	public Screen setSettings() {
		Screen s = super.setSettings();
		
		//s.getPane().setBackground(new Background(new BackgroundFill(Settings.backgroundColor, null, null)));
		SettingManager sm= new SettingManager(s);
		sm.setAll();
		return s;
		
		
		
	}
	
	public Screen setPlay() {
		Settings.score = 0;
		
		Screen s = super.setPlay();
		colorButtons(s);
		//s.getPane().setBackground(new Background(new BackgroundFill(Settings.backgroundColor, null, null)));
		//s.addText(0, height/2, width, height/25, true, "Start Placing Units!\nPress ENTER to Begin!");
		PlayHandler handler = new PlayHandler(s, this);
		handler.prepare();
		return s;

	}
	
	public Screen setScore() {
		Screen s = super.setScore();
		colorButtons(s);
		//s.getPane().setBackground(new Background(new BackgroundFill(Settings.backgroundColor, null, null)));
		s.addText(0, height/4, width, height/10, true, "SCORE", Settings.foregroundColor);
		s.addText(0, height/7*4, width, height/5, true, ""+Settings.score, Settings.foregroundColor);
		return s;
	}
}
