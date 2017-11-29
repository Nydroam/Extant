import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Screen {
	
	private Scene screenScene;
	private Pane screenPane;
	private int width;
	private int height;
	
	//Sets up a default Screen
	public Screen(Scene scene) {
		screenScene = scene;
		screenPane = new Pane();
		scene.setRoot(screenPane);
	}
	
	//Accessors BEGIN
	public Scene getScene() {
		return screenScene;
	}
	
	public Pane getPane() {
		return screenPane;
	}
	//Accessors END
	
	//Add a button to the pane at a certain position with given parameters and attached event
	public void addButton(double x, double y, double w, double h, String text, EventHandler<? super MouseEvent> e) {
		Button b = new Button();
		b.setLayoutX(x);
		b.setLayoutY(y);
		b.setPrefWidth(w);
		b.setPrefHeight(h);
		b.setText(text);
		b.setOnMouseClicked(e);
		
		screenPane.getChildren().add(b);
	}
	
	//Add text to the pane at a certain position with given parameters 
	public void addText(double x, double y, double w, Font f, String text) {
		Text t = new Text();
		t.setX(x);
		t.setY(y);
		t.setWrappingWidth(w);
		t.setText(text);
		t.setFont(f);
		
		screenPane.getChildren().add(t);
	}
}
