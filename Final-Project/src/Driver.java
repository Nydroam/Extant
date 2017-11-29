import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class Driver extends Application {
	
	StateManager manage;
	Screen[] screens = new Screen[5];
	
	Scene scene = new Scene(new Pane());
	
	private double width;
	private double height;
	
	public void start(Stage primaryStage) {
		primaryStage.setMaximized(true);
		primaryStage.setScene(scene);
		primaryStage.show();
		width = primaryStage.getWidth();
		height = primaryStage.getHeight();
		setupFields();
		setupScreens();
		
		//BEGIN
		manage.setState(StateManager.MENU_STATE);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void setupFields() {
		manage = new StateManager(width,height,scene);
	}
	
	public void setupScreens(){
		screens[StateManager.MENU_STATE] = manage.setState(StateManager.MENU_STATE);
		screens[StateManager.HELP_STATE] = manage.setState(StateManager.HELP_STATE);
		screens[StateManager.SETTINGS_STATE] = manage.setState(StateManager.SETTINGS_STATE);
		screens[StateManager.PLAY_STATE] = manage.setState(StateManager.PLAY_STATE);
	}

}
