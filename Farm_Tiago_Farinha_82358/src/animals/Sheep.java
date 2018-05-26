package animals;

import pt.iul.ista.poo.utils.Point2D;
import entities.Farmer;
import farm.Farm;
import objects.FarmObject;
import vegetables.Vegetable;

public class Sheep extends Animal {

	private static final long serialVersionUID = 1L;

	private static final int FOME = 10;
	private static final int FAMINTO = 50;
	
	private static final int PONTOS = 1;
	
	private static final int PRICE = 100;

	public Sheep(Point2D p) {
		super(p);
		setState("sheep");
	}

	@Override
	public void updateState() {
		if (getCycles() >= FOME && getCycles() < FAMINTO) {
			startMoving();
			eatIfPossible();
		} else if (getCycles() >= FAMINTO) {
			setState("famished_sheep");
			stopMoving();
		} else Farm.getInstance().addPontos(PONTOS);

	}

	@Override
	public void interactWith(FarmObject x) {
		if (x instanceof Farmer) {
			setCare(true);
			stopMoving();
			setState("sheep");
			setCare(false);
			resetCycle();
		}
		if (x instanceof Vegetable) {
			setCare(true);
			stopMoving();
			setCare(false);
			resetCycle();
		}
	}

	@Override
	public void addCycle() {
		super.addCycle();
		move();
	}

	public static int getPrice() {
		return PRICE;
	}

}
