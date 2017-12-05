import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Settings {
	public static int numTankers = 3;
	public static int numShooters = 3;
	public static int numEnemies = 5;
	public static boolean eraserExists = true;
	public static int diff = 2;
	
	public static Color backgroundColor = Color.WHITE;
	public static Color foregroundColor = Color.BLACK;
	public static Color tankerColor = Color.BLUE;
	public static Color shooterColor = Color.RED;
	public static Color secShooterColor = Color.BLANCHEDALMOND;
	public static Color eraserColor = Color.LIME;
	public static Color secEraserColor = Color.YELLOW;
	public static Color enemyColor = Color.ORANGE;
	public static Color trackerColor = Color.GREEN;
	public static Color pulsarColor = Color.PURPLE;
	public static Color secPulsarColor = Color.MEDIUMPURPLE;
	public static Color warningColor = Color.RED;
	public static int score = 0;
	
	public static ArrayList<Color[]> colorPalettes;
	public static int paletteIndex;
	public static Color[] currentPalette;
	public static void setPalette() {
		currentPalette = colorPalettes.get(paletteIndex);
		backgroundColor = currentPalette[0];
		foregroundColor = currentPalette[1];
		tankerColor = currentPalette[2];
		shooterColor = currentPalette[3];
		secShooterColor = currentPalette[4];
		eraserColor = currentPalette[5];
		secEraserColor = currentPalette[6];
		enemyColor = currentPalette[7];
		trackerColor = currentPalette[8];
		pulsarColor = currentPalette[9];
		secPulsarColor = currentPalette[10];
		warningColor = currentPalette[11];
	}
	public static Color[] original = new Color[] {
			Color.WHITE,
			Color.BLACK,
			Color.BLUE,
			Color.RED,
			Color.BLANCHEDALMOND,
			Color.LIME,
			Color.YELLOW,
			Color.ORANGE,
			Color.GREEN,
			Color.PURPLE,
			Color.MEDIUMPURPLE,
			Color.RED 
	};
	public static Color[] neon = new Color[] {
			Color.BLACK,
			Color.WHITE,
			Color.web("#00FFFF"),//tanker
			Color.web("#FF6600"),//shooter
			Color.web("#FF0099"),//shooter2
			Color.web("#00FF00"),//eraser
			Color.web("#0062FF"),//eraser2
			Color.web("#F2EA02"),//enemy
			Color.web("#099FFF"),//tracker
			Color.web("#FF00FF"),//pulsar2
			Color.web("#9D00FF"),//pulsar
			
			Color.web("E6FB04") //warning
	};
	public static Color[] warm = new Color[] {
			Color.web("#D3C9CE"),
			Color.web("#793F0D"),
			Color.web("#AC703D"),//tanker
			Color.web("#E49969"),//shooter
			Color.web("#EEC5A9"),//shooter2
			Color.web("#DFD27C"),//eraser
			Color.web("#E7E3B5"),//eraser2
			Color.web("#C7C397"),//enemy
			Color.web("#6E7649"),//tracker
			Color.web("#B7A6AD"),//pulsar2
			Color.web("#846D74"),//pulsar
			Color.web("#E5AE86") //warning
	};
	public static Color[] cool = new Color[] {
			Color.web("#9EE7FA"),
			Color.web("#00ADCE"),
			Color.web("#9A93EC"),//tanker
			Color.web("#413BF7"),//shooter
			Color.web("#81CBF8"),//shooter2
			Color.web("#59DBF1"),//eraser
			Color.web("#004159"),//eraser2
			Color.web("#0052A5"),//enemy
			Color.web("#8C65D3"),//tracker
			Color.web("#73EBAE"),//pulsar2
			Color.web("#00C590"),//pulsar
			Color.web("#65A8C4") //warning
	};
	
	
	
}
