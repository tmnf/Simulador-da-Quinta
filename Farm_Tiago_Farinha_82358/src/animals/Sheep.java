package animals;

import java.util.Random;

import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class Sheep extends Animal {

	private boolean moving;

	private Point2D nova;
	private Point2D atual;

	private static final int Fome = 10;
	private static final int faminto = 50;

	public Sheep(Point2D p) {
		super(p);
		moving = false;
	}

	@Override
	public void updateStatus() {

		System.out.println(getCiclosCuidado());
		if (getCiclosCuidado() >= Fome)
			startMoving();
		else if (getCiclos() >= faminto) {
			setEstado("famished_sheep");
			stopMoving();
		} else
			setEstado("sheep");
	}

	@Override
	public void addCiclo() {

		if (getCuidado()) {
			Ciclo(0);
			setCiclosCuidado(0);
			setCuidado(false);
			stopMoving();
		} else
			setCiclosCuidado(getCiclosCuidado() + 1);

		setCiclo(1);
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
