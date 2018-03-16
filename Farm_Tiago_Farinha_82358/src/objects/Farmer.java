package objects;

import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

import java.awt.Point;

import farm.Farm;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;

public class Farmer extends FarmObject {

	private Point2D actual;
	Point2D nova;
	
	public Farmer(Point2D p) {
		super(p);
	}

	@Override
	public int getLayer() {
		return 3;
	}

	public void Position(Direction x) {
		actual = getPosition();
		nova = null;

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
		if(isInside(nova))
			setPosition(nova);
		else return;
	}


	public Point2D getActual() {
		return actual;
	}
	public Point2D getNova() {
		return nova;
	}

}
