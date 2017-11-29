import java.util.HashSet;

public class UnitHandler {
	
	HashSet<GameUnit> playerUnits;
	HashSet<GameUnit> enemyUnits;
	HashSet<GameUnit> selectedUnits;
	
	public static final int PLAYER = 0;
	public static final int ENEMY = 1;
	public static final int SELECTED = 2;
	
	public UnitHandler() {
		playerUnits = new HashSet<>();
		enemyUnits = new HashSet<>();
		selectedUnits = new HashSet<>();
	}
	
	public HashSet<GameUnit> getSet(int i){
		switch(i) {
			case PLAYER:
				return playerUnits;
			case ENEMY:
				return enemyUnits;
			case SELECTED:
				return selectedUnits;
		}
		return null;
	}
	
	public void addUnit(int i, GameUnit u){
		switch(i) {
			case PLAYER:
				playerUnits.add(u);
				break;
			case ENEMY:
				enemyUnits.add(u);
				break;
			case SELECTED:
				selectedUnits.add(u);
				u.toggleSelect();
				break;
		}
	}
	public void removeUnit(int i, GameUnit u){
		switch(i) {
			case PLAYER:
				playerUnits.remove(u);
				break;
			case ENEMY:
				enemyUnits.remove(u);
				break;
			case SELECTED:
				selectedUnits.remove(u);
				u.toggleSelect();
				break;
		}
	}
	public void clearUnits(int i){
		switch(i) {
			case PLAYER:
				playerUnits = new HashSet<>();
				break;
			case ENEMY:
				enemyUnits = new HashSet<>();
				break;
			case SELECTED:
				selectedUnits.stream().forEach(u -> u.toggleSelect());
				selectedUnits = new HashSet<>();
				break;
		}
	}
	public void clearAll() {
		clearUnits(PLAYER);
		clearUnits(ENEMY);
		clearUnits(SELECTED);
	}
}
