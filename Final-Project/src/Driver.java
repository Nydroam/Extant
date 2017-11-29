import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
		primaryStage.setScene(scene);
		primaryStage.show();
	
		//Need to show to get width and height
		width = primaryStage.getWidth();
		height = primaryStage.getHeight();
		manage = new GameScreenManager(width,height,scene);
		//BEGIN
		manage.setState(ScreenManager.MENU_STATE);
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
