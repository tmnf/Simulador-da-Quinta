package entities;


import farm.Farm;
import objects.FarmObject;
import objects.PositionUtil;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Farmer extends FarmObject{

	private static final long serialVersionUID = 1L;
	
	private Point2D newPosition;

	public Farmer(Point2D p) {
		super(p);
	}

	@Override
	public int getLayer() {
		return 3;
	}

	public void Position(Direction x) {
		Point2D actual = getPosition();

		switch (x) {
		case LEFT:
			newPosition = actual.plus(Direction.LEFT.asVector());
			break;
		case UP:
			newPosition = actual.plus(Direction.UP.asVector());
			break;
		case RIGHT:
			newPosition = actual.plus(Direction.RIGHT.asVector());
			break;
		case DOWN:
			newPosition = actual.plus(Direction.DOWN.asVector());
			break;
		default:
			return;
		}
	}

	public void move() {
		if (PositionUtil.isInside(newPosition) && !(Farm.getInstance().colides(newPosition)))
			setPosition(newPosition);
		else
			return;
	}



	public Point2D getNova() {
		return newPosition;
	}

}
