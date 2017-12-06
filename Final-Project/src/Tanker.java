import javafx.scene.shape.Circle;

public class Tanker extends PlayerUnit{

	public Tanker(double r) {
		radius = r;
		color = Settings.tankerColor;
		shape = new Circle(radius);
		highlight = new Circle(radius+5);
		speed = 4;
		maxHP = 3000;
		setup();
		
	}
	
	
	
	
	
}
