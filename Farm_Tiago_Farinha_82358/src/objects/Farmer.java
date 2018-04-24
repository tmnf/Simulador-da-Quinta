package objects;

import farm.Farm;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Farmer extends FarmObject{

	private static final long serialVersionUID = 1L;
	
	private Point2D actual;
	private Point2D nova;

	public Farmer(Point2D p) {
		super(p);
	}

	@Override
	public int getLayer() {
		return 3;
	}

	public void Position(Direction x) {
		actual = getPosition();

		switch (x) {
		case LEFT:
			nova = actual.plus(Direction.LEFT.asVector());
			break;
		case UP:
			nova = actual.plus(Direction.UP.asVector());
			break;
		case RIGHT:
			nova = actual.plus(Direction.RIGHT.asVector());
			break;
		case DOWN:
			nova = actual.plus(Direction.DOWN.asVector());
			break;
		default:
			return;
		}
	}

	public void move() {
		if (isInside(nova) && !(Farm.getInstance().colides(nova)))
			setPosition(nova);
		else
			return;
	}



	public Point2D getNova() {
		return nova;
	}

}
