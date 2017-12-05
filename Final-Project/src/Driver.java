import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import screen.ScreenManager;
public class Driver extends Application {
	
	ScreenManager manage;
	Scene scene = new Scene(new Pane());
	
	private double width;
	private double height;
	
	public void start(Stage primaryStage) {
		
		//Making the Application FullScreen
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("");
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		primaryStage.setScene(scene);
		primaryStage.show();
	
		//Need to show to get width and height
		width = primaryStage.getWidth();
		height = primaryStage.getHeight();
		manage = new GameScreenManager(width,height,scene);
		
		//Color setup
		Settings.colorPalettes = new ArrayList<Color[]>();
		Settings.colorPalettes.add(Settings.original);
		Settings.colorPalettes.add(Settings.neon);
		Settings.colorPalettes.add(Settings.warm);
		Settings.colorPalettes.add(Settings.cool);
		Settings.currentPalette = Settings.original;
		Settings.paletteIndex = 0;
		//BEGIN
		manage.setState(ScreenManager.MENU_STATE);
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
