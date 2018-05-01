package entities;

import java.util.List;

import farm.Farm;
import farm.Window;
import interfaces.Interactable;
import interfaces.Updatable;
import objects.FarmObject;
import objects.Farmer;
import objects.Land;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import vegetables.Vegetable;

public class Cultivator extends Farmer implements Updatable, Interactable {

	private static final long serialVersionUID = 1L;

	private static final int MAX_FUEL = 50;

	private int fuel;
	private boolean working;

	public Cultivator(Point2D p) {
		super(p);
		working = true;
		fuel = MAX_FUEL;
	}

	public void Work() {
		List<Point2D> points = Direction.getNeighbourhoodPoints(getPosition());
		for (Point2D x : points) {
			FarmObject obj = getMajorObject(x);
			if (obj instanceof Vegetable || obj instanceof Land)
				((Interactable) obj).interact(this);
		}
	}

	@Override
	public void interact(FarmObject x) {
		if (x instanceof Farmer) {
			fuel = MAX_FUEL;
			Farm.getInstance().takePontos(25);
			new Window("Cultivador Abastecido!");
		}
	}
	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public int getPriority() {
		return 4;
	}

	@Override
	public void addCiclo() {
		updateStatus();
		if (working) {
			Work();
			fuel--;
		}
	}

	@Override
	public void updateStatus() {
		if (fuel == 0)
			working = false;
		if (fuel == MAX_FUEL)
			working = true;
	}

}
