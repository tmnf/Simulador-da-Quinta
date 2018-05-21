package animals;

import pt.iul.ista.poo.utils.Point2D;
import farm.Farm;
import objects.FarmObject;
import objects.Farmer;
import vegetables.Tomato;
import vegetables.Vegetable;

public class Chicken extends Animal {

	private static final long serialVersionUID = 1L;

	private static final int PONTOS = 2;
	private static final int OVO = 10;

	public Chicken(Point2D p) {
		super(p);
		setEstado("chicken");
	}

	@Override
	public void updateState() {
		if (getCiclos() == OVO) {
			resetCiclo();
			Farm.getInstance().addImage(new Egg(getPosition()));
		}
		if (getCiclos() % 2 == 0) {
			startMoving();
			comer();
			move();
			stopMoving();
		}
	}

	@Override
	public boolean podeComer() {
		Position();
		for (FarmObject x : Farm.getInstance().getInteratables(getNova()))
			if (x instanceof Tomato && x.getName().equals(x.getClass().getSimpleName().toLowerCase())) {
				setVegetal((Vegetable) x);
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

}
