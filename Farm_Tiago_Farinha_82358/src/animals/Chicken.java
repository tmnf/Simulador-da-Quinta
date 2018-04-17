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
		contaCiclo = 0;
	}

	@Override
	public void updateStatus() {
		if (contaCiclo == 2) {
			startMoving();
			Position();
			stopMoving();
			contaCiclo = 0;
		}
//		if (getCiclos() == OVO) {
//			Farm.getInstance().addImage(new Egg(getPosition()));
//			resetCiclo();
//		}
		contaCiclo++;
		setEstado("chicken");
	}
	
	@Override
	public boolean podeComer() {
		for (FarmObject x : Farm.getInstance().getLista())
			if (x.getPosition().equals(this.getPosition()))
				if (x instanceof Tomato)
					if (x.getName().equals(x.getClass().getSimpleName().toLowerCase())) {
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
