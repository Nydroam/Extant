import java.util.HashSet;

import com.sun.prism.paint.Color;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Driver extends Application {

	HashSet<Unit> allUnits = new HashSet<>();
	HashSet<Unit> selectedUnits = new HashSet<>();
	Rectangle selectionBox = new Rectangle();
	double width;
	double height;
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		pane.setStyle("-fx-border-color: black");
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		//Maximized screen
		primaryStage.setMaximized(true);
		primaryStage.show();
		width = primaryStage.getWidth();
		height = primaryStage.getHeight();
		
		Unit test = new Unit(width/2,height/2,15);
		Unit test2 = new Unit(width/2 + 40, height/2 + 40, 15);
		allUnits.add(test);
		allUnits.add(test2);
		
		allUnits.stream().forEach(u->{
			pane.getChildren().addAll(u.shape(),u.deselect());
			u.shape().setOnMouseClicked(e -> {
				if(e.getButton()==MouseButton.PRIMARY) {
					if(!u.selected()) {
						selectedUnits.stream().forEach(un->un.deselect());
						selectedUnits.removeAll(selectedUnits);
						selectedUnits.add(u);
						u.select();
					}
					else {
						System.out.println("deselect");
						selectedUnits.remove(u);
						u.deselect();
					}
				}
			});
		});
		
		scene.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.SECONDARY) {
				if(!selectedUnits.isEmpty()) {
					selectedUnits.parallelStream().forEach(u->u.setAnim(e.getX(),e.getY()));	
				}
			}
		});
		
		
			
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static double dist(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
	}

}
