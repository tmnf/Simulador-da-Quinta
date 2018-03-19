package animals;

import java.util.Random;

import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class Sheep extends Animal {

	private int ciclosCuidado;
	private boolean moving;

	private Point2D nova;
	private Point2D atual;

	private static final int Fome = 10;
	private static final int faminto = 20;

	public Sheep(Point2D p) {
		super(p);
		moving = false;
	}

	@Override
	public void getStatus() {
		if (getCuidado()) {
			Ciclo(0);
			setCuidado(false);
		}

		if (ciclosCuidado == Fome)
			startMoving();
		else if (getCiclos() >= faminto) {
			setEstado("famished_sheep");
			stopMoving();
		} else
			setEstado("sheep");
	}

	@Override
	public void addCiclo() {

		if (getCuidado())
			ciclosCuidado = 0;
		else
			ciclosCuidado++;

		setCiclo(1);
		getStatus();
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
