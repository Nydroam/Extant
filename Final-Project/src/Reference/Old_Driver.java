package Reference;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Old_Driver extends Application {

	HashSet<Old_Unit> allUnits = new HashSet<>();
	HashSet<Old_Unit> selectedUnits = new HashSet<>();
	Rectangle selectionBox = new Rectangle();
	ExecutorService executor = Executors.newFixedThreadPool(8);
	ReentrantLock lock = new ReentrantLock();
	double boxX;
	double boxY;
	double width;
	double height;
	
	public void start(Stage primaryStage) {
		
		Pane pane = new Pane();
		pane.setStyle("-fx-border-color: black");
		pane.getChildren().add(selectionBox);
		Scene scene = new Scene(pane);
		
		primaryStage.setScene(scene);
		//Maximized screen
		primaryStage.setMaximized(true);
		primaryStage.show();
		width = primaryStage.getWidth();
		height = primaryStage.getHeight();
		
		selectionBox.setFill(Color.TRANSPARENT);
		pane.setOnMousePressed(e->{
			if(e.getButton()==MouseButton.PRIMARY) {
				selectionBox.setStroke(Color.BLACK);
				boxX = e.getX();
				boxY = e.getY();
				selectionBox.setX(boxX);
				selectionBox.setY(boxY);
				selectionBox.setWidth(0);
				selectionBox.setHeight(0);
				lock.lock();
				selectedUnits.stream().forEach(un->un.deselect());
				
				selectedUnits = new HashSet<>();
				lock.unlock();
			}
		});
		pane.setOnMouseDragged(e->{
			if(e.getButton()==MouseButton.PRIMARY) {
				selectionBox.setStroke(Color.BLACK);
				if(e.getX()-boxX<0) {
					selectionBox.setWidth(boxX-e.getX());
					selectionBox.setX(e.getX());
				}
				else {
					selectionBox.setWidth(e.getX()-selectionBox.getX());
				}
				if(e.getY()-boxY<0) {
					selectionBox.setHeight(boxY-e.getY());
					selectionBox.setY(e.getY());
				}
				else {
					selectionBox.setHeight(e.getY()-selectionBox.getY());
				}
				//Platform.runLater(() ->{
				
				allUnits.stream()
				.forEach(u->{
					
					if(selectionBox.contains(new Point2D(u.getX(),u.getY()))) {
						u.select();
						
						selectedUnits.add(u);
						
					}
					else {
						u.deselect();
						
						selectedUnits.remove(u);
						
					}
				});
				
				//});
			}
		});
		pane.setOnMouseReleased(e->{
			selectionBox.setStroke(Color.TRANSPARENT);
		});
		//THIS IS SEQUENTIAL, NOT PARALLEL
		int unitNum = 1000;
		for(int i = 0; i < unitNum; i++)
			allUnits.add(new Old_Unit(Math.random()*width,Math.random()*height,20));
		
		allUnits.stream().forEach(u->{
			
			pane.getChildren().addAll(u.shape(),u.deselect());
			u.shape().setOnMouseClicked(e -> {
				
				if(e.getButton()==MouseButton.PRIMARY) {
					
					if(!u.selected()) {
						
						selectedUnits.stream().forEach(un->un.deselect());
						
						selectedUnits = new HashSet<>();
						
						selectedUnits.add(u);
						
						u.select();
						
					}
					else {
					
						selectedUnits.remove(u);
						
						u.deselect();
						
					}
					
				}
			});
			
		
		});
		
		
		pane.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.SECONDARY) {
				if(!selectedUnits.isEmpty()) {
					//Platform.runLater(() ->{
					double x = e.getX();
					double y = e.getY();
					HashSet<Old_Unit> temp = new HashSet<>(selectedUnits);
			
				
					temp.stream().forEach(u->Platform.runLater(()->{	
						
						u.setAnim(x, y);
						}));
					
					
					//});
				}
			}
		});
		
			
	}
	
	
	public static double dist(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
	}

}
