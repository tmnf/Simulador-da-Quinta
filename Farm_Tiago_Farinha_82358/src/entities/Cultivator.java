package entities;

import java.util.List;

import farm.Farm;
import farm.Window;
import interfaces.Interactable;
import interfaces.Updatable;
import objects.FarmObject;
import objects.PositionUtil;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import vegetables.Vegetable;

public class Cultivator extends Farmer implements Updatable, Interactable{

	private static final long serialVersionUID = 1L;

	private static final int MAX_FUEL = 50;
	private static final int MAX_DURABILITY = 600;

	private int fuel, durability;
	private boolean working;

	public static final int PRICE = 400;

	public Cultivator(Point2D p) {
		super(p);
		working = true;
		fuel = MAX_FUEL;
		durability = MAX_DURABILITY;
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public int getPriority() {
		return 4;
	}

	public void Work() {
		List<Point2D> points = Direction.getNeighbourhoodPoints(getPosition());
		for (Point2D x : points) {
			FarmObject obj = PositionUtil.getMajorObject(x);
			if (obj instanceof Vegetable || obj instanceof Land)
				((Interactable) obj).interactWith(this);
		}
	}

	@Override
	public void interactWith(FarmObject x) {
		if (x instanceof Farmer) {
			fuel = MAX_FUEL;
			Farm.getInstance().takePontos(25);
			Window.aviso("Cultivador Abastecido!");
		}
	}

	@Override
	public void addCycle() {
		updateState();
		if (working) {
			Work();
			fuel--;
			durability--;
		}
	}

	@Override
	public void updateState() {
		if (durability == 0) {
			Farm.getInstance().removeImage(this);
			Window.aviso("Um dos cultivadores fartou-se de trabalhar!");
		}

		if (fuel == 0)
			working = false;
		if (fuel == MAX_FUEL)
			working = true;
	}

	public static int getPrice() {
		return PRICE;
	}

}
