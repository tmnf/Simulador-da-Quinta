package animals;

import pt.iul.ista.poo.utils.Point2D;
import entities.Farmer;
import farm.Farm;
import objects.FarmObject;
import objects.PositionUtil;

public class Egg extends Animal {

	private static final long serialVersionUID = 1L;

	private static final int PONTOS = 1;
	private static final int ECLODE = 20;

	public Egg(Point2D p) {
		super(p);
		setState("egg");
	}

	@Override
	public int getPriority() {
		return 2;
	}

	@Override
	public void updateState() {
		if (getCycles() >= ECLODE) {
			Farm.getInstance().addImage(new Chicken(PositionUtil.getNewPosition(getPosition())));
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

}
