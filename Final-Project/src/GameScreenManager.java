import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import screen.Screen;
import screen.ScreenManager;
public class GameScreenManager extends ScreenManager {

	private int numChasers;
	private int numShooters;
	
	public GameScreenManager(double w, double h, Scene s) {
		super(w, h, s);
		numChasers = 4;
		numShooters = 4;
	}

	public Screen setHelp() {
		Screen s = super.setHelp();
		// Help Text
		String text = "This is the game Blahdiblah.\n\n";
		text += "Would you like to play?\n\n";
		text += "If not, then would you kindly leave, the door is that way.\n\n";
		text += "Make sure it hits you on the way out.\n\n";
		text += "If you decide to come back, we're not saving any cookies for you.\n\n";
		text += "YOU HAVE BEEN WARNED!";
		s.addText(width / 8, height / 6, width / 5 * 3, height / 25, false, text);
		return s;
	}

	public Screen setSettings() {
		Screen s = super.setSettings();
		//Settings Text
		Text chaserText = new Text();
		chaserText.setFont(new Font("Times New Roman", height/25));
		chaserText.setX(width/5);
		chaserText.setY(height/11*5);
		chaserText.setWrappingWidth(width/3);
		chaserText.setTextAlignment(TextAlignment.RIGHT);
		chaserText.setText("Number of Chasers: " + numChasers);
		s.addText(chaserText);
		
		Text shooterText = new Text();
		shooterText.setFont(new Font("Times New Roman", height/25));
		shooterText.setX(width/5);
		shooterText.setY(height/11*6);
		shooterText.setWrappingWidth(width/3);
		shooterText.setTextAlignment(TextAlignment.RIGHT);
		shooterText.setText("Number of Shooters: " + numShooters);
		s.addText(shooterText);
		
		return s;
	}
	
	public Screen setPlay() {
		Screen s = super.setPlay();
		s.addText(0, height/2, width, height/25, true, "Start Placing Units!\nPress ENTER to Begin!");
		PlayHandler handler = new PlayHandler(s, numChasers, numShooters);
		handler.prepare();
		return s;

	}
}
