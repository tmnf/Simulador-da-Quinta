package vegetables;

import farm.Farm;
import objects.Estado;
import objects.Objeto;
import pt.iul.ista.poo.utils.Point2D;

public abstract class Vegetable extends Objeto {

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
			setEstado(Estado.RUINED.getState() + getClass().getSimpleName().toLowerCase());
		else
			setEstado(Estado.SMALL.getState() + getClass().getSimpleName().toLowerCase());
	}

	@Override
	public void interact() {

		if (getName().equals(Estado.RUINED.getState() + getClass().getSimpleName().toLowerCase()))
			remove();
		else if (getName().equals(getClass().getSimpleName().toLowerCase()))
			cut();
		else if (getName().equals(Estado.SMALL.getState() + getClass().getSimpleName().toLowerCase()))
			setCuidado(true);
	}

	public void cut() {
		Farm.getInstance().addPontos(getPontos());
		remove();
	}

	public abstract int getPontos();

	public abstract int getRotten();

	public abstract int getMature();

}
