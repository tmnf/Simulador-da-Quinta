package animals;

import java.util.Random;

import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class Sheep extends Animal {

	private boolean moving;

	private Point2D nova;
	private Point2D atual;

	private boolean dead;

	private static final int Fome = 10;
	private static final int faminto = 50;

	public Sheep(Point2D p) {
		super(p);
	}

	@Override
	public void updateStatus() {

		if (!dead) { // Caso esteja morta nada acontece
			if (getCiclos() >= Fome && getCiclos() < faminto)
				startMoving();
			else if (getCiclos() >= faminto) {
				setEstado("famished_sheep");
				stopMoving();
				dead = true;
			} else
				setEstado("sheep");
		}
	}

	@Override
	public void addCiclo() {

		if (getCuidado()) {
			resetCiclo();
			setCuidado(false);
			stopMoving();
		} else
			setCiclosCuidado(getCiclosCuidado() + 1);

		sumCicles(1);
		Position();
		updateStatus();
	}

	private void Position() {
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

	private void move() {
		setPosition(nova);
	}

	private void startMoving() {
		moving = true;
	}

	private void stopMoving() {
		moving = false;
	}

}
