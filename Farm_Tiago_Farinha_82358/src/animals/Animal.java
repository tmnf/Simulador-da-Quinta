package animals;

import java.util.Random;

import farm.Farm;
import objects.FarmObject;
import objects.ObjectStatus;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;
import vegetables.Vegetable;

public abstract class Animal extends ObjectStatus {

	private Vegetable vegetal;

	private boolean moving;

	private Point2D nova;
	private Point2D atual;


	public Animal(Point2D p) {
		super(p);
	}

	@Override
	public int getLayer() {
		return 2;
	}

	public void comer() {
		if (podeComer()) {
			vegetal.remove();
			setCuidado(true);
		}
	}

	public boolean podeComer() {
		for (FarmObject x : Farm.getInstance().getLista())
			if (x.getPosition().equals(this.getPosition()))
				if (x instanceof Vegetable)
					if (x.getName().equals(x.getClass().getSimpleName().toLowerCase())) {
						vegetal = (Vegetable) x;
						return true;
					}
		return false;
	}
	public void setVegetal(Vegetable x) {
		vegetal = x;
	}

	public void Position() {
		if (moving) {
			Random rnd = new Random();
			atual = getPosition();
			nova = atual.plus(new Vector2D(rnd.nextInt(3) - 1, rnd.nextInt(3) - 1));
			if (isInside(nova))
				move();
			else
				Position();
		}
	}

	public void move() {
		setPosition(nova);
	}

	public void startMoving() {
		moving = true;
	}

	public void stopMoving() {
		moving = false;
	}


}
