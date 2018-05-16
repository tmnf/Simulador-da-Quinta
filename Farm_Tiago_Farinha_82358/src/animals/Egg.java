package animals;

import pt.iul.ista.poo.utils.Point2D;

import farm.Farm;
import objects.FarmObject;
import objects.Farmer;

public class Egg extends Animal {

	private static final long serialVersionUID = 1L;

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
			Farm.getInstance().addImage(new Chicken(getNova()));
			Farm.getInstance().removeImage(this);
		}
	}

	@Override
	public void interactWith(FarmObject x) {
		if (x instanceof Farmer) {
			Farm.getInstance().addPontos(PONTOS);
			Farm.getInstance().removeImage(this);
		}
	}

	@Override
	public int getPriority() {
		return 2;
	}
}
