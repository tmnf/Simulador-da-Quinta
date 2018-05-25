package vegetables;

import pt.iul.ista.poo.utils.Point2D;

import animals.Animal;
import entities.Farmer;
import farm.Farm;
import objects.Estado;
import objects.FarmObject;
import objects.ObjectState;

public abstract class Vegetable extends ObjectState {

	private static final long serialVersionUID = 1L;

	public Vegetable(Point2D p) {
		super(p);
		updateState();
	}

	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public int getPriority() {
		return 1;
	}

	@Override
	public void updateState() {
		if (getCycles() >= getMature() && getCycles() < getRotten())
			setState(getClass().getSimpleName().toLowerCase());
		else if (getCycles() >= getRotten())
			setState(Estado.RUINED.getPrefix() + getClass().getSimpleName().toLowerCase());
		else
			setState(Estado.SMALL.getPrefix() + getClass().getSimpleName().toLowerCase());
	}

	@Override
	public void interactWith(FarmObject x) {
		if (x instanceof Farmer) {
			if (getName().equals(Estado.RUINED.getPrefix() + getClass().getSimpleName().toLowerCase()))
				remove();
			else if (getName().equals(getClass().getSimpleName().toLowerCase()))
				cut();
			else if (getName().equals(Estado.SMALL.getPrefix() + getClass().getSimpleName().toLowerCase()))
				takeCare();
		}
		if (x instanceof Animal) {
			UnplowLand(getPosition());
			Farm.getInstance().removeImage(this);
		}
	}

	public void cut() {
		Farm.getInstance().addPontos(getPontos());
		Farm.getInstance().removeImage(this);
		UnplowLand(getPosition());
	}

	public void remove() {
		UnplowLand(getPosition());
		Farm.getInstance().removeImage(this);
	}

	public void takeCare() {
		setCare(true);
	}

	public abstract int getPontos();

	public abstract int getRotten();

	public abstract int getMature();

}
