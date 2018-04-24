package animals;

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

	public void comer() {
		if (podeComer()) {
			Farm.getInstance().removeImage(vegetal);
			UnplowLand(vegetal.getPosition());
			setCuidado(true);
		}
	}

	public boolean podeComer() {
		Position();
		for (FarmObject x : Farm.getInstance().getInteratables())
			if (x.getPosition().equals(nova) && x instanceof Vegetable
					&& x.getName().equals(x.getClass().getSimpleName().toLowerCase())) {

				vegetal = (Vegetable) x;
				stopMoving();
				return true;
			}
		return false;
	}

	public void setVegetal(Vegetable x) {
		vegetal = x;
	}

	public void Position() {
		nova = getPosition().plus(Direction.random().asVector());
		while (!isInside(nova) || Farm.getInstance().colides(nova))
			nova = getPosition().plus(Direction.random().asVector());
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
