package animals;

import pt.iul.ista.poo.utils.Point2D;
import farm.Farm;
import objects.FarmObject;
import objects.Farmer;
import vegetables.Vegetable;

public class Sheep extends Animal {

	private static final long serialVersionUID = 1L;

	private static final int FOME = 10;
	private static final int FAMINTO = 50;
	
	private static final int PONTOS = 1;

	public Sheep(Point2D p) {
		super(p);
		setEstado("sheep");
	}

	@Override
	public void updateStatus() {
		if (getCiclos() >= FOME && getCiclos() < FAMINTO) {
			startMoving();
			comer();
		} else if (getCiclos() >= FAMINTO) {
			setEstado("famished_sheep");
			stopMoving();
		} else Farm.getInstance().addPontos(PONTOS);

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
