package animals;

import java.util.List;

import farm.Farm;
import objects.FarmObject;
import objects.ObjectStatus;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import vegetables.Vegetable;

public abstract class Animal extends ObjectStatus {

	private static final long serialVersionUID = 1L;

	private Vegetable vegetal;

	private boolean moving;

	private Point2D nova;

	public Animal(Point2D p) {
		super(p);
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public int getPriority() {
		return 3;
	}

	public void comer() {
		if (podeComer()) {
			stopMoving();
			vegetal.interact(this);
			interact(vegetal);
			setCuidado(true);
		}
	}

	public boolean podeComer() {
		Position();
		for (FarmObject x : Farm.getInstance().getInteratables(nova))
			if (x instanceof Vegetable && x.getName().equals(x.getClass().getSimpleName().toLowerCase())) {
				vegetal = (Vegetable) x;
				return true;
			}
		return false;
	}

	public void setVegetal(Vegetable x) {
		vegetal = x;
	}

	public void Position() {
		List<Point2D> points = Direction.getNeighbourhoodPoints(getPosition());
		for (Point2D x : points)
			if (!Farm.getInstance().colides(x) && isInside(x)) {
				nova = x;
				return;
			} else
				nova = getPosition();
	}

	public void move() {
		if (moving) {
			Position();
			setPosition(nova);
		}
	}

	public void startMoving() {
		moving = true;
	}

	public void stopMoving() {
		moving = false;
	}

	public Point2D getNova() {
		return nova;
	}

}
