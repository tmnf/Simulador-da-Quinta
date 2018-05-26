package animals;

import farm.Farm;
import objects.FarmObject;
import objects.ObjectState;
import pt.iul.ista.poo.utils.Point2D;
import vegetables.Vegetable;
import objects.PositionUtil;

public abstract class Animal extends ObjectState {

	private static final long serialVersionUID = 1L;

	private boolean moving;

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

	public void eat(Vegetable veg) {
		stopMoving();
		veg.interactWith(this);
		interactWith(veg);
		setCare(true);
	}

	public void eatIfPossible() {
		for (FarmObject x : Farm.getInstance().getInteratables(PositionUtil.getNewPosition(getPosition())))
			if (x instanceof Vegetable && x.getName().equals(x.getClass().getSimpleName().toLowerCase()))
				eat((Vegetable) x);
	}

	public void move() {
		if (moving) {
			setPosition(PositionUtil.getNewPosition(getPosition()));
		}
	}

	public void startMoving() {
		moving = true;
	}

	public void stopMoving() {
		moving = false;
	}

}
