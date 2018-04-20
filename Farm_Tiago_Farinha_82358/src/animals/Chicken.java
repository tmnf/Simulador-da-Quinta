package animals;

import farm.Farm;
import objects.FarmObject;
import pt.iul.ista.poo.utils.Point2D;
import vegetables.Tomato;
import vegetables.Vegetable;

public class Chicken extends Animal {

	private static final int PONTOS = 2;
	private static final int OVO = 10;

	private int contaCiclo;

	public Chicken(Point2D p) {
		super(p);
		setEstado("chicken");
	}

	@Override
	public void updateStatus() {
		if (contaCiclo == 2) {
			startMoving();
			move();
			stopMoving();
			contaCiclo = 0;
		}
		contaCiclo++;

		if (getCiclos() == OVO) {
			resetCiclo();
			Position();
			Farm.getInstance().addToBuffer(new Egg(getNova()));
		}

	}

	@Override
	public boolean podeComer() {
		for (FarmObject x : Farm.getInstance().getLista())
			if (x.getPosition().equals(this.getPosition()) && x instanceof Tomato
					&& x.getName().equals(x.getClass().getSimpleName().toLowerCase())) {
				
				setVegetal((Vegetable) x);
				return true;
			}
		return false;

	}

	@Override
	public void interact() {
		remove();
		Farm.getInstance().addPontos(PONTOS);
	}
}
