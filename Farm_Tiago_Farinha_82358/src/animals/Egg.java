package animals;

import farm.Farm;
import pt.iul.ista.poo.utils.Point2D;

public class Egg extends Animal {

	private static final int PONTOS = 1;
	private static final int ECLODE = 20;

	public Egg(Point2D p) {
		super(p);
		setEstado("egg");
	}

	@Override
	public void updateStatus() {
		if (getCiclos() >= ECLODE) {
			Position();
			while (!isInside(getNova()))
				Position();
			Farm.getInstance().addToBuffer(new Chicken(getNova()));
			Farm.getInstance().addToTrash(this);
		}
	}

	@Override
	public void interact() {
		Farm.getInstance().addPontos(PONTOS);
		remove();
	}

}
