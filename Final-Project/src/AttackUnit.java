import javafx.scene.layout.Pane;

public interface AttackUnit {
	
	public void setAttackAnimation(UnitHandler unitHandler, Pane pane);
	public void retarget(UnitHandler unitHandler);
}
