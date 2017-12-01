
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class AttackHandler {
	UnitHandler unitHandler;
	Scene scene;
	
	public AttackHandler(UnitHandler unitHandler, Scene scene) {
		this.unitHandler = unitHandler;
		this.scene = scene;
	}
	
	public void prepareAnimations() {
		unitHandler.getSet(UnitHandler.PLAYER).stream().forEach(u->{
			unitHandler.getSet(UnitHandler.ENEMY).stream().forEach(un->u.setTarget(un));
			u.setAttackAnimation(
					new AnimationTimer() {
						public void handle(long now) {
							if (u.getTarget()!=null&&u.getAttackRange()!=null)
								System.out.println(u.getAttackRange().getCenterX());
							if (u.getTarget()!=null&&u.getAttackRange()!=null&&u.getAttackRange().contains(u.getTarget().getX()-u.getX(),u.getTarget().getY()-u.getY())) {
								
								Line l= u.getAttackLine();
								l.setStroke(Color.BLACK);
								l.toFront();
								l.setStartX(u.getX());
								l.setStartY(u.getY());
								l.setEndX(u.getTarget().getX());
								l.setEndY(u.getTarget().getY());
								//System.out.println("DOING STUFF");
							}
							else if (u.getTarget()!=null&&u.getAttackRange()!=null) {
								u.getAttackLine().setStroke(Color.TRANSPARENT);
							}
						}
					}
			);
			u.startAttackAnimation();
		});
		
	}
}
