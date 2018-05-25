package animals;

import pt.iul.ista.poo.utils.Point2D;
import entities.Farmer;
import farm.Farm;
import objects.FarmObject;
import objects.PositionUtil;
import vegetables.Tomato;
import vegetables.Vegetable;

public class Chicken extends Animal {

	private static final long serialVersionUID = 1L;

	private static final int PONTOS = 2;
	private static final int EGG = 10;

	private static final int PRICE = 50;

	public Chicken(Point2D p) {
		super(p);
		setState("chicken");
	}

	@Override
	public void updateState() {
		if (getCycles() == EGG) {
			resetCycle();
			Farm.getInstance().addImage(new Egg(getPosition()));
		}
		if (getCycles() % 2 == 0) {
			startMoving();
			eat();
			move();
			stopMoving();
		}
	}

	@Override
	public boolean canEat() {
		for (FarmObject x : Farm.getInstance().getInteratables(PositionUtil.getNewPosition(getPosition())))
			if (x instanceof Tomato && x.getName().equals(x.getClass().getSimpleName().toLowerCase())) {
				setVegetable((Vegetable) x);
				return true;
			}
		return false;
	}

	@Override
	public void interactWith(FarmObject x) {
		if (x instanceof Farmer) {
			Farm.getInstance().removeImage(this);
			Farm.getInstance().addPontos(PONTOS);
		}
	}

	public static int getPrice() {
		return PRICE;
	}
	
}
