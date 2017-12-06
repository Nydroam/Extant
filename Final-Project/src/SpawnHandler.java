import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import screen.Screen;

public class SpawnHandler {

	private AnimationTimer spawnTimer;
	private UnitHandler unitHandler;
	private Screen screen;
	private HashSet<Text> warnings;
	private int wave;
	private Text waveText;
	
	public SpawnHandler(UnitHandler u, Screen s) {
		unitHandler = u;
		screen = s;
	}
	
	public void setupTimer() {
		waveText = new Text(wave+"");
		waveText.setMouseTransparent(true);
		waveText.setFont(Font.font("Comic Sans", FontWeight.EXTRA_BOLD, screen.getHeight()/15));
		waveText.setTextAlignment(TextAlignment.CENTER);
		
		waveText.setY(screen.getHeight()/2+screen.getHeight()/45);
		waveText.setWrappingWidth(screen.getWidth());
		waveText.setFill(Color.TRANSPARENT);
		waveText.setStroke(Color.TRANSPARENT);
		screen.addNode(waveText);
		wave = 0;
		Settings.numEnemies = Settings.diff*3;
		warnings = new HashSet<Text>();
		for(int i = 0; i < Settings.numEnemies; i++) {
			Text next = new Text("!");
			next.setFill(Color.TRANSPARENT);
			next.setStroke(Color.TRANSPARENT);
			next.setFont(Font.font("Times New Roman",FontWeight.BOLD,screen.getHeight()/15));
			screen.addNode(next);
			warnings.add(next);
		}
		spawnTimer = new AnimationTimer() {
			int i = 0;
			
			boolean gaveWarning = false;
			int direction = 0;
			public void handle(long now) {
				
				if(i>400-Settings.diff*100&&!gaveWarning) {
					direction = (int)(Math.random()*4);
					giveWarning(direction);
					gaveWarning = true;
				}
				if(i>600-Settings.diff*100) {
					//waveText.setStroke(Color.TRANSPARENT);
					warnings.stream().forEach(t->t.setFill(Color.TRANSPARENT));
					spawnUnits(direction);
					i = 0;
					gaveWarning = false;
					wave++;
					if(wave%3==0) {
						Text next = new Text("!");
						next.setFill(Color.TRANSPARENT);
						next.setStroke(Color.TRANSPARENT);
						next.setFont(Font.font("Times New Roman",FontWeight.BOLD,screen.getHeight()/15));
						screen.addNode(next);
						warnings.add(next);
					}
				}
				
				i++;
			}
		};
	}
	
	public void startTimer() {
		spawnTimer.start();
	}
	public void stopTimer() {
		spawnTimer.stop();
	}
	
	public void giveWarning(int direction) {
		waveText.setText(""+wave);
		waveText.setFill(Settings.foregroundColor);
		int i = 0;
		for(Text t:warnings) {
			t.setFill(Settings.warningColor);
			if(direction == 0) {
				t.setX(screen.getHeight()/25);
				t.setY((i+1)*screen.getHeight()/(warnings.size()+1));
			}
			if(direction == 1) {
				t.setX(screen.getWidth()-screen.getHeight()*2/25);
				t.setY((i+1)*screen.getHeight()/(warnings.size()+1));
			}
			if(direction == 2) {
				t.setX((i+1)*screen.getWidth()/(warnings.size()+1));
				t.setY(screen.getHeight()*2/25);
			}
			if(direction == 3) {
				t.setX((i+1)*screen.getWidth()/(warnings.size()+1));
				t.setY(screen.getHeight()*24/25);
			}
			i++;
		};
		
	}
	
	public void spawnUnits(int direction) {
		for(int i = 0; i < warnings.size(); i++) {
			EnemyUnit e = new Enemy(screen.getHeight()/50);
			
			double rand = Math.random();
			for(int j = 0; j < wave/10; j++)
				if(i==j)
					e = new Pulsar(screen.getHeight()/50);
			
			for(int j = warnings.size()-1; j >= warnings.size()-wave/5; j--)
				if(i==j)
					e = new Tracker(screen.getHeight()/50);
			
			if(rand<wave/100.0)
				e.setStronger();
			if(direction == 0) //left
				e.move(-1*e.getRadius()*2, (i+1)*screen.getHeight()/(warnings.size()+1));
			if(direction == 1) //right
				e.move(screen.getWidth(), (i+1)*screen.getHeight()/(warnings.size()+1));
			if(direction == 2) //up
				e.move((i+1)*screen.getWidth()/(warnings.size()+1), -1*e.getRadius()*2);
			if(direction == 3) //down
				e.move((i+1)*screen.getWidth()/(warnings.size()+1), screen.getHeight());
			unitHandler.addUnit(UnitHandler.ENEMY, e);
			screen.addNode(e.getShape());
			screen.addNode(e.getAttackLine());
			screen.addNode(e.getAttackRange());
			if(e instanceof AttackUnit) {
			((AttackUnit)e).setAttackAnimation(unitHandler, screen.getPane());
			e.startAttackAnimation();
			}
			e.setMoveAnimation(screen.getWidth(), screen.getHeight());
			e.startMoveAnimation();
		}
	};
	
}
