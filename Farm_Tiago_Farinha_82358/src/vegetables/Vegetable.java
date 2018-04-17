package vegetables;

import farm.Farm;
import objects.Estado;
import objects.FarmObject;
import objects.Land;
import objects.ObjectStatus;
import pt.iul.ista.poo.utils.Point2D;

public abstract class Vegetable extends ObjectStatus {

	public Vegetable(Point2D p) {
		super(p);
	}

	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public void updateStatus() {
		if (getCiclos() >= getMature() && getCiclos() < getRotten())
			setEstado(getClass().getSimpleName().toLowerCase());
		else if (getCiclos() >= getRotten())
			setEstado(Estado.RUINED.getPrefix() + getClass().getSimpleName().toLowerCase());
		else
			setEstado(Estado.SMALL.getPrefix() + getClass().getSimpleName().toLowerCase());
	}

	@Override
	public void interact() {

		if (getName().equals(Estado.RUINED.getPrefix() + getClass().getSimpleName().toLowerCase())) {
			landToUnplow().setUnplowed();
			remove();
		} else if (getName().equals(getClass().getSimpleName().toLowerCase()))
			cut();
		else if (getName().equals(Estado.SMALL.getPrefix() + getClass().getSimpleName().toLowerCase()))
			takeCare();
	}

	public void cut() {
		Farm.getInstance().addPontos(getPontos());
		remove();
		landToUnplow().setUnplowed();
	}
	public void takeCare() {
		setCuidado(true);
	}

	private Land landToUnplow() {
		for (FarmObject x : Farm.getInstance().getLista())
			if (x instanceof Land && x.getPosition().equals(getPosition()))
				return (Land) x;
		return null;
	}

	public abstract int getPontos();

	public abstract int getRotten();

	public abstract int getMature();

}
