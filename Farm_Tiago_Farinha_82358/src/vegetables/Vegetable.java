package vegetables;

import pt.iul.ista.poo.utils.Point2D;

import animals.Animal;
import farm.Farm;
import objects.Estado;
import objects.FarmObject;
import objects.Farmer;
import objects.ObjectState;

public abstract class Vegetable extends ObjectState {

	private static final long serialVersionUID = 1L;

	public Vegetable(Point2D p) {
		super(p);
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
		if (getCiclos() >= getMature() && getCiclos() < getRotten())
			setEstado(getClass().getSimpleName().toLowerCase());
		else if (getCiclos() >= getRotten())
			setEstado(Estado.RUINED.getPrefix() + getClass().getSimpleName().toLowerCase());
		else
			setEstado(Estado.SMALL.getPrefix() + getClass().getSimpleName().toLowerCase());
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
		setCuidado(true);
	}

	public abstract int getPontos();

	public abstract int getRotten();

	public abstract int getMature();

}
