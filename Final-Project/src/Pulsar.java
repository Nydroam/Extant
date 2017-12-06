import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public class Pulsar extends EnemyUnit implements AttackUnit {
	Rotate rotate;
	public Pulsar(double r) {
		radius = r * 2;
		color = Settings.pulsarColor;
		secColor = Settings.secPulsarColor;
		shape = new Polygon();
		((Polygon) shape).getPoints()
				.addAll(new Double[] { 0.0, radius, -1 * Math.sin(2 * Math.PI / 5) * radius,
						Math.cos(2 * Math.PI / 5) * radius,

						-1 * Math.sin(4 * Math.PI / 5) * radius, -1 * Math.cos(Math.PI / 5) * radius,
						Math.sin(4 * Math.PI / 5) * radius, -1 * Math.cos(Math.PI / 5) * radius,
						Math.sin(2 * Math.PI / 5) * radius, Math.cos(2 * Math.PI / 5) * radius });
		shape.setFill(color);
		speed = 2;
		maxHP = 300;
		attackRange = new Circle(radius * 4);
		attackLine = new Line();
		attackLine.setStrokeWidth(1);
		setup();
	}

	public void setup() {
		super.setup();
		attackRange.setMouseTransparent(true);
		attackRange.setFill(Color.TRANSPARENT);
		attackRange.setStroke(Color.TRANSPARENT);
		rotate = new Rotate();

		shape.getTransforms().add(rotate);
	}

	public void move(double x, double y) {
		super.move(x, y);
		attackRange.setLayoutX(xPos);
		attackRange.setLayoutY(yPos);
	}
	
	public void setAttackAnimation(UnitHandler unitHandler, Pane pane) {
		attackAnim = new AnimationTimer() {
			int i = 0;
			int charge = 100;
			int warning = charge + 50;
			int warning2 = warning + 50;
			int blast = warning2 + 50;

			public void handle(long now) {
				i++;
				if (target != null && target.isAlive()) {

				} else {
					retarget(unitHandler);

				}
				if (i <= charge) {
					shape.setFill(color);
					rotate.setAngle(i / (double) charge * 360);
					attackRange.setFill(Color.TRANSPARENT);
					// attackRange.setStroke(Color.TRANSPARENT);
					startMoveAnimation();
				} else if (i <= warning) {
					stopMoveAnimation();
					attackRange.toBack();
					attackRange.setRadius((i - charge) / (double) (warning - charge) * radius * 3);
					attackRange.setStroke(color);
					// attackRange.setFill(color.brighter());
					// attackRange.setStroke(color.BLACK);
					shape.setFill(secColor);

				} else if (i <= warning2) {
				} else if (i <= blast) {
					HashSet<GameUnit> deadUnits = new HashSet<>();
					unitHandler.getSet(UnitHandler.PLAYER).stream().forEach(u -> {

						if (attackRange.contains(u.getX() - xPos, u.getY() - yPos)) {
							if (u.isAlive()) {
								if(u instanceof Tanker)
									u.decHP(20);
								else
									u.decHP(5);
							} else {
								deadUnits.add(u);
							}
						}
					});
					deadUnits.stream().forEach(u -> {
						unitHandler.removeUnit(UnitHandler.PLAYER, u);
						pane.getChildren().removeAll(u.getShape(), u.getAttackLine(), u.getAttackRange(),
								((PlayerUnit) u).getHighlight());
						u.stopMoveAnimation();
						if (u instanceof AttackUnit)
							u.stopAttackAnimation();
						attackLine.setStroke(Color.TRANSPARENT);
					});
					attackRange.toBack();
					attackRange.setFill(color);

				} else {
					attackRange.setStroke(Color.TRANSPARENT);
					i = 0;
				}
			}
		};
	}

	public void retarget(UnitHandler unitHandler) {
		HashSet<GameUnit> enemies = (HashSet<GameUnit>) unitHandler.getSet(UnitHandler.PLAYER);
		if (!enemies.isEmpty()) {
			int i = 0;
			int rand = (int) (Math.random() * enemies.size());

			for (GameUnit u : enemies) {
				if (i == rand)
					target = u;
				i++;
			}
			attackLine.toBack();
		} else {
			target = null;
		}

	}
}
