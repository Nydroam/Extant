import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.shape.Circle;
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
		String title = "BLAHDIBLAH";
		s.addText(0,height/3,width,height/6,true,title, Settings.foregroundColor);
		colorButtons(s);
		return s;
	}
	public Screen setHelp() {
		Screen s = super.setHelp();
		// Help Text
		colorButtons(s);
		//s.getPane().setBackground(new Background(new BackgroundFill(Settings.backgroundColor, null, null)));
		
		String text = "\n\n";
		text += "These are your units\n\n";
		text += "shoots the closest enemy\n\n";
		text += "takes damage well\n\n";
		text += "shoots all enemies\n\n";
		text += "Polygons are your enemies";
		
		s.addText(width / 6, height / 6, width / 3, height / 25, false, text, Settings.foregroundColor);
		text = "\n\n";
		text +="Click to select a unit\n";
		text +="Click and drag to select more\n";
		text += "WASD to move selected units\n\n";
		text += "E shoots all enemies\n\n";
		text += "ESC ends current game\n\n";
		text += "Survive for as long as possible";
		
		s.addText(width/2, height/6, width/3, height/25, false, text, Settings.foregroundColor);
		Circle c = new Circle(height/25);
		c.setFill(Settings.shooterColor);
		c.setCenterX(width/10);
		c.setCenterY(height/6+4*height/20);
		s.addNode(c);
		c = new Circle(height/25);
		c.setFill(Settings.tankerColor);
		c.setCenterX(width/10);
		c.setCenterY(height/6+6*height/20);
		s.addNode(c);
		c = new Circle(height/25);
		c.setFill(Settings.eraserColor);
		c.setCenterX(width/10);
		c.setCenterY(height/6+8*height/20);
		s.addNode(c);
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
		s.getScene().setOnKeyReleased(null);
		colorButtons(s);
		//s.getPane().setBackground(new Background(new BackgroundFill(Settings.backgroundColor, null, null)));
		s.addText(0, height/4, width, height/10, true, "SCORE", Settings.foregroundColor);
		s.addText(0, height/7*4, width, height/5, true, ""+Settings.score, Settings.foregroundColor);
		return s;
	}
}
