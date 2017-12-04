import java.util.Stack;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import screen.Screen;

public class SettingManager {
	
	Screen screen;
	double width;
	double height;
	
	
	public SettingManager(Screen s) {
		screen = s;
		width = s.getWidth();
		height = s.getHeight();
	}
	
	public void setTop(){
		setUnitSetting(PlayerUnit.TANKER);
		setUnitSetting(PlayerUnit.SHOOTER);
		setUnitSetting(PlayerUnit.ERASER);
	}
	public void setMid() {
		Circle[] circles = new Circle[3];
		
		setDiffSetting(1,circles);
		setDiffSetting(2, circles);
		setDiffSetting(3, circles);
	}
	
	public void setDiffSetting(int i, Circle[] circles) {
		Circle invis;
		Text diffText;
		invis = unitCircle(Color.TRANSPARENT);
		invis.setStrokeWidth(5);
		
		invis.setStroke(Color.TRANSPARENT);
		if(Settings.diff==i)
			invis.setStroke(Color.BLACK);
		invis.setCenterX(i*width/5);
		invis.setCenterY(2*height/4);
		circles[i-1]=invis;
		String s = "";
		for(int j = 0; j < i; j++)
			s+="!";
		diffText = new Text(s);
		diffText.setFill(Settings.warningColor);
		diffText.setFont(Font.font("Times New Roman",FontWeight.BOLD,screen.getHeight()/20));
		diffText.setTextAlignment(TextAlignment.CENTER);
		diffText.setWrappingWidth(invis.getRadius()*2);
		diffText.setX(invis.getCenterX()-invis.getRadius());
		diffText.setY(invis.getCenterY()+invis.getRadius()/3);
		screen.addNode(invis);
		screen.addNode(diffText);
		
		diffText.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY) {
				for(Circle c: circles) {
					c.setStroke(Color.TRANSPARENT);
				}
				invis.setStroke(Color.BLACK);
				Settings.diff = i;
			}
		});
	}
	
	public void setUnitSetting(int type){
		Circle c;
		Color color = Color.TRANSPARENT;
		int i=type+1;
		int num = 1;
		switch (type) {
		case PlayerUnit.TANKER:
			color = Settings.tankerColor;
			num = Settings.numTankers;
			break;
		case PlayerUnit.SHOOTER:
			color = Settings.shooterColor;
			num = Settings.numShooters;
			break;
		case PlayerUnit.ERASER:
			color = Settings.eraserColor;
			if(!Settings.eraserExists)
				num = 0;
			break;
		}
		Stack<Circle> stack = new Stack<Circle>();
		
		c = unitCircle(color);
		c.setCenterX(i*width/5);
		c.setCenterY(height/4);
		screen.addNode(c);
		stack.push(c);
		Polygon u = triangleButton(true);
		u.setOnMouseClicked(e->triangleClickEvent(e,type,stack,true));
		Polygon p = triangleButton(false);
		p.setOnMouseClicked(e->triangleClickEvent(e,type,stack,false));
		u.setLayoutX(c.getCenterX()+c.getRadius()*2);
		u.setLayoutY(c.getCenterY()-c.getRadius());
		p.setLayoutX(c.getCenterX()+c.getRadius()*2);
		p.setLayoutY(c.getCenterY()+c.getRadius());
		screen.addNode(u);
		screen.addNode(p);
		for(int j = 1; j < num; j++) {
			Circle next = unitCircle(color);
			next.setCenterX(c.getCenterX());
			next.setCenterY(c.getCenterY()+(j)*c.getRadius()/5);
			screen.addNode(next);
			stack.push(next);
		}
	}
	
	public void triangleClickEvent(MouseEvent e, int type, Stack<Circle> stack, boolean up) {
		Color c = Color.TRANSPARENT;
		if(e.getButton()==MouseButton.PRIMARY) {
			switch (type) {
			case PlayerUnit.TANKER:
				c = Settings.tankerColor;
				if(up) {
					if(Settings.numTankers==10)
						return;
					else
						Settings.numTankers++;
				}
				else {
					if(Settings.numTankers==0)
						return;
					else
						Settings.numTankers--;
				}
				break;
			case PlayerUnit.SHOOTER:
				c = Settings.shooterColor;
				if(up) {
					if(Settings.numShooters==10)
						return;
					else
						Settings.numShooters++;
				}
				else {
					if(Settings.numShooters==1)
						return;
					else
						Settings.numShooters--;
					
				}
				break;
			case PlayerUnit.ERASER:
				c = Settings.eraserColor;
				if(up) {
					if(Settings.eraserExists)
						return;
					else {
						Settings.eraserExists=true;
						stack.peek().setFill(Settings.eraserColor);
						return;
					}
				}else {
					if(!Settings.eraserExists)
						return;
					else {
						Settings.eraserExists=false;
						stack.peek().setFill(Settings.secEraserColor);
						return;
					}
				}
			}
			
			if(up) {
				Circle newCircle = unitCircle(c);
				if(!stack.isEmpty()) {
				Circle prevCircle = (Circle)stack.peek();
				newCircle.setCenterX(prevCircle.getCenterX());
				newCircle.setCenterY(prevCircle.getCenterY()+prevCircle.getRadius()/5);
				}else {
					newCircle.setCenterX((type+1)*width/5);
					newCircle.setCenterY(height/4);
				}
				screen.addNode(newCircle);
				stack.push(newCircle);
			}
			else {
				screen.getPane().getChildren().remove(stack.pop());
			}
		}
	}
	public Circle unitCircle(Color c) {
		Circle circle = new Circle();
		circle.setStroke(Color.BLACK);
		circle.setRadius(height/25);
		circle.setFill(c);
		return circle;
	}
	
	public Polygon triangleButton(boolean up) {
		Polygon tri = new Polygon();
		double radius = height/50;
		tri.getPoints().addAll(new Double[] {
				0.0,-radius,
				-radius*Math.sqrt(3)/2, radius/2,
				radius*Math.sqrt(3)/2, radius/2
		});
		if(!up) {
			Rotate r = new Rotate();
			tri.getTransforms().add(r);
			r.setAngle(180);
		}
		tri.setFill(Settings.trackerColor);
		return tri;
	}
	
}
