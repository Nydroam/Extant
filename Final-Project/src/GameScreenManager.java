import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import screen.Screen;
import screen.ScreenManager;
public class GameScreenManager extends ScreenManager {

	
	public GameScreenManager(double w, double h, Scene s) {
		super(w, h, s);
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
		chaserText.setText("Number of Chasers: " + Settings.numTankers);
		s.addText(chaserText);
		s.addButton(width/11*6, height/22*9, width/50, height/50, "", e->{
			Settings.numTankers++;
			if(Settings.numTankers>=10)
				Settings.numTankers=10;
			chaserText.setText("Number of Chasers: " + Settings.numTankers);
		});
		s.addButton(width/11*6, height/11*5, width/50, height/50, "", e->{
			Settings.numTankers--;
			if(Settings.numTankers<=0)
				Settings.numTankers=0;
			chaserText.setText("Number of Chasers: " + Settings.numTankers);
		});
		
		Text shooterText = new Text();
		shooterText.setFont(new Font("Times New Roman", height/25));
		shooterText.setX(width/5);
		shooterText.setY(height/11*6);
		shooterText.setWrappingWidth(width/3);
		shooterText.setTextAlignment(TextAlignment.RIGHT);
		shooterText.setText("Number of Shooters: " + Settings.numShooters);
		s.addText(shooterText);
		s.addButton(width/11*6, height/22*11, width/50, height/50, "", e->{
			Settings.numShooters++;
			if(Settings.numShooters>=10)
				Settings.numShooters=10;
			shooterText.setText("Number of Shooters: " + Settings.numShooters);
		});
		s.addButton(width/11*6, height/11*6, width/50, height/50, "", e->{
			Settings.numShooters--;
			if(Settings.numShooters<=1)
				Settings.numShooters=1;
			shooterText.setText("Number of Shooters: " + Settings.numShooters);
		});
		
		return s;
	}
	
	public Screen setPlay() {
		Settings.score = 0;
		Screen s = super.setPlay();
		//s.addText(0, height/2, width, height/25, true, "Start Placing Units!\nPress ENTER to Begin!");
		PlayHandler handler = new PlayHandler(s, this);
		handler.prepare();
		return s;

	}
	
	public Screen setScore() {
		Screen s = super.setScore();
		s.addText(0, height/4, width, height/10, true, "SCORE");
		s.addText(0, height/2, width, height/5, true, ""+Settings.score);
		return s;
	}
}
