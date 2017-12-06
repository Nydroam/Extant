import java.util.Comparator;
import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public class Morpher extends EnemyUnit implements AttackUnit {
	Rotate rotate;
	int status;
	int shoot = 250;
	int track = 500;
	int pulse = 1000;
	public Morpher(double r) {
		radius = r;
		color = Settings.enemyColor;
		shape = new Polygon();
		((Polygon) shape).getPoints()
				.addAll(new Double[] { -radius, 0.0, -radius / 2, radius / 2 * Math.sqrt(3), radius / 2,
						radius / 2 * Math.sqrt(3), radius, 0.0, radius / 2, -radius / 2 * Math.sqrt(3), -radius / 2,
						-radius / 2 * Math.sqrt(3),

		});
		shape.setFill(color);
		speed = 5;
		maxHP = 1000;
		attackRange = new Circle(radius * 6);
		attackRange.setStroke(Settings.foregroundColor);
		attackLine = new Line();
		attackLine.setStrokeWidth(12);
		setup();
	}

	public void setup() {
		super.setup();
		attackRange.setMouseTransparent(true);
		attackRange.setFill(Color.TRANSPARENT);
rotate = new Rotate();
		
		shape.getTransforms().add(rotate);
		status = 0;
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
				status++;
				if (status < shoot) {
				
				if(target!=null&&attackRange.contains(target.getX()-xPos,target.getY()-yPos)) {
					i++;
					rotate.setAngle(i);
					attackLine.toBack();
					attackLine.setStroke(color);
					attackLine.setStartX(xPos);
					attackLine.setStartY(yPos);
					attackLine.setEndX(target.getX());
					attackLine.setEndY(target.getY());
					if(target.isAlive()) {
						target.decHP(3);
					if(!target.isAlive()) {
						unitHandler.removeUnit(UnitHandler.PLAYER, target);
						pane.getChildren().removeAll(target.getShape(),target.getAttackLine(),target.getAttackRange(),((PlayerUnit)target).getHighlight());
						target.stopMoveAnimation();
						if(target instanceof AttackUnit)
							target.stopAttackAnimation();
						attackLine.setStroke(Color.TRANSPARENT);
					}}
						retarget(unitHandler);
				}
				else {
					attackLine.setStroke(Color.TRANSPARENT);
					
					retarget(unitHandler);
					
				}}else if(status==shoot) {attackRange.setRadius(radius);
				attackLine.setStrokeWidth(1);
				attackRange.setFill(Color.TRANSPARENT);
				attackRange.setStroke(Color.TRANSPARENT);
				color = Settings.trackerColor;
				shape.setFill(color);
				speed = 5;}
				else if (status < track) {
					
					i++;
					if (target != null && target.isAlive()) {

						attackLine.setStroke(Settings.foregroundColor);
						attackLine.setStartX(shape.getLayoutX());
						attackLine.setStartY(shape.getLayoutY());
						attackLine.setEndX(target.getX());
						attackLine.setEndY(target.getY());
						rotate.setAngle(i);
						if (attackRange.contains(target.getX() - xPos, target.getY() - yPos)) {
							shape.setFill(target.getShape().getFill());
							i += 3;
							if (target.isAlive()) {
								target.decHP(20);
							}
							if (!target.isAlive()) {
								unitHandler.removeUnit(UnitHandler.PLAYER, target);
								pane.getChildren().removeAll(target.getShape(), target.getAttackLine(),
										target.getAttackRange(), ((PlayerUnit) target).getHighlight());
								target.stopMoveAnimation();
								if (target instanceof AttackUnit)
									target.stopAttackAnimation();
								attackLine.setStroke(Color.TRANSPARENT);

							}
							retarget(unitHandler);
						} else {
							shape.setFill(color);
						}
					} else {
						attackLine.setStroke(Color.TRANSPARENT);
						retarget(unitHandler);
					}
				}else if(status==track) {

					attackLine.setStrokeWidth(0);
				attackRange.setFill(Color.TRANSPARENT);
				attackRange.setStroke(Color.TRANSPARENT);
				color = Settings.pulsarColor;
				shape.setFill(color);
				speed = 3;
				i = 0;}
				else if(status<pulse) {
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
						attackRange.setRadius((i - charge) / (double) (warning - charge) * radius * 6);
						attackRange.setStroke(color);
						// attackRange.setFill(color.brighter());
						// attackRange.setStroke(color.BLACK);
						shape.setFill(Settings.secPulsarColor);

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
				else {
					startMoveAnimation();
					status = 0;
					attackRange.setRadius(radius*6);
					attackRange.setFill(Color.TRANSPARENT);
					attackLine.setStrokeWidth(12);
					color = Settings.enemyColor;
					shape.setFill(color);
					speed = 4;
				}
			}
		};
	}

	public void retarget(UnitHandler unitHandler) {
		HashSet<GameUnit> enemies = (HashSet<GameUnit>) unitHandler.getSet(UnitHandler.PLAYER);
		if(status<shoot) {
			if(!enemies.isEmpty()) {
			target = unitHandler.getSet(UnitHandler.PLAYER).stream().
					min( new Comparator<GameUnit>() {
				public int compare(GameUnit a, GameUnit b) {
					double dist1 = Math.sqrt(Math.pow(a.getX()-xPos,2)+Math.pow(a.getY()-yPos, 2));
					double dist2 = Math.sqrt(Math.pow(b.getX()-xPos,2)+Math.pow(b.getY()-yPos, 2));
					if(dist1>dist2)
						return 1;
					else
						return -1;
				}
			}).get();
			}else {
				target = null;
				this.stopMoveAnimation();
				this.stopAttackAnimation();
				
			}}
		else {
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
}
