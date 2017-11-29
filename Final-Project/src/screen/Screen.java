package screen;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Screen {
	
	private Scene screenScene;
	private Pane screenPane;
	
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
		
		//button positioning
		b.setLayoutX(x);
		b.setLayoutY(y);
		b.setPrefWidth(w);
		b.setPrefHeight(h);
		
		//button text
		b.setText(text);
		b.setFont(new Font("Times New Roman", h/3));
		
		//event handling
		b.setOnMouseClicked(e);
		
		//adding to pane
		screenPane.getChildren().add(b);
	}
	public void addButton(Button b) {
		screenPane.getChildren().add(b);
	}
	
	
	//Add text to the pane at a certain position with given parameters 
	public void addText(double x, double y, double w, double h, boolean center, String text) {
		Text t = new Text();
		t.setX(x);
		t.setY(y);
		t.setWrappingWidth(w);
		t.setText(text);
		if(center)
			t.setTextAlignment(TextAlignment.CENTER);
		t.setFont(new Font("Comic Sans", h));
		
		screenPane.getChildren().add(t);
	}
	public void addText(Text t) {
		screenPane.getChildren().add(t);
	}
}