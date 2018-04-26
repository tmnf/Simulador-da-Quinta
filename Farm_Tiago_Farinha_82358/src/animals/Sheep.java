package animals;

import objects.FarmObject;
import objects.Farmer;
import pt.iul.ista.poo.utils.Point2D;
import vegetables.Vegetable;

public class Sheep extends Animal {

	private static final long serialVersionUID = 1L;

	private static final int Fome = 10;
	private static final int faminto = 50;

	public Sheep(Point2D p) {
		super(p);
		setEstado("sheep");
	}

	@Override
	public void updateStatus() {
		if (getCiclos() >= Fome && getCiclos() < faminto) {
			startMoving();
			comer();
		} else if (getCiclos() >= faminto) {
			setEstado("famished_sheep");
			stopMoving();
		}

	}

	@Override
	public void interact(FarmObject x) {
		if (x instanceof Farmer) {
			setCuidado(true);
			stopMoving();
			setEstado("sheep");
			setCuidado(false);
			resetCiclo();
		}
		if (x instanceof Vegetable) {
			setCuidado(true);
			stopMoving();
			setCuidado(false);
			resetCiclo();
		}
	}

	@Override
	public void addCiclo() {
		super.addCiclo();
		move();
	}

}
